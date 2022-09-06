package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.ZoneDto;
import com.deltacode.kcb.payload.ZoneResponse;

public interface ZoneService {
    ZoneDto createZone(ZoneDto zoneDto);
    ZoneResponse getAllZones(int pageNo, int pageSize, String sortBy, String sortDir );

    ZoneDto getZoneById(Long id);
    ZoneDto updateZone(ZoneDto zoneDto,Long id);
    void deleteZoneById(Long id);
}
