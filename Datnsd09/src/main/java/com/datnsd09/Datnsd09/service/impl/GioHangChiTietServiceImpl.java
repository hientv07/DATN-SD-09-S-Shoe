package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.GioHangChiTiet;
import com.datnsd09.Datnsd09.repository.GioHangChiTietRepository;
import com.datnsd09.Datnsd09.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
