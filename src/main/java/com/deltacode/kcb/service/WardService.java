package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.CountyResponse;
import com.deltacode.kcb.payload.WardDto;
import com.deltacode.kcb.payload.WardResponse;

import java.util.List;

public interface WardService {
    WardDto createWard(long constituencyId, WardDto wardDto);

    List<WardDto> getWardByConstituencyId(long constituencyId);
    WardResponse getAllWards(int pageNo, int pageSize, String sortBy, String sortDir );

    WardDto getWardById(Long constituencyId, Long wardId);

    WardDto updateWard(Long constituencyId, long wardId, WardDto wardDto);

    void deleteWard(Long constituencyId, Long wardId);
}
