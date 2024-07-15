package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.GioHang;
import com.datnsd09.Datnsd09.entity.GioHangChiTiet;
import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import com.datnsd09.Datnsd09.repository.GioHangChiTietRepository;
import com.datnsd09.Datnsd09.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Override
    public List<GioHangChiTiet> fillAllByIdGioHang(Long idGioHang) {
        return gioHangChiTietRepository.findAllByIdGioHang(idGioHang);
    }

    @Override
    public Integer soLuongSPGioHangCT(Long idGioHang) {

        List<GioHangChiTiet> list = gioHangChiTietRepository.findAllByIdGioHang(idGioHang);
        return list.size();

       // return gioHangChiTietRepository.soLuongSpTrongGioHangCT(idGioHang);
      // return null;
    }

    @Override
    public void deleteById(Long id) {
        gioHangChiTietRepository.deleteById(id);
    }

    @Override
    public GioHangChiTiet fillById(Long id) {
        return gioHangChiTietRepository.findById(id).get();
    }

    @Override
    public GioHangChiTiet update(GioHangChiTiet gioHangChiTiet) {
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public GioHangChiTiet fillByIdCTSP(Long idCTSP) {
        return gioHangChiTietRepository.fillByIdCTSP(idCTSP);
    }

    @Override
    public List<GioHangChiTiet> save(Long idGioHang, List<String> idChiTietSp, Integer soLuong) {

        List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietRepository.getByGioHangChiTiet(idGioHang, idChiTietSp);

        List<GioHangChiTiet> gioHangChiTietNewList = new ArrayList<>();

        // Nếu chưa có, thêm mới
        if (gioHangChiTietList.isEmpty()) {
            for (String idChiTiet : idChiTietSp) {
                GioHangChiTiet gioHangChiTietNew = new GioHangChiTiet();
                gioHangChiTietNew.setSoLuong(soLuong);
                gioHangChiTietNew.setNgayTao(new Date());
                gioHangChiTietNew.setNgaySua(new Date());
                SanPhamChiTiet chiTietSanPham = SanPhamChiTiet.builder().id(Long.valueOf(idChiTiet)).build();
                gioHangChiTietNew.setChiTietSanPham(chiTietSanPham);
                gioHangChiTietNew.setGioHang(GioHang.builder().id(idGioHang).build());
                gioHangChiTietNew.setTrangThai(0);

                gioHangChiTietNewList.add(gioHangChiTietNew);
            }
        } else {
            for (GioHangChiTiet gioHangChiTiet : gioHangChiTietList) {
                for (String idChiTiet : idChiTietSp) {
                    GioHangChiTiet gioHangChiTietNew = new GioHangChiTiet();
                    gioHangChiTietNew.setId(gioHangChiTiet.getId());
                    gioHangChiTietNew.setSoLuong(gioHangChiTiet.getSoLuong() + soLuong);
                    gioHangChiTietNew.setGhiChu(gioHangChiTiet.getGhiChu());
                    gioHangChiTietNew.setNgayTao(gioHangChiTiet.getNgayTao());
                    gioHangChiTietNew.setNgaySua(new Date());
                    SanPhamChiTiet chiTietSanPham = SanPhamChiTiet.builder().id(Long.valueOf(idChiTiet)).build();
                    gioHangChiTietNew.setChiTietSanPham(chiTietSanPham);
                    gioHangChiTietNew.setGioHang(gioHangChiTiet.getGioHang());
                    gioHangChiTietNew.setTrangThai(0);

                    gioHangChiTietNewList.add(gioHangChiTietNew);
                }
            }
        }

        return gioHangChiTietRepository.saveAll(gioHangChiTietNewList);
       // return null;
    }
}
