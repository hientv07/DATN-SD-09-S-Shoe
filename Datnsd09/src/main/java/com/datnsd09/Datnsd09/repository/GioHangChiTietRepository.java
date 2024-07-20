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

    @Query(value = "SELECT top 1 * FROM gio_hang_chi_tiet where chi_tiet_san_pham_id = :idCTSP",nativeQuery = true)
    GioHangChiTiet fillByIdCTSP(@Param("idCTSP")Long idCTSP);

    @Query(value = "select * from gio_hang_chi_tiet where gio_hang_id = :idGioHang  and chi_tiet_san_pham_id IN (:idChiTetSP)",nativeQuery = true)
    List<GioHangChiTiet> getByGioHangChiTiet(@Param("idGioHang")Long idGioHang,@Param("idChiTetSP")List<String> idChiTetSP);

    @Query(value = "SELECT * FROM gio_hang_chi_tiet WHERE id_ghct IN (:listId)\n" +
            "AND gio_hang_id = :idGioHang",nativeQuery = true)
    List<GioHangChiTiet> findAllByIdGHCT(@Param("listId")List<Long> listId,@Param("idGioHang")Long idGioHang);
}
