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
@Table(name = "business_type_tb")
public class BusinessType {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @SQLDelete(sql = "UPDATE business_type_tb SET deleted = true WHERE id = ?")
    @Where(clause = "deleted = false")
    private Long id;
    private String businessTypeName;
    private Boolean status=true;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private Boolean deleted = Boolean.FALSE;
}
