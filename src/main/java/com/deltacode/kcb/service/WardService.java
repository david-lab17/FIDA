package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.WardDto;
import com.deltacode.kcb.payload.WardResponse;

public interface WardService {
    WardDto createWard(WardDto wardDto);
    WardResponse getAllWards(int pageNo, int pageSize, String sortBy, String sortDir );
    WardDto getWardById(Long id);
    WardDto updateWard(WardDto wardDto,Long id);
    void deleteWardById(Long id);
}
