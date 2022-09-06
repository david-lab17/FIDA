package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.EmploymentType;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.EmploymentTypeDto;
import com.deltacode.kcb.payload.EmploymentTypeResponse;
import com.deltacode.kcb.repository.EmploymentTypeRepository;
import com.deltacode.kcb.service.EmploymentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmploymentTypesImpl implements EmploymentTypeService {
    private  final EmploymentTypeRepository employmentTypeRepository;
    private final ModelMapper modelMapper;

    public EmploymentTypesImpl(EmploymentTypeRepository employmentTypeRepository, ModelMapper modelMapper) {
        this.employmentTypeRepository = employmentTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmploymentTypeDto createEmploymentType(EmploymentTypeDto employmentTypeDto) {
        EmploymentType employmentType = modelMapper.map(employmentTypeDto, EmploymentType.class);
        employmentTypeRepository.save(employmentType);
        return modelMapper.map(employmentType, EmploymentTypeDto.class);
    }

    @Override
    public EmploymentTypeResponse getAllEmploymentTypes(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<EmploymentType> employmentTypes = employmentTypeRepository.findAll(pageable);
        //get content for page object
        List<EmploymentType> listOfEmploymentTypes = employmentTypes.getContent();
        List<EmploymentTypeDto> content = listOfEmploymentTypes.stream().map(this::mapToDto).toList();
        EmploymentTypeResponse employmentTypeResponse = new EmploymentTypeResponse();
        employmentTypeResponse.setContent(content);
        employmentTypeResponse.setPageNo(employmentTypes.getNumber());
        employmentTypeResponse.setPageSize(employmentTypes.getSize());
        employmentTypeResponse.setTotalElements(employmentTypes.getNumberOfElements());
        employmentTypeResponse.setTotalPages(employmentTypes.getTotalPages());
        employmentTypeResponse.setLast(employmentTypes.isLast());
        return employmentTypeResponse;
    }

    @Override
    public EmploymentTypeDto getEmploymentTypesById(Long id) {
        EmploymentType employmentType = employmentTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EmploymentType", "id", id));
        return mapToDto(employmentType);
    }

    @Override
    public EmploymentTypeDto updateEmploymentTypes(EmploymentTypeDto employmentTypeDto, Long id) {
        EmploymentType employmentType = employmentTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EmploymentType", "id", id));
        employmentType.setEmploymentTypeName(employmentTypeDto.getEmploymentTypeName());
        employmentTypeRepository.save(employmentType);
        return mapToDto(employmentType);
    }

    @Override
    public void deleteEmploymentTypeById(Long id) {
        EmploymentType employmentType = employmentTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EmploymentType", "id", id));
        employmentTypeRepository.delete(employmentType);

    }
    //convert entity to dto
    private EmploymentTypeDto mapToDto(EmploymentType employmentType) {

        return modelMapper.map(employmentType, EmploymentTypeDto .class);

    }
    //convert dto to entity
    private EmploymentType mapToEntity(EmploymentTypeDto employmentTypeDto) {

        return modelMapper.map(employmentTypeDto, EmploymentType.class);

    }
}
