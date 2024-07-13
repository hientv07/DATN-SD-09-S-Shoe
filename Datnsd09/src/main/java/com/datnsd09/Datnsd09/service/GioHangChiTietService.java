package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.GioHangChiTiet;

import java.util.List;

public interface GioHangChiTietService {

    List<GioHangChiTiet> fillAllByIdGioHang(Long idGioHang);

    Integer soLuongSPGioHangCT(Long idGioHang);

    void deleteById(Long id);

    GioHangChiTiet fillById(Long id);

    GioHangChiTiet update(GioHangChiTiet gioHangChiTiet);
}
