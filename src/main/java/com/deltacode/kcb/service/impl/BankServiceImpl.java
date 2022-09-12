package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Bank;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.BankDto;
import com.deltacode.kcb.payload.BankResponse;
import com.deltacode.kcb.repository.BankRepository;
import com.deltacode.kcb.service.BankService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    private  final BankRepository bankRepository;
    private  final ModelMapper modelMapper;

    public BankServiceImpl(BankRepository bankRepository, ModelMapper modelMapper) {
        this.bankRepository = bankRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public BankDto createBank(BankDto bankDto) {
        //convert Dto to entity
        Bank bank = mapToEntity(bankDto);
        Bank newBank = bankRepository.save(bank);
        //convert entity to Dto
        return mapToDto(newBank);

    }


    @Override
    public BankResponse getAllBanks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        org.springframework.data.domain.Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        //create a pageable instance
        Page<Bank> banks=bankRepository.findAll(pageable);
        //get content for page object
        List<Bank> listOfBank = banks.getContent();
        List<BankDto> content = listOfBank.stream().map(this::mapToDto).toList();
        BankResponse bankResponse =new BankResponse();
        bankResponse.setContent(content);
        bankResponse.setPageNo(banks.getNumber());
        bankResponse.setPageSize(banks.getSize());
        bankResponse.setTotalElements(banks.getNumberOfElements());
        bankResponse.setTotalPages(banks.getTotalPages());
        bankResponse.setLast(banks.isLast());
        return bankResponse;


    }

    @Override
    public BankDto getBankById(Long id) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bank", "id", id));
        return mapToDto(bank);
    }

    @Override
    public BankDto updateBank(BankDto bankDto, Long id) {
          Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bank", "id", id));
            bank.setBankName(bankDto.getBankName());
            bank.setBankCode(bankDto.getBankCode());
            Bank updatedBank = bankRepository.save(bank);
            return mapToDto(updatedBank);
    }

    @Override
    public void deleteBankById(Long id) {
        //check if bank exists
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bank", "id", id));
        bankRepository.delete(bank);
    }

    //convert entity to dto
    private BankDto  mapToDto(Bank bank) {

        return modelMapper.map(bank, BankDto .class);

    }
    //convert dto to entity
    private Bank mapToEntity(BankDto bankDto) {

        return modelMapper.map(bankDto, Bank.class);

    }
}
