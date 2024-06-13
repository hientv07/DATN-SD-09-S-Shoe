package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import com.datnsd09.Datnsd09.repository.SanPhamChiTietRepository;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {
    @Autowired
    private SanPhamChiTietRepository chiTietSanPhamRepository;

    @Override
    public List<SanPhamChiTiet> getAll() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public List<SanPhamChiTiet> getAllCTSPOneSanPham() {
//        return chiTietSanPhamRepository.fillAllCtspOneSanPham();
        return null;
    }

    @Override
    public SanPhamChiTiet getById(Long id) {
        return chiTietSanPhamRepository.findById(id).get();
    }

    @Override
    public List<SanPhamChiTiet> getAllById(Long id) {
        return null;
    }

    @Override
    public List<SanPhamChiTiet> add(List<String> listSanPham, List<String> listKichCo, List<String> listMauSac, List<String> listLoaiDe, List<String> listSoLuong, List<String> listDonGia) {
        return null;
    }

    @Override
    public List<SanPhamChiTiet> updateAllCTSP(List<String> listIdChiTietSp, List<String> listSanPham, List<String> listKichCo, List<String> listMauSac, List<String> listLoaiDe, List<String> listTrangThai, List<String> listSoLuong, List<String> listDonGia) {
        return null;
    }

    @Override
    public SanPhamChiTiet update(SanPhamChiTiet chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public void delete(Long id) {
        chiTietSanPhamRepository.deleteById(id);
    }

    @Override
    public List<SanPhamChiTiet> getAllDangHoatDong() {
        return null;
    }

    @Override
    public List<SanPhamChiTiet> getAllNgungHoatDong() {
        return null;
    }

    @Override
    public void checkSoLuongBang0() {

    }

    @Override
    public SanPhamChiTiet saveExcel(SanPhamChiTiet chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public List<SanPhamChiTiet> getAllbyIdSPAndIdMS(Long idSanPham, Long idMauSac) {
        return null;
    }

    @Override
    public List<SanPhamChiTiet> getAllCtspByIdSanPham(Long idSanPham) {
        return null;
    }

    @Override
    public List<SanPhamChiTiet> fillAllDangHoatDongLonHon0() {
        return null;
    }

    @Override
    public Page<List<SanPhamChiTiet>> searchAll(Integer pageNo, Integer size, String tenSanPham, List<Long> idMauSac, List<Long> idKichCo, List<Long> idLoaiDe, List<Long> idThuongHieu, Long minGia, Long maxGia) {
        return null;
    }

    @Override
    public List<Long> getAllIdMauSacCTSP() {
        return null;
    }

    @Override
    public List<Long> getAllIdKichCoCTSP() {
        return null;
    }

    @Override
    public List<Long> getAllIdLoaiDeCTSP() {
        return null;
    }

    @Override
    public List<Long> getAllIdThuongHieuCTSP() {
        return null;
    }

    @Override
    public Long getAllMinGiaCTSP() {
        return null;
    }

    @Override
    public Long getAllMaxGiaCTSP() {
        return null;
    }

    @Override
    public Integer checkPage(Integer page) {
        return null;
    }

    @Override
    public List<Object[]> danhSachHangSapHet(Integer soLuong) {
        return null;
    }
}
