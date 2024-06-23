package com.datnsd09.Datnsd09.service;

import java.util.Date;
import java.util.List;


public interface HoaDonChiTietService {

    //Thống kê bình
    //top sản phẩm bán chạy
    List<Object[]> findByTongSoLuongBetween(
            Date startDate,
            Date endDate);
    Integer sumSanPhamBanDuocBetween(Date startDate, Date endDate);

    List<Object[]> findByTongSoLuongBetweenGetAll();

    Integer sumSanPhamHoaDonAll();

    //kết thúc bình
}
