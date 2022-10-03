package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.CountyDto;
import com.deltacode.kcb.payload.CountyResponse;
import org.springframework.http.ResponseEntity;

public interface CountyService {
    CountyDto createCounty(CountyDto countyDto);
    CountyResponse getAllCounties(int pageNo, int pageSize, String sortBy, String sortDir );

    CountyDto getCountyById(Long id);
    ResponseEntity<?> updateCounty(CountyDto countyDto, Long id);
    void deleteCountyById(Long id);
}
