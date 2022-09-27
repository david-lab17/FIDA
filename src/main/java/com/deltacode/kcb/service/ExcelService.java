package com.deltacode.kcb.service;

import com.deltacode.kcb.entity.BusinessType;
import com.deltacode.kcb.entity.UserApp;
import com.deltacode.kcb.helper.ExcelHelper;
import com.deltacode.kcb.payload.BusinessTypeDto;
import com.deltacode.kcb.payload.UserAppDto;
import com.deltacode.kcb.payload.UserAppResponse;
import com.deltacode.kcb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExcelService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;


    public void save(MultipartFile file) {
        try {
            List<UserApp> users = ExcelHelper.excelToUsers(file.getInputStream());
            userRepository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    //    public List<UserApp> getAllUsers() {
//        return userRepository.findAll();
//    }
    public UserAppResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<UserApp> user = userRepository.findAll(pageable);
        //get content from pageable instance
        List<UserApp> users = user.getContent();
        List<UserAppDto> content = users.stream().map(this::mapToDto).collect(Collectors.toList());
        UserAppResponse userAppResponse = new UserAppResponse();
        userAppResponse.setContent(content);
        userAppResponse.setTotalElements((int) user.getTotalElements());
        userAppResponse.setTotalPages(user.getTotalPages());
        userAppResponse.setPageNo(user.getNumber());
        userAppResponse.setPageSize(user.getSize());
        return userAppResponse;


    }
    //update user
    public UserAppDto updateUser(UserAppDto userAppDto, Long id) {
        log.info("Updating user by id {}", id);
        UserApp userApp = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userApp.setFirstName(userAppDto.getFirstName());
        userApp.setLastName(userAppDto.getLastName());
        userApp.setEmail(userAppDto.getEmail());
        userApp.setPhoneNumber(userAppDto.getPhoneNumber());
        userApp.setUsername(userAppDto.getUsername());
        userRepository.save(userApp);
        return mapToDto(userApp);
    }
    //delete user
    public void deleteUserById(Long id) {
        log.info("Deleting User by id {}", id);
        UserApp userApp = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userApp);


    }

    private UserAppDto mapToDto(UserApp userApp) {

        return modelMapper.map(userApp, UserAppDto.class);

    }
}
