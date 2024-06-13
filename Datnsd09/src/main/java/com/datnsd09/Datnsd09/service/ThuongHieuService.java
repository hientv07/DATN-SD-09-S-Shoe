package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.ThuongHieu;

import java.util.List;

public interface ThuongHieuService {
    List<ThuongHieu> getAll();

    List<ThuongHieu> getAllDangHoatDong();

    List<ThuongHieu> getAllDungHoatDong();

    void deleteById(Long id);

    ThuongHieu add(ThuongHieu thuongHieu);

    ThuongHieu update(ThuongHieu thuongHieu);

    ThuongHieu getById(Long id);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(Long id, String ten);

}
