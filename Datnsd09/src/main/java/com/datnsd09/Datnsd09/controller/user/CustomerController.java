package com.datnsd09.Datnsd09.controller.user;


import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.entity.*;
import com.datnsd09.Datnsd09.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.NumberFormat;
import java.util.*;

@Controller
public class CustomerController {

    private Long idKhachHang;

    @Autowired
    DiaChiService diaChiService;

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
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private PrincipalCustom principalCustom = new PrincipalCustom();


    @GetMapping("/logout/true")
    public String logout() {
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
            model.addAttribute("soLuongSPGioHangCT",
                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }
        return "/customer/home";

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
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getAllDangHoatDong().get(size);
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
            model.addAttribute("soLuongSPGioHangCT",
                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
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
        model.addAttribute("formattedPrice", formatCurrency(sanPhamChiTiet.getGiaHienHanh()));

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

    @GetMapping("/user/shop-single/{id}")
    public String shopSingle(Model model, @PathVariable("id") String id) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getAllById(Long.valueOf(id)).get(0);
        List<SanPhamChiTiet> listCTSP = sanPhamChiTietService.getAllById(Long.valueOf(id));
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        model.addAttribute("soLuongSPGioHangCT",
                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        model.addAttribute("chiTietSp", sanPhamChiTiet);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listTop5HDCT", hoaDonChiTietService.finTop5HDCT());
        model.addAttribute("formattedPrice", formatCurrency(sanPhamChiTiet.getGiaHienHanh()));
        return "/customer/shop-single";
    }

    public static String formatCurrency(long giaHienHanh) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(giaHienHanh) + " VND";
    }

    @GetMapping("/user/shop-single/get-so-luong")
    @ResponseBody
    public Integer getSoLuongGHCT() {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        Integer soLuong = gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId());
        return soLuong;
      //  return null;
    }

    @GetMapping("/user/shop-single/check-so-luong/{idCTSP}")
    @ResponseBody
    public Integer checkSoLuongSpEndGHCT(@PathVariable String idCTSP) {
        try {
            Long id = Long.valueOf(idCTSP);
            GioHangChiTiet gioHangChiTiet = gioHangChiTietService.fillByIdCTSP(id);

            if (gioHangChiTiet != null) {
                return gioHangChiTiet.getSoLuong();
            } else {
                return 0;
            }
        } catch (NumberFormatException e) {
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }


    //giohang
//    @GetMapping("/user/cart")
//    public String cart(
//            Model model) {
//        KhachHang khachHang = khachHangService.getById(idKhachHang);
//        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService
//                .fillAllByIdGioHang(khachHang.getGioHang().getId());
//        model.addAttribute("soLuongSPGioHangCT",
//                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
//        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
//        return "/customer/cart";
//    }
    @GetMapping("/user/cart")
    public String cart(Model model) {
//        // Giả sử bạn có cách lấy ID khách hàng (ví dụ: từ phiên đăng nhập)
//        Long idKhachHang = 1L; // Thay thế bằng cách lấy ID khách hàng thực tế

        // Lấy thông tin khách hàng từ dịch vụ
        KhachHang khachHang = khachHangService.getById(idKhachHang);

        if (khachHang == null) {
            // Xử lý trường hợp khachHang là null (ví dụ: chuyển hướng đến trang lỗi hoặc đăng nhập)
            return "redirect:/error"; // Hoặc một trang lỗi phù hợp
        }

        // Lấy giỏ hàng của khách hàng
        GioHang gioHang = khachHang.getGioHang();

        if (gioHang == null) {
            // Xử lý khi giỏ hàng của khách hàng chưa tồn tại
            model.addAttribute("soLuongSPGioHangCT", 0);
            model.addAttribute("listGioHangChiTiet", new ArrayList<>());
            return "/customer/cart"; // Trả về trang giỏ hàng trống
        }

        // Lấy danh sách chi tiết giỏ hàng từ dịch vụ
        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.fillAllByIdGioHang(gioHang.getId());

        // Đặt các thuộc tính cho mô hình để sử dụng trong view
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(gioHang.getId()));
        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);

        // Trả về tên của view
        return "/customer/cart";
    }

    @GetMapping("/user/cart/detele/{id}")
    public String deleteCart(@PathVariable("id") Long id){
        gioHangChiTietService.deleteById(id);
        return "redirect:/user/cart";
    }

    @GetMapping("/user/cart/update/{id}")
    public String updateCart(
            @PathVariable("id") Long id,
            @RequestParam("soLuong") String soLuong
    ){
        GioHangChiTiet gioHangChiTiet = gioHangChiTietService.fillById(id);
        gioHangChiTiet.setSoLuong(Integer.valueOf(soLuong));
        gioHangChiTietService.update(gioHangChiTiet);
        return "redirect:/user/cart";
    }

    @PostMapping("/user/gio-hang-chi-tiet/add/{idChiTietSpAdd}/{soLuongAdd}")
    public String addGioHangChiTiet(
            @PathVariable String idChiTietSpAdd,
            @PathVariable String soLuongAdd) {
        String[] optionArray = idChiTietSpAdd.split(",");
        List<String> listIdString = Arrays.asList(optionArray);
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        gioHangChiTietService.save(khachHang.getGioHang().getId(), listIdString, Integer.valueOf(soLuongAdd));
        return "redirect:/shop";
    }


    ////////sau
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
//
//    @GetMapping("/tra-cuu-don-hang")
//    public String traCuuDonHang(Model model) {
//        if (principalCustom.getCurrentUserNameCustomer() != null) {
//            KhachHang khachHang = khachHangService.getById(idKhachHang);
//            model.addAttribute("checkDangNhap", "true");
////            model.addAttribute("soLuongSPGioHangCT",
////                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
//        } else {
//            model.addAttribute("checkDangNhap", "false");
//        }
//        return "/customer/tra-cuu-don-hang";
//    }
//
//    @GetMapping("/user/thankyou")
//    public String thankYou(Model model){
//        KhachHang khachHang =khachHangService.getById(idKhachHang);
////            model.addAttribute("soLuongSPGioHangCT",
////                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
//        return "/customer/thankyou";
//    }
//
//    @GetMapping("/chinh-sach")
//    public String chinhSach(Model model) {
//        if (principalCustom.getCurrentUserNameCustomer() != null) {
//            KhachHang khachHang = khachHangService.getById(idKhachHang);
//            model.addAttribute("checkDangNhap", "true");
//        } else {
//            model.addAttribute("checkDangNhap", "false");
//        }
//        return "/customer/chinh-sach";
//    }
//
//    @PostMapping("/user/dia-chi/add")
//    public String adđDiaChi(
//            @RequestParam("phuongXaID") String phuongXa,
//            @RequestParam("quanHuyenID") String quanHuyen,
//            @RequestParam("thanhPhoID") String thanhPho,
//            @RequestParam("diaChiCuThe") String diaChiCuThe) {
//        Date date = new Date();
//        DiaChi diaChi = new DiaChi();
//        diaChi.setPhuongXa(phuongXa);
//        diaChi.setQuanHuyen(quanHuyen);
//        diaChi.setThanhPho(thanhPho);
//        diaChi.setDiaChiCuThe(diaChiCuThe);
//        diaChi.setTrangThai(1);
//        diaChi.setNgayTao(date);
//        diaChi.setNgaySua(date);
//        diaChi.setKhachHang(KhachHang.builder().id(idKhachHang).build());
//        diaChiService.save(diaChi);
//        return "redirect:/user/cart";
//    }
//
//    @PostMapping("/user/dia-chi/update")
//    public String updateDiaChi(
//            @RequestParam("idDiaChi") Long idDiaChi,
//            @RequestParam("phuongXa") String phuongXa,
//            @RequestParam("quanHuyen") String quanHuyen,
//            @RequestParam("thanhPho") String thanhPho,
//            @RequestParam("diaChiCuThe") String diaChiCuThe,
//            @RequestParam("trangThai") Integer trangThai) {
//        if (trangThai == 0) {
//            List<DiaChi> listDiaChi = diaChiService.getAllTrangThai(0);
//            DiaChi diaChiNew = new DiaChi();
//            for (DiaChi diaChiUpdate : listDiaChi) {
//                diaChiNew.setId(diaChiUpdate.getId());
//                diaChiNew.setPhuongXa(diaChiUpdate.getPhuongXa());
//                diaChiNew.setQuanHuyen(diaChiUpdate.getQuanHuyen());
//                diaChiNew.setThanhPho(diaChiUpdate.getThanhPho());
//                diaChiNew.setDiaChiCuThe(diaChiUpdate.getDiaChiCuThe());
//                diaChiNew.setTrangThai(1);
//                diaChiNew.setNgayTao(diaChiUpdate.getNgayTao());
//                diaChiNew.setNgaySua(diaChiUpdate.getNgaySua());
//                diaChiNew.setKhachHang(diaChiUpdate.getKhachHang());
//                diaChiService.update(diaChiNew);
//            }
//        }
//        Date date = new Date();
//        DiaChi diaChi = new DiaChi();
//        diaChi.setId(idDiaChi);
//        diaChi.setPhuongXa(phuongXa);
//        diaChi.setQuanHuyen(quanHuyen);
//        diaChi.setThanhPho(thanhPho);
//        diaChi.setDiaChiCuThe(diaChiCuThe);
//        diaChi.setTrangThai(trangThai);
//        diaChi.setNgayTao(date);
//        diaChi.setNgaySua(date);
//        diaChi.setKhachHang(KhachHang.builder().id(idKhachHang).build());
//        diaChiService.update(diaChi);
//
//        return "redirect:/user/cart";
//    }
//
//    @GetMapping("/user/dia-chi")
//    public String diaChiKhachHang(
//            Model model) {
//        KhachHang khachHang = khachHangService.getById(idKhachHang);
////        model.addAttribute("soLuongSPGioHangCT",
////                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
//        List<DiaChi> diaChi = diaChiService.getAllByTaiKhoan(idKhachHang);
//        model.addAttribute("listDiaChi", diaChi);
//        if (diaChi.size() == 5) {
//            model.addAttribute("checkButtonAdd", "true");
//            model.addAttribute("soDiaChi", diaChi.size());
//        } else {
//            model.addAttribute("checkButtonAdd", "false");
//            model.addAttribute("soDiaChi", diaChi.size());
//        }
//        return "/customer/dia-chi";
//    }
//    @GetMapping("/lien-he")
//    public String lienHe(Model model) {
//        if (principalCustom.getCurrentUserNameCustomer() != null) {
//            KhachHang khachHang = khachHangService.getById(idKhachHang);
//            model.addAttribute("checkDangNhap", "true");
////            model.addAttribute("soLuongSPGioHangCT",
////                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
//        } else {
//            model.addAttribute("checkDangNhap", "false");
//        }
//        return "/customer/lien-he";
//    }
//
//    @GetMapping("/lien-he/add")
//    public String addLienHe(@RequestParam("hoTen") String hoTen,
//                            @RequestParam("email") String email,
//                            @RequestParam("chuDe") String chuDe,
//                            @RequestParam("tinNhan") String tinNhan,
//                            RedirectAttributes redirectAttributes) {
//        khachHangService.guiLieuHe(hoTen, email, chuDe, tinNhan);
//        redirectAttributes.addFlashAttribute("checkTBLienHe", true);
//        return "redirect:/lien-he";
//    }


}
