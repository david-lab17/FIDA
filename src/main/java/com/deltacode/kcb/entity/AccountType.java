package com.deltacode.kcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "accounttype_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"accountTypeName"})}
)
@SQLDelete(sql = "UPDATE accounttype_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class AccountType  {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String accountTypeName;
    private String accountTypeCode;
    private Boolean status=true;
    private Boolean deleted = Boolean.FALSE;
}

