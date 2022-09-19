package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.ComplaintType;
import com.deltacode.kcb.entity.Constituency;
import com.deltacode.kcb.entity.County;
import com.deltacode.kcb.exception.FieldPortalApiException;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.*;
import com.deltacode.kcb.repository.ConstituencyRepository;
import com.deltacode.kcb.repository.CountyRepository;
import com.deltacode.kcb.service.ConstituencyService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service


public class ConstituencyServiceImpl implements ConstituencyService {
    private final ConstituencyRepository constituencyRepository;
    private final CountyRepository countyRepository;
    private final ModelMapper modelMapper;

    public ConstituencyServiceImpl(ConstituencyRepository constituencyRepository,
                                   CountyRepository countyRepository,
                                   ModelMapper modelMapper) {
        this.constituencyRepository = constituencyRepository;
        this.countyRepository = countyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ConstituencyDto createConstituency(long countyId, ConstituencyDto constituencyDto) {
        Constituency constituency =mapToEntity(constituencyDto);
        // retrieve county entity by id
        County county = countyRepository.findById(countyId).orElseThrow(
                () -> new ResourceNotFoundException("County", "id", countyId));

        // set county to constituency entity
        constituency.setCounty(county);

        // constituency entity to DB
        Constituency newConstituency =  constituencyRepository.save(constituency);

        return mapToDto(newConstituency);
    }

    @Override
    public List<ConstituencyDto> getConstituencyByCountyId(long countyId) {
        // retrieve constituency by countyId
        List<Constituency> constituencies = constituencyRepository.findByCountyId(countyId);

        // convert list of constituency entities to list of constituency dto's
        return constituencies.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ConstituencyResponse getAllConstituency(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort
                .Direction
                .ASC
                .name())?Sort.by(sortBy)
                .ascending():Sort.
                by(sortBy).
                descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<Constituency> constituency=constituencyRepository.findAll(pageable);
        //get content for page object
        List<Constituency> constituencyList=constituency.getContent();
        List<ConstituencyDto> content=constituencyList.stream().map(this::mapToDto).toList();
        ConstituencyResponse constituencyResponse=new ConstituencyResponse();
        constituencyResponse.setContent(content);
        constituencyResponse.setPageNo(constituency.getNumber());
        constituencyResponse.setPageSize(constituency.getSize());
        constituencyResponse.setTotalElements((int) constituency.getTotalElements());
        constituencyResponse.setTotalPages(constituency.getTotalPages());
        constituencyResponse.setLast(constituency.isLast());
        return constituencyResponse;
    }

    @Override
    public ConstituencyDto getConstituencyById(Long countyId, Long constituencyId) {
        // retrieve county entity by id
        County county = countyRepository.findById(countyId).orElseThrow(
                () -> new ResourceNotFoundException("County", "id", countyId));

        // retrieve constituency by id
        Constituency constituency = constituencyRepository.findById(constituencyId).orElseThrow(() ->
                new ResourceNotFoundException("Constituency", "id", constituencyId));

        if(!constituency.getCounty().getId().equals(county.getId())){
            throw new FieldPortalApiException(HttpStatus.BAD_REQUEST, "Constituency does not belong to a county");
        }

        return mapToDto(constituency);
    }

    @Override
    public ConstituencyDto updateConstituency(Long countyId, long constituencyId, ConstituencyDto constituencyDto) {
        // retrieve county entity by id
        County county = countyRepository.findById(countyId).orElseThrow(
                () -> new ResourceNotFoundException("County", "id", countyId));
        //retrieve constituency by id
        Constituency constituency = constituencyRepository.findById(constituencyId).orElseThrow(() ->
                new ResourceNotFoundException("Constituency", "id", constituencyId));
        if (!constituency.getCounty().getId().equals(county.getId())) {
            throw new FieldPortalApiException(HttpStatus.BAD_REQUEST, "Constituency does not belong to a county");
        }
        constituency.setConstituencyName(constituencyDto.getConstituencyName());
        constituency.setConstituencyCode(constituencyDto.getConstituencyCode());
        constituency.setDescription(constituencyDto.getDescription());
        Constituency updatedConstituency = constituencyRepository.save(constituency);
        return mapToDto(updatedConstituency);
    }

    @Override
    public void deleteConstituency(Long countyId, Long constituencyId) {
        // retrieve county entity by id
        County county = countyRepository.findById(countyId).orElseThrow(
                () -> new ResourceNotFoundException("County", "id", countyId));
        //retrieve constituency by id
        Constituency constituency = constituencyRepository.findById(constituencyId).orElseThrow(() ->
                new ResourceNotFoundException("Constituency", "id", constituencyId));
        if (!constituency.getCounty().getId().equals(county.getId())) {
            throw new FieldPortalApiException(HttpStatus.BAD_REQUEST, "Constituency does not belong to a county");
        }
        constituencyRepository.delete(constituency);

    }

    //convert entity to dto
    private ConstituencyDto mapToDto(Constituency constituency) {

        return modelMapper.map(constituency, ConstituencyDto .class);
    }
    //convert dto to entity
    private Constituency mapToEntity(ConstituencyDto constituencyDto) {

        return modelMapper.map(constituencyDto, Constituency.class);

    }
}
