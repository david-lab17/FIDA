package com.deltacode.kcb.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    @CreatedDate
    private Date createdDate;
//    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id",nullable = false)
    private Team team;
    private Boolean status=true;
    private Boolean gender;
}
