package com.deltacode.kcb.repository;

import com.deltacode.kcb.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone findByZoneCode(String zoneCode);

}
