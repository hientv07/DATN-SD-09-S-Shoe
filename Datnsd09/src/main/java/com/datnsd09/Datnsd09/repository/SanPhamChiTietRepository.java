package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {

    @Query(value = "select * from chi_tiet_san_pham where trang_thai = 0", nativeQuery = true)
    List<SanPhamChiTiet> fillAllDangHoatDong();

    @Query(value = "WITH CTE AS (\n" +
            "    SELECT\n" +
            "        id_ctsp,\n" +
            "        san_pham_id,\n" +
            "        ROW_NUMBER() OVER (PARTITION BY san_pham_id ORDER BY id_ctsp DESC) AS rn\n" +
            "    FROM chi_tiet_san_pham\n" +
            "    WHERE trang_thai = 1\n" +
            ")\n" +
            "SELECT \n" +
            "    cts.id_ctsp,\n" +
            "    cts.so_luong,\n" +
            "    cts.gia,\n" +
            "    cts.trang_thai,\n" +
            "    cts.san_pham_id,\n" +
            "    cts.kich_co_id,\n" +
            "    cts.mau_sac_id,\n" +
            "    cts.loai_de_id,\n" +
            "    cts.ngay_tao,\n" +
            "    cts.ngay_sua\n" +
            "FROM chi_tiet_san_pham cts\n" +
            "JOIN CTE ON cts.id_ctsp = CTE.id_ctsp\n" +
            "WHERE CTE.rn = 1  ORDER BY cts.id_ctsp DESC;", nativeQuery = true)
    List<SanPhamChiTiet> fillAllNgungHoatDong();

    @Query(value = "WITH CTE AS (\n" +
            "    SELECT\n" +
            "        id_ctsp,\n" +
            "        san_pham_id,\n" +
            "        ROW_NUMBER() OVER (PARTITION BY san_pham_id ORDER BY id_ctsp DESC) AS rn\n" +
            "    FROM chi_tiet_san_pham\n" +
            "    WHERE trang_thai = 0\n" +
            ")\n" +
            "SELECT \n" +
            "    cts.id_ctsp,\n" +
            "    cts.so_luong,\n" +
            "    cts.gia,\n" +
            "    cts.trang_thai,\n" +
            "    cts.san_pham_id,\n" +
            "    cts.kich_co_id,\n" +
            "    cts.mau_sac_id,\n" +
            "    cts.loai_de_id,\n" +
            "    cts.ngay_tao,\n" +
            "    cts.ngay_sua\n" +
            "FROM chi_tiet_san_pham cts\n" +
            "JOIN CTE ON cts.id_ctsp = CTE.id_ctsp\n" +
            "WHERE CTE.rn = 1 ORDER BY cts.id_ctsp DESC;", nativeQuery = true)
    List<SanPhamChiTiet> fillAllCtspOneSanPham();

    @Query(value = "SELECT cts.*\n" +
            "FROM (\n" +
            "    SELECT *,\n" +
            "           ROW_NUMBER() OVER (PARTITION BY san_pham_id, mau_sac_id ORDER BY mau_sac_id) AS RowAsc\n"
            +
            "    FROM chi_tiet_san_pham\n" +
            "    WHERE san_pham_id = :id\n" +
            ") cts\n" +
            "WHERE cts.RowAsc = 1  AND cts.trang_thai = 0;", nativeQuery = true)
    List<SanPhamChiTiet> fillAllChiTietSpShop(@Param("id") Long id);

    @Query(value = "select * from chi_tiet_san_pham where san_pham_id = :idSanPham and mau_sac_id = :idMauSac and trang_thai = 0 ORDER BY kich_co_id ASC, id ASC;\n", nativeQuery = true)
    List<SanPhamChiTiet> fillAllChiTietSpMauSac(@Param("idSanPham") Long idSanPham,
                                                @Param("idMauSac") Long idMauSac);

    @Query(value = "SELECT *\n" +
            "FROM chi_tiet_san_pham\n" +
            "WHERE san_pham_id = :idSanPham\n" +
            "ORDER BY mau_sac_id, kich_co_id", nativeQuery = true)
    List<SanPhamChiTiet> fillAllChiTietSpBySanPham(@Param("idSanPham") Long idSanPham);


    @Query(value = "select DISTINCT chi_tiet_san_pham.mau_sac_id from chi_tiet_san_pham where trang_thai = 0", nativeQuery = true)
    List<Long> getAllIdMauSacCTSP();

    @Query(value = "select DISTINCT chi_tiet_san_pham.kich_co_id from chi_tiet_san_pham where trang_thai = 0", nativeQuery = true)
    List<Long> getAllIdKichCoCTSP();

    @Query(value = "select DISTINCT chi_tiet_san_pham.loai_de_id from chi_tiet_san_pham where trang_thai = 0", nativeQuery = true)
    List<Long> getAllIdLoaiDeCTSP();

    @Query(value = "select DISTINCT th.id_thuong_hieu,th.ten_thuong_hieu from chi_tiet_san_pham c \n" +
            "JOIN san_pham s ON c.san_pham_id = s.id_sp JOIN thuong_hieu th ON s.thuong_hieu_id = th.id_thuong_hieu", nativeQuery = true)
    List<Long> getAllIdThuongHieuCTSP();


    //Thống kê
    @Query("select sp.ten, kc.ten, ms.ten, ld.ten, ctsp.soLuong " +
            "from SanPhamChiTiet ctsp " +
            "join ctsp.sanPham sp " +
            "join ctsp.kichCo kc " +
            "join ctsp.mauSac ms " +
            "join ctsp.loaiDe ld " +
            "where ctsp.soLuong <= :soLuong " +
            "and ctsp.trangThai = 0")
    List<Object[]> danhSachSapHetHang(@Param("soLuong") int soLuong);


//    @Query(value = "select * from chi_tiet_san_pham where san_pham_id = :idSanPham and mau_sac_id = :idMauSac and loai_de_id = :idLoaiDe and kich_co_id = :idKichCo", nativeQuery = true)
//    SanPhamChiTiet findChiTietSanPham(@Param("idSanPham") Long idSanPham, @Param("idMauSac") Long idMauSac,
//                                      @Param("idLoaiDe") Long idLoaiDe, @Param("idKichCo") Long idKichCo);
}
