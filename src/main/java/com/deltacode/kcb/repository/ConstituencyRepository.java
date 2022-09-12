package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConstituencyRepository extends JpaRepository<Constituency, Long> {

    List<Constituency> findByCountyId(long countyId);
}
