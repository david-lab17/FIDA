package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.UserAccType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccTypeRepository extends JpaRepository<UserAccType, Long> {

}
