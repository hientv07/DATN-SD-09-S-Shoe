package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.HoaDon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon,Long> {

    @Query("Select hd from HoaDon hd where hd.trangThai=:tt order by hd.ngayTao asc")
    List<HoaDon> findByTrangThai(@Param("tt") Integer trangThai);

    @Query("Select hd from HoaDon hd where hd.trangThai !=-1 and hd.trangThai !=8 order by hd.ngaySua desc")
    List<HoaDon> findAllOrderByNgaySua();

    @Query("Select hd from HoaDon hd where hd.maHoaDon=:ma")
    HoaDon findByMa(@Param("ma") String ma);

    @Query(value = "select * from hoa_don where ma_hoa_don = :maDonHang and sdt_nguoi_nhan = :sdt",nativeQuery = true)
    HoaDon finByHoaDonMaHDSdt(@Param("maDonHang") String maDonHang,@Param("sdt") String sdt);

    @Query("select COUNT(hd) from HoaDon hd where hd.trangThai = -1")
    Integer countHoaDonTreo();

    @Query("Select hd from HoaDon hd where hd.trangThai=:tt order by hd.ngaySua desc")
    List<HoaDon> find5ByTrangThai(@Param("tt") Integer trangThai);

    @Query(value = "select * from hoa_don where tai_khoan_id = :idTaiKhoan and trang_thai != -1 order by ngay_sua desc", nativeQuery = true)
    List<HoaDon> findAllHoaDonByTaiKhoanOrderByNgaySua(@Param("idTaiKhoan") Long idTaiKhoan);

    @Query(value = "select * from hoa_don where tai_khoan_id = :idTaiKhoan and trang_thai = :trangThai order by ngay_sua desc", nativeQuery = true)
    List<HoaDon> findAllHoaDonByTaiKhoanAndTrangThaiOrderByNgaySua(@Param("idTaiKhoan") Long idTaiKhoan, @Param("trangThai") Integer trangThai);



    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE CAST(hd.ngayTao AS DATE) = :ngayTao AND (hd.trangThai = 3 or hd.trangThai = 6)")
    Integer countHoaDonNgay(@Param("ngayTao") Date ngayTao);

    @Query("select SUM(hd.tongTienKhiGiam) from HoaDon hd where (hd.trangThai =3 or hd.trangThai = 6) and CAST(hd.ngayTao AS DATE) = :ngayTao")
    Long sumGiaTriHoaDonNgay(@Param("ngayTao") Date ngayTao);

    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE MONTH(hd.ngayTao) = MONTH(:ngayTao) AND (hd.trangThai = 3 or hd.trangThai = 6)")
    Integer countHoaDonThang(@Param("ngayTao") Date ngayTao);

    @Query("SELECT SUM(hd.tongTienKhiGiam) FROM HoaDon hd WHERE (hd.trangThai = 3 or hd.trangThai = 6) and MONTH(hd.ngayTao) = MONTH(:ngayTao)")
    Long sumGiaTriHoaDonThang(@Param("ngayTao") Date ngayTao);

    @Transactional
    @Modifying
    @Query(value = "delete hoa_don_chi_tiet where hoa_don_id in(select id from hoa_don where ma_hoa_don like '%DOITRA')\r\n"
            + //
            "delete hoa_don where ma_hoa_don like '%DOITRA'", nativeQuery = true)
    void deleteHoaDonHoanTra();


    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE hd.trangThai = :trangThai and CAST(hd.ngayTao AS DATE) = :ngayTao ")
    Integer countHoaDonTrangThaiNgay(@Param("ngayTao") Date ngayTao,
                                     @Param("trangThai") Integer trangThai);

    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE hd.trangThai = :trangThai and MONTH(hd.ngayTao) = MONTH(:ngayTao)")
    Integer countHoaDonTrangThaiThang(@Param("ngayTao") Date ngayTao,
                                      @Param("trangThai") Integer trangThai);

    // Ngọc Hiếu
    @Query(value = "select hoa_don.id_hd from hoa_don where trang_thai = 3", nativeQuery = true)
    List<Long> fillAllIdHoaDonTrangThaiHoanThanh();

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


    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate")
    Integer countHoaDonTrongKhoangThoiGian(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query("SELECT CAST(hd.ngayTao AS DATE) AS ngay, COUNT(hd) AS soLuongHoaDon " +
            "FROM HoaDon hd " +
            "WHERE CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate " +
            "GROUP BY CAST(hd.ngayTao AS DATE)")
    List<Object[]> thongKeHoaDonTheoNgay(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    //kết thúc thống kê

}
