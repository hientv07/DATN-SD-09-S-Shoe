package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.GioHang;

public interface GioHangService {

    GioHang save(GioHang gioHang);

    Integer genMaTuDong();
}
