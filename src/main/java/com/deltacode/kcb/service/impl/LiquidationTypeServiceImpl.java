package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.LiquidationType;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.LiquidationResponse;
import com.deltacode.kcb.payload.LiquidationTypeDto;
import com.deltacode.kcb.repository.LiquidationRepository;
import com.deltacode.kcb.service.LiquidationTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiquidationTypeServiceImpl implements LiquidationTypeService {
    private final LiquidationRepository liquidationRepository;
    private final ModelMapper modelMapper;

    public LiquidationTypeServiceImpl(LiquidationRepository liquidationRepository, ModelMapper modelMapper) {
        this.liquidationRepository = liquidationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LiquidationTypeDto createLiquidationType(LiquidationTypeDto liquidationTypeDto) {
        LiquidationType liquidationType = modelMapper.map(liquidationTypeDto, LiquidationType.class);
        LiquidationType newLiquidationType = liquidationRepository.save(liquidationType);
        return modelMapper.map(newLiquidationType, LiquidationTypeDto.class);
    }

    @Override
    public LiquidationResponse getAllLiquidationType(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<LiquidationType> liquidationTypes = liquidationRepository.findAll(pageable);
        //get content for page object
        List<LiquidationType> listOfLiquidationType = liquidationTypes.getContent();
        List<LiquidationTypeDto> content = listOfLiquidationType.stream().map(this::mapToDto).toList();
        LiquidationResponse liquidationResponse = new LiquidationResponse();
        liquidationResponse.setContent(content);
        liquidationResponse.setPageNo(liquidationTypes.getNumber());
        liquidationResponse.setPageSize(liquidationTypes.getSize());
        liquidationResponse.setTotalElements(liquidationTypes.getNumberOfElements());
        liquidationResponse.setTotalPages(liquidationTypes.getTotalPages());
        liquidationResponse.setLast(liquidationTypes.isLast());
        return liquidationResponse;
    }

    @Override
    public LiquidationTypeDto getLiquidationTypeById(Long id) {
        LiquidationType liquidationType = liquidationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LiquidationType", "id", id));
        return modelMapper.map(liquidationType, LiquidationTypeDto.class);
    }

    @Override
    public LiquidationTypeDto updateLiquidationType(LiquidationTypeDto liquidationTypeDto, Long id) {
        LiquidationType liquidationType = liquidationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LiquidationType", "id", id));
        modelMapper.map(liquidationTypeDto, liquidationType);
        LiquidationType updatedLiquidationType = liquidationRepository.save(liquidationType);
        return modelMapper.map(updatedLiquidationType, LiquidationTypeDto.class);
    }

    @Override
    public void deleteLiquidationTypeById(Long id) {

    }
    //convert entity to dto
    private LiquidationTypeDto mapToDto(LiquidationType liquidationType) {

        return modelMapper.map(liquidationType, LiquidationTypeDto .class);

    }
    //convert dto to entity
    private LiquidationType mapToEntity(LiquidationTypeDto liquidationTypeDto) {

        return modelMapper.map(liquidationTypeDto, LiquidationType.class);

    }
}

