package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.ConstituencyDto;
import com.deltacode.kcb.payload.ConstituencyResponse;

public interface ConstituencyService {
    ConstituencyDto createConstituency(ConstituencyDto constituencyDto);
    ConstituencyResponse getAllConstituencies(int pageNo, int pageSize,String sortBy, String sortDir );
    ConstituencyDto getConstituencyById(Long id);
    ConstituencyDto updateConstituency(ConstituencyDto constituencyDto, Long id);
    void deleteConstituencyById(Long id);
}
