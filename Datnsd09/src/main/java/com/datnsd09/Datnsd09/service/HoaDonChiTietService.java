package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.HoaDon;
import com.datnsd09.Datnsd09.entity.HoaDonChiTiet;

import java.util.Date;
import java.util.List;


public interface HoaDonChiTietService {

    List<HoaDonChiTiet> findAll();

    HoaDonChiTiet findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(HoaDonChiTiet hoaDonChiTiet);

    List<HoaDonChiTiet> findByIdHoaDon(Long idHoaDon);

    //Thống kê bình
    //top sản phẩm bán chạy
    List<Object[]> findByTongSoLuongBetween(
            Date startDate,
            Date endDate);
    Integer sumSanPhamBanDuocBetween(Date startDate, Date endDate);

    List<Object[]> findByTongSoLuongBetweenGetAll();

    Integer sumSanPhamHoaDonAll();

    List<Object[]> thongKeSanPhamTheoNgay(
            Date startDateChart,
            Date endDateChart
    );

    //kết thúc bình

    // Ngọc Hiếu : Sản Phẩm ở client
    List<HoaDonChiTiet> finTop5HDCT();
}
