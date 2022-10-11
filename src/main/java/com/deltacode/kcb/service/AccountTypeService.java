package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.AccountTypeDto;
import com.deltacode.kcb.payload.AccountTypeResponse;
import org.springframework.http.ResponseEntity;

public interface AccountTypeService {
    AccountTypeDto createAccountType(AccountTypeDto accountTypeDto);
    AccountTypeResponse getAllAccountTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    AccountTypeDto getAccountTypesById(Long id);
    ResponseEntity<?> updateAccountTypes(AccountTypeDto accountTypeDto, Long id);
    ResponseEntity<?> deleteAccountTypeById(Long id);
}
