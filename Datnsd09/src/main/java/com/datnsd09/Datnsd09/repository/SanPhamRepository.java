package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham,Long> {

    @Query(value = "SELECT MAX(CONVERT(INT, SUBSTRING(ma_sp,3,10))) from san_pham",nativeQuery = true)
    Integer index();

    @Query(value = "SELECT * FROM san_pham WHERE LOWER(ten_sp) = LOWER(:name)",nativeQuery = true)
    SanPham findSanPhamByTen(@Param("name")String name);
}
