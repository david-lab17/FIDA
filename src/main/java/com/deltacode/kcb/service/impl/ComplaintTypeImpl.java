package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.ComplaintType;
import com.deltacode.kcb.payload.ComplaintTypeDto;
import com.deltacode.kcb.payload.ComplaintTypeResponse;
import com.deltacode.kcb.repository.ComplaintTypeRepository;
import com.deltacode.kcb.service.ComplaintTypeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j

@Service
public class ComplaintTypeImpl implements ComplaintTypeService {
    private final ComplaintTypeRepository complaintTypeRepository;
    private final ModelMapper modelMapper;

    public ComplaintTypeImpl(ComplaintTypeRepository complaintTypeRepository, ModelMapper modelMapper) {
        this.complaintTypeRepository = complaintTypeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ComplaintTypeDto createComplaintType(ComplaintTypeDto complaintTypeDto) {
        log.info("Creating complaint type");
        ComplaintType complaintType = modelMapper.map(complaintTypeDto, ComplaintType.class);
        complaintTypeRepository.save(complaintType);
        return modelMapper.map(complaintType, ComplaintTypeDto.class);
    }

    @Override
    public ComplaintTypeResponse getAllComplaintTypes(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all complaint types");
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<ComplaintType> complaintTypes=complaintTypeRepository.findAll(pageable);
        //get content for page object
        List<ComplaintType> complaintTypeList=complaintTypes.getContent();
        List<ComplaintTypeDto> content=complaintTypeList.stream().map(this::mapToDto).toList();
        ComplaintTypeResponse complaintTypeResponse=new ComplaintTypeResponse();
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
        ComplaintType complaintType = complaintTypeRepository.findById(id).orElseThrow( () -> new RuntimeException("Complaint Type not found"));
        return modelMapper.map(complaintType, ComplaintTypeDto.class);
    }

    @Override
    public ComplaintTypeDto updateComplaintTypes(ComplaintTypeDto complaintTypeDto, Long id) {
        log.info("Updating complaint type by id {}", id);
        ComplaintType complaintType = complaintTypeRepository.findById(id).orElseThrow();
        complaintType.setComplaintTypeName(complaintTypeDto.getComplaintTypeName());
        complaintType.setDescription(complaintTypeDto.getDescription());
        complaintTypeRepository.save(complaintType);
        return modelMapper.map(complaintType, ComplaintTypeDto.class);
    }

    @Override
    public void deleteComplaintTypeById(Long id) {
        log.info("Deleting complaint type by id {}", id);
        complaintTypeRepository.deleteById(id);

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


