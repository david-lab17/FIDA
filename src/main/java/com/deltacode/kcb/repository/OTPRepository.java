//package com.deltacode.kcb.repository;
//
//import com.deltacode.kcb.entity.OTP;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface OTPRepository extends JpaRepository<OTP, Long> {
//
//    Optional<OTP> findByRecipientAndOtpNumberAndActiveTrue(String recipient, int otpNumber);
//    List<OTP> findAllByActiveTrue();
//}
//
