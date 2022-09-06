package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.LiquidationResponse;
import com.deltacode.kcb.payload.LiquidationTypeDto;
import com.deltacode.kcb.service.LiquidationTypeService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@Api(value = "LiquidationType API")
@RestController()

@RequestMapping(path = "/config/liquidationTypes")
public class LiquidationTypeController {
    private final LiquidationTypeService liquidationTypeService;

    public LiquidationTypeController(LiquidationTypeService liquidationTypeService) {
        this.liquidationTypeService = liquidationTypeService;
    }

    //create liquidationType
    @ApiOperation(value = "Create Liquidation Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LiquidationTypeDto> createLiquidationType(@Valid @RequestBody LiquidationTypeDto liquidationTypeDto) {
        return new ResponseEntity<>(liquidationTypeService.createLiquidationType(liquidationTypeDto), HttpStatus.CREATED);
    }

    //get All liquidationTypes
    @ApiOperation(value = "Fetching all Liquidation Type  Api")
    @GetMapping
    public LiquidationResponse getAllLiquidationTypes(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ) {
        return liquidationTypeService.getAllLiquidationType(pageNo, pageSize, sortBy, sortDir);
    }

    //get liquidationType by id
    @ApiOperation(value = "Fetching Liquidation Type by Id Api")
    @GetMapping("/{id}")
    public ResponseEntity<LiquidationTypeDto> getLiquidationTypeById(@PathVariable Long id) {
        return new ResponseEntity<>(liquidationTypeService.getLiquidationTypeById(id), HttpStatus.OK);
    }

    //update liquidationType
    @ApiOperation(value = "Update Liquidation Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public LiquidationTypeDto updateLiquidationType(@Valid @RequestBody LiquidationTypeDto liquidationTypeDto, @PathVariable Long id) {
        return liquidationTypeService.updateLiquidationType(liquidationTypeDto, id);
    }
    //delete liquidationType
    @ApiOperation(value = "Delete Liquidation Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLiquidationType(@PathVariable Long id) {
        liquidationTypeService.deleteLiquidationTypeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
