package com.deltacode.kcb.controller;
import com.deltacode.kcb.payload.ComplaintTypeDto;
import com.deltacode.kcb.payload.ComplaintTypeResponse;
import com.deltacode.kcb.service.ComplaintTypeService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping(path = "/config/complaint-types")
public class ComplaintTypeController {
    private final ComplaintTypeService complaintTypeService;

    public ComplaintTypeController(ComplaintTypeService complaintTypeService) {
        this.complaintTypeService = complaintTypeService;
    }


    //create account type
    @ApiOperation(value = "Create Complaint Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ComplaintTypeDto> createComplaintType(@Valid @RequestBody ComplaintTypeDto complaintTypeDto){
        return  new ResponseEntity<>(complaintTypeService.createComplaintType(complaintTypeDto), HttpStatus.CREATED);
    }
    //get All account types
    @ApiOperation(value = "Fetching all Complaint Type  Api")
    @GetMapping
    public ComplaintTypeResponse getAllComplaintTypes(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return complaintTypeService.getAllComplaintTypes(pageNo,pageSize,sortBy,sortDir);
    }
    //get account type by id
    @ApiOperation(value = "Fetching  Complaint Type  Api by Id")
    @GetMapping("/{id}")
    public ComplaintTypeDto getComplaintTypeById(@PathVariable Long id){
        return complaintTypeService.getComplaintTypesById(id);
    }
    //update account type
    @ApiOperation(value = "Update Business Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ComplaintTypeDto updateComplaintType(@Valid @RequestBody ComplaintTypeDto complaintType, @PathVariable Long id){
        return complaintTypeService.updateComplaintTypes(complaintType,id);
    }
    //delete account type
    @ApiOperation(value = "Delete Complaint Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteComplaintTypeById(@PathVariable Long id){
        complaintTypeService.deleteComplaintTypeById(id);
    }
}
