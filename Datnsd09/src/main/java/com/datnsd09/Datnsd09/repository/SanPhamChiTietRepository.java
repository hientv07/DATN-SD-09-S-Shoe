package com.datnsd09.Datnsd09.repository;

import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet,Long> {

//    @Query(value = "WITH CTE AS (\n" +
//            "    SELECT\n" +
//            "        id,\n" +
//            "        san_pham_id,\n" +
//            "        ROW_NUMBER() OVER (PARTITION BY san_pham_id ORDER BY id DESC) AS rn\n" +
//            "    FROM chi_tiet_san_pham\n" +
//            "    WHERE trang_thai = 0\n" +
//            ")\n" +
//            "SELECT \n" +
//            "    cts.id,\n" +
//            "    cts.so_luong,\n" +
//            "    cts.gia_hien_hanh,\n" +
//            "    cts.trang_thai,\n" +
//            "    cts.san_pham_id,\n" +
//            "    cts.kich_co_id,\n" +
//            "    cts.mau_sac_id,\n" +
//            "    cts.loai_de_id,\n" +
//            "    cts.ngay_tao,\n" +
//            "    cts.ngay_sua\n" +
//            "FROM chi_tiet_san_pham cts\n" +
//            "JOIN CTE ON cts.id = CTE.id\n" +
//            "WHERE CTE.rn = 1 ORDER BY cts.id DESC;", nativeQuery = true)
//    List<SanPhamChiTiet> fillAllCtspOneSanPham();
}
