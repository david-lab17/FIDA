package com.deltacode.kcb.service;

import com.deltacode.kcb.entity.UserApp;
import com.deltacode.kcb.helper.ExcelHelper;
import com.deltacode.kcb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class ExcelService {
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
}
