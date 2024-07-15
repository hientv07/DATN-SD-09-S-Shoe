package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.GioHang;
import com.datnsd09.Datnsd09.repository.GioHangRepository;
import com.datnsd09.Datnsd09.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public GioHang save(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    @Override
    public Integer genMaTuDong() {
        String maStr = "";
        try {
            if (gioHangRepository.index() != null) {
                maStr = gioHangRepository.index().toString();
            } else {
                maStr = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maStr == null) {
            maStr = "0";
            int ma = Integer.parseInt(maStr);
            return ++ma;
        }
        int ma = Integer.parseInt(maStr);
        return ++ma;
    }
}
