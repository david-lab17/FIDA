package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.BankDto;
import com.deltacode.kcb.payload.BankResponse;
import com.deltacode.kcb.payload.BranchDto;
import com.deltacode.kcb.payload.BranchResponse;
import com.deltacode.kcb.service.BranchService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*")
@Api(value = "Branch Controller Rest Api")
@RestController()
@RequestMapping(path = "/config/branch")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }
    //create branch
    @ApiOperation(value = "Create Branch Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BranchDto> createTeam(@Valid @RequestBody BranchDto branchDto){
        return  new ResponseEntity<>(branchService.createBranch(branchDto), HttpStatus.CREATED);
    }
    //get All branches
    @ApiOperation(value = "Fetching all Branch  Api")
    @GetMapping
    public BranchResponse getAllBranches(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return branchService.getAllBranches(pageNo,pageSize,sortBy,sortDir);
    }
    //get branch by id
    @ApiOperation(value = "Fetching  Branch  Api by Id")
    @GetMapping("/{id}")
    public BranchDto getBranchById(@PathVariable Long id){
        return branchService.getBranchById(id);
    }
    //update branch
    @ApiOperation(value = "Update Branch Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BranchDto updateBank(@Valid @RequestBody BranchDto bankDto,@PathVariable Long id){
        return branchService.updateBranch(bankDto,id);
    }
    //delete branch
    @ApiOperation(value = "Delete Branch Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable Long id){
        branchService.deleteBranchById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
