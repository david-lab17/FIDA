//package com.deltacode.kcb.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "privileges_tb")
//
//public class Privileges {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    @ManyToMany(mappedBy = "privileges")
//    private Collection<Role> roles;
//}
