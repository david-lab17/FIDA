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
        name = "accountType_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"accountTypeName"})}
)
public class AccountType {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String accountTypeName;
    private String accountTypeCode;
    private Boolean status=true;
    @CreatedDate
    private Date createdDate;
}

