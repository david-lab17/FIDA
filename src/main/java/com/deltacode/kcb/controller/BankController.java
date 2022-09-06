package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.BankDto;
import com.deltacode.kcb.payload.BankResponse;
import com.deltacode.kcb.service.BankService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*")
@Api(value = "Bank Controller Rest Api")
@RestController()

@RequestMapping(path = "/config/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }
    //create bank
    @ApiOperation(value = "Create Bank Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BankDto> createBank(@Valid @RequestBody BankDto bankDto){
        return  new ResponseEntity<>(bankService.createBank(bankDto), HttpStatus.CREATED);
    }
    //get All banks
    @ApiOperation(value = "Fetching all Bank  Api")
    @GetMapping
    public BankResponse getAllBanks(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return bankService.getAllBanks(pageNo,pageSize,sortBy,sortDir);
    }
    //get bank by id
    @ApiOperation(value = "Fetching  Bank  Api by Id")
    @GetMapping("/{id}")
    public BankDto getBankById(@PathVariable Long id){
        return bankService.getBankById(id);
    }
    //update bank
    @ApiOperation(value = "Update Bank Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BankDto updateBank(@Valid @RequestBody BankDto bankDto,@PathVariable Long id){
        return bankService.updateBank(bankDto,id);
    }
    //delete bank
    @ApiOperation(value = "Delete Bank Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable Long id){
        bankService.deleteBankById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
