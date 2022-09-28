package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.AccountType;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.AccountTypeDto;
import com.deltacode.kcb.payload.AccountTypeResponse;
import com.deltacode.kcb.repository.AccountTypeRepository;
import com.deltacode.kcb.service.AccountTypeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j

@Service
public class AccountTypeImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final ModelMapper modelMapper;

    public AccountTypeImpl(AccountTypeRepository accountTypeRepository, ModelMapper modelMapper) {
        this.accountTypeRepository = accountTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountTypeDto createAccountType(AccountTypeDto accountTypeDto) {
        log.info("Creating account type");
        //convert Dto to entity
        AccountType accountType = mapToEntity(accountTypeDto);
        AccountType newAccountType = accountTypeRepository.save(accountType);
        //convert entity to Dto
        return mapToDto(newAccountType);
    }

    @Override
    public AccountTypeResponse getAllAccountTypes(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all account types");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        org.springframework.data.domain.Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        //create a pageable instance
        Page<AccountType> accountTypes=accountTypeRepository.findAll(pageable);
        //get content for page object
        List<AccountType> listOfAccountType = accountTypes.getContent();
        List<AccountTypeDto> content = listOfAccountType.stream().map(this::mapToDto).toList();
        AccountTypeResponse accountTypeResponse =new AccountTypeResponse();
        accountTypeResponse.setContent(content);
        accountTypeResponse.setPageNo(accountTypes.getNumber());
        accountTypeResponse.setPageSize(accountTypes.getSize());
        accountTypeResponse.setTotalElements(accountTypes.getNumberOfElements());
        accountTypeResponse.setTotalPages(accountTypes.getTotalPages());
        accountTypeResponse.setLast(accountTypes.isLast());
        return accountTypeResponse;
    }



    @Override
    public AccountTypeDto getAccountTypesById(Long id) {
        log.info("Getting account type by id = {}", id);
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("AccountType", "id", id));
        return mapToDto(accountType);
    }

    @Override
    public AccountTypeDto updateAccountTypes(AccountTypeDto accountTypeDto, Long id) {
        log.info("Updating account type by id = {}", id);
        //check if accountType exists
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("AccountType", "id", id));
        //update accountType
        accountType.setAccountTypeName(accountTypeDto.getAccountTypeName());
        accountType.setAccountTypeCode(accountTypeDto.getAccountTypeCode());
        accountType.setStatus(accountTypeDto.isStatus());
        AccountType updatedAccountType = accountTypeRepository.save(accountType);
        mapToDto(updatedAccountType);
        return null;

    }


    @Override
    public void deleteAccountTypeById(Long id) {
        log.info("Deleting account type by id = {}", id);
        AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("AccountType", "id", id));
        accountTypeRepository.delete(accountType);

    }
    //covert Dto to entity
    private AccountTypeDto mapToDto(AccountType accountType){

        return modelMapper.map(accountType, AccountTypeDto .class);

    }
    //convert entity to Dto
    private AccountType mapToEntity(AccountTypeDto accountTypeDto){
        return modelMapper.map(accountTypeDto, AccountType.class);
    }
}
