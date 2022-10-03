package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.BankDto;
import com.deltacode.kcb.payload.BankResponse;
import com.deltacode.kcb.payload.CountyDto;
import com.deltacode.kcb.payload.CountyResponse;
import com.deltacode.kcb.service.CountyService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@Api(value = "County Controller Rest Api")
@RestController()

@RequestMapping(path = "county/api/v1")
public class CountyController {
    private final CountyService countyService;

    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    @ApiOperation(value = "Create County Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CountyDto> createCounty(@Valid @RequestBody CountyDto countyDto){
        return  new ResponseEntity<>(countyService.createCounty(countyDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Fetching all County  Api")
    @GetMapping
    public CountyResponse getAllCounties(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return countyService.getAllCounties(pageNo,pageSize,sortBy,sortDir);
    }

    @ApiOperation(value = "Fetching  County  Api by Id")
    @GetMapping("/{id}")
    public CountyDto getCountyById(@PathVariable Long id){
        return countyService.getCountyById(id);
    }

    @ApiOperation(value = "Update Bank Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCounty(@Valid @RequestBody CountyDto countyDto,@PathVariable Long id){
        return countyService.updateCounty(countyDto,id);
    }

    @ApiOperation(value = "Delete County Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCounty(@PathVariable Long id){
        countyService.deleteCountyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

