package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.UserAccType;
import com.deltacode.kcb.payload.UserAccTypeDto;
import com.deltacode.kcb.payload.UserAccTypeResponse;
import com.deltacode.kcb.repository.UserAccTypeRepository;
import com.deltacode.kcb.service.UserAccTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
        UserAccType userAccType = modelMapper.map(userAccTypeDto, UserAccType.class);
        userAccTypeRepository.save(userAccType);
        return modelMapper.map(userAccType, UserAccTypeDto.class);

    }

    @Override
    public UserAccTypeResponse getAllUserAccTypes(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable=org.springframework.data.domain.PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<UserAccType> userAccTypes=userAccTypeRepository.findAll(pageable);
        //get content for page object
        List<UserAccType> listOfUserAccTypes=userAccTypes.getContent();
        List<UserAccTypeDto> content=listOfUserAccTypes.stream().map(this::mapToDto).toList();
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
        UserAccType userAccType=userAccTypeRepository.findById(id).orElseThrow(()->new RuntimeException("User Account Type not found"));
        return modelMapper.map(userAccType, UserAccTypeDto.class);
    }

    @Override
    public UserAccTypeDto updateUserAccTypes(UserAccTypeDto userAccTypeDto, Long id) {
        UserAccType userAccType=userAccTypeRepository.findById(id).orElseThrow(()->new RuntimeException("User Account Type not found"));
        userAccType.setUserAccTypeName(userAccTypeDto.getUserAccTypeName());
        userAccTypeRepository.save(userAccType);
        return modelMapper.map(userAccType, UserAccTypeDto.class);

    }

    @Override
    public void deleteUserAccTypeById(Long id) {
        UserAccType userAccType=userAccTypeRepository.findById(id).orElseThrow(()->new RuntimeException("User Account Type not found"));
        userAccTypeRepository.delete(userAccType);

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
