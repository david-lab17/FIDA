package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.LiquidationType;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.LiquidationResponse;
import com.deltacode.kcb.payload.LiquidationTypeDto;
import com.deltacode.kcb.repository.LiquidationRepository;
import com.deltacode.kcb.service.LiquidationTypeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.Utilities;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j

@Service
public class LiquidationTypeServiceImpl implements LiquidationTypeService {
    private final LiquidationRepository liquidationRepository;
    private final ModelMapper modelMapper;

    public LiquidationTypeServiceImpl(LiquidationRepository liquidationRepository, ModelMapper modelMapper) {
        this.liquidationRepository = liquidationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<?> createLiquidationType(LiquidationTypeDto liquidationTypeDto) {
        ConcurrentHashMap<String,Object>responseObject=new ConcurrentHashMap<>();
        ConcurrentHashMap<String,Object> responseParams=new ConcurrentHashMap<>();
        try {
            log.info("Creating liquidation type");
            //convert Dto to entity
            LiquidationType liquidationType = mapToEntity(liquidationTypeDto);
            LiquidationType newLiquidationType = liquidationRepository.save(liquidationType);
//            //convert entity to Dto
//            return mapToDto(newLiquidationType);
            responseObject.put("status", "success");
            responseObject.put("message", "Liquidation type "
                    +liquidationTypeDto.getLiquidationTypeName()+" successfully created");
            responseObject.put("data", responseParams);
            //convert entity to Dto
             mapToDto(newLiquidationType);
             return ResponseEntity.ok(responseObject);


        } catch (
                Exception e) {
            log.error("Error creating liquidation type", e);
            responseObject.put("status", "error");
            responseObject.put("message", "Error creating liquidation type");
            responseObject.put("data", responseParams);
            return null;
        }


    }

    @Override
    public LiquidationResponse getAllLiquidationType(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all liquidation types");
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
    public ResponseEntity<?> getLiquidationTypeById(Long id) {
        log.info("Getting liquidation type by id = {}", id);
        ConcurrentHashMap<String, Object> responseObject = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Object> responseParams = new ConcurrentHashMap<>();
       try {
           LiquidationType liquidationType = liquidationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LiquidationType", "id", id));

           responseObject.put("status", "success");
              responseObject.put("message", "Liquidation type found" + liquidationType.getLiquidationTypeName());
                responseParams.put("liquidationType", liquidationType);
                responseObject.put("params", responseParams);
            modelMapper.map(liquidationType, LiquidationTypeDto.class);
            return ResponseEntity.ok(responseObject);


       } catch (
                ResourceNotFoundException e) {
              responseObject.put("status", "error");
              responseObject.put("message", e.getMessage());
              responseParams.put("liquidationType", null);
              responseObject.put("params", responseParams);
               modelMapper.map(responseObject, LiquidationTypeDto.class);
                return ResponseEntity.ok(responseObject);
         }


    }

    @Override
    public ResponseEntity<ConcurrentHashMap<String, Object>> updateLiquidationType(LiquidationTypeDto liquidationTypeDto, Long id) {
        ConcurrentHashMap<String, Object> responseObject = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Object> responseParams = new ConcurrentHashMap<>();
        try {
            log.info("Updating liquidation type with id = {}", id);
            LiquidationType liquidationType = liquidationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LiquidationType", "id", id));
            //convert Dto to entity
            LiquidationType updatedLiquidationType = mapToEntity(liquidationTypeDto);
            LiquidationType newLiquidationType = liquidationRepository.save(updatedLiquidationType);
            responseObject.put("status", "success");
            responseObject.put("message", "Liquidation type "
                    +liquidationTypeDto.getLiquidationTypeName()+" successfully updated");
            responseObject.put("data", responseParams);
            //convert entity to Dto
             mapToDto(newLiquidationType);
                return ResponseEntity.ok(responseObject);
        } catch (ResourceNotFoundException e) {
            log.error("Error updating liquidation type", e);
            responseObject.put("status", "error");
            responseObject.put("message", e.getMessage());
            responseParams.put("liquidationType", null);
            responseObject.put("params", responseParams);
            modelMapper.map(responseObject, LiquidationTypeDto.class);
            return ResponseEntity.ok(responseObject);
        }
    }



    @Override
    public ResponseEntity<?> deleteLiquidationTypeById(Long id) {
        log.info("Deleting liquidation type by id = {}", id);
        ConcurrentHashMap<String, Object> responseObject = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Object> responseParams = new ConcurrentHashMap<>();
        try {
            LiquidationType liquidationType = liquidationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LiquidationType", "id", id));
            liquidationRepository.delete(liquidationType);
            responseObject.put("status", "success");
            responseObject.put("message", "Liquidation type "
                    +liquidationType.getLiquidationTypeName()+" successfully deleted");
            responseParams.put("liquidationType", liquidationType);
            responseObject.put("params", responseParams);
            modelMapper.map(liquidationType, LiquidationTypeDto.class);
            return ResponseEntity.ok(responseObject);
    } catch (ResourceNotFoundException e) {
    responseObject.put("status", "error");
            responseObject.put("message", e.getMessage());
            responseParams.put("liquidationType", null);
            responseObject.put("params", responseParams);
            modelMapper.map(responseObject, LiquidationTypeDto.class);
            return ResponseEntity.ok(responseObject);
        }

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

