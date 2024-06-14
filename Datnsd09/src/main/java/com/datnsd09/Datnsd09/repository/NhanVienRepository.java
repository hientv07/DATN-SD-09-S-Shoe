package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Long> {
    Optional<NhanVien> findByTenTaiKhoan(String name);

    NhanVien findByMatKhau(String code);

    NhanVien findByEmail(String email);

    @Query(value = "SELECT * FROM nhan_vien WHERE vai_tro_id = 1 ORDER BY ngay_sua DESC",nativeQuery = true)
    List<NhanVien> fillAllNhanVien();

    @Query(value = "select * from nhan_vien where trang_thai = 0 and vai_tro_id = 1",nativeQuery = true)
    List<NhanVien> fillAllDangHoatDong();

    @Query(value = "select * from nhan_vien where trang_thai = 1 and vai_tro_id = 1",nativeQuery = true)
    List<NhanVien> fillAllNgungHoatDong();

}
