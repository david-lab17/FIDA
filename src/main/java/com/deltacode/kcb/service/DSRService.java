package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.DSRDto;
import com.deltacode.kcb.payload.DSRResponse;

public interface DSRService {
    DSRDto createDSR(DSRDto dsrDto);
    DSRResponse getAllDSR(int pageNo, int pageSize, String sortBy, String sortDir );
    DSRDto getDSRById(Long id);
    DSRDto updateDSR(DSRDto dsrDto,Long id);
    void deleteDSRById(Long id);
}
