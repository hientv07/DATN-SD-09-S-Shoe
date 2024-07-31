package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.config.UserInfoUserDetails;
import com.datnsd09.Datnsd09.config.payment.VNPayConfig;
import com.datnsd09.Datnsd09.entity.*;
import com.datnsd09.Datnsd09.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/ban-hang-tai-quay")
public class BanHangController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    SanPhamChiTietService chiTietSanPhamSerivce;

    @Autowired
    KhachHangService khachHangService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    DiaChiService diaChiService;

    @Autowired
    VoucherService voucherService;

    @Autowired
    HttpServletRequest request;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping("/hoa-don")
    public String home() {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            request.setAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        return "/admin/ban-hang-admin";
    }
    void addKhachLe() {
        if (khachHangService.findKhachLe() == null) {
            khachHangService.addKhachLe();
        }
    }

    @PostMapping("/hoa-don/add")
    public String taoHoaDon(RedirectAttributes redirectAttributes) {
        addKhachLe();
        if (hoaDonService.countHoaDonTreo() < 5) {
            HoaDon hd = new HoaDon();
            hd.setTrangThai(-1); // view 5 hoa don
            hd.setNgaySua(new Date());
            hd.setNgayTao(new Date());
            hd.setKhachHang(khachHangService.findKhachLe());
            hd.setPhiShip((long) 0);
            hd.setLoaiHoaDon(2);
            hd.setTongTien((long) 0);
            hd.setTongTienKhiGiam((long) 0);
            hd.setTienGiam((long)0);
            hoaDonService.saveOrUpdate(hd);
            hd.setMaHoaDon("HD" + hd.getId());
            hoaDonService.saveOrUpdate(hd);

            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
        }
        return "redirect:/ban-hang-tai-quay/hoa-don";
    }

    void thongBao(RedirectAttributes redirectAttributes, String thongBao, int trangThai) {
        if (trangThai == 0) {
            redirectAttributes.addFlashAttribute("checkThongBao", "thatBai");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        } else if (trangThai == 1) {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        } else {

            redirectAttributes.addFlashAttribute("checkThongBao", "canhBao");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        }

    }


    @GetMapping("/hoa-don/{id}")
    public String hoaDon(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            request.setAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        chiTietSanPhamSerivce.checkSoLuongBang0();
        KhachHang tk = new KhachHang();
        tk.setGioiTinh(0);
        model.addAttribute("khachHang", tk);
        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        request.setAttribute("lstHdct", hoaDonChiTietService.findAll());
        request.setAttribute("lstCtsp", chiTietSanPhamSerivce.fillAllDangHoatDongLonHon0());
        request.setAttribute("lstTaiKhoan", khachHangService.getAll());
        request.setAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(id).getKhachHang().getId()));
        request.setAttribute("listVoucher", voucherService.fillAllDangDienRa());

        // request.setAttribute("checkThongBao", "thatBai");
        HoaDon hd = hoaDonService.findById(id);

        Boolean ctb = false;

        if (hd.getVoucher() != null && hd.getTrangThai() != 6) {
            if (hd.tongTienHoaDonDaNhan() < hd.getVoucher().getGiaTriDonToiThieu().longValue()) {

                hd.setVoucher(null);
                hd.setTongTien(hd.tongTienHoaDonDaNhan());
                hd.setTongTienKhiGiam(hd.tongTienHoaDonDaNhan());
                hoaDonService.saveOrUpdate(hd);
                ctb=true;
                // thongBao(redirectAttributes, "Đã xóa mã giảm giá vì chưa đạt giá trị đơn tối thiếu", 0);
            }
        }
        if(ctb){
            request.setAttribute("thongBao", "Đã xóa mã giảm giá vì chưa đạt giá trị đơn tối thiếu");
            request.setAttribute("checkThongBao", "thatBai");
        }

        request.setAttribute("hoaDon", hd);

        return "/admin/hoa-don-chi-tiet";
    }

    void updateSoLuongRollBack(Long idhdc) {
        HoaDon hd = hoaDonService.findById(idhdc);
        List<SanPhamChiTiet> lstCtsp = chiTietSanPhamSerivce.getAll();
        for (HoaDonChiTiet hoaDonChiTiet : hd.getLstHoaDonChiTiet()) {
            for (SanPhamChiTiet ctsp : lstCtsp) {
                if (hoaDonChiTiet.getSanPhamChiTiet().getId() == ctsp.getId()) {
                    ctsp.setSoLuong(ctsp.getSoLuong() + hoaDonChiTiet.getSoLuong());
                    chiTietSanPhamSerivce.update(ctsp);
                }
            }
        }

        if (hd.getVoucher() != null) {
            Voucher v = hd.getVoucher();
            v.setSoLuong(v.getSoLuong().add(new BigDecimal(1)));
            voucherService.save(v);
        }

    }

    void sendMail(HoaDon hd) {
        if (hd.getNguoiNhan() != null) {
            if (hd.getTrangThai() == 1 || hd.getTrangThai() == 2 || hd.getTrangThai() == 3 || hd.getTrangThai() == 5
                    || hd.getTrangThai() == 6) {
                String url = request.getRequestURL().toString();
                url = url.replace(request.getServletPath(), "");
                hoaDonService.guiHoaDonDienTu(hd, url);
            }
        }
    }

    @PostMapping("/hoa-don/delete/{id}")
    public String delete(RedirectAttributes redirectAttributes, @PathVariable Long id, @RequestParam String ghiChu) {
        HoaDon hd = hoaDonService.findById(id);
        if(hd.getTrangThai()==1){
            updateSoLuongRollBack(id);
        }
        hd.setTrangThai(5);
        sendMail(hd);

        hoaDonService.saveOrUpdate(hd);
        thongBao(redirectAttributes, "Thành công", 1);
        if (hd.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + id;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + id;
        }
    }

    @PostMapping("/hoa-don/update")
    public String updateHoaDon(@RequestParam Long phiShip,@RequestParam Long idhdc,
                               @RequestParam String inputHoVaTen, @RequestParam String inputSoDienThoai,
                               @RequestParam String inputDcct, @RequestParam String inputGhiChu,
                               @RequestParam(defaultValue = "") String thanhPho,
                               @RequestParam(defaultValue = "") String quanHuyen, @RequestParam(defaultValue = "") String phuongXa) {
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setNgaySua(new Date());
        hd.setPhiShip(phiShip);
        hd.setSdtNguoiNhan(inputSoDienThoai);
        hd.setNguoiNhan(inputHoVaTen);
        hd.setDiaChiNguoiNhan(inputDcct);
        hd.setGhiChu(inputGhiChu);
        hd.setThanhPho(thanhPho);
        hd.setQuanHuyen(quanHuyen);
        hd.setPhuongXa(phuongXa);
        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
    }

    @PostMapping("/hoa-don-chi-tiet/add")
    public String addHdct(@RequestParam Long idHoaDon, @RequestParam Long idCtsp,
                          RedirectAttributes redirectAttributes) {

        Boolean cr = true;
        HoaDonChiTiet hdct = new HoaDonChiTiet();

        HoaDon hoaDon = hoaDonService.findById(idHoaDon);
        hoaDon.setNgaySua(new Date());
        hoaDonService.saveOrUpdate(hoaDon);
        SanPhamChiTiet ctsp = chiTietSanPhamSerivce.getById(idCtsp);
        for (HoaDonChiTiet obj : hoaDonChiTietService.findAll()) {
            if (obj.getHoaDon() == hoaDon) {
                if (obj.getSanPhamChiTiet() == ctsp) {
                    hdct = obj;
                    cr = false;
                    break;
                }
            }
        }

        if (cr) {
            hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDon);
            hdct.setSanPhamChiTiet(ctsp);
            hdct.setSoLuong(1);
            hdct.setDonGia(ctsp.getGiaHienHanh());
        } else {
            if (ctsp.getSoLuong() > hdct.getSoLuong())
                hdct.setSoLuong(hdct.getSoLuong() + 1);
        }
        hdct.setTrangThai(0);
        hoaDonChiTietService.saveOrUpdate(hdct);
        System.out.println(idCtsp + "idctsp");
        System.out.println(idHoaDon + "idctsp");
        thongBao(redirectAttributes, "Thành công", 1);
        redirectAttributes.addFlashAttribute("batModal", "ok");
        if (hoaDon.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idHoaDon;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idHoaDon;
        }
    }

    @GetMapping("/hoa-don-chi-tiet/delete/{id}")
    public String deleteHdct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // HoaDon hd = hoaDonService.findById(id);
        HoaDon hd = hoaDonChiTietService.findById(id).getHoaDon();

        hd.setNgaySua(new Date());
        hoaDonService.saveOrUpdate(hd);
        hoaDonChiTietService.deleteById(id);
        thongBao(redirectAttributes, "Thành công", 1);
        if (hd.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }

    }

    @PostMapping("/hoa-don-chi-tiet/update")
    public String updateSoLuong(RedirectAttributes redirectAttributes,
                                @RequestParam(defaultValue = "") Integer soLuongEdit,
                                @RequestParam(defaultValue = "") Integer soLuongEditTra, @RequestParam Long idHdct) {
        HoaDonChiTiet hdct = hoaDonChiTietService.findById(idHdct);
        HoaDon hd = hdct.getHoaDon();
        hd.setNgaySua(new Date());
        HoaDonChiTiet hdctnew = new HoaDonChiTiet();

        hdctnew.setSoLuong(0);
        System.out.println(hd.getTrangThai() + "tthd");
        if (hd.getTrangThai() == 3) {

            for (HoaDonChiTiet hdctf : hd.getLstHoaDonChiTiet()) {
                if (hdctf.getSanPhamChiTiet() == hdct.getSanPhamChiTiet() && hdctf.getTrangThai() == 2) {
                    hdctnew = hdctf;
                    break;
                }
            }

            hdct.setSoLuong(hdct.getSoLuong() - soLuongEditTra);
            hdctnew.setHoaDon(hdct.getHoaDon());
            hdctnew.setSanPhamChiTiet(hdct.getSanPhamChiTiet());
            hdctnew.setSoLuong(hdctnew.getSoLuong() + soLuongEditTra);
            hdctnew.setTrangThai(2);
            hdctnew.setDonGia(hdct.getDonGia());
            hoaDonChiTietService.saveOrUpdate(hdctnew);
            hoaDonChiTietService.saveOrUpdate(hdct);

            if (hd.getTrangThai() == -1) {
                thongBao(redirectAttributes, "Thành công", 1);
                return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
            } else {
                thongBao(redirectAttributes, "Thành công", 1);
                return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
            }
        }
        if (soLuongEdit == 0) {
            hoaDonChiTietService.deleteById(idHdct);
        } else {
            hdct.setSoLuong(soLuongEdit);
            hdct.setTrangThai(0);
            hoaDonChiTietService.saveOrUpdate(hdct);
        }

        if (hd.getTrangThai() == -1) {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
        } else if (hd.getTrangThai() == 3) {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/doi-tra/" + hd.getId();

        } else {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }
    }

    @PostMapping("/hoa-don/add-khach-hang")
    public String addKhachHang(@RequestParam Long idTaiKhoan,@RequestParam Long idhdc, RedirectAttributes redirectAttributes) {
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setNgaySua(new Date());
        if (idTaiKhoan == -1) {
            hd.setKhachHang(khachHangService.findKhachLe());
            hd.setDiaChiNguoiNhan(null);
            hd.setThanhPho(null);
            hd.setQuanHuyen(null);
            hd.setPhuongXa(null);
            hd.setNguoiNhan(null);
            hd.setSdtNguoiNhan(null);
        } else {
            KhachHang kh = khachHangService.getById(idTaiKhoan);
            hd.setKhachHang(kh);
            hd.setNguoiNhan(kh.getHoVaTen());
            hd.setSdtNguoiNhan(kh.getSoDienThoai());

        }

        hoaDonService.saveOrUpdate(hd);
        thongBao(redirectAttributes, "Thành công", 1);
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @PostMapping("/hoa-don/add-dia-chi")
    public String addDiaChi(@RequestParam Long idDiaChi,@RequestParam Long idhdc, RedirectAttributes redirectAttributes) {
        System.out.println(idDiaChi + "==========");
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setNgaySua(new Date());
        DiaChi dc = diaChiService.getById(idDiaChi);
        hd.setDiaChiNguoiNhan(dc.getDiaChiCuThe());
        hd.setQuanHuyen(dc.getQuanHuyen());
        hd.setPhuongXa(dc.getPhuongXa());
        hd.setThanhPho(dc.getThanhPho());
        hoaDonService.saveOrUpdate(hd);
        thongBao(redirectAttributes, "Thành công", 1);
        if (hd.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
        }
    }

    @GetMapping("/hoa-don/bo-voucher/{id}")
    public String boChonVoucher(@PathVariable Long id) {
        HoaDon hd = hoaDonService.findById(id);
        hd.setVoucher(null);
        hd.setTongTienKhiGiam(hd.tongTienHoaDonDaNhan() + hd.getPhiShip());
        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
    }

    Boolean checkSlDb(HoaDon hd) {
        if (hd.getTrangThai() == 0 || hd.getTrangThai() == -1) {
            for (HoaDonChiTiet hdct : hd.getLstHoaDonChiTiet()) {
                if (hdct.getSoLuong() > chiTietSanPhamSerivce.getById(hdct.getSanPhamChiTiet().getId()).getSoLuong()) {
                    return false;
                }
            }
        }
        return true;
    }

    @PostMapping("/hoa-don/chuyen-nhanh")
    public String chuyenNhanh(
            @RequestParam Long idHoaDon,
            @RequestParam String ghiChu,
            RedirectAttributes redirectAttributes

    ) {

        HoaDon hd = hoaDonService.findById(idHoaDon);
        if (!checkSlDb(hd)) {
            thongBao(redirectAttributes, "Có sản phẩm vượt quá số lượng vui lòng kiểm tra lại", 0);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idHoaDon;
        }
        hd.setTrangThai(hd.getTrangThai() + 1);
        hd.setNgaySua(new Date());
        hoaDonService.saveOrUpdate(hd);

        System.out.println(ghiChu + "ghiChu");

        return "redirect:/admin/hoa-don/quan-ly";
    }

    @PostMapping("/hoa-don/thanh-toan")
    public String thanhToan(@RequestParam(defaultValue = "off") String treo,
                            @RequestParam(defaultValue = "off") String giaoHang, @RequestParam Long phiShip,@RequestParam Long idhdc,
                            @RequestParam Long giamGia, @RequestParam String inputHoVaTen, @RequestParam String inputSoDienThoai,
                            @RequestParam String inputDcct, @RequestParam String inputGhiChu,
                            @RequestParam(defaultValue = "") String thanhPho,
                            @RequestParam(defaultValue = "") String quanHuyen, @RequestParam(defaultValue = "") String phuongXa,
                            @RequestParam String voucherID, @RequestParam String ghiChuThanhToan,
                            @RequestParam String paymentMethod,
                            @RequestParam Map<String, String> allParams,
                            RedirectAttributes redirectAttributes,
                            @RequestParam(defaultValue = "") String luuDiaChi) throws UnsupportedEncodingException {

        HoaDon hd = hoaDonService.findById(idhdc);
        if (!checkSlDb(hd)) {
            thongBao(redirectAttributes, "Có sản phẩm vượt quá số lượng vui lòng kiểm tra lại", 0);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        }
        thongBao(redirectAttributes, "Thành công", 1);
        chiTietSanPhamSerivce.checkSoLuongBang0();
        hd.setNgaySua(new Date());
        System.out.println("ttttttttt" + thanhPho + quanHuyen + phuongXa);
        if (voucherID != "") {
            hd.setVoucher(voucherService.findById(Long.parseLong(voucherID)));
            hd.setTienGiam(giamGia);
        }

        if ("vnpay".equals(paymentMethod)) {
            String vnp_ReturnUrl = "http://localhost:8080/ban-hang-tai-quay/callback";
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";
            long amount = hd.tongTienHoaDonKhiGiam() * 100;
            String bankCode = "NCB";

            String vnp_TxnRef = hd.getMaHoaDon();
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

            return "redirect:" + paymentUrl;
        }
        switch (hd.getTrangThai()) {
            case -1:
                if (treo.equals("on")) {
                    hd.setTrangThai(4);

                } else if (giaoHang.equals("on")) {
                    // Giao hàng
                    hd.setTrangThai(1);
                    hd.setPhiShip(phiShip);
                    hd.setNgayThanhToan(new Date());
                    hd.setSdtNguoiNhan(inputSoDienThoai);
                    hd.setNguoiNhan(inputHoVaTen);
                    hd.setDiaChiNguoiNhan(inputDcct);
                    hd.setGhiChu(inputGhiChu);
                    hd.setThanhPho(thanhPho);
                    hd.setQuanHuyen(quanHuyen);
                    hd.setPhuongXa(phuongXa);
                    sendMail(hd);
                    if (luuDiaChi.equals("on") && hd.getKhachHang().getTenTaiKhoan() != null) {
                        if (hd.getKhachHang().getLstDiaChi().size() < 5) {
                            DiaChi dc = new DiaChi();
                            dc.setQuanHuyen(quanHuyen);
                            dc.setPhuongXa(phuongXa);
                            dc.setThanhPho(thanhPho);
                            dc.setDiaChiCuThe(inputDcct);
                            dc.setKhachHang(hd.getKhachHang());
                            dc.setNgaySua(new Date());
                            dc.setNgayTao(new Date());
                            dc.setTrangThai(1);
                            diaChiService.save(dc);
                        }
                    }
                } else {
                    // Hoàn thành
                    hd.setTrangThai(3);
                    hd.setNgayThanhToan(new Date());
                    hd.setNgaySua(new Date());
                    hd.setTongTien(hd.tongTienHoaDon());
                    hd.setTongTienKhiGiam(hd.tongTienHoaDon() - giamGia);
                    hd.setPhiShip((long) 0);
                    hd.setQuanHuyen(null);
                    hd.setThanhPho(null);
                    hd.setPhuongXa(null);
                    sendMail(hd);
                    if (hd.getNguoiNhan() == null) {
                        hd.setNguoiNhan("Khách lẻ");
                    }
                }
                break;
            case 0:
                // xác nhận đơn
                hd.setTrangThai(1);
                hd.setNgaySua(new Date());

                break;
            case 1:
                // Giao hàng
                hd.setTrangThai(2);
                hd.setNgaySua(new Date());
                break;
            case 2:
                // Giao hàng thành công
                hd.setTrangThai(3);
                hd.setNgaySua(new Date());
                hd.setNgayThanhToan(new Date());
                System.out.println("updateSoLuong");
                updateSl(hd);
                break;
            case 3:
                HoaDon hdDoiTra = new HoaDon();
                hdDoiTra.setNguoiNhan(hd.getNguoiNhan());
                hdDoiTra.setEmailNguoiNhan(hd.getEmailNguoiNhan());
                hdDoiTra.setNgayTao(new Date());
                hdDoiTra.setNgaySua(new Date());
                hdDoiTra.setKhachHang(hd.getKhachHang());
                hdDoiTra.setQuanHuyen(hd.getQuanHuyen());
                hdDoiTra.setThanhPho(hd.getThanhPho());
                hdDoiTra.setPhuongXa(hd.getPhuongXa());
                hdDoiTra.setLoaiHoaDon(2);
                hdDoiTra.setTrangThai(7);
                hdDoiTra.setTongTien((long) 0);
                hdDoiTra.setTongTienKhiGiam((long) 0);
                hdDoiTra.setPhiShip((long) 0);
                hoaDonService.saveOrUpdate(hdDoiTra);
                hdDoiTra.setMaHoaDon("HD-DOITRA" + hdDoiTra.getId());
                hoaDonService.saveOrUpdate(hdDoiTra);
                for (HoaDonChiTiet hdctf : hd.getLstHoaDonChiTiet()) {
                    if (hdctf.getTrangThai() == 2) {
                        HoaDonChiTiet hdctn = hdctf;
                        hdctn.setHoaDon(hdDoiTra);
                        hoaDonChiTietService.saveOrUpdate(hdctn);
                    }
                }

                hd.setNgaySua(new Date());
                break;
            case 4:
                if (giaoHang.equals("on")) {
                    // Giao hàng
                    hd.setTrangThai(1);
                    hd.setPhiShip(phiShip);
                    hd.setSdtNguoiNhan(inputSoDienThoai);
                    hd.setNguoiNhan(inputHoVaTen);
                    hd.setDiaChiNguoiNhan(inputDcct);
                    hd.setGhiChu(inputGhiChu);
                    hd.setThanhPho(thanhPho);
                    hd.setQuanHuyen(quanHuyen);
                    hd.setPhuongXa(phuongXa);

                    if (luuDiaChi.equals("on") && hd.getKhachHang().getTenTaiKhoan() != null) {
                        if (hd.getKhachHang().getLstDiaChi().size() < 5) {
                            DiaChi dc = new DiaChi();
                            dc.setQuanHuyen(quanHuyen);
                            dc.setPhuongXa(phuongXa);
                            dc.setThanhPho(thanhPho);
                            dc.setDiaChiCuThe(inputDcct);
                            dc.setKhachHang(hd.getKhachHang());
                            dc.setNgaySua(new Date());
                            dc.setNgayTao(new Date());
                            dc.setTrangThai(1);
                            diaChiService.save(dc);
                        }
                    }
                } else {
                    // Hoàn thành
                    hd.setTrangThai(3);
                    hd.setNgayThanhToan(new Date());
                    hd.setNgaySua(new Date());
                    hd.setTongTien(hd.tongTienHoaDon());
                    hd.setTongTienKhiGiam(hd.tongTienHoaDon() - giamGia);
                    sendMail(hd);
                    if (hd.getNguoiNhan() == null) {
                        hd.setNguoiNhan("Khách lẻ");
                    }

                }

                break;
            case 6:
                hd.setTrangThai(7);
            default:
                break;

        }
          if ("cash".equals(paymentMethod)) {
            hd.setTongTien(hd.tongTienHoaDon());
            hd.setTongTienKhiGiam(hd.tongTienHoaDon() - giamGia);

            hoaDonService.saveOrUpdate(hd);
            updateSl(hd);
            if (hd.getTrangThai() == 4) {
                return "redirect:/ban-hang-tai-quay/hoa-don";
            }
        }

        return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
    }


    @GetMapping("/callback")
    public String vnPayCallback(@RequestParam Map<String, String> allParams,

                                RedirectAttributes redirectAttributes) {
        String vnp_ResponseCode = allParams.get("vnp_ResponseCode");
        String vnp_TxnRef = allParams.get("vnp_TxnRef");

        HoaDon hd = hoaDonService.findByMa(vnp_TxnRef);

        if ("00".equals(vnp_ResponseCode)) { // Kiểm tra mã phản hồi thành công từ VNPay
            hd.setTrangThai(3);
            hd.setNgayThanhToan(new Date());
            hd.setNgaySua(new Date());
            hd.setTongTien(hd.tongTienHoaDon());
            hd.setTongTienKhiGiam(hd.tongTienHoaDonKhiGiam());
            hd.setPhiShip((long) 0);
            hd.setQuanHuyen(null);
            hd.setThanhPho(null);
            hd.setPhuongXa(null);
            sendMail(hd);
            if (hd.getNguoiNhan() == null) {
                hd.setNguoiNhan("Khách lẻ");
            }
            hd.setTongTien(hd.tongTienHoaDon());
            hd.setTongTienKhiGiam(hd.tongTienHoaDonKhiGiam());

            hoaDonService.saveOrUpdate(hd);
            updateSl(hd);
            if (hd.getTrangThai() == 4) {
                return "redirect:/ban-hang-tai-quay/hoa-don";
            }
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        } else {
            hd.setTrangThai(-1); // Cập nhật trạng thái thất bại
            hoaDonService.saveOrUpdate(hd);
            redirectAttributes.addFlashAttribute("message", "Thanh toán thất bại. Vui lòng thử lại.");
            return "redirect:/ban-hang-tai-quay/hoa-don/" + hd.getId();
        }
    }

    private void updateSl(HoaDon hd) {
        List<HoaDonChiTiet> lstHdct = hoaDonService.findById(hd.getId()).getLstHoaDonChiTiet();
        for (HoaDonChiTiet hdct : lstHdct) {
            Long idid = hdct.getSanPhamChiTiet().getId();
            SanPhamChiTiet ctsp = chiTietSanPhamSerivce.getById(idid);
            ctsp.setSoLuong(ctsp.getSoLuong() - hdct.getSoLuong());
            chiTietSanPhamSerivce.update(ctsp);
            if (ctsp.getSoLuong() == 0) {
                ctsp.setTrangThai(1);
                chiTietSanPhamSerivce.update(ctsp);
            }
        }
        if (hd.getVoucher() != null) {
            Voucher v = hd.getVoucher();
            v.setSoLuong(v.getSoLuong().subtract(new BigDecimal(1)));
            voucherService.save(v);
        }
    }

    @GetMapping("/hoa-don/detail/{id}")
    public String detailHoaDon(@PathVariable Long id) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            request.setAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        request.setAttribute("lstHdct", hoaDonChiTietService.findAll());
        request.setAttribute("lstCtsp", chiTietSanPhamSerivce.fillAllDangHoatDongLonHon0());
        request.setAttribute("lstTaiKhoan", khachHangService.getAll());
        request.setAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(id).getKhachHang().getId()));
        request.setAttribute("listVoucher", voucherService.fillAllDangDienRa());
        // idhdc = id;

        HoaDon hd = hoaDonService.findById(id);
        if(hd.getTrangThai()==6&&hd.getNgayMongMuon()==null){
            hd.setNgayMongMuon(new Date());
            sendMail(hd);
            hoaDonService.saveOrUpdate(hd);
        }
        if (hd.getVoucher() != null && hd.getTrangThai() != 6) {
            if (hd.tongTienHoaDonDaNhan() < hd.getVoucher().getGiaTriDonToiThieu().longValue()) {
                hd.setVoucher(null);
                hd.setTienGiam((long)0);
                hd.setTongTien(hd.tongTienHoaDonDaNhan());
                hd.setTongTienKhiGiam(hd.tongTienHoaDonDaNhan());
                hoaDonService.saveOrUpdate(hd);
            }
        }

        // checkVoucher();
        request.setAttribute("hoaDon", hd);
        request.setAttribute("byHoaDon", hd);
        if (hd.getTrangThai() == 4) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + id;
        }
        return "/admin/detail-hoa-don";
    }

    @PostMapping("/hoa-don/xac-nhan")
    public String xacNhan(
            @RequestParam Long idHoaDon,
            @RequestParam String ghiChu,
            @RequestParam(defaultValue = "") String detail,
            @RequestParam Long phiShip2,
            @RequestParam Long giamGia,
            @RequestParam String voucherID, RedirectAttributes redirectAttributes) {

        HoaDon hd = hoaDonService.findById(idHoaDon);
        if (!checkSlDb(hd)) {
            thongBao(redirectAttributes, "Có sản phẩm vượt quá số lượng vui lòng kiểm tra lại", 0);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }
        if(hd.getTrangThai()==5){
            thongBao(redirectAttributes, "Khách hàng đã hủy đơn", 0);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }
        if (voucherID != "") {
            hd.setVoucher(voucherService.findById(Long.parseLong(voucherID)));
            hd.setTienGiam(giamGia);
        }
        if (hd.getTrangThai() == 0 && hd.getLoaiHoaDon() == 1) {
            updateSl(hd);
        }

        hd.setTrangThai(hd.getTrangThai() + 1);
        hd.setNgaySua(new Date());

        hoaDonService.saveOrUpdate(hd);
        System.out.println(ghiChu + "ghiChu");
        sendMail(hd);
        if (detail.equals("ok")) {
            hd.setTongTien(hd.tongTienHoaDon() );
            hd.setTongTienKhiGiam(hd.tongTienHoaDon()- giamGia);
            hoaDonService.saveOrUpdate(hd);
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/quan-ly";
        }

    }
    @GetMapping("/doi-tra")
    public String doiTra() {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            request.setAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        hoaDonService.deleteHoaDonHoanTra();

        return "/admin/doi-tra";
    }
    public static boolean checkDate(Date dateToCheck) {
        // Lấy ngày hiện tại
        Date currentDate = new Date();

        // Lấy số milliseconds từ ngày hiện tại và ngày cần kiểm tra
        long millisecondsDifference = dateToCheck.getTime() - currentDate.getTime();

        // Chuyển số milliseconds thành số ngày
        long daysDifference = millisecondsDifference / (1000 * 60 * 60 * 24);

        // Kiểm tra xem chênh lệch có lớn hơn 7 ngày không
        return daysDifference <= 7;
    }
    @GetMapping("/doi-tra/{maHoaDon}")
    public String detailHoaDonDoiTra(@PathVariable String maHoaDon, RedirectAttributes redirectAttributes) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            request.setAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        HoaDon hd = hoaDonService.findByMa(maHoaDon);

        if(hd==null){
            thongBao(redirectAttributes, "Không có kết quả phù hợp", 0);

            return "redirect:/ban-hang-tai-quay/doi-tra";
        }

        if (hd.getTrangThai() != 3||!checkDate(hd.getNgaySua())) {
            thongBao(redirectAttributes, "Không có kết quả phù hợp", 0);
            return "redirect:/ban-hang-tai-quay/doi-tra";
        }

        // hoaDonService.saveOrUpdate(hdHangTra);
        if (hoaDonService.findByMa(maHoaDon + "-DOITRA") == null) {
            HoaDon hdHangTra = new HoaDon();
            hdHangTra.setMaHoaDon(hd.getMaHoaDon() + "-DOITRA");
            hdHangTra.setTrangThai(8);
            hoaDonService.saveOrUpdate(hdHangTra);
            request.setAttribute("check", 0);
        } else {
            request.setAttribute("check", 1);
            request.setAttribute("hdHangTra", hoaDonService.findByMa(maHoaDon + "-DOITRA"));
        }

        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        request.setAttribute("lstHdct", hoaDonChiTietService.findAll());
        request.setAttribute("lstCtsp", chiTietSanPhamSerivce.getAll());
        request.setAttribute("lstTaiKhoan", khachHangService.getAll());
        request.setAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(hd.getId()).getKhachHang().getId()));
        request.setAttribute("listVoucher", voucherService.fillAllDangDienRa());

        request.setAttribute("lstLshd", hoaDonService.findByIdhd(hd.getId()));
        request.setAttribute("listLichSuHoaDon", hoaDonService.findByIdhdNgaySuaAsc(hd.getId()));

        request.setAttribute("hoaDon", hd);
        request.setAttribute("byHoaDon", hd);
        thongBao(redirectAttributes, "Thành công", 1);
        return "/admin/detail-hoa-don-tra";
    }
    @PostMapping("/hoa-don-chi-tiet/tra-hang")
    public String traHang(@RequestParam(defaultValue = "") Integer soLuongEdit,
                          @RequestParam(defaultValue = "") Integer soLuongEditTra, @RequestParam Long idHdct, @RequestParam Long idhdc) {
        HoaDonChiTiet hdct = hoaDonChiTietService.findById(idHdct);
        HoaDon hd = hdct.getHoaDon();
        HoaDon hdTraHang = hoaDonService.findByMa("HD" + idhdc + "-DOITRA");
        System.out.println("mama" + hdTraHang.getMaHoaDon());
        Boolean checkUpdate = false;
        HoaDonChiTiet hdctnew;
        hd.setNgaySua(new Date());

        for (HoaDonChiTiet hdctf : hdTraHang.getLstHoaDonChiTiet()) {
            System.out.println("datimthay");
            System.out.println(hdctf.toString());
            if (hdctf.getSanPhamChiTiet().getId() == hdct.getSanPhamChiTiet().getId()) {
                checkUpdate = true;
                hdctnew = hdctf;
                hdctnew.setSoLuong(soLuongEditTra);
                hdctnew.setTrangThai(2);
                if (soLuongEditTra <= 0) {
                    hoaDonChiTietService.deleteById(hdctnew.getId());
                } else {
                    hoaDonChiTietService.saveOrUpdate(hdctnew);
                }

                break;
            }
        }

        if (!checkUpdate) {
            if (soLuongEditTra != 0) {
                hdctnew = new HoaDonChiTiet();
                hdctnew.setSoLuong(soLuongEditTra);
                hdctnew.setSanPhamChiTiet(hdct.getSanPhamChiTiet());
                hdctnew.setHoaDon(hdTraHang);
                hdctnew.setTrangThai(2);
                hdctnew.setDonGia(hdct.getDonGia());
                hoaDonChiTietService.saveOrUpdate(hdctnew);
            }
        }

        return "redirect:/ban-hang-tai-quay/doi-tra/HD" + idhdc;

    }

    @PostMapping("/doi-tra/xac-nhan")
    public String xacNhan(@RequestParam String lyDo,@RequestParam Long idhdc) {
        Long tongTienHoanTra;
        Long tienGiamGia;
        Long tongTienHdcCu;
        Long tienShip;

//        addLichSuHoaDon(idhdc, lyDo, 6);
        System.out.println(idhdc + "-----hdc");
        HoaDon hdc = hoaDonService.findById(idhdc);
        System.out.println(hdc.getTrangThai());
        hdc.setTrangThai(6);
        // hdc.setTienGiam(hdc.getGiamGia());
        System.out.println(hdc.getId());
        System.out.println(hdc.getTrangThai());
        HoaDon hdDoiTra = hoaDonService.findByMa(hdc.getMaHoaDon() + "-DOITRA");
        tongTienHoanTra = hdDoiTra.tongTienHoaDon();
        // tienGiamGia = hdc.getGiamGia();
        tienShip = hdc.getPhiShip();
        tongTienHdcCu = hdc.getTongTien() - tienShip;

        for (HoaDonChiTiet hdctChinh : hdc.getLstHoaDonChiTiet()) {
            for (HoaDonChiTiet hdctDoiTra : hdDoiTra.getLstHoaDonChiTiet()) {
                if (hdctChinh.getSanPhamChiTiet().getId() == hdctDoiTra.getSanPhamChiTiet().getId()) {
                    hdctChinh.setSoLuong(hdctChinh.getSoLuong() - hdctDoiTra.getSoLuong());
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setSanPhamChiTiet(hdctDoiTra.getSanPhamChiTiet());
                    hdct.setHoaDon(hdc);
                    hdct.setSoLuong(hdctDoiTra.getSoLuong());
                    hdct.setTrangThai(2);
                    hdct.setDonGia(hdctDoiTra.getDonGia());
                    hoaDonChiTietService.saveOrUpdate(hdct);
                    hoaDonChiTietService.saveOrUpdate(hdctChinh);
                }
            }
        }


        // System.out.println("tongTienHdcCu-tongTienHoanTra-tienGiamGia " + tongTienHdcCu + "-" + tongTienHoanTra
        //         + "-" + tienGiamGia);
        if (tongTienHdcCu - tongTienHoanTra - hdc.getTienGiam() < 0) {
            hdc.setTongTienKhiGiam((long) 0);
            hdc.setTongTien((long)0);
        }else{
            hdc.setTongTienKhiGiam(hdc.tongTienHoaDonDaNhan()+hdc.getPhiShip());
            hdc.setTongTien(hdc.tongTienHoaDonDaNhan());
            // hdc.setTienGiam((long)0);
            hdc.setVoucher(null);
        }



        hoaDonService.saveOrUpdate(hdc);
        for (HoaDonChiTiet hdct : hoaDonChiTietService.findByIdHoaDon(idhdc)) {
            if (hdct.getSoLuong() == 0) {
                System.out.println(hdct.getId());
                hoaDonChiTietService.deleteById(hdct.getId());

            }
        }
        hoaDonService.deleteHoaDonHoanTra();
        // hoaDonService.deleteById(hdDoiTra.getId()); // Xóa luôn hóa đơn đổi trả tạm

        return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
    }

}
