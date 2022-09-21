package com.deltacode.kcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "accountType_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"accountTypeName"})}
)
@SQLDelete(sql = "UPDATE accountType_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class AccountType {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String accountTypeName;
    private String accountTypeCode;
    private Boolean status=true;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private Boolean deleted = Boolean.FALSE;
}

