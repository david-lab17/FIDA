package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.AccountType;
import com.deltacode.kcb.entity.LiquidationType;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.AccountTypeDto;
import com.deltacode.kcb.payload.AccountTypeResponse;
import com.deltacode.kcb.payload.LiquidationTypeDto;
import com.deltacode.kcb.repository.AccountTypeRepository;
import com.deltacode.kcb.service.AccountTypeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
        List<AccountTypeDto> content = listOfAccountType.stream().map(accountType -> mapToDto(accountType)).collect(Collectors.toList());
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
    public ResponseEntity<?> updateAccountTypes(AccountTypeDto accountTypeDto, Long id) {
        HashMap<String, Object> responseObject = new HashMap<>();
        HashMap<String, Object> responseParams = new HashMap<>();
        try {
            log.info("Updating liquidation type with id = {}", id);
            AccountType accountType = accountTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LiquidationType", "id", id));
            accountType.setAccountTypeName(accountTypeDto.getAccountTypeName());
            accountType.setAccountTypeName(accountTypeDto.getAccountTypeName());
            accountTypeRepository.save(accountType);

            responseObject.put("status", "success");
            responseObject.put("message", "Liquidation type "
                    +accountTypeDto.getAccountTypeName()+" successfully updated");
            responseObject.put("data", responseParams);
            //convert entity to Dto
//            mapToDto(newAccType);
            return ResponseEntity.ok(responseObject);
        } catch (Exception e) {
            log.error("Error updating Acc  type", e);
            responseObject.put("status", "error");
            responseObject.put("message", e.getMessage());
            responseParams.put("accType", null);
            responseObject.put("params", responseParams);
            modelMapper.map(responseObject, AccountTypeDto.class);
            return ResponseEntity.ok(responseObject);
        }

    }


    @Override
    public ResponseEntity<?> deleteAccountTypeById(Long id) {
        HashMap<String,Object> responseObject = new HashMap<>();
        HashMap<String,Object> responseParams = new HashMap<>();

        try {
            Optional<AccountType> optionalAccountType = accountTypeRepository.findById(id);
            AccountType accountType = optionalAccountType.get();
            accountTypeRepository.delete(accountType);
            responseObject.put("status", "success");
            responseObject.put("message", "Account type "
                    +accountType.getAccountTypeName()+" successfully deleted");
            responseObject.put("data", responseParams);
            return ResponseEntity.ok(responseObject);
        } catch (Exception e) {
            responseObject.put("status", "failed");
            responseObject.put("message", e.getMessage());
            return ResponseEntity.ok().body(responseObject);
        }


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
