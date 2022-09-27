package com.deltacode.kcb.entity;

import com.deltacode.kcb.utils.Auditable;
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
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "banks_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"bankCode"})}
)
@SQLDelete(sql = "UPDATE banks_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Bank extends Auditable<String> {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String bankName;
    private String bankCode;
    private Boolean status=true;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Branch> branches =new HashSet<>();
    private Boolean deleted = Boolean.FALSE;
}