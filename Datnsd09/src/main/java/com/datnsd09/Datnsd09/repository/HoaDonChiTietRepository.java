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

    //Thống kê bình
    //top san pham ban chay chuyen ngay
    @Query("SELECT hdct.sanPhamChiTiet.sanPham.ten, SUM(hdct.soLuong), SUM(hdct.donGia) " +
            "FROM HoaDonChiTiet hdct " +
            "WHERE hdct.trangThai = 0 " +
            "AND (hdct.hoaDon.trangThai = 3 OR hdct.hoaDon.trangThai = 6) " +
            "AND CAST(hdct.hoaDon.ngayTao AS DATE) BETWEEN :startDate AND :endDate " +
            "GROUP BY hdct.sanPhamChiTiet.sanPham.ten " +
            "ORDER BY SUM(hdct.soLuong) DESC")
    List<Object[]> findByTongSoLuongBetween(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    @Query("SELECT COALESCE(SUM(hdct.soLuong), 0) " +
            "FROM HoaDonChiTiet hdct " +
            "JOIN hdct.hoaDon hd " +
            "WHERE hdct.trangThai = 0 " +
            "AND (hd.trangThai = 3 OR hd.trangThai = 6) " +
            "AND hd.ngayTao BETWEEN :startDate AND :endDate")
    Integer sumSanPhamBanDuocBetween(@Param("startDate") Date startDate,
                                     @Param("endDate") Date endDate);

    ///thong ke
    @Query("SELECT hdct.sanPhamChiTiet.sanPham.ten, SUM(hdct.soLuong), SUM(hdct.donGia) " +
            "FROM HoaDonChiTiet hdct " +
            "WHERE hdct.trangThai = 0 " +
            "AND (hdct.hoaDon.trangThai = 3 OR hdct.hoaDon.trangThai = 6) " +
            "GROUP BY hdct.sanPhamChiTiet.sanPham.ten " +
            "ORDER BY SUM(hdct.soLuong) DESC")
    List<Object[]> findByTongSoLuongBetweenGetAll();

    //get all tổng sp bán dc
    @Query("select SUM(hd.soLuong) " +
            "from HoaDonChiTiet hd " +
            "where hd.trangThai=0 " +
            "and (hd.hoaDon.trangThai = 3 or hd.hoaDon.trangThai = 6)")
    Integer sumSanPhamHoaDonAll();

    //kết thúc thống kê bình
}
