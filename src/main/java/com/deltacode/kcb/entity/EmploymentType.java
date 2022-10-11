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
@Table(name = "employmenttype_tb")
@SQLDelete(sql = "UPDATE employmenttype_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class EmploymentType {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String employmentTypeName;
    private Boolean status=true;
    private Boolean deleted = Boolean.FALSE;
}
