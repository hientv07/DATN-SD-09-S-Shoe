package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
    Optional<KhachHang> findByTenTaiKhoan(String name);

    KhachHang findByMatKhau(String code);

    Optional<KhachHang> findByEmail(String email);


    @Query(value = "SELECT * FROM khach_hang  ",nativeQuery = true)
    List<KhachHang> fillAllKhachHang();

    @Query(value = "select * from khach_hang where trang_thai = 0 ",nativeQuery = true)
    List<KhachHang> fillAllDangHoatDong();

    @Query(value = "select * from khach_hang where trang_thai = 1 ",nativeQuery = true)
    List<KhachHang> fillAllNgungHoatDong();
}
