package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.CountyDto;
import com.deltacode.kcb.payload.CountyResponse;
import com.deltacode.kcb.payload.WardDto;
import com.deltacode.kcb.payload.WardResponse;
import com.deltacode.kcb.service.WardService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@Api(value = "Ward Controller Rest Api")
@RestController()

@RequestMapping(path = "/config/ward")
public class WardController {
    private final WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @ApiOperation(value = "Create Ward Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<WardDto> createWard(@Valid @RequestBody WardDto wardDto){
        return  new ResponseEntity<>(wardService.createWard(wardDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Fetching all Wards  Api")
    @GetMapping
    public WardResponse getAllWards(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return wardService.getAllWards(pageNo,pageSize,sortBy,sortDir);
    }

    @ApiOperation(value = "Fetching  Ward  Api by Id")
    @GetMapping("/{id}")
    public WardDto getWardById(@PathVariable Long id){
        return wardService.getWardById(id);
    }

    @ApiOperation(value = "Update Ward Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public WardDto updateWard(@Valid @RequestBody WardDto wardDto,@PathVariable Long id){
        return wardService.updateWard(wardDto,id);
    }

    @ApiOperation(value = "Delete Ward Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWard(@PathVariable Long id){
        wardService.deleteWardById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
