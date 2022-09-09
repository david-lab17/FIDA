package com.deltacode.kcb.entity;

import com.deltacode.kcb.payload.DSRDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "zone_tb",uniqueConstraints = {@UniqueConstraint(columnNames = {"zoneName"}),
        @UniqueConstraint(columnNames = {"zoneCode"})})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    private String description;
    private Boolean status=true;
    private String teamCode;
    private String teamManager;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id",nullable = false)
    private Zone zone;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private Set<DSR> dsr;
}

