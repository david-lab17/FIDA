package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.LiquidationResponse;
import com.deltacode.kcb.payload.LiquidationTypeDto;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

public interface LiquidationTypeService {
    ResponseEntity<?> createLiquidationType(LiquidationTypeDto liquidationTypeDto);
    LiquidationResponse getAllLiquidationType(int pageNo, int pageSize, String sortBy, String sortDir );
    ResponseEntity<?> getLiquidationTypeById(Long id);
    ResponseEntity<?> updateLiquidationType(LiquidationTypeDto liquidationTypeDto);
    ResponseEntity<?> deleteLiquidationTypeById(Long id);
}
