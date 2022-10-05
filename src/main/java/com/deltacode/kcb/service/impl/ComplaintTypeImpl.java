package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Complain;
import com.deltacode.kcb.entity.ComplaintType;
import com.deltacode.kcb.entity.UserAccType;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.ComplaintTypeDto;
import com.deltacode.kcb.payload.ComplaintTypeResponse;
import com.deltacode.kcb.repository.ComplainsRepository;
import com.deltacode.kcb.repository.ComplaintTypeRepository;
import com.deltacode.kcb.repository.UserAccTypeRepository;
import com.deltacode.kcb.security.CustomUserDetailsService;
import com.deltacode.kcb.service.ComplaintTypeService;
import com.deltacode.kcb.service.FileStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j

@Service

public class ComplaintTypeImpl implements ComplaintTypeService {
    private final ComplaintTypeRepository complaintTypeRepository;
    private final ComplainsRepository complainsRepository;
    private final ModelMapper modelMapper;
    private final FileStorageService fileStorageService;
    private final UserAccTypeRepository userAccTypeRepository;

    public ComplaintTypeImpl(ComplaintTypeRepository complaintTypeRepository, ComplainsRepository complainsRepository, ModelMapper modelMapper, CustomUserDetailsService customUserDetailsService, FileStorageService fileStorageService, UserAccTypeRepository userAccTypeRepository) {
        this.complaintTypeRepository = complaintTypeRepository;
        this.complainsRepository = complainsRepository;
        this.modelMapper = modelMapper;
        this.fileStorageService = fileStorageService;
        this.userAccTypeRepository = userAccTypeRepository;
    }


    @Override
    public ComplaintTypeResponse getAllComplaintTypes(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all complaint types");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<ComplaintType> complaintTypes = complaintTypeRepository.findAll(pageable);
        //get content for page object
        List<ComplaintType> complaintTypeList = complaintTypes.getContent();
        List<ComplaintTypeDto> content = complaintTypeList.stream().map(this::mapToDto).toList();
        ComplaintTypeResponse complaintTypeResponse = new ComplaintTypeResponse();
        complaintTypeResponse.setContent(content);
        complaintTypeResponse.setPageNo(complaintTypes.getNumber());
        complaintTypeResponse.setPageSize(complaintTypes.getSize());
        complaintTypeResponse.setTotalElements((int) complaintTypes.getTotalElements());
        complaintTypeResponse.setTotalPages(complaintTypes.getTotalPages());
        complaintTypeResponse.setLast(complaintTypes.isLast());
        return complaintTypeResponse;
    }

    @Override
    public ComplaintTypeDto getComplaintTypesById(Long id) {
        log.info("Getting complaint type by id {}", id);
        ComplaintType complaintType = complaintTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint Type not found"));
        return modelMapper.map(complaintType, ComplaintTypeDto.class);
    }

    @Override
    public ComplaintTypeDto updateComplaintTypes(ComplaintTypeDto complaintTypeDto, Long id) {
        //get post by id from database
        ComplaintType complaintType = complaintTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", id));
        complaintType.setComplaintTypeName(complaintTypeDto.getComplaintTypeName());
        complaintType.setDescription(complaintTypeDto.getDescription());

        ComplaintType updatedComplaintType = complaintTypeRepository.save(complaintType);
        mapToDto(updatedComplaintType);


        return null;
    }

    @Override
    public void deleteComplaintTypeById(Long id) {
        log.info("Deleting complaint type by id {}", id);
        complaintTypeRepository.deleteById(id);

    }

    @Override
    public ResponseEntity<?> addComplain(String complainDetails, MultipartFile[] complainFiles,
                                         HttpServletRequest httpServletRequest) {

        HashMap<String, String> responseObject = new HashMap<>();
        if (complainDetails == null) throw new RuntimeException("Bad Request");
        //get user id of logged in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getUsername();
        log.info("User id of logged in user is {}", userId);
        //get user id of logged in user
        ObjectMapper objectMapper = new ObjectMapper();
        ComplaintTypeDto complaintTypeDto = null;
        try {
            complaintTypeDto = objectMapper.readValue(complainDetails, ComplaintTypeDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        List<String> filePathList = new ArrayList<>();
        boolean hasFiles = false;
        Optional<UserAccType> optionalUserAccType = userAccTypeRepository.findById(complaintTypeDto.getUserAccountTypeId());
        Optional<ComplaintType> optionalComplaintType = complaintTypeRepository.findById(complaintTypeDto.getComplainTypeId());
        if (complainFiles.length > 0) {
            hasFiles = true;
            filePathList = fileStorageService.saveMultipleFileWithSpecificFileName(
                    "Complain_", complainFiles);
        }
        Complain complain = new Complain();
        complain.setComplaintType(optionalComplaintType.get());
        complain.setUserAccType(optionalUserAccType.get());
        complain.setAccountNo(complaintTypeDto.getAccountNo());


        complain.setSubject(complaintTypeDto.getSubject());
        complain.setMessage(complaintTypeDto.getMessage());

        if (hasFiles) {
            complain.setComplainAttachmentPath(filePathList.get(0));
        } else {
            complain.setComplainAttachmentPath("");
        }
        complain.setCreatedOn(new Date());
        complain.setCreatedBy(Long.valueOf(userId));
        complain.setUpdatedOn(new Date());
        complainsRepository.save(complain);
        responseObject.put("message", "Complain added successfully");
        return ResponseEntity.ok(responseObject);


    }



    //convert entity to dto
    private ComplaintTypeDto mapToDto(ComplaintType complaintType) {

        return modelMapper.map(complaintType, ComplaintTypeDto .class);

    }
    //convert dto to entity
    private ComplaintType mapToEntity(ComplaintTypeDto complaintTypeDto) {

        return modelMapper.map(complaintTypeDto, ComplaintType.class);

    }
}


