package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.ComplaintType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintTypeRepository extends JpaRepository<ComplaintType, Long> {

}
