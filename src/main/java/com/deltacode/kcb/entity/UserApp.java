package com.deltacode.kcb.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "app_user_tb",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})})
@SQLDelete(sql = "UPDATE app_user_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String middleName;
    @Column(name="dob")
    private Date dateOfBirth;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    private Boolean deleted = Boolean.FALSE;
}
