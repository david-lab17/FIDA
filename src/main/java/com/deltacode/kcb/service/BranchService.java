package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.BranchDto;
import com.deltacode.kcb.payload.BranchResponse;
import com.deltacode.kcb.payload.TeamDto;
import com.deltacode.kcb.payload.TeamResponse;

import java.util.List;

public interface BranchService {
    BranchDto createBranch(Long bankId, BranchDto branchDto);

    List<BranchDto> getBranchByBankId(Long bankId);

    BranchResponse getAllBranches(int pageNo, int pageSize, String sortBy, String sortDir );

    BranchDto getBranchById(Long bankId, Long branchId);

    BranchDto updateBranch(Long bankId, Long branchId, BranchDto branchDto);

    void deleteBranch(Long bankId, Long branchId);
}
