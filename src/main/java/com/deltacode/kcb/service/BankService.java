package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.BankDto;
import com.deltacode.kcb.payload.BankResponse;

public interface BankService {
    BankDto createBank(BankDto bankDto);
    BankResponse getAllBanks(int pageNo, int pageSize, String sortBy, String sortDir );
    BankDto getBankById(Long id);
    BankDto updateBank(BankDto bankDto,Long id);
    void deleteBankById(Long id);
}