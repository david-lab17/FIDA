package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.DSRDto;
import com.deltacode.kcb.payload.DSRResponse;
import com.deltacode.kcb.service.DSRService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@Api(value = "Direct Sale Representative Controller Rest Api")
@RestController()

@RequestMapping(path = "/dsr-teams/dsr")
public class DSRController {
    private final DSRService dsrService;

    public DSRController(DSRService dsrService) {
        this.dsrService = dsrService;
    }

    //create DSR
    @ApiOperation(value = "Create DSR Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DSRDto> createDSR(@Valid @RequestBody DSRDto dsrDto){
        return  new ResponseEntity<>(dsrService.createDSR(dsrDto), HttpStatus.CREATED);
    }
    //get All banks
    @ApiOperation(value = "Fetching all DSR  Api")
    @GetMapping
    public DSRResponse getAllDSR(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return dsrService.getAllDSR(pageNo,pageSize,sortBy,sortDir);
    }
    //get bank by id
    @ApiOperation(value = "Fetching  DSR  Api by Id")
    @GetMapping("/{id}")
    public DSRDto getDSRById(@PathVariable Long id){
        return dsrService.getDSRById(id);
    }
    //update bank
    @ApiOperation(value = "Update DSR Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public DSRDto updateDSR(@Valid @RequestBody DSRDto dsrDto,@PathVariable Long id){
        return dsrService.updateDSR(dsrDto,id);
    }
    //delete bank
    @ApiOperation(value = "Delete DSR Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable Long id){
        dsrService.deleteDSRById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
