package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChiTietSanPhamService {
    List<ChiTietSanPham> getAll();

    List<ChiTietSanPham> getAllCTSPOneSanPham();

    ChiTietSanPham getById(Long id);

    List<ChiTietSanPham> getAllById(Long id);

    List<ChiTietSanPham> add(List<String> listSanPham, List<String> listKichCo,
                             List<String> listMauSac, List<String> listLoaiDe,
                             List<String> listSoLuong, List<String> listDonGia);

    List<ChiTietSanPham> updateAllCTSP(
            List<String> listIdChiTietSp, List<String> listSanPham,
            List<String> listKichCo, List<String> listMauSac,
            List<String> listLoaiDe, List<String> listTrangThai,
            List<String> listSoLuong, List<String> listDonGia);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham);

    void delete(Long id);

    List<ChiTietSanPham> getAllDangHoatDong();

    List<ChiTietSanPham> getAllNgungHoatDong();

    void checkSoLuongBang0();

    ChiTietSanPham saveExcel(ChiTietSanPham chiTietSanPham);

    List<ChiTietSanPham> getAllbyIdSPAndIdMS(Long idSanPham, Long idMauSac);

    List<ChiTietSanPham> getAllCtspByIdSanPham(Long idSanPham);

    List<ChiTietSanPham> fillAllDangHoatDongLonHon0();

    Page<List<ChiTietSanPham>> searchAll(Integer pageNo, Integer size, String tenSanPham, List<Long> idMauSac,
                                         List<Long> idKichCo,
                                         List<Long> idLoaiDe, List<Long> idThuongHieu, Long minGia, Long maxGia);

    List<Long> getAllIdMauSacCTSP();

    List<Long> getAllIdKichCoCTSP();

    List<Long> getAllIdLoaiDeCTSP();

    List<Long> getAllIdThuongHieuCTSP();

    Long getAllMinGiaCTSP();

    Long getAllMaxGiaCTSP();

    Integer checkPage(Integer page);

    List<Object[]> danhSachHangSapHet(Integer soLuong);
}
