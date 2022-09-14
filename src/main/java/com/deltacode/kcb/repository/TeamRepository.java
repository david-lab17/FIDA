package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {


    List<Team> findByZoneId(long zoneId);
}
