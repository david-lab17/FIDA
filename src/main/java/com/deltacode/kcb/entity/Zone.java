package com.deltacode.kcb.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "zone_tb",uniqueConstraints = {@UniqueConstraint(columnNames = {"zoneName"})})
@SQLDelete(sql = "UPDATE zone_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zoneName;
    private String zoneCode;
    private String zoneDescription;
    private Boolean status=true;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Team> teams =new HashSet<>();
    private Boolean deleted = Boolean.FALSE;

}
