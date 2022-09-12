package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Long> {

    List<Ward> findByConstituencyId(long constituencyId);
}
