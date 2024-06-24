package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.DiaChi;

import java.util.List;

public interface DiaChiService {
    List<DiaChi> getAll();

    List<DiaChi> getAllByTaiKhoan(Long id);

    void deleteById(Long id);

    DiaChi save(DiaChi diaChi);

    boolean checkTenTrung(String ten,Long idTaiKhoan);

    boolean checkTenTrungSua(Long idDiaChi, String ten,Long idTaiKhoan);

    DiaChi update(DiaChi diaChi);

    DiaChi getById(Long id);

    List<DiaChi> getAllTrangThai(Integer trangThai);

}
