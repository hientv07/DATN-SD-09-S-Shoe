package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {

//    @Query(value = "SELECT COUNT(id) FROM gio_hang_chi_tiet where gio_hang_id = :idGioHang and trang_thai = 0",nativeQuery = true)
//    Integer soLuongSpTrongGioHangCT(@Param("idGioHang")Long idGioHang);

}
