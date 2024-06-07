package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.ChiTietSanPham;
import com.datnsd09.Datnsd09.repository.ChiTietSanPhamRepository;
import com.datnsd09.Datnsd09.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService{
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public List<ChiTietSanPham> getAllCTSPOneSanPham() {
        return null;
    }

    @Override
    public ChiTietSanPham getById(Long id) {
        return chiTietSanPhamRepository.findById(id).get();
    }

    @Override
    public List<ChiTietSanPham> getAllById(Long id) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> add(List<String> listSanPham, List<String> listKichCo, List<String> listMauSac, List<String> listLoaiDe, List<String> listSoLuong, List<String> listDonGia) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> updateAllCTSP(List<String> listIdChiTietSp, List<String> listSanPham, List<String> listKichCo, List<String> listMauSac, List<String> listLoaiDe, List<String> listTrangThai, List<String> listSoLuong, List<String> listDonGia) {
        return null;
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public void delete(Long id) {
        chiTietSanPhamRepository.deleteById(id);
    }

    @Override
    public List<ChiTietSanPham> getAllDangHoatDong() {
        return null;
    }

    @Override
    public List<ChiTietSanPham> getAllNgungHoatDong() {
        return null;
    }

    @Override
    public void checkSoLuongBang0() {

    }

    @Override
    public ChiTietSanPham saveExcel(ChiTietSanPham chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public List<ChiTietSanPham> getAllbyIdSPAndIdMS(Long idSanPham, Long idMauSac) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> getAllCtspByIdSanPham(Long idSanPham) {
        return null;
    }

    @Override
    public List<ChiTietSanPham> fillAllDangHoatDongLonHon0() {
        return null;
    }

    @Override
    public Page<List<ChiTietSanPham>> searchAll(Integer pageNo, Integer size, String tenSanPham, List<Long> idMauSac, List<Long> idKichCo, List<Long> idLoaiDe, List<Long> idThuongHieu, Long minGia, Long maxGia) {
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
