package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Bank;
import com.deltacode.kcb.entity.Branch;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.BranchDto;
import com.deltacode.kcb.payload.BranchResponse;
import com.deltacode.kcb.repository.BankRepository;
import com.deltacode.kcb.repository.BranchRepository;
import com.deltacode.kcb.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private  final BankRepository bankRepository;




    @Override
    public BranchDto createBranch(Long bankId, BranchDto branchDto) {
        Branch branch =mapToEntity(branchDto);//map dto to entity
        //set bank to branch
        //retrieve bank entity by id
        Bank bank = bankRepository.findById(bankId).orElseThrow(
                () -> new ResourceNotFoundException("Bank", "id", bankId));
        //set bank to branch entity
        branch.setBank(bank);
        //branch entity to DB
        Branch newBranch = branchRepository.save(branch);
        return mapToDto(newBranch);

    }

    @Override
    public List<BranchDto> getBranchByBankId(Long bankId) {
        log.info("Getting all branches by bank id");
        List<Branch> branches=branchRepository.findAllByBankId(bankId);
        List<BranchDto> collect = branches.stream().map(this::mapToDto).collect(Collectors.toList());
        return collect;
    }

    @Override
    public BranchResponse getAllBranches(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable=org.springframework.data.domain.PageRequest.of(pageNo,pageSize,sort);
        //create Pageble instance
        Page<Branch> branchPage=branchRepository.findAll(pageable);
        //get content from page
        List<Branch> branches=branchPage.getContent();
        List<BranchDto> content = branches.stream().map(this::mapToDto).collect(Collectors.toList());//map entity to dto
        BranchResponse branchResponse=new BranchResponse();
        branchResponse.setContent(content);
        branchResponse.setPageNo(branchPage.getNumber());
        branchResponse.setPageSize(branchPage.getSize());
        branchResponse.setTotalPages(branchPage.getTotalPages());
        branchResponse.setTotalElements((int) branchPage.getTotalElements());
        return branchResponse;
    }

    @Override
    public BranchDto getBranchById(Long bankId, Long branchId) {
        HashMap<String, Objects>responseObject=new HashMap<>();
        HashMap<String,Object> responseParams =new HashMap<>();
        log.info("Getting branch by id");
       Bank bank= bankRepository.findById(bankId).orElseThrow(
                () -> new ResourceNotFoundException("Bank", "id", bankId));
        Branch branch=branchRepository.findById(branchId).orElseThrow(
                () -> new ResourceNotFoundException("Branch", "id", branchId));
        if(branch.getBank().getId()!=bankId){
            throw new ResourceNotFoundException("Branch", "id", branchId);
        }
        return mapToDto(branch);
    }

    @Override
    public BranchDto updateBranch(Long bankId, Long branchId, BranchDto branchDto) {
        log.info("Updating branch by id");
        Bank bank= bankRepository.findById(bankId).orElseThrow(
                () -> new ResourceNotFoundException("Bank", "id", bankId));
        Branch branch=branchRepository.findById(branchId).orElseThrow(
                () -> new ResourceNotFoundException("Branch", "id", branchId));
        if(branch.getBank().getId()!=bankId){
            throw new ResourceNotFoundException("Branch", "id", branchId);
        }
        branch.setBranchName(branchDto.getBranchName());
        branch.setBranchCode(branchDto.getBranchCode());
        Branch updatedBranch=branchRepository.save(branch);
        return mapToDto(updatedBranch);
    }

    @Override
    public void deleteBranch(Long bankId, Long branchId) {
        log.info("Deleting branch by id");
        Bank bank= bankRepository.findById(bankId).orElseThrow(
                () -> new ResourceNotFoundException("Bank", "id", bankId));
        Branch branch=branchRepository.findById(branchId).orElseThrow(
                () -> new ResourceNotFoundException("Branch", "id", branchId));
        if(branch.getBank().getId()!=bankId){
            throw new ResourceNotFoundException("Branch", "id", branchId);
        }
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

