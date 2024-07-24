package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.HoaDonChiTiet;
import com.datnsd09.Datnsd09.repository.HoaDonChiTietRepository;
import com.datnsd09.Datnsd09.repository.HoaDonRepository;
import com.datnsd09.Datnsd09.service.HoaDonChiTietService;
import com.datnsd09.Datnsd09.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public List<HoaDonChiTiet> findAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet findById(Long id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        hoaDonChiTietRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public List<HoaDonChiTiet> findByIdHoaDon(Long idHoaDon) {
        return hoaDonChiTietRepository.findByIdHoaDon(idHoaDon);
    }

    @Override
    public List<Object[]> findByTongSoLuongBetween(Date startDate, Date endDate) {
        return hoaDonChiTietRepository.findByTongSoLuongBetween(startDate,endDate);
    }

    @Override
    public Integer sumSanPhamBanDuocBetween(Date startDate, Date endDate) {
        return hoaDonChiTietRepository.sumSanPhamBanDuocBetween(startDate, endDate);
    }

    @Override
    public List<Object[]> findByTongSoLuongBetweenGetAll() {
        return hoaDonChiTietRepository.findByTongSoLuongBetweenGetAll();
    }

    @Override
    public Integer sumSanPhamHoaDonAll() {
        return hoaDonChiTietRepository.sumSanPhamHoaDonAll();
    }

    @Override
    public List<Object[]> thongKeSanPhamTheoNgay(Date startDateChart, Date endDateChart) {
        return hoaDonChiTietRepository.thongKeSanPhamTheoNgay(startDateChart, endDateChart);
    }

    // Ngọc Hiếu
    @Override
    public List<HoaDonChiTiet> finTop5HDCT() {
        return hoaDonChiTietRepository.fillAllIdHoaDonTrangThaiHoanThanh(hoaDonRepository.fillAllIdHoaDonTrangThaiHoanThanh());
    }

}
