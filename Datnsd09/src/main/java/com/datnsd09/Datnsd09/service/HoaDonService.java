package com.datnsd09.Datnsd09.service;

import java.util.Date;

public interface HoaDonService {
//thống kê bình
    // đến hóa đơn
    Integer countHoaDonBetween(Date startDate,
                               Date endDate);
    //tính toonge tien hoa dơn
    Long sumGiaTriHoaDonBetween(Date startDate,
                                Date endDate);

    //Trạng thái các hóa đơn
    Integer countHoaDonTrangThaiBetween(Date startDate,
                                        Date endDate,

                                        Integer trangThai);
    Integer countHoaDonAll();

    Long sumGiaTriHoaDonAll();
    //trạng thái hoa don
    Integer countHoaDon(Integer trangThai);
    //kết thúc thống kê bình
}
