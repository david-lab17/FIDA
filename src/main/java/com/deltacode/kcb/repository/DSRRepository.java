package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.DSR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DSRRepository extends JpaRepository<DSR,Long> {

    List<DSR> findByTeamId(long teamId);
}
