package com.deltacode.kcb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team_tb",uniqueConstraints = {@UniqueConstraint(columnNames = {"teamName"}),
        @UniqueConstraint(columnNames = {"teamCode"})})
@SQLDelete(sql = "UPDATE team_tb SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Team  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    private String description;
    private Boolean status=true;
    private String teamCode;
    private String teamManager;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id",nullable = false)
    private Zone zone;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private Set<DSR> dsr;
    private Boolean deleted = Boolean.FALSE;

}

