package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo,Long> {

    @Query(value = "select * from kich_co where trang_thai = 0", nativeQuery = true)
    List<KichCo> fillAllDangHoatDong();

    @Query(value = "select * from kich_co where trang_thai = 1", nativeQuery = true)
    List<KichCo> fillAllNgungHoatDong();

    @Query(value = "SELECT * FROM kich_co WHERE LOWER(ten_kich_co) = LOWER(:name)",nativeQuery = true)
    KichCo findKichCoByTen(@Param("name") Integer name);
}
