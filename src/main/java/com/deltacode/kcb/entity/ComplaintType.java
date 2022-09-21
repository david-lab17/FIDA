package com.deltacode.kcb.entity;

import com.deltacode.kcb.utils.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "complaint_type_tb"

)
@SQLDelete(sql = "UPDATE complaint_type_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class ComplaintType extends Auditable<String> {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String complaintTypeName;
    private String description;
    private Boolean status=true;
    private Boolean deleted = Boolean.FALSE;
}

