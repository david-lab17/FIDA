package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.ComplaintType;
import com.deltacode.kcb.entity.County;
import com.deltacode.kcb.payload.ComplaintTypeDto;
import com.deltacode.kcb.payload.CountyDto;
import com.deltacode.kcb.payload.CountyResponse;
import com.deltacode.kcb.repository.CountyRepository;
import com.deltacode.kcb.service.CountyService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountyServiceImpl implements CountyService {
    private final CountyRepository countyRepository;
    private final ModelMapper modelMapper;

    public CountyServiceImpl(CountyRepository countyRepository, ModelMapper modelMapper) {
        this.countyRepository = countyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountyDto createCounty(CountyDto countyDto) {
        County county = modelMapper.map(countyDto, County.class);
        countyRepository.save(county);
        return modelMapper.map(county, CountyDto.class);

    }

    @Override
    public CountyResponse getAllCounties(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<County> counties=countyRepository.findAll(pageable);
        //get content for page object
        List<County> countyList=counties.getContent();
        List<CountyDto> content=countyList.stream().map(this::mapToDto).toList();
        CountyResponse countyResponse =new CountyResponse();
        countyResponse.setContent(content);
        countyResponse.setTotalPages(counties.getTotalPages());
        countyResponse.setTotalElements((int) counties.getTotalElements());
        countyResponse.setPageSize(counties.getSize());
        countyResponse.setPageNo(counties.getTotalPages());
        countyResponse.setLast(counties.isLast());
        return countyResponse;
    }

    @Override
    public CountyDto getCountyById(Long id) {
        County county = countyRepository.findById(id).orElseThrow( () -> new RuntimeException("County not found"));
        return modelMapper.map(county, CountyDto.class);
    }

    @Override
    public CountyDto updateCounty(CountyDto countyDto, Long id) {
        County county = countyRepository.findById(id).orElseThrow( () -> new RuntimeException("County not found"));
        county.setCountyName(countyDto.getCountyName());
        county.setCountyCode(countyDto.getCountyCode());
        county.setDescription(countyDto.getDescription());
        countyRepository.save(county);
        return modelMapper.map(county, CountyDto.class);
    }

    @Override
    public void deleteCountyById(Long id) {
        //check if county exists
        countyRepository.findById(id).orElseThrow( () -> new RuntimeException("County not found"));
        countyRepository.deleteById(id);

    }
    //convert entity to dto
    private CountyDto mapToDto(County county) {

        return modelMapper.map(county, CountyDto .class);
    }
    //convert dto to entity
    private County mapToEntity(CountyDto countyDto) {

        return modelMapper.map(countyDto, County.class);

    }

}
