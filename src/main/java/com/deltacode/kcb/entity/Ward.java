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
        name = "ward_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"wardName"}),
                @UniqueConstraint(columnNames = {"wardCode"})}
)
@SQLDelete(sql = "UPDATE ward_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Ward  {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String wardName;
    private String  wardCode;
    private String description;
    private Boolean status=true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constituency_id",nullable = false)
    private Constituency constituency;
    private Boolean deleted = Boolean.FALSE;
}
