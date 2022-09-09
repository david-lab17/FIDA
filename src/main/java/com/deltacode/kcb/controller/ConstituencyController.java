package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.ConstituencyDto;
import com.deltacode.kcb.payload.ConstituencyResponse;
import com.deltacode.kcb.service.ConstituencyService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@Api(value = "Constituency Controller Rest Api")
@RestController()

@RequestMapping(path = "/config/constituency")
public class ConstituencyController {
    private final ConstituencyService constituencyService;

    public ConstituencyController(ConstituencyService constituencyService) {
        this.constituencyService = constituencyService;
    }

    @ApiOperation(value = "Create Constituency Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ConstituencyDto> createConstituency(@Valid @RequestBody ConstituencyDto constituencyDto){
        return  new ResponseEntity<>(constituencyService.createConstituency(constituencyDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Fetching all Constituency  Api")
    @GetMapping
    public ConstituencyResponse getAllConstituency(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return constituencyService.getAllConstituencies(pageNo,pageSize,sortBy,sortDir);
    }

    @ApiOperation(value = "Fetching  Constituency  Api by Id")
    @GetMapping("/{id}")
    public ConstituencyDto getConstituencyById(@PathVariable Long id){
        return constituencyService.getConstituencyById(id);
    }

    @ApiOperation(value = "Update Constituency Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ConstituencyDto updateConstituency(@Valid @RequestBody ConstituencyDto constituencyDto,@PathVariable Long id){
        return constituencyService.updateConstituency(constituencyDto,id);
    }

    @ApiOperation(value = "Delete Constituency Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConstituency(@PathVariable Long id){
        constituencyService.deleteConstituencyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
