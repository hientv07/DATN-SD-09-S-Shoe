package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
//    @Query("SELECT h.chiTietSanPham.sanPham.ten, SUM(h.soLuong),SUM(h.donGia)" +
//            "FROM HoaDonChiTiet h " +
//            "WHERE h.trangThai = 0 and (h.hoaDon.trangThai = 3 or h.hoaDon.trangThai = 6) and CAST(h.hoaDon.ngayTao AS DATE) BETWEEN :startDate AND :endDate " +
//            "GROUP BY h.chiTietSanPham.sanPham.ten " +
//            "ORDER BY SUM(h.soLuong) DESC ")
//    List<Object[]> findByTongSoLuongBetween(
//            @Param("startDate") Date startDate,
//            @Param("endDate") Date endDate);
}
