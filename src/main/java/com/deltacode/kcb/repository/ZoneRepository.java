package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone findByZoneCode(String zoneCode);

}
