package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.UserAccType;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.UserAccTypeDto;
import com.deltacode.kcb.payload.UserAccTypeResponse;
import com.deltacode.kcb.repository.UserAccTypeRepository;
import com.deltacode.kcb.service.UserAccTypeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service


public class UserAccTypeImpl implements UserAccTypeService {
    private final UserAccTypeRepository userAccTypeRepository;
    private final ModelMapper modelMapper;

    public UserAccTypeImpl(UserAccTypeRepository userAccTypeRepository, ModelMapper modelMapper) {
        this.userAccTypeRepository = userAccTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserAccTypeDto createUserAccType(UserAccTypeDto userAccTypeDto) {
        log.info("Creating user account type");
        UserAccType userAccType = modelMapper.map(userAccTypeDto, UserAccType.class);
        userAccTypeRepository.save(userAccType);
        return modelMapper.map(userAccType, UserAccTypeDto.class);

    }

    @Override
    public UserAccTypeResponse getAllUserAccTypes(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all user account types");
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable=org.springframework.data.domain.PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<UserAccType> userAccTypes=userAccTypeRepository.findAll(pageable);
        //get content for page object
        List<UserAccType> listOfUserAccTypes=userAccTypes.getContent();
        List<UserAccTypeDto> content=listOfUserAccTypes.stream().map(userAccType ->mapToDto(userAccType)).collect(Collectors.toList());
        UserAccTypeResponse userAccTypeResponse=new UserAccTypeResponse();
        userAccTypeResponse.setContent(content);
        userAccTypeResponse.setPageNo(userAccTypes.getNumber());
        userAccTypeResponse.setPageSize(userAccTypes.getSize());
        userAccTypeResponse.setTotalElements((int) userAccTypes.getTotalElements());
        userAccTypeResponse.setTotalPages(userAccTypes.getTotalPages());
        userAccTypeResponse.setLast(userAccTypes.isLast());
        return userAccTypeResponse;

    }

    @Override
    public UserAccTypeDto getUserAccTypesById(Long id) {
        log.info("Getting user account type by id = {}", id);
        UserAccType userAccType=userAccTypeRepository.findById(id).orElseThrow(()->new RuntimeException("User Account Type not found"));
        return modelMapper.map(userAccType, UserAccTypeDto.class);
    }

    @Override
    public ResponseEntity<?> updateUserAccTypes(UserAccTypeDto userAccTypeDto, Long id) {
        HashMap<String,Object> responseObject= new HashMap<>();
        HashMap<String,Object> responseParams=new HashMap<>();
        try {
            log.info("Updating user account type by id = {}", id);
            UserAccType userAccType=userAccTypeRepository
                    .findById(id).orElseThrow(()->new ResourceNotFoundException("User Acc Type", "id", id));
            userAccType.setUserAccTypeName(userAccTypeDto.getUserAccTypeName());
            UserAccType updatedUserAcc = userAccTypeRepository.save(userAccType);
            responseObject.put("status", "success");//set status
            responseObject.put("message", "user Acc Type "
                    +userAccTypeDto.getUserAccTypeName()+" successfully updated");//set message
            responseObject.put("data", responseParams);
             mapToDto(updatedUserAcc);
             return  ResponseEntity.ok(responseObject);


        } catch (Exception e) {
            responseObject.put("status", "failed");
            responseObject.put("message", e.getMessage());
            return ResponseEntity.ok().body(responseObject);
        }


    }

    @Override
    public ResponseEntity<?> deleteUserAccTypeById(Long id) {
        HashMap<String, Object> responseObject = new HashMap<>();
        HashMap<String, Object> responseParams = new HashMap<>();
        try {
            log.info("Deleting user account type by id = {}", id);
            UserAccType userAccType = userAccTypeRepository
                    .findById(id).orElseThrow(() -> new ResourceNotFoundException("User Acc Type", "id", id));
            userAccTypeRepository.delete(userAccType);
            responseObject.put("status", "success");//set status
            responseObject.put("message", "user Acc Type "
                    + userAccType.getUserAccTypeName() + " successfully deleted");//set message
            responseObject.put("data", responseParams);
            return ResponseEntity.ok(responseObject);
        } catch (ResourceNotFoundException e) {
            responseObject.put("status", "failed");
            responseObject.put("message", e.getMessage());
            return ResponseEntity.ok().body(responseObject);
        }
    }
        //convert entity to dto
    private UserAccTypeDto mapToDto(UserAccType userAccType) {

        return modelMapper.map(userAccType, UserAccTypeDto .class);

    }
    //convert dto to entity
    private UserAccType mapToEntity(UserAccTypeDto userAccTypeDto) {

        return modelMapper.map(userAccTypeDto, UserAccType.class);

    }
}
