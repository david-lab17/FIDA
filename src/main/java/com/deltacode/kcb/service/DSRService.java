package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.DSRDto;
import com.deltacode.kcb.payload.WardDto;

import java.util.List;

public interface DSRService {
    DSRDto createDSR(long teamId, DSRDto dsrDto);

    List<DSRDto> getDSRByTeamId(long teamId);

    DSRDto getDSRById(Long teamId, Long dsrId);

    DSRDto updateDSR(Long teamId, long dsrId, DSRDto dsrDto);

    void deleteDSR(Long teamId, Long dsrId);

}
