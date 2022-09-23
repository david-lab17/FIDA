package com.deltacode.kcb.controller;

import com.deltacode.kcb.entity.UserApp;
import com.deltacode.kcb.helper.ExcelHelper;
import com.deltacode.kcb.message.ResponseMessage;
import com.deltacode.kcb.payload.UserAppResponse;
import com.deltacode.kcb.service.ExcelService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value = "Upload User  Rest Api")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/excel")


public class ExcelController {
    @Autowired
    ExcelService excelService;
    @ApiOperation(value = "Upload Users Rest  Api")
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


//    @GetMapping("/users")
//    public ResponseEntity<List<UserApp>> getAllUsers() {
//        try {
//            List<UserApp> userApps = excelService.getAllUsers();
//
//            if (userApps.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(userApps, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
@ApiOperation(value = "Fetching all Users  Api")
@GetMapping("/users")
public UserAppResponse getAllUsers(
        @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
        @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
        @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
        @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
){
    return excelService.getAllUsers(pageNo,pageSize,sortBy,sortDir);
}
}
