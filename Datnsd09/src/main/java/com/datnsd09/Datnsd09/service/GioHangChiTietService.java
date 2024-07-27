package com.datnsd09.Datnsd09.service;

import com.datnsd09.Datnsd09.entity.GioHangChiTiet;
import com.datnsd09.Datnsd09.entity.HoaDonChiTiet;
import com.datnsd09.Datnsd09.entity.KhachHang;

import java.util.Date;
import java.util.List;

public interface GioHangChiTietService {

    List<GioHangChiTiet> fillAllByIdGioHang(Long idGioHang);

    Integer soLuongSPGioHangCT(Long idGioHang);

    void deleteById(Long id);

    GioHangChiTiet fillById(Long id);

    GioHangChiTiet update(GioHangChiTiet gioHangChiTiet);

    GioHangChiTiet fillByIdCTSP(Long idCTSP);

    List<GioHangChiTiet> save(Long idGioHang, List<String> idChiTietSp, Integer soLuong);

    List<GioHangChiTiet> findAllById(List<String> listIdString, Long idGioHang);

    HoaDonChiTiet addHoaDon(List<String> listStringIdGioHangCT, Long tongTien, Long tongTienSale,
                            String hoVaTen, String soDienThoai, String tienShip, String tienGiam, String email,
                            String voucher, String diaChiCuThe, String ghiChu, KhachHang taiKhoan,
                            String phuongXaID, String quanHuyenID, String thanhPhoID, Long idGioHang, Date ngayThanhToan);
}
