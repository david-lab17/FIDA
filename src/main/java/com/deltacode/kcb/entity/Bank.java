package com.deltacode.kcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "banks_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"bankCode"})}
)
public class Bank {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String bankName;
    private String bankCode;
    private Boolean status=true;
    @CreatedDate
    private Date createdDate;
}