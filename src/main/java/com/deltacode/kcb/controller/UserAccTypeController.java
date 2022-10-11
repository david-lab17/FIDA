package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.UserAccTypeDto;
import com.deltacode.kcb.payload.UserAccTypeResponse;
import com.deltacode.kcb.service.UserAccTypeService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = " User Acc Type Controller Rest Api")
@RestController()
@CrossOrigin(origins = "*")
@RequestMapping(path = "/config/user-account-type")
public class UserAccTypeController {
   private final UserAccTypeService userAccTypeService;

    public UserAccTypeController(UserAccTypeService userAccTypeService) {
        this.userAccTypeService = userAccTypeService;
    }

    //create account type
    @ApiOperation(value = "Create  User Acc Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserAccTypeDto> createUserAccType(@Valid @RequestBody UserAccTypeDto userAccTypeDto){
        return  new ResponseEntity<>(userAccTypeService.createUserAccType(userAccTypeDto), HttpStatus.CREATED);
    }
    //get All account types
    @ApiOperation(value = "Fetching all  User Acc Type  Api")
    @GetMapping
    public UserAccTypeResponse getAllUserAccTypes(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return userAccTypeService.getAllUserAccTypes(pageNo,pageSize,sortBy,sortDir);
    }
    //get account type by id
    @ApiOperation(value = "Fetching  Acc Type  Api by Id")
    @GetMapping("/{id}")
       public UserAccTypeDto getUserAccTypesById(@PathVariable Long id){
            return userAccTypeService.getUserAccTypesById(id);
        }

    //update account type
    @ApiOperation(value = "Update Acc Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserAccType(@Valid @RequestBody UserAccTypeDto userAccTypeDto,@PathVariable Long id){
        return userAccTypeService.updateUserAccTypes(userAccTypeDto,id);
    }
    //delete account type
    @ApiOperation(value = "Delete Acc Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserAccTypeById(@PathVariable Long id){
       return userAccTypeService.deleteUserAccTypeById(id);
    }
}
