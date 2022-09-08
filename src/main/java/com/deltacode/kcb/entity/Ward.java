package com.deltacode.kcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "ward_tb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"wardName"}),
                @UniqueConstraint(columnNames = {"wardCode"})}
)
public class Ward {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String wardName;
    private Integer  wardCode;
    private String constituencyCode;
    private String description;
    private Boolean status=true;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constituency_id",nullable = false)
    private Constituency constituency;
}
