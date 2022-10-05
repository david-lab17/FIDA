package com.deltacode.kcb.utils;


import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Utility {
    private Logger log = LoggerFactory.getLogger(getClass());
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
//validate Status
    public static boolean validateStatus(String status) throws Exception{
        status = status.trim();
        ArrayList<String> arrValidStatus = new ArrayList<>();
        arrValidStatus.add("A");
        arrValidStatus.add("I");
        arrValidStatus.add("D");
        return arrValidStatus.contains(status);
    }
    public static Date getPostgresCurrentTimeStampForInsert() throws Exception{
        SimpleDateFormat formatter1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new Date();
        return formatter1.parse(formatter1.format(date));
    }

  public static int generateOtp(){
        Random rand = new Random();
        int randInt = rand.nextInt(10000);
        return  randInt;
    }

//    public static String generateUniqueNoByDate(){
//        SimpleDateFormat formatter1 = new SimpleDateFormat ("ddMMyy");
//        String unique = formatter1.format(new java.util.Date())	+
//                RandomStringUtils.random(10, false, true);
//        return unique;
//    }

    public static String generateUniqueNoByDate(){
        SimpleDateFormat formatter1 =new SimpleDateFormat("ddMMyy");
        String unique =formatter1.format(new Date()) + RandomStringUtils.random(10,false,true);
        return unique;
    }

}
