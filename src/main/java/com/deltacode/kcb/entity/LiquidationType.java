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
        name = "liquidation_type_tb"

)
@SQLDelete(sql = "UPDATE liquidation_type_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class LiquidationType  {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String liquidationTypeName;
    private Boolean status=true;
    private Boolean deleted = Boolean.FALSE;
}
