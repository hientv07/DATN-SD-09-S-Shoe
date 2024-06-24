package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,Long> {
    //thống kê bình
    //đếm số hóa đơn
    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate AND ( hd.trangThai = 3 OR  hd.trangThai = 6) ")
    Integer countHoaDonBetween(@Param("startDate") Date startDate,
                               @Param("endDate") Date endDate);

    //tính tổng tiền hóa đơn
    @Query("SELECT COALESCE(SUM(hd.tongTienKhiGiam), 0) FROM HoaDon hd WHERE (hd.trangThai=3 or hd.trangThai=6) and CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate ")
    Long sumGiaTriHoaDonBetween(@Param("startDate") Date startDate,
                                @Param("endDate") Date endDate);

    //trạng thái của các hóa đơn
    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE hd.trangThai = :trangThai and CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate ")
    Integer countHoaDonTrangThaiBetween(@Param("startDate") Date startDate,
                                        @Param("endDate") Date endDate,
                                        @Param("trangThai") Integer trangThai);
    //tổng hoa don
    @Query("SELECT COUNT(hd) FROM HoaDon hd where hd.trangThai = 3 or hd.trangThai = 6 ")
    Integer countHoaDonAll();
    @Query("SELECT SUM(hd.tongTienKhiGiam) FROM HoaDon hd WHERE hd.trangThai = 3 or hd.trangThai = 6 ")
    Long sumGiaTriHoaDonAll();

    //trang thai hoa don
    @Query("select COUNT(hd) from HoaDon hd where hd.trangThai = :trangThai")
    Integer countHoaDon(@Param("trangThai") Integer trangThai);
    //kết thúc thống kê


    // Ngọc Hiếu
    @Query(value = "select hoa_don.id_hd from hoa_don where trang_thai = 3", nativeQuery = true)
    List<Long> fillAllIdHoaDonTrangThaiHoanThanh();
}
