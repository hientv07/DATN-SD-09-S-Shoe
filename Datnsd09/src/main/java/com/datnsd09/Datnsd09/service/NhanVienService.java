package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.NhanVien;

import java.util.List;

public interface NhanVienService {

//    String addUser(NhanVien userInfo);
//
//    String updateUser(NhanVien userInfo);
//
//    void sendEmail(NhanVien taiKhoan, String path);
//
//    void sendEmail1(NhanVien taiKhoan, String url, String random);
//
//    boolean verifyAccount(String verificationPassWord, String resetPass);
//
//    NhanVien saveUser(NhanVien user, String url);
//
//    NhanVien getNhanVienByName(String name);

    List<NhanVien> getAll();

    List<NhanVien> getAllDangHoatDong();

    List<NhanVien> getAllNgungHoatDong();

    NhanVien add(NhanVien sanPham);

    NhanVien update(NhanVien sanPham);

    void remove(Long id);

    NhanVien getById(Long id);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String id,String ten);

    boolean checkTenTkTrungSua(Long id,String ten);

    boolean checkTenTaiKhoanTrung(String ten);

    boolean checkEmailSua(Long id,String email);

    boolean checkEmail(String email);

    void sendEmail(NhanVien taiKhoan, String path,String random);
}
