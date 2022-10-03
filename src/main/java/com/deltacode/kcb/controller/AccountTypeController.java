package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.AccountTypeDto;
import com.deltacode.kcb.payload.AccountTypeResponse;
import com.deltacode.kcb.payload.BankDto;
import com.deltacode.kcb.service.AccountTypeService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Acc Type Controller Rest Api")
@RestController()
@CrossOrigin(origins = "*")
@RequestMapping(path = "/config/account-types")
public class AccountTypeController {
    private final AccountTypeService accountTypeService;

    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }
    //create account type
    @ApiOperation(value = "Create Acc Type Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AccountTypeDto> createAccountType(@Valid @RequestBody AccountTypeDto accountTypeDto){
        return  new ResponseEntity<>(accountTypeService.createAccountType(accountTypeDto), HttpStatus.CREATED);
    }
    //get All account types
    @ApiOperation(value = "Fetching all Acc Type  Api")
    @GetMapping
    public AccountTypeResponse getAllAccountTypes(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return accountTypeService.getAllAccountTypes(pageNo,pageSize,sortBy,sortDir);
    }
    //get account type by id
    @ApiOperation(value = "Fetching  Acc Type  Api by Id")
    @GetMapping("/{id}")
    public AccountTypeDto getAccountTypeById(@PathVariable Long id){
        return accountTypeService.getAccountTypesById(id);
    }
    //update account type
    @ApiOperation(value = "Update Acc Type Api")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccountType(@Valid @RequestBody AccountTypeDto accountTypeDto,@PathVariable Long id){
        return accountTypeService.updateAccountTypes(accountTypeDto,id);
    }
    //delete account type
    @ApiOperation(value = "Delete Acc Type Api")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAccountTypeById(@PathVariable Long id){
        accountTypeService.deleteAccountTypeById(id);
    }
}
