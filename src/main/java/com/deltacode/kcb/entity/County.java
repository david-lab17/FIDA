package com.deltacode.kcb.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "county_tb",uniqueConstraints = {@UniqueConstraint(columnNames = {"countyName"}),
        @UniqueConstraint(columnNames = {"countyCode"})})
//@SQLDelete(sql = "UPDATE county_tb SET deleted = true WHERE id = ?")
//@Where(clause = "deleted = false")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String countyName;
    private String countyCode;
    private String description;
    @Column(nullable = false)
    private Boolean status=true;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "county", cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<Constituency> constituencies =new HashSet<>();
//    private Boolean deleted = Boolean.FALSE;
}
