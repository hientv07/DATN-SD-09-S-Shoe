package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.entity.NhanVien;

import java.util.List;

public interface KhachHangService {
    List<KhachHang> getAll();

    KhachHang save(KhachHang khachHang);

    List<KhachHang> getAllDangHoatDong();

    List<KhachHang> getAllNgungHoatDong();

    KhachHang add(KhachHang khachHang);

    KhachHang update(KhachHang khachHang);

    void remove(Long id);

    KhachHang getById(Long id);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String id,String ten);

    boolean checkTenTkTrungSua(Long id,String ten);

    boolean checkTenTaiKhoanTrung(String ten);

    boolean checkEmailSua(Long id,String email);

    boolean checkEmail(String email);

    void sendEmail(KhachHang taiKhoan, String path,String random);

    void sendEmailKhoiPhuc(KhachHang taiKhoan, String url);

    KhachHang getTaiKhoanByName(String name);

    String addUser(KhachHang userInfo);

    String updateUser(KhachHang userInfo);

    KhachHang findKhachLe();

    void addKhachLe();

    // Ngọc Hiếu
    void guiLieuHe(String hoTen,String email,String chuDe,String tinNhan);
}
