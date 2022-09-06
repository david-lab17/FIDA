package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.LiquidationResponse;
import com.deltacode.kcb.payload.LiquidationTypeDto;

public interface LiquidationTypeService {
    LiquidationTypeDto createLiquidationType(LiquidationTypeDto liquidationTypeDto);
    LiquidationResponse getAllLiquidationType(int pageNo, int pageSize, String sortBy, String sortDir );
    LiquidationTypeDto getLiquidationTypeById(Long id);
    LiquidationTypeDto updateLiquidationType(LiquidationTypeDto liquidationTypeDto,Long id);
    void deleteLiquidationTypeById(Long id);
}
