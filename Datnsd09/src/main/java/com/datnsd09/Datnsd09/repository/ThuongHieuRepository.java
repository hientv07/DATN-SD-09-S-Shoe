package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu,Long> {
}
