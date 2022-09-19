package com.deltacode.kcb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "roles_tb")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    private String name;
    private String description;
//    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinTable(name = "role_privileges",joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
//    private Set<Privileges> privileges;

}
