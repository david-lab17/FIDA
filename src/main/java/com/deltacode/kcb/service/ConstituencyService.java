package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.BusinessTypeDto;
import com.deltacode.kcb.payload.ComplaintTypeResponse;
import com.deltacode.kcb.payload.ConstituencyDto;
import com.deltacode.kcb.payload.ConstituencyResponse;

import java.util.List;

public interface ConstituencyService {
    ConstituencyDto createConstituency(long countyId, ConstituencyDto constituencyDto);

    List<ConstituencyDto> getConstituencyByCountyId(long countyId);

    ConstituencyDto getConstituencyById(Long countyId, Long constituencyId);

    ConstituencyDto updateConstituency(Long countyId, long constituencyId, ConstituencyDto constituencyDto);
    ConstituencyResponse getAllConstituency(int pageNo, int pageSize, String sortBy, String sortDir );

    void deleteConstituency(Long countyId, Long constituencyId);
}