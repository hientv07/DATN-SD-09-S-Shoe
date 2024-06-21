package com.datnsd09.Datnsd09.controller.user;


import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.entity.SanPhamChiTiet;
import com.datnsd09.Datnsd09.service.HoaDonChiTietService;
import com.datnsd09.Datnsd09.service.KhachHangService;
import com.datnsd09.Datnsd09.service.KichCoService;
import com.datnsd09.Datnsd09.service.LoaiDeService;
import com.datnsd09.Datnsd09.service.MauSacService;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import com.datnsd09.Datnsd09.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CustomerController {

    private Long idKhachHang;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private LoaiDeService loaiDeService;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private PrincipalCustom principalCustom = new PrincipalCustom();


    @GetMapping("/logout/true")
    public String logout(){
        idKhachHang = null;
        return "dang-nhap";
    }

    @GetMapping("/home")
    public String home(
            Model model) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang taiKhoan = khachHangService.getTaiKhoanByName(principalCustom.getCurrentUserNameCustomer());
            idKhachHang = taiKhoan.getId();
        } else {
            idKhachHang = null;
        }

//        model.addAttribute("listTop5HDCT", hoaDonChiTietService.finTop5HDCT());
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("checkDangNhap", "true");
//            model.addAttribute("soLuongSPGioHangCT",
//                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }
        return "/customer/home";

    }

    @GetMapping("/user/thong-tin-khach-hang")
    public String info(Model model) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang taiKhoan = khachHangService.getTaiKhoanByName(principalCustom.getCurrentUserNameCustomer());
            idKhachHang = taiKhoan.getId();
        }
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        model.addAttribute("khachHang", khachHang);
//        model.addAttribute("soLuongSPGioHangCT",
//                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        return "/customer/thong-tin-khach-hang";
    }

    @GetMapping("/shop")
    public String search(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "MauSac", required = false) List<Long> MauSac,
            @RequestParam(value = "KichCo", required = false) List<Long> KichCo,
            @RequestParam(value = "LoaiDe", required = false) List<Long> LoaiDe,
            @RequestParam(value = "ThuongHieu", required = false) List<Long> ThuongHieu,
            @RequestParam(value = "minPrice", defaultValue = "") Long minPrice,
            @RequestParam(value = "maxPrice", defaultValue = "") Long maxPrice,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "28") Integer size,
            Model model) {
        if (MauSac == null) {
            MauSac = sanPhamChiTietService.getAllIdMauSacCTSP();
        }
        if (KichCo == null) {
            KichCo = sanPhamChiTietService.getAllIdKichCoCTSP();
        }
        if (LoaiDe == null) {
            LoaiDe = sanPhamChiTietService.getAllIdLoaiDeCTSP();
        }
        if (ThuongHieu == null) {
            ThuongHieu = sanPhamChiTietService.getAllIdThuongHieuCTSP();
        }
        if (minPrice == null) {
            minPrice = sanPhamChiTietService.getAllMinGiaCTSP();
        }
        if (maxPrice == null) {
            maxPrice = sanPhamChiTietService.getAllMaxGiaCTSP();
        }

        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("checkDangNhap", "true");
//            model.addAttribute("soLuongSPGioHangCT",
//                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }
        if (sanPhamChiTietService.searchAll(page, size, keyword, MauSac, KichCo, LoaiDe, ThuongHieu, minPrice, maxPrice)
                .isEmpty()) {
            model.addAttribute("checkListChiTietSP", "true");
        } else {
            model.addAttribute("listCTSP",
                    sanPhamChiTietService
                            .searchAll(page, size, keyword, MauSac, KichCo, LoaiDe, ThuongHieu, minPrice, maxPrice)
                            .stream().toList());
        }
        model.addAttribute("pageCount",
                sanPhamChiTietService
                        .searchAll(page, size, keyword, MauSac, KichCo, LoaiDe, ThuongHieu, minPrice, maxPrice)
                        .getTotalPages());
        model.addAttribute("listMauSac", mauSacService.getAll());
        model.addAttribute("listKichCo", kichCoService.getAll());
        model.addAttribute("listLoaiDe", loaiDeService.findAll());
        model.addAttribute("listThuongHieu", thuongHieuService.getAllDangHoatDong());
        return "/customer/shop";
    }

    @GetMapping("/user/san-pham-chi-tiet/{idSanPham}/{idMauSac}")
    @ResponseBody
    public List<SanPhamChiTiet> getAllbyIdSPAndIdMS(
            @PathVariable String idSanPham,
            @PathVariable String idMauSac) {
        List<SanPhamChiTiet> listChiTietSanPham1 = sanPhamChiTietService.getAllbyIdSPAndIdMS(Long.valueOf(idSanPham),
                Long.valueOf(idMauSac));
        return listChiTietSanPham1;
    }


    @GetMapping("/lien-he")
    public String lienHe(
            Model model) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("checkDangNhap", "true");
//            model.addAttribute("soLuongSPGioHangCT",
//                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }
        return "/customer/lien-he";
    }

}
