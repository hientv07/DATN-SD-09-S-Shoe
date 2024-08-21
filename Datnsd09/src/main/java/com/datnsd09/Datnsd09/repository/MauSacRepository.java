package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Long> {
    @Query(value = "select * from mau_sac where trang_thai = 0",nativeQuery = true)
    List<MauSac> fillAllDangHoatDong();

    @Query(value = "select * from mau_sac where trang_thai = 1",nativeQuery = true)
    List<MauSac> fillAllNgungHoatDong();

    @Query(value = "SELECT * FROM mau_sac WHERE LOWER(ten_mau) = LOWER(:name)", nativeQuery = true)
    MauSac findMauSacByTen(@Param("name") String name);
}
