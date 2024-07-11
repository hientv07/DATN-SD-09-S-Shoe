package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.repository.GioHangChiTietRepository;
import com.datnsd09.Datnsd09.service.GioHangChiTietService;
import org.springframework.stereotype.Service;

@Service

public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    private GioHangChiTietRepository gioHangChiTietRepository;


    @Override
    public Integer soLuongSPGioHangCT(Long idGioHang) {
        return gioHangChiTietRepository.soLuongSpTrongGioHangCT(idGioHang);
    }
}
