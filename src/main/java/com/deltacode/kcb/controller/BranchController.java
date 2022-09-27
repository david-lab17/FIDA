package com.deltacode.kcb.controller;

import com.deltacode.kcb.entity.Branch;
import com.deltacode.kcb.payload.BranchDto;
import com.deltacode.kcb.payload.BranchResponse;
import com.deltacode.kcb.payload.TeamDto;
import com.deltacode.kcb.payload.TeamResponse;
import com.deltacode.kcb.service.BranchService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@Api(value = "Branch Controller Rest Api")
@RestController()

@RequestMapping(path = "/banks/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    //create branch
    @ApiOperation(value = "Create Branch REST API")
    @PostMapping("/bank/{bankId}/branch")
    public ResponseEntity<BranchDto> createBranch(@PathVariable(value = "bankId") Long bankId,
                                               @Valid @RequestBody BranchDto branchDto) {
        return new ResponseEntity<>(branchService.createBranch(bankId, branchDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Fetching all Branch  Api")
    @GetMapping("/branches")
    public BranchResponse getAllBranches(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return branchService.getAllBranches(pageNo,pageSize,sortBy,sortDir);
    }
    @ApiOperation(value = "Get Single Branch By ID REST API")
    @GetMapping("/banks/{bankId/branch/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable(value = "bankId") Long bankId,
                                                 @PathVariable(value = "id") Long branchId) {
        BranchDto branchDto = branchService.getBranchById(bankId, branchId);
        return new ResponseEntity<>(branchDto, HttpStatus.OK);

    }

    @ApiOperation(value = "Update Branch By ID REST API")
    @PutMapping("/bank/{bankId}/branch/{id}")
    public ResponseEntity<BranchDto> updateBranch(@PathVariable(value = "bankId") Long bankId,
                                                @PathVariable(value = "id") Long branchId,
                                                @Valid @RequestBody BranchDto branchDto) {

        BranchDto updatedBranch = branchService.updateBranch(bankId, branchId, branchDto);
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }
    @ApiOperation(value = "Delete Branch By ID REST API")
    @DeleteMapping("/banks/{bankId}/branch/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable(value = "bankId") Long BankId,
                                             @PathVariable(value = "id") Long branchId) {
        branchService.deleteBranch(BankId, branchId);
        return new ResponseEntity<>("Team deleted successfully", HttpStatus.OK);
    }

}
