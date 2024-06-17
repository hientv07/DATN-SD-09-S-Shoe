package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SanPhamChiTietService {

    //Ngoc hieu
    List<SanPhamChiTiet> getAll();

    List<SanPhamChiTiet> getAllCTSPOneSanPham();

    SanPhamChiTiet getById(Long id);

    List<SanPhamChiTiet> getAllById(Long id);

    List<SanPhamChiTiet> add(List<String> listSanPham, List<String> listKichCo,
                             List<String> listMauSac, List<String> listLoaiDe,
                             List<String> listSoLuong, List<String> listDonGia);

    List<SanPhamChiTiet> updateAllCTSP(
            List<String> listIdChiTietSp, List<String> listSanPham,
            List<String> listKichCo, List<String> listMauSac,
            List<String> listLoaiDe, List<String> listTrangThai,
            List<String> listSoLuong, List<String> listDonGia);

    SanPhamChiTiet update(SanPhamChiTiet chiTietSanPham);

    void delete(Long id);

    List<SanPhamChiTiet> getAllDangHoatDong();

    List<SanPhamChiTiet> getAllNgungHoatDong();

    void checkSoLuongBang0();

    SanPhamChiTiet saveExcel(SanPhamChiTiet chiTietSanPham);

    List<SanPhamChiTiet> getAllbyIdSPAndIdMS(Long idSanPham, Long idMauSac);

    List<SanPhamChiTiet> getAllCtspByIdSanPham(Long idSanPham);

    List<SanPhamChiTiet> fillAllDangHoatDongLonHon0();

    Page<List<SanPhamChiTiet>> searchAll(Integer pageNo, Integer size, String tenSanPham, List<Long> idMauSac,
                                         List<Long> idKichCo,
                                         List<Long> idLoaiDe, List<Long> idThuongHieu, Long minGia, Long maxGia);

    List<Long> getAllIdMauSacCTSP();

    List<Long> getAllIdKichCoCTSP();

    List<Long> getAllIdLoaiDeCTSP();

    List<Long> getAllIdThuongHieuCTSP();

    Long getAllMinGiaCTSP();

    Long getAllMaxGiaCTSP();

    Integer checkPage(Integer page);

    //Thống kê
    List<Object[]> danhSachHangSapHet(Integer soLuong);

    List<Object[]> danhSachSapHetHang(Integer soLuong);
}
