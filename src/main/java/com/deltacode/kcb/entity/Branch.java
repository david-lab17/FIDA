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
public class Branch extends Auditable<String> {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String branchName;
    private String branchCode;
    private Boolean status=true;
    private Boolean deleted = Boolean.FALSE;
}
