package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham,Long> {
}
