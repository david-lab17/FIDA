package com.deltacode.kcb.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dsr_tb",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"idNumber"}),
        @UniqueConstraint(columnNames = {"email"})})
@SQLDelete(sql = "UPDATE dsr_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class DSR   {
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id",nullable = false)
    private Team team;
    private Boolean deleted = Boolean.FALSE;

}
