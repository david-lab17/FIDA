package com.deltacode.kcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "branches_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"branchCode"})}
)
@SQLDelete(sql = "UPDATE branches_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Branch  {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String branchName;
    private String branchCode;
    private Boolean status=true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id",nullable = false)
    private Bank bank;
    private Boolean deleted = Boolean.FALSE;
}
