package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.KichCo;
import com.datnsd09.Datnsd09.entity.LoaiDe;
import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import com.datnsd09.Datnsd09.repository.SanPhamChiTietRepository;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        return chiTietSanPhamRepository.fillAllCtspOneSanPham();
    }

    @Override
    public SanPhamChiTiet getById(Long id) {
        return chiTietSanPhamRepository.findById(id).get();
    }

    @Override
    public List<SanPhamChiTiet> getAllById(Long id) {
        return chiTietSanPhamRepository.fillAllChiTietSpShop(id);
    }

    @Override
    public List<SanPhamChiTiet> add(List<String> listSanPham, List<String> listKichCo, List<String> listMauSac, List<String> listLoaiDe, List<String> listSoLuong, List<String> listDonGia) {
        List<SanPhamChiTiet> chiTietSanPhamList = new ArrayList<>();
        List<SanPhamChiTiet> listCtspCheck = chiTietSanPhamRepository.findAll();
        for (int i = 0; i < listSanPham.size(); i++) {
            SanPhamChiTiet chiTietSanPham = new SanPhamChiTiet();
            boolean isUpdated = false;

            for (SanPhamChiTiet listCheck : listCtspCheck) {
                if (listCheck.getSanPham().getId().equals(Long.valueOf(listSanPham.get(i))) &&
                        listCheck.getKichCo().getId().equals(Long.valueOf(listKichCo.get(i))) &&
                        listCheck.getMauSac().getId().equals(Long.valueOf(listMauSac.get(i))) &&
                        listCheck.getLoaiDe().getId().equals(Long.valueOf(listLoaiDe.get(i)))
                ) {
                    int soLuongMoi = Integer.parseInt(listSoLuong.get(i));
                    listCheck.setSoLuong(listCheck.getSoLuong() + soLuongMoi);
                    listCheck.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
                    listCheck.setTrangThai(0);
                    chiTietSanPham.setNgaySua(new Date());

                    SanPhamChiTiet updatedChiTietSanPham = chiTietSanPhamRepository.save(listCheck);
                    chiTietSanPhamList.add(updatedChiTietSanPham);
                    isUpdated = true;

                    break;
                }
            }

            if (!isUpdated) {
                chiTietSanPham.setSanPham(SanPham.builder().id(Long.valueOf(listSanPham.get(i))).build());
                chiTietSanPham.setKichCo(KichCo.builder().id(Long.valueOf(listKichCo.get(i))).build());
                chiTietSanPham.setMauSac(MauSac.builder().id(Long.valueOf(listMauSac.get(i))).build());
                chiTietSanPham.setLoaiDe(LoaiDe.builder().id(Long.valueOf(listLoaiDe.get(i))).build());
                chiTietSanPham.setSoLuong(Integer.parseInt(listSoLuong.get(i)));
                chiTietSanPham.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
                chiTietSanPham.setTrangThai(0);
                chiTietSanPham.setNgayTao(new Date());
                chiTietSanPham.setNgaySua(new Date());

                if (chiTietSanPham.getSoLuong() > 0) {
                    SanPhamChiTiet savedChiTietSanPham = chiTietSanPhamRepository.save(chiTietSanPham);
                    chiTietSanPhamList.add(savedChiTietSanPham);
                }
            }
        }
        return chiTietSanPhamList;
    }

    @Override
    public List<SanPhamChiTiet> updateAllCTSP(List<String> listIdChiTietSp, List<String> listSanPham, List<String> listKichCo, List<String> listMauSac, List<String> listLoaiDe, List<String> listTrangThai, List<String> listSoLuong, List<String> listDonGia) {
        SanPhamChiTiet chiTietSanPhamNew = this.getById(Long.valueOf(listIdChiTietSp.get(0)));
        List<SanPhamChiTiet> chiTietSanPhamList = new ArrayList<>();

        for (int i = 0; i < listSanPham.size(); i++) {
            SanPhamChiTiet chiTietSanPham = new SanPhamChiTiet();
            chiTietSanPham.setId(Long.valueOf(listIdChiTietSp.get(i)));
            chiTietSanPham.setSanPham(SanPham.builder().id(Long.valueOf(listSanPham.get(i))).build());
            chiTietSanPham.setKichCo(KichCo.builder().id(Long.valueOf(listKichCo.get(i))).build());
            chiTietSanPham.setMauSac(MauSac.builder().id(Long.valueOf(listMauSac.get(i))).build());
            chiTietSanPham.setLoaiDe(LoaiDe.builder().id(Long.valueOf(listLoaiDe.get(i))).build());
            chiTietSanPham.setTrangThai(Integer.parseInt(listTrangThai.get(i)));
            chiTietSanPham.setSoLuong(Integer.parseInt(listSoLuong.get(i)));
            chiTietSanPham.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
            chiTietSanPham.setNgayTao(chiTietSanPhamNew.getNgayTao());
            chiTietSanPham.setNgaySua(new Date());
            SanPhamChiTiet savedChiTietSanPham = chiTietSanPhamRepository.save(chiTietSanPham);
            chiTietSanPhamList.add(savedChiTietSanPham);
        }

        return chiTietSanPhamList;
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
        return chiTietSanPhamRepository.fillAllDangHoatDong();
    }

    @Override
    public List<SanPhamChiTiet> getAllNgungHoatDong() {
        return chiTietSanPhamRepository.fillAllNgungHoatDong();
    }

    @Override
    public void checkSoLuongBang0() {
        chiTietSanPhamRepository.checkSoLuongBang0();
    }

    @Override
    public SanPhamChiTiet saveExcel(SanPhamChiTiet chiTietSanPham) {
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public List<SanPhamChiTiet> getAllbyIdSPAndIdMS(Long idSanPham, Long idMauSac) {
        return chiTietSanPhamRepository.fillAllChiTietSpMauSac(idSanPham, idMauSac);
    }

    @Override
    public List<SanPhamChiTiet> getAllCtspByIdSanPham(Long idSanPham) {
        return chiTietSanPhamRepository.fillAllChiTietSpBySanPham(idSanPham);
    }

    @Override
    public List<SanPhamChiTiet> fillAllDangHoatDongLonHon0() {
        return chiTietSanPhamRepository.fillAllDangHoatDongLonHon0();
    }

    @Override
    public Page<List<SanPhamChiTiet>> searchAll(Integer pageNo, Integer size, String tenSanPham, List<Long> idMauSac, List<Long> idKichCo, List<Long> idLoaiDe, List<Long> idThuongHieu, Long minGia, Long maxGia) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return chiTietSanPhamRepository.searchAll(pageable, tenSanPham, idMauSac, idKichCo, idLoaiDe, idThuongHieu, minGia, maxGia);
    }

    @Override
    public List<Long> getAllIdMauSacCTSP() {
        return chiTietSanPhamRepository.getAllIdMauSacCTSP();
    }

    @Override
    public List<Long> getAllIdKichCoCTSP() {
        return chiTietSanPhamRepository.getAllIdKichCoCTSP();
    }

    @Override
    public List<Long> getAllIdLoaiDeCTSP() {
        return chiTietSanPhamRepository.getAllIdLoaiDeCTSP();
    }

    @Override
    public List<Long> getAllIdThuongHieuCTSP() {
        return chiTietSanPhamRepository.getAllIdThuongHieuCTSP();
    }

    @Override
    public Long getAllMinGiaCTSP() {
        return chiTietSanPhamRepository.getAllMinGiaCTSP();
    }

    @Override
    public Long getAllMaxGiaCTSP() {
        return chiTietSanPhamRepository.getAllMaxGiaCTSP();
    }

    @Override
    public Integer checkPage(Integer page) {
        Integer sizeList = chiTietSanPhamRepository.findAll().size();
        Integer pageCount = (int) Math.ceil((double) sizeList / 5);
        if (page >= pageCount) {
            page = 0;
        } else if (page < 0) {
            page = pageCount - 1;
        }
        return page;
    }

    //thống kê
    @Override
    public List<Object[]> danhSachHangSapHet(Integer soLuong) {
        return null;
    }

    @Override
    public List<Object[]> danhSachSapHetHang(Integer soLuong) {
        return chiTietSanPhamRepository.danhSachSapHetHang(soLuong);
    }
}
