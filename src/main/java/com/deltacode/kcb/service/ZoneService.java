package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.ZoneDto;
import com.deltacode.kcb.payload.ZoneResponse;
import org.springframework.http.ResponseEntity;

public interface ZoneService {
    ZoneDto createZone(ZoneDto zoneDto);
    ZoneResponse getAllZones(int pageNo, int pageSize, String sortBy, String sortDir );

    ZoneDto getZoneById(Long id);
    ResponseEntity<?> updateZone(ZoneDto zoneDto, Long id);
    void deleteZoneById(Long id);
}
