package com.deltacode.kcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "county_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"countyName"}),
                @UniqueConstraint(columnNames = {"countyCode"})}
)
public class County {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String countyName;
    private Integer  countyCode;
    private String description;
    private Boolean status=true;
    @CreatedDate
    private Date createdDate;
}
