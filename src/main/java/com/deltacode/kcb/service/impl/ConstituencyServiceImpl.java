package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Constituency;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.ConstituencyDto;
import com.deltacode.kcb.payload.ConstituencyResponse;
import com.deltacode.kcb.repository.ConstituencyRepository;
import com.deltacode.kcb.service.ConstituencyService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ConstituencyServiceImpl implements ConstituencyService {
    private final ConstituencyRepository constituencyRepository;
    private final ModelMapper modelMapper;

    public ConstituencyServiceImpl(ConstituencyRepository constituencyRepository,
                                   ModelMapper modelMapper) {
        this.constituencyRepository = constituencyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ConstituencyDto createConstituency(ConstituencyDto constituencyDto) {
        Constituency constituency = mapToEntity(constituencyDto);
        Constituency newConstituency = constituencyRepository.save(constituency);
        return mapToDto(newConstituency);
    }

    @Override
    public ConstituencyResponse getAllConstituencies(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<Constituency> constituencies = constituencyRepository.findAll(pageable);
        //get content for page object
        List<Constituency> listOfConstituency = constituencies.getContent();
        List<ConstituencyDto> content = listOfConstituency.stream().map(this::mapToDto).toList();
        ConstituencyResponse constituencyResponse = new ConstituencyResponse();
        constituencyResponse.setContent(content);
        constituencyResponse.setPageNo(constituencies.getNumber());
        constituencyResponse.setPageSize(constituencies.getSize());
        constituencyResponse.setTotalElements(constituencies.getNumberOfElements());
        constituencyResponse.setTotalPages(constituencies.getTotalPages());
        constituencyResponse.setLast(constituencies.isLast());
        return constituencyResponse;
    }

    @Override
    public ConstituencyDto getConstituencyById(Long id) {
        //check if constituency exists
        Constituency constituency = constituencyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Constituency", "id", id));
        return mapToDto(constituency);
    }

    @Override
    public ConstituencyDto updateConstituency(ConstituencyDto constituencyDto, Long id) {
        //check if constituency exists
        Constituency constituency = constituencyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Constituency", "id", id));
        constituency.setConstituencyName(constituencyDto.getConstituencyName());
        constituency.setConstituencyCode(constituencyDto.getConstituencyCode());
        constituency.setDescription(constituencyDto.getDescription());
        constituencyRepository.save(constituency);
        return mapToDto(constituency);
    }

    @Override
    public void deleteConstituencyById(Long id) {
        //check if constituency exists
        constituencyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Constituency", "id", id));
        constituencyRepository.deleteById(id);

    }
    //convert entity to dto
    private ConstituencyDto mapToDto(Constituency constituency){
    return modelMapper.map(constituency, ConstituencyDto.class);
    }
    //convert dto to entity
    private Constituency mapToEntity(ConstituencyDto constituencyDto){
    return modelMapper.map(constituencyDto, Constituency.class);
    }
}
