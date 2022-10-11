package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.EmploymentTypeDto;
import com.deltacode.kcb.payload.EmploymentTypeResponse;
import com.deltacode.kcb.service.EmploymentTypeService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Employment Type Controller Rest Api")
@RestController()
@CrossOrigin(origins = "*")
@RequestMapping(path = "/config/employment-types")
public class EmploymentTypeController {
    private final EmploymentTypeService employmentTypeService;

    public EmploymentTypeController(EmploymentTypeService employmentTypeService) {
        this.employmentTypeService = employmentTypeService;
    }


    //create account type
    @ApiOperation(value = "Create Employment Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmploymentTypeDto> createAccountType(@Valid @RequestBody EmploymentTypeDto employmentTypeDto){
        return  new ResponseEntity<>(employmentTypeService.createEmploymentType(employmentTypeDto), HttpStatus.CREATED);
    }
    //get All account types
    @ApiOperation(value = "Fetching all Employment Type  Api")
    @GetMapping
    public EmploymentTypeResponse getAllEmploymentTypes(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return employmentTypeService.getAllEmploymentTypes(pageNo,pageSize,sortBy,sortDir);
    }
    //get account type by id
    @ApiOperation(value = "Fetching  Employment Type  Api by Id")
    @GetMapping("/{id}")
    public EmploymentTypeDto getEmploymentTypeById(@PathVariable Long id){
        return employmentTypeService.getEmploymentTypesById(id);
    }
    //update account type
    @ApiOperation(value = "Update Employment Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EmploymentTypeDto updateEmploymentType(@Valid @RequestBody EmploymentTypeDto employmentTypeDto, @PathVariable Long id){
        return employmentTypeService.updateEmploymentTypes(employmentTypeDto,id);
    }
    //delete account type
    @ApiOperation(value = "Delete Employment Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmploymentTypeById(@PathVariable Long id){
        return employmentTypeService.deleteEmploymentTypeById(id);
    }
}
