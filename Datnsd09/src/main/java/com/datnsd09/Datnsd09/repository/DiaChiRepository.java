package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi,Long> {

    @Query(value = "select*from dia_chi where khach_hang_id = :idTaiKhoan order by trang_thai asc",nativeQuery = true)
    List<DiaChi> getAllByIdTaiKhoan(@Param("idTaiKhoan") Long id);

    @Query(value = "select*from dia_chi where trang_thai = :trangThai",nativeQuery = true)
    List<DiaChi> fillAllByTrangThai(@Param("trangThai") Integer trangThai);
}
