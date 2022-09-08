package com.deltacode.kcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "branches_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"branchCode"})}
)
public class Branch {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String branchName;
    private String branchCode;
    private Boolean status=true;
    @CreationTimestamp
    private LocalDateTime createdDate;
}
