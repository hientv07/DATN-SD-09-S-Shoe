package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.entity.NhanVien;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
    Optional<KhachHang> findByTenTaiKhoan(String name);

    KhachHang findByMatKhau(String code);

    Optional<KhachHang> findByEmail(String email);

    @Query(value = "select top(1) * from khach_hang where ho_va_ten =N'Khách lẻ'", nativeQuery = true)
    KhachHang findKhachLe();

    @Transactional
    @Modifying
    @Query(value = "INSERT into khach_hang ( ho_va_ten, ngay_sinh, gioi_tinh, so_dien_thoai, email, ten_tai_khoan, mat_khau, ngay_tao, ngay_sua, trang_thai, ten_vai_tro) values ( N'Khách lẻ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -1, NULL)", nativeQuery = true)
    void addKhachLe();

    @Query(value = "SELECT * FROM khach_hang  ",nativeQuery = true)
    List<KhachHang> fillAllKhachHang();

    @Query(value = "select * from khach_hang where trang_thai = 0 ",nativeQuery = true)
    List<KhachHang> fillAllDangHoatDong();

    @Query(value = "select * from khach_hang where trang_thai = 1 ",nativeQuery = true)
    List<KhachHang> fillAllNgungHoatDong();
}
