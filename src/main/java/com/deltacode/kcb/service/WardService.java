package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.WardDto;

import java.util.List;

public interface WardService {
    WardDto createWard(long constituencyId, WardDto wardDto);

    List<WardDto> getWardByConstituencyId(long constituencyId);

    WardDto getWardById(Long constituencyId, Long wardId);

    WardDto updateWard(Long constituencyId, long wardId, WardDto wardDto);

    void deleteWard(Long constituencyId, Long wardId);
}
