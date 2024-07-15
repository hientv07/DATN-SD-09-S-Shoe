package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.HoaDon;

import java.util.Date;
import java.util.List;

public interface HoaDonService {
    List<HoaDon> findAll();

    HoaDon findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(HoaDon hoaDon);

    List<HoaDon> findByTrangThai(Integer trangThai);

    Integer countHoaDonTreo();

    List<HoaDon> find5ByTrangThai(Integer trangThai);

    HoaDon findByMa(String ma);

    HoaDon finByHoaDonMaHDSdt(String maDonHang, String sdt);

    List<HoaDon> findAllOrderByNgaySua();

    List<HoaDon> getAllHoaDonByTaiKhoanOrderByNgaySua(Long idTaiKhoan);

    List<HoaDon> getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(Long idTaiKhoan, Integer trangThai);

    Integer countHoaDonDay(Date ngayTao);

    Long sumHoaDonDay(Date ngayTao);

    Integer countHoaDonMonth(Date ngayTao);

    Long sumHoaDonMonth(Date ngayTao);

    Integer countHoaDon(Integer trangThai);

    Integer countHoaDonBetween(Date startDate,
                               Date endDate);

    Long sumGiaTriHoaDonBetween(Date startDate,
                                Date endDate);

    Integer countHoaDonTrangThaiBetween(Date startDate,
                                        Date endDate,
                                        Integer trangThai);

    Integer countHoaDonTrangThaiNgay(Date ngayTao,
                                     Integer trangThai);

    Integer countHoaDonTrangThaiThang(Date ngayTao,
                                      Integer trangThai);

    Integer countHoaDonAll();

    Long sumGiaTriHoaDonAll();

    void deleteHoaDonHoanTra();

    void guiHoaDonDienTu(HoaDon hoaDon, String url);

}
