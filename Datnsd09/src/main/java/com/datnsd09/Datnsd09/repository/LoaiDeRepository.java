package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.LoaiDe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiDeRepository extends JpaRepository<LoaiDe,Long> {
    @Query(value = "select * from loai_de where trang_thai = 0",nativeQuery = true)
    List<LoaiDe> fillAllDangHoatDong();

    @Query(value = "select * from loai_de where trang_thai = 1",nativeQuery = true)
    List<LoaiDe> fillAllNgungHoatDong();

    @Query(value = "SELECT * FROM loai_de WHERE LOWER(ten_ld) = LOWER(:name)", nativeQuery = true)
    LoaiDe findDeGiayByTen(@Param("name") String name);
}
