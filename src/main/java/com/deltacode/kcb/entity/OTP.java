//package com.deltacode.kcb.entity;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.util.Date;
//
//public class OTP {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name="otp_number")
//    private int otpNumber;
//    @Column(name="recipient")
//    private String recipient;
//    private boolean active;
//    @Column(name="created_on")
//    private Date createdOn;
//    @Column(name="updated_on")
//    private Date updatedOn;
//    @Column(name = "created_by")
//    private Long createdBy;
//    @Column(name = "updated_by")
//    private Long updatedBy;
//}
