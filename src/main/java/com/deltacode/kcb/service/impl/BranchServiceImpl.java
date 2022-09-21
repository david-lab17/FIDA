package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Bank;
import com.deltacode.kcb.entity.Branch;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.BankDto;
import com.deltacode.kcb.payload.BranchDto;
import com.deltacode.kcb.payload.BranchResponse;
import com.deltacode.kcb.repository.BranchRepository;
import com.deltacode.kcb.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BranchDto createBranch(BranchDto branchDto) {
        log.info("Creating branch");
        //convert Dto to entity
        Branch branch = mapToEntity(branchDto);
        Branch newBranch = branchRepository.save(branch);
        //convert entity to Dto
        return mapToDto(newBranch);
    }

    @Override
    public BranchResponse getAllBranches(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all branches");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        org.springframework.data.domain.Page<Branch> branches = branchRepository.findAll(pageable);
        //get content for page object
        List<Branch> listOfBranch = branches.getContent();
        List<BranchDto> content = listOfBranch.stream().map(this::mapToDto).toList();
        BranchResponse branchResponse = new BranchResponse();
        branchResponse.setContent(content);
        branchResponse.setPageNo(branches.getNumber());
        branchResponse.setPageSize(branches.getSize());
        branchResponse.setTotalElements(branches.getNumberOfElements());
        branchResponse.setTotalPages(branches.getTotalPages());
        branchResponse.setLast(branches.isLast());
        return branchResponse;
    }

    @Override
    public BranchDto getBranchById(Long id) {
        log.info("Getting branch by id = {}", id);
        //check if branch exists
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));
        return mapToDto(branch);
    }

    @Override
    public BranchDto updateBranch(BranchDto branchDto, Long id) {
        log.info("Updating branch with id = {}", id);
        //check if branch exists
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));
        //convert Dto to entity
        Branch newBranch = mapToEntity(branchDto);
        newBranch.setBranchName(branch.getBranchName());
        newBranch.setBranchCode(branch.getBranchCode());
        Branch updatedBranch = branchRepository.save(newBranch);
        //convert entity to Dto
        return mapToDto(updatedBranch);
    }

    @Override
    public void deleteBranchById(Long id) {
        log.info("Deleting branch with id = {}", id);
        //check if branch exists
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch", "id", id));
        branchRepository.delete(branch);

    }
    //convert entity to dto
    private BranchDto mapToDto(Branch branch) {

        return modelMapper.map(branch, BranchDto .class);

    }
    //convert dto to entity
    private Branch mapToEntity(BranchDto branchDto) {

        return modelMapper.map(branchDto, Branch.class);

    }

}

