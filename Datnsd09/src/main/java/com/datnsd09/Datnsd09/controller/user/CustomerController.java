package com.datnsd09.Datnsd09.controller.user;


import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.config.payment.VNPayConfig;
import com.datnsd09.Datnsd09.entity.*;
import com.datnsd09.Datnsd09.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
    private HoaDonService hoaDonService;

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
        return formatter.format(giaHienHanh) + " VNĐ";
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
    public String deleteCart(@PathVariable("id") Long id) {
        gioHangChiTietService.deleteById(id);
        return "redirect:/user/cart";
    }

    @GetMapping("/user/cart/update/{id}")
    public String updateCart(
            @PathVariable("id") Long id,
            @RequestParam("soLuong") String soLuong
    ) {
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

    @PostMapping("/user/gio-hang-chi-tiet/add-fast/{idChiTietSpAdd}/{soLuongAdd}")
    public String addGioHangChiTietNhanh(
            @PathVariable String idChiTietSpAdd,
            @PathVariable String soLuongAdd) {
        String[] optionArray = idChiTietSpAdd.split(",");
        List<String> listIdString = Arrays.asList(optionArray);
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        gioHangChiTietService.save(khachHang.getGioHang().getId(), listIdString, Integer.valueOf(soLuongAdd));
        return "redirect:/user/cart";
    }


    @GetMapping("/user/checkout")
    public String checkout(
            @RequestParam String options,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        String[] optionArray = options.split(",");
        List<String> listIdString = Arrays.asList(optionArray);

        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietService.findAllById(listIdString, khachHang.getGioHang().getId())) {
            if (gioHangChiTiet.getSoLuong() > sanPhamChiTietService.getById(gioHangChiTiet.getChiTietSanPham().getId()).getSoLuong()) {
                redirectAttributes.addFlashAttribute("checkSoLuongDB", "true");
                return "redirect:/user/cart";
            }
        }

        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.findAllById(listIdString,
                khachHang.getGioHang().getId());
        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
        List<DiaChi> diaChi = diaChiService.getAllByTaiKhoan(idKhachHang);
        model.addAttribute("listVoucher", voucherService.fillAllDangDienRa());
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("soLuongSPGioHangCT",
                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        if (khachHang.getLstDiaChi() == null || khachHang.getLstDiaChi().size() == 0) {
            model.addAttribute("checkDiaChi", "DiaChiNull");
        } else {
            model.addAttribute("checkDiaChi", "DiaChi");
            model.addAttribute("listDiaChi", diaChi);
            if (diaChi.size() == 5) {
                model.addAttribute("checkButtonAdd", "true");
                model.addAttribute("soDiaChi", diaChi.size());
            } else {
                model.addAttribute("checkButtonAdd", "false");
                model.addAttribute("soDiaChi", diaChi.size());
            }
        }
        return "/customer/checkout";
    }

    @PostMapping("/user/checkout/add")
    public String addHoaDon(
            @RequestParam("idGioHangChiTiet") String idGioHangChiTiet,
            @RequestParam("tongTien") String tongTien,
            @RequestParam("tienGiam") String tienGiam,
            @RequestParam("tongTienAndSale") String tongTienAndSale,
            @RequestParam("hoVaTen") String hoVaTen,
            @RequestParam("soDienThoai") String soDienThoai,
            @RequestParam("tienShip") String tienShip,
            @RequestParam("email") String email,
            @RequestParam("voucher") String voucher,
            @RequestParam("diaChiCuThe") String diaChiCuThe,
            @RequestParam("ghiChu") String ghiChu,
            @RequestParam("phuongXaID") String phuongXaID,
            @RequestParam("quanHuyenID") String quanHuyenID,
            @RequestParam("thanhPhoID") String thanhPhoID,
            @RequestParam("trangThaiLuuDC") String trangThaiLuuDC,
            @RequestParam String paymentMethod,
            RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        String[] optionArray = idGioHangChiTiet.split(",");

        // Remove non-numeric characters from the currency strings
        tongTien = tongTien.replaceAll("[^\\d]", "");
        tienGiam = tienGiam.replaceAll("[^\\d]", "");
        tongTienAndSale = tongTienAndSale.replaceAll("[^\\d]", "");
        tienShip = tienShip.replaceAll("[^\\d]", "");

        KhachHang khachHang = khachHangService.getById(idKhachHang);
        List<String> listIdString = Arrays.asList(optionArray);
        for (GioHangChiTiet gioHangChiTiet : gioHangChiTietService.findAllById(listIdString, khachHang.getGioHang().getId())) {
            if (gioHangChiTiet.getSoLuong() > sanPhamChiTietService.getById(gioHangChiTiet.getChiTietSanPham().getId()).getSoLuong()) {
                redirectAttributes.addFlashAttribute("checkSoLuongDB", "true");
                return "redirect:/user/checkout?options=" + idGioHangChiTiet;
            }
        }
        if (trangThaiLuuDC.equals("0")) {
            Date date = new Date();
            DiaChi diaChi = new DiaChi();
            diaChi.setPhuongXa(phuongXaID);
            diaChi.setQuanHuyen(quanHuyenID);
            diaChi.setThanhPho(thanhPhoID);
            diaChi.setDiaChiCuThe(diaChiCuThe);
            diaChi.setTrangThai(0);
            diaChi.setNgayTao(date);
            diaChi.setNgaySua(date);
            diaChi.setKhachHang(KhachHang.builder().id(idKhachHang).build());
            diaChiService.save(diaChi);
        }
        if ("vnpay".equals(paymentMethod)) {
            String vnp_ReturnUrl = "http://localhost:8080/callback";
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";
            long amount = Long.valueOf(tongTienAndSale) * 100;
            String bankCode = "NCB";

            String vnp_TxnRef = VNPayConfig.getRandomNumber(5);
            String vnp_IpAddr = "127.0.0.1";

            String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_BankCode", bankCode);
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
            System.out.println(paymentUrl);
            gioHangChiTietService.addHoaDon(listIdString, Long.valueOf(tongTien), Long.valueOf(tongTienAndSale), hoVaTen,
                    soDienThoai, tienShip, tienGiam, email, voucher, diaChiCuThe, ghiChu, khachHang, phuongXaID, quanHuyenID,
                    thanhPhoID, khachHang.getGioHang().getId(), new Date());
            return "redirect:" + paymentUrl;
        }
        else if ("cash".equals(paymentMethod)){
            gioHangChiTietService.addHoaDon(listIdString, Long.valueOf(tongTien), Long.valueOf(tongTienAndSale), hoVaTen,
                    soDienThoai, tienShip, tienGiam, email, voucher, diaChiCuThe, ghiChu, khachHang, phuongXaID, quanHuyenID,
                    thanhPhoID, khachHang.getGioHang().getId(), null );
        }
        return "redirect:/user/thankyou";
    }


    @GetMapping("/callback")
    public String vnPayCallback(@RequestParam Map<String, String> allParams,

                                RedirectAttributes redirectAttributes) {
        String vnp_ResponseCode = allParams.get("vnp_ResponseCode");
        String vnp_TxnRef = allParams.get("vnp_TxnRef");

        HoaDon hd = hoaDonService.findByMa(vnp_TxnRef);

        if ("00".equals(vnp_ResponseCode)) { // Kiểm tra mã phản hồi thành công từ VNPay
            return "redirect:/user/thankyou";
        } else {
            hd.setTrangThai(-1); // Cập nhật trạng thái thất bại
            hoaDonService.saveOrUpdate(hd);

            redirectAttributes.addFlashAttribute("message", "Thanh toán thất bại. Vui lòng thử lại.");
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }
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
        model.addAttribute("soLuongSPGioHangCT",
                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        return "/customer/thong-tin-khach-hang";
    }

    @PostMapping("/user/thong-tin-khach-hang/update")
    public String updateInfo(@Valid @ModelAttribute("khachHang") KhachHang khachHang,
                             BindingResult result, Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            KhachHang kh = khachHangService.getById(idKhachHang);
            model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(kh.getGioHang().getId()));
            return "/customer/thong-tin-khach-hang";
        } else if (!khachHang.isValidNgaySinh()) {
            KhachHang kh = khachHangService.getById(idKhachHang);
            model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(kh.getGioHang().getId()));
            model.addAttribute("checkNgaySinh", "Năm sinh phải lớn 1900");
            return "/customer/thong-tin-khach-hang";
        } else if (!khachHangService.checkEmailSua(khachHang.getId(), khachHang.getEmail())) {
            KhachHang kh = khachHangService.getById(idKhachHang);
            model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(kh.getGioHang().getId()));
            model.addAttribute("checkEmailTrung", "Email đã được đăng ký");
            return "/customer/thong-tin-khach-hang";
        } else {
            khachHang.setNgaySua(new Date());
//            khachHang.setVaiTro(VaiTro.builder().id(Long.valueOf(2)).build());
            redirectAttributes.addFlashAttribute("checkModal", "modal");
            khachHangService.update(khachHang);
        }
        return "redirect:/user/thong-tin-khach-hang";
    }


    @GetMapping("/tra-cuu-don-hang")
    public String traCuuDonHang(Model model) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("checkDangNhap", "true");
            model.addAttribute("soLuongSPGioHangCT",
                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }
        return "/customer/tra-cuu-don-hang";
    }





    @GetMapping("/user/thankyou")
    public String thankYou(Model model) {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
//            model.addAttribute("soLuongSPGioHangCT",
//                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        return "/customer/thankyou";
    }

    @GetMapping("/about")
    public String about(
            Model model) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("checkDangNhap", "true");
            model.addAttribute("soLuongSPGioHangCT",
                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }
        return "/customer/about";
    }

    @GetMapping("/chinh-sach")
    public String chinhSach(Model model) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("checkDangNhap", "true");
            model.addAttribute("soLuongSPGioHangCT",
                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }
        return "/customer/chinh-sach";
    }

    @PostMapping("/user/dia-chi/add")
    public String adđDiaChi(
            @RequestParam("phuongXaID") String phuongXa,
            @RequestParam("quanHuyenID") String quanHuyen,
            @RequestParam("thanhPhoID") String thanhPho,
            @RequestParam("diaChiCuThe") String diaChiCuThe) {
        Date date = new Date();
        DiaChi diaChi = new DiaChi();
        diaChi.setPhuongXa(phuongXa);
        diaChi.setQuanHuyen(quanHuyen);
        diaChi.setThanhPho(thanhPho);
        diaChi.setDiaChiCuThe(diaChiCuThe);
        diaChi.setTrangThai(1);
        diaChi.setNgayTao(date);
        diaChi.setNgaySua(date);
        diaChi.setKhachHang(KhachHang.builder().id(idKhachHang).build());
        diaChiService.save(diaChi);
        return "redirect:/user/cart";
    }

    @GetMapping("/user/dia-chi/delete/{id}")
    public String deleteDiaChiKhachHang(
            @PathVariable("id") Long idDiaChi,
            RedirectAttributes redirectAttributes) {
        diaChiService.deleteById(idDiaChi);
        redirectAttributes.addFlashAttribute("checkModal", "modal");
        return "redirect:/user/dia-chi";
    }

    @PostMapping("/user/dia-chi/update")
    public String updateDiaChi(
            @RequestParam("idDiaChi") Long idDiaChi,
            @RequestParam("phuongXa") String phuongXa,
            @RequestParam("quanHuyen") String quanHuyen,
            @RequestParam("thanhPho") String thanhPho,
            @RequestParam("diaChiCuThe") String diaChiCuThe,
            @RequestParam("trangThai") Integer trangThai) {
        if (trangThai == 0) {
            List<DiaChi> listDiaChi = diaChiService.getAllTrangThai(0);
            DiaChi diaChiNew = new DiaChi();
            for (DiaChi diaChiUpdate : listDiaChi) {
                diaChiNew.setId(diaChiUpdate.getId());
                diaChiNew.setPhuongXa(diaChiUpdate.getPhuongXa());
                diaChiNew.setQuanHuyen(diaChiUpdate.getQuanHuyen());
                diaChiNew.setThanhPho(diaChiUpdate.getThanhPho());
                diaChiNew.setDiaChiCuThe(diaChiUpdate.getDiaChiCuThe());
                diaChiNew.setTrangThai(1);
                diaChiNew.setNgayTao(diaChiUpdate.getNgayTao());
                diaChiNew.setNgaySua(diaChiUpdate.getNgaySua());
                diaChiNew.setKhachHang(diaChiUpdate.getKhachHang());
                diaChiService.update(diaChiNew);
            }
        }
        Date date = new Date();
        DiaChi diaChi = new DiaChi();
        diaChi.setId(idDiaChi);
        diaChi.setPhuongXa(phuongXa);
        diaChi.setQuanHuyen(quanHuyen);
        diaChi.setThanhPho(thanhPho);
        diaChi.setDiaChiCuThe(diaChiCuThe);
        diaChi.setTrangThai(trangThai);
        diaChi.setNgayTao(date);
        diaChi.setNgaySua(date);
        diaChi.setKhachHang(KhachHang.builder().id(idKhachHang).build());
        diaChiService.update(diaChi);

        return "redirect:/user/cart";
    }

    @GetMapping("/user/dia-chi")
    public String diaChiKhachHang(
            Model model) {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        model.addAttribute("soLuongSPGioHangCT",
                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        List<DiaChi> diaChi = diaChiService.getAllByTaiKhoan(idKhachHang);
        model.addAttribute("listDiaChi", diaChi);
        if (diaChi.size() == 5) {
            model.addAttribute("checkButtonAdd", "true");
            model.addAttribute("soDiaChi", diaChi.size());
        } else {
            model.addAttribute("checkButtonAdd", "false");
            model.addAttribute("soDiaChi", diaChi.size());
        }
        return "/customer/dia-chi";
    }

    @GetMapping("/lien-he")
    public String lienHe(Model model) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("checkDangNhap", "true");
            model.addAttribute("soLuongSPGioHangCT",
                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }
        return "/customer/lien-he";
    }

    @GetMapping("/lien-he/add")
    public String addLienHe(@RequestParam("hoTen") String hoTen,
                            @RequestParam("email") String email,
                            @RequestParam("chuDe") String chuDe,
                            @RequestParam("tinNhan") String tinNhan,
                            RedirectAttributes redirectAttributes) {
        khachHangService.guiLieuHe(hoTen, email, chuDe, tinNhan);
        redirectAttributes.addFlashAttribute("checkTBLienHe", true);
        return "redirect:/lien-he";
    }



    @GetMapping("/user/mat-khau")
    public String doiMatKhau(Model model) {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        model.addAttribute("soLuongSPGioHangCT",
                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        return "/customer/doi-mat-khau-khach-hang";
    }

    @GetMapping("/user/mat-khau/update")
    public String updateMatKhau(@RequestParam("matKhauCu") String matKhauCu,
                                @RequestParam("xacNhanmatKhauMoi") String xacNhanmatKhauMoi,
                                Model model, RedirectAttributes redirectAttributes) {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        if (!passwordEncoder.matches(matKhauCu, khachHang.getMatKhau())) {
            model.addAttribute("messages", "Mật khẩu cũ không chính xác, vui lòng thử lại");
        } else {
            khachHang.setNgaySua(new Date());
            khachHang.setMatKhau(passwordEncoder.encode(xacNhanmatKhauMoi));
            khachHangService.update(khachHang);
            redirectAttributes.addFlashAttribute("checkModal", "modal");
            return "redirect:/user/mat-khau";
        }
        return "/customer/doi-mat-khau-khach-hang";
    }
    @GetMapping("/user/don-mua")
    public String donMua(
            Model model) {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        model.addAttribute("soLuongSPGioHangCT",
                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        model.addAttribute("listAllHoaDon", hoaDonService.getAllHoaDonByTaiKhoanOrderByNgaySua(idKhachHang));
        model.addAttribute("listHDChoXacNhan", hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idKhachHang, 0));
        model.addAttribute("listHDChoGiao", hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idKhachHang, 1));
        model.addAttribute("listHDDangGiao", hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idKhachHang, 2));
        model.addAttribute("listHDHoanThanh", hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idKhachHang, 3));
        model.addAttribute("listHDDaHuy", hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idKhachHang, 5));
        model.addAttribute("listHDTraHang", hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idKhachHang, 6));
        return "/customer/don-mua";
    }

    @PostMapping("/user/don-mua/mua-lai")
    public String muaLaiDonMua(
            @RequestParam String options) {
        String[] optionArray = options.split(",");
        List<String> listIdString = Arrays.asList(optionArray);
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        gioHangChiTietService.save(khachHang.getGioHang().getId(), listIdString, 1);
        return "redirect:/user/cart";
    }

    @GetMapping("/user/huy-don/{idHoaDon}")
    public String huyDon(
            @PathVariable("idHoaDon") Long idHoaDon,
            @RequestParam("ghiChu") String ghiChu,
            Model model,
            RedirectAttributes redirectAttributes) {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        model.addAttribute("soLuongSPGioHangCT",
                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        HoaDon hoaDon = hoaDonService.findById(idHoaDon);
        hoaDon.setNgaySua(new Date());
        hoaDon.setTrangThai(5);

        hoaDonService.saveOrUpdate(hoaDon);
        return "redirect:/user/don-mua";
    }

    @GetMapping("/user/don-mua/{idHoaDon}")
    public String donMuaChiTiet(
            @PathVariable("idHoaDon") Long idHoaDon,
            Model model) {
        KhachHang khachHang = khachHangService.getById(idKhachHang);
        model.addAttribute("soLuongSPGioHangCT",
                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        model.addAttribute("byHoaDon", hoaDonService.findById(idHoaDon));
        model.addAttribute("listLichSuHoaDon", hoaDonService.findByIdhdNgaySuaAsc(idHoaDon));
        System.out.println(hoaDonService.findById(idHoaDon));
        return "/customer/don-mua-chi-tiet";
    }

    @GetMapping("/tra-cuu-don-hang/{idHoaDon}/{email}")
    public String detailTraCuuHoaDon(
            @RequestParam("maDonHang") String maDonHang,
            @RequestParam("sdt") String sdt,
            Model model, RedirectAttributes attributes) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("checkDangNhap", "true");
            model.addAttribute("soLuongSPGioHangCT",
                    gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        } else {
            model.addAttribute("checkDangNhap", "false");
        }

        HoaDon hoaDon = hoaDonService.finByHoaDonMaHDSdt(maDonHang, sdt);
        if (hoaDon == null) {
            attributes.addFlashAttribute("checkLoiTraCuuHoaDon",
                    "Không tìm thấy đơn hàng. Vui lòng kiểm tra lại mã đơn hàng và số điện thoại.");
            return "redirect:/tra-cuu-don-hang";
        } else {
            model.addAttribute("byHoaDon", hoaDonService.findById(hoaDon.getId()));
            model.addAttribute("listLichSuHoaDon", hoaDonService.findByIdhdNgaySuaAsc(hoaDon.getId()));
        }
        return "/customer/detail-tra-cuu-don-hang";
    }
}
