//package com.deltacode.kcb.service.impl;
//
//import com.deltacode.kcb.repository.OTPRepository;
//import com.deltacode.kcb.service.SmsService;
//import com.deltacode.kcb.utils.Utility;
//import com.google.gson.JsonObject;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.env.Environment;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.LinkedHashMap;
//import java.util.Objects;
//import java.util.logging.Logger;
//
//@Service
//@RequiredArgsConstructor
//public class SmsServiceImpl implements SmsService {
//    private final static Logger LOGGER = Logger.getLogger(SmsServiceImpl.class.getName());
//    @Resource
//    public Environment environment;
//
//    private final OTPRepository otpRepository;
//     private final UserDetails userDetails;
//
//    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
//
//    @Override
//    public ResponseEntity<?> sendOTP(String phoneNo, HttpServletRequest httpServletRequest) {
//        LinkedHashMap<String, Object> responseObject = new LinkedHashMap<>();
//        try {
//            //get user details
//
//
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<?> sendValidateOtp(int otpNumber, HttpServletRequest httpServletRequest) {
//        return null;
//    }
//
//}
