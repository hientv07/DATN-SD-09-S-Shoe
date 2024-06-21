package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.repository.HoaDonChiTietRepository;
import com.datnsd09.Datnsd09.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    //Thống kê bình
//    @Override
//    public List<Object[]> findByTongSoLuongBetween(Date startDate, Date endDate) {
////        return hoaDonChiTietRepository.findByTongSoLuongBetween(startDate, endDate);
//        return ;
//    }

    //kết thúc binh
}
