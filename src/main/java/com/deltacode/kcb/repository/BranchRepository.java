package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findAllByBankId(Long bankId);
}

