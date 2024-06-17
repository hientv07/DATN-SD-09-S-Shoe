package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.SanPham;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAll();

    SanPham getById(Long id);

    SanPham add(SanPham sanPham);

    SanPham update(SanPham sanPham);

    void delete(Long id);

    Integer genMaTuDong();

    List<SanPham> getAllDangHoatDong();

    List<SanPham> getAllNgungHoatDong();

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String ma, String ten);
}
