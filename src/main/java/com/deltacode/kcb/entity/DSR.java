package com.deltacode.kcb.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "dsr_tb",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"idNumber"}),
        @UniqueConstraint(columnNames = {"email"})})
public class DSR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //staff number
    private String username;
    private Integer idNumber;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String middleName;
    private String dateOfBirth;
    private Boolean status=true;
    private String gender;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id",nullable = false)
    private Team team;

}
