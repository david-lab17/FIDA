package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.ConstituencyDto;

import java.util.List;

public interface ConstituencyService {
    ConstituencyDto createConstituency(long countyId, ConstituencyDto constituencyDto);

    List<ConstituencyDto> getConstituencyByCountyId(long countyId);

    ConstituencyDto getConstituencyById(Long countyId, Long constituencyId);

    ConstituencyDto updateConstituency(Long countyId, long constituencyId, ConstituencyDto constituencyDto);

    void deleteConstituency(Long countyId, Long constituencyId);
}