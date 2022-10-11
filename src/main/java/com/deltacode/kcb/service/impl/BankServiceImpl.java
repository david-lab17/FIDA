package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Bank;
import com.deltacode.kcb.entity.LiquidationType;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.BankDto;
import com.deltacode.kcb.payload.BankResponse;
import com.deltacode.kcb.payload.LiquidationTypeDto;
import com.deltacode.kcb.repository.BankRepository;
import com.deltacode.kcb.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j

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
        log.info("Creating bank");
        //convert Dto to entity
        Bank bank = mapToEntity(bankDto);
        Bank newBank = bankRepository.save(bank);
        //convert entity to Dto
        return mapToDto(newBank);

    }


    @Override
    public BankResponse getAllBanks(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all banks");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        org.springframework.data.domain.Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        //create a pageable instance
        Page<Bank> banks=bankRepository.findAll(pageable);
        //get content for page object
        List<Bank> listOfBank = banks.getContent();
        List<BankDto> content = listOfBank.stream().map(bank -> mapToDto(bank)).collect(Collectors.toList());
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
        log.info("Getting bank by id = {}",id);
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bank", "id", id));
        return mapToDto(bank);
    }

    @Override
    public ResponseEntity<?> updateBank(BankDto bankDto, Long id) {
        HashMap<String, Object> responseObject = new HashMap<>();
        HashMap<String, Object> responseParams = new HashMap<>();
        try {
            log.info("Updating bank with id = {}",id);
            //check if bank exists with id
            Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LiquidationType", "id", id));
//            Bank updatedBank = mapToEntity(bankDto);//convert dto to entity
            Bank newBank = bankRepository.save(bank);//save updated bank
            responseObject.put("status", "success");//set status
            responseObject.put("message", "bank "
                    +bankDto.getBankName()+" successfully updated");//set message
            responseObject.put("data", responseParams);//set data
            //convert entity to Dto
            mapToDto(newBank);//convert entity to Dto
            return ResponseEntity.ok(responseObject);//return response
        } catch (Exception e) {
            log.error("Error updating County ", e);
            responseObject.put("status", "failed");
            responseObject.put("message", e.getMessage());
            return ResponseEntity.ok().body(responseObject);
        }
    }

    @Override
    public void deleteBankById(Long id) {
        log.info("Deleting bank with id = {}",id);
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
