package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends JpaRepository<County,Long> {
}

