package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.BranchDto;
import com.deltacode.kcb.payload.BranchResponse;

public interface BranchService {
    BranchDto createBranch(BranchDto bankDto);
    BranchResponse getAllBranches(int pageNo, int pageSize, String sortBy, String sortDir );
    BranchDto getBranchById(Long id);
    BranchDto updateBranch(BranchDto bankDto,Long id);
    void deleteBranchById(Long id);
}
