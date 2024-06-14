package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,Long> {
}
