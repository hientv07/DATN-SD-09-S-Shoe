package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {

    //bám Giỏ hàng lấy tất cả bản ghi theo id gio hang getfi
    @Query(value = "select * from gio_hang_chi_tiet where gio_hang_id = :idGioHang\n" +
            "                       and trang_thai = 0 order by ngay_sua desc",nativeQuery = true)
    List<GioHangChiTiet> findAllByIdGioHang(@Param("idGioHang")Long idGioHang);

    @Query(value = "SELECT COUNT(id_ghct) FROM gio_hang_chi_tiet \n" +
            "where id_ghct = :idGioHang and trang_thai = 0",nativeQuery = true)
    Integer soLuongSpTrongGioHangCT(@Param("idGioHang")Long idGioHang);

}
