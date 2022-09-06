package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.DSR;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.DSRDto;
import com.deltacode.kcb.payload.DSRResponse;
import com.deltacode.kcb.repository.DSRRepository;
import com.deltacode.kcb.service.DSRService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DSRServiceImpl implements DSRService {
    private final DSRRepository dsrRepository;
    private final ModelMapper modelMapper;

    public DSRServiceImpl(DSRRepository dsrRepository, ModelMapper modelMapper) {
        this.dsrRepository = dsrRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DSRDto createDSR(DSRDto dsrDto) {
        //convert Dto to entity
        DSR dsr = mapToEntity(dsrDto);
        DSR newDSR = dsrRepository.save(dsr);
        //convert entity to Dto
        return mapToDto(newDSR);

    }

    @Override
    public DSRResponse getAllDSR(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<DSR> dsrs = dsrRepository.findAll(pageable);
        //get content for page object
        List<DSR> listOfDSR = dsrs.getContent();
        List<DSRDto> content = listOfDSR.stream().map(this::mapToDto).toList();
        DSRResponse dsrResponse = new DSRResponse();
        dsrResponse.setContent(content);
        dsrResponse.setPageNo(dsrs.getNumber());
        dsrResponse.setPageSize(dsrs.getSize());
        dsrResponse.setTotalElements(dsrs.getNumberOfElements());
        dsrResponse.setTotalPages(dsrs.getTotalPages());
        dsrResponse.setLast(dsrs.isLast());
        return dsrResponse;
    }

    @Override
    public DSRDto getDSRById(Long id) {
        DSR dsr = dsrRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DSR", "id", id));
        return mapToDto(dsr);

    }

    @Override
    public DSRDto updateDSR(DSRDto dsrDto, Long id) {
        DSR dsr = dsrRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DSR", "id", id));
        dsr.setUsername(dsrDto.getUsername());
        dsr.setFirstName(dsrDto.getFirstName());
        dsr.setLastName(dsrDto.getLastName());
        dsr.setPhoneNumber(dsrDto.getPhoneNumber());
        dsr.setEmail(dsrDto.getEmail());
        dsr.setTeam(dsrDto.getTeam());
        dsr.setMiddleName(dsrDto.getMiddleName());
        dsr.setIdNumber(Integer.valueOf(dsrDto.getIdNumber()));
        dsr.setGender(dsrDto.getGender());
        dsr.setStatus(dsrDto.getStatus());
        DSR updatedDSR = dsrRepository.save(dsr);
        return mapToDto(updatedDSR);
    }

    @Override
    public void deleteDSRById(Long id) {
        DSR dsr = dsrRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DSR", "id", id));
        dsrRepository.delete(dsr);

    }
    //convert entity to dto
    private DSRDto mapToDto(DSR dsr) {

        return modelMapper.map(dsr, DSRDto .class);

    }
    //convert dto to entity
    private DSR mapToEntity(DSRDto dsrDto) {

        return modelMapper.map(dsrDto, DSR.class);

    }

}
