package com.deltacode.kcb.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "privileges_tb")
@SQLDelete(sql = "UPDATE privileges_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    private String name;
    private Boolean deleted = Boolean.FALSE;
}
