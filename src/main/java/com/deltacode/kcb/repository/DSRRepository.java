package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.DSR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DSRRepository extends JpaRepository<DSR,Long> {

    List<DSR> findByTeamId(long teamId);
}
