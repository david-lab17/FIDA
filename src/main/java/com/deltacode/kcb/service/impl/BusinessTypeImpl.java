package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.BusinessType;
import com.deltacode.kcb.payload.BusinessTypeDto;
import com.deltacode.kcb.payload.BusinessTypeResponse;
import com.deltacode.kcb.repository.BusinessTypeRepository;
import com.deltacode.kcb.service.BusinessTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeImpl implements BusinessTypeService {
    private final BusinessTypeRepository businessTypeRepository;
    private  final ModelMapper modelMapper;

    public BusinessTypeImpl(BusinessTypeRepository businessTypeRepository, ModelMapper modelMapper) {
        this.businessTypeRepository = businessTypeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public BusinessTypeDto createBusinessType(BusinessTypeDto businessTypeDto) {
        BusinessType businessType = modelMapper.map(businessTypeDto, BusinessType.class);
        businessTypeRepository.save(businessType);
        return modelMapper.map(businessType, BusinessTypeDto.class);

    }

    @Override
    public BusinessTypeResponse getAllBusinessTypes(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable=org.springframework.data.domain.PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<BusinessType> businessTypes=businessTypeRepository.findAll(pageable);
        //get content for page object
        List<BusinessType> businessTypeList=businessTypes.getContent();
        List<BusinessTypeDto> content=businessTypeList.stream().map(this::mapToDto).toList();
        BusinessTypeResponse businessTypeResponse=new BusinessTypeResponse();
        businessTypeResponse.setContent(content);
        businessTypeResponse.setPageNo(businessTypes.getNumber());
        businessTypeResponse.setPageSize(businessTypes.getSize());
        businessTypeResponse.setTotalElements((int) businessTypes.getTotalElements());
        businessTypeResponse.setTotalPages(businessTypes.getTotalPages());
        businessTypeResponse.setLast(businessTypes.isLast());
        return businessTypeResponse;

    }

    @Override
    public BusinessTypeDto getBusinessTypesById(Long id) {
        BusinessType businessType = businessTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Business Type not found"));
        return modelMapper.map(businessType, BusinessTypeDto.class);
    }

    @Override
    public BusinessTypeDto updateBusinessTypes(BusinessTypeDto businessTypeDto, Long id) {
        BusinessType businessType = businessTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Business Type not found"));
        businessType.setBusinessTypeName(businessTypeDto.getBusinessTypeName());
        businessTypeRepository.save(businessType);
        return modelMapper.map(businessType, BusinessTypeDto.class);
    }

    @Override
    public void deleteBusinessTypeById(Long id) {
        BusinessType businessType = businessTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Business Type not found"));
        businessTypeRepository.delete(businessType);

    }
    private BusinessTypeDto mapToDto(BusinessType businessType) {

        return modelMapper.map(businessType, BusinessTypeDto .class);

    }
    //convert dto to entity
    private BusinessType mapToEntity(BusinessTypeDto businessTypeDto) {

        return modelMapper.map(businessTypeDto, BusinessType.class);

    }
}
