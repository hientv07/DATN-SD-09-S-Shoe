package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.*;
import com.datnsd09.Datnsd09.repository.GioHangChiTietRepository;
import com.datnsd09.Datnsd09.repository.HoaDonChiTietRepository;
import com.datnsd09.Datnsd09.repository.HoaDonRepository;
import com.datnsd09.Datnsd09.service.GioHangChiTietService;
import com.datnsd09.Datnsd09.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

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

    @Override
    public List<GioHangChiTiet> findAllById(List<String> listIdString, Long idGioHang) {
        List<Long> listIdLong = new ArrayList<>();
        for (String str : listIdString) {
            try {
                Long value = Long.parseLong(str);
                listIdLong.add(value);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
                // Xử lý ngoại lệ nếu có giá trị không hợp lệ
            }
        }

        return gioHangChiTietRepository.findAllByIdGHCT(listIdLong, idGioHang);
    }

    @Override
    public HoaDonChiTiet addHoaDon(List<String> listStringIdGioHangCT,
                                   Long tongTien, Long tongTienSale,
                                   String hoVaTen, String soDienThoai,
                                   String tienShip, String tienGiam,
                                   String email, String voucher,
                                   String diaChiCuThe, String ghiChu,
                                   KhachHang taiKhoan, String phuongXaID,
                                   String quanHuyenID, String thanhPhoID,
                                   Long idGioHang , Date ngayThanhToan) {

        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon("HD" + hoaDon.getId());
        hoaDon.setLoaiHoaDon(1);
        hoaDon.setPhiShip(Long.valueOf(tienShip));
        hoaDon.setTienGiam(Long.valueOf(tienGiam));
        hoaDon.setTongTien(tongTien);
        hoaDon.setTongTienKhiGiam(tongTienSale);
        hoaDon.setGhiChu(ghiChu);
        hoaDon.setNguoiNhan(hoVaTen);
        hoaDon.setSdtNguoiNhan(soDienThoai);
        hoaDon.setDiaChiNguoiNhan(diaChiCuThe);
        hoaDon.setEmailNguoiNhan(email);
        hoaDon.setNgayTao(new Date());
        hoaDon.setNgaySua(new Date());
        hoaDon.setTrangThai(0);
        hoaDon.setPhuongXa(phuongXaID);
        hoaDon.setQuanHuyen(quanHuyenID);
        hoaDon.setThanhPho(thanhPhoID);
        hoaDon.setNgayThanhToan(ngayThanhToan);
        if (voucher != "") {
            hoaDon.setVoucher(Voucher.builder().id(Long.valueOf(voucher)).build());
        }

        hoaDon.setKhachHang(taiKhoan);
        hoaDonRepository.save(hoaDon);


        hoaDon.setMaHoaDon("HD" + hoaDon.getId());

        hoaDonRepository.save(hoaDon);


        List<GioHangChiTiet> listGioHangChiTiet = this.findAllById(listStringIdGioHangCT, idGioHang);

        for (GioHangChiTiet gioHangChiTiet : listGioHangChiTiet) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());
            hoaDonChiTiet.setDonGia(gioHangChiTiet.getChiTietSanPham().getGiaHienHanh());
            hoaDonChiTiet.setHoaDon(HoaDon.builder().id(hoaDon.getId()).build());
            hoaDonChiTiet.setSanPhamChiTiet(gioHangChiTiet.getChiTietSanPham());
            hoaDonChiTiet.setTrangThai(0);
            hoaDonChiTiet.setNgaySua(new Date());
            hoaDonChiTiet.setNgayTao(new Date());
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            gioHangChiTietRepository.delete(gioHangChiTiet);
        }

        return null;
    }
}
