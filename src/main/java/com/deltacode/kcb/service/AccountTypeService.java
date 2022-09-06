package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.AccountTypeDto;
import com.deltacode.kcb.payload.AccountTypeResponse;
public interface AccountTypeService {
    AccountTypeDto createAccountType(AccountTypeDto accountTypeDto);
    AccountTypeResponse getAllAccountTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    AccountTypeDto getAccountTypesById(Long id);
    AccountTypeDto updateAccountTypes(AccountTypeDto accountTypeDto,Long id);
    void deleteAccountTypeById(Long id);
}
