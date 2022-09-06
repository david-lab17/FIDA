package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.LiquidationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidationRepository extends JpaRepository<LiquidationType, Long> {
}

