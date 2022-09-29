package com.deltacode.kcb.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utility {
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

}
