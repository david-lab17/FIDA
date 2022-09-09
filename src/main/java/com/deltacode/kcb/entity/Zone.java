package com.deltacode.kcb.entity;

import com.deltacode.kcb.payload.TeamDto;
import com.fasterxml.jackson.databind.Module;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "zone_tb",uniqueConstraints = {@UniqueConstraint(columnNames = {"zoneName"})})
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

}
