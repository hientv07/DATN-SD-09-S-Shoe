package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.entity.NhanVien;
import com.datnsd09.Datnsd09.repository.KhachHangRepository;
import com.datnsd09.Datnsd09.repository.NhanVienRepository;
import com.datnsd09.Datnsd09.service.KhachHangService;
import com.datnsd09.Datnsd09.service.NhanVienService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Controller
public class LoginController {

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    KhachHangService khachHangService;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    KhachHang userInfoStorage = new KhachHang();

    String ranDomMa;

    String emailKiemTra;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping("/login")
    public String formLogin() {
        if (principalCustom.getCurrentUserNameAdmin() != null) {
            return "redirect:/ban-hang-tai-quay/hoa-don";
        } else if (principalCustom.getCurrentUserNameCustomer() != null) {
            return "redirect:/home";
        } else {
            return "dang-nhap";
        }
    }

    @GetMapping("/login-error")
    public String loginErorr(
            Model model
    ) {
        model.addAttribute("message", "Tài khoản chưa kích hoạt hoặc sai thông tin tài khoản");
        return "dang-nhap";
    }

    @GetMapping("/register")
    public String register(Model model){
        return "dang-ky";
    }

    @GetMapping("/xac-minh")
    public String checkRanDOm() {
        return "xac-minh";
    }


    @PostMapping("/xac-minh/check")
    public String checkRanDOm1(
            @RequestParam("ranDom") String ranDom1,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (ranDom1.isEmpty()) {
            model.addAttribute("checkMaXacMinh", "Bạn chưa nhập mã xác minh!");
            return "xac-minh";
        } else if (ranDom1.equalsIgnoreCase(ranDomMa)) {
            khachHangService.addUser(userInfoStorage);
            redirectAttributes.addFlashAttribute("checkThongBao","dangKy");
            System.out.println("thêm tài khoản thành công form check");
            return "redirect:/login";
        } else {
            System.out.println("mã không đúng");
            model.addAttribute("checkMaXacMinh", "Mã xác nhận không đúng!");
            return "xac-minh";
        }

    }

    @PostMapping("/them-tai-khoan")
    public String addNewUser1(
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        KhachHang taiKhoanDk = khachHangRepository.findByTenTaiKhoan(username).orElse(null);
        KhachHang emailDk = khachHangRepository.findByEmail(email).orElse(null);
        if (emailDk != null) {
            System.out.println("Da ton tai");
            model.addAttribute("checkTrungEmail", "Email đã tồn tại");
            return "dang-ky";
        } else if (taiKhoanDk != null) {
            System.out.println("Da ton tai");
            model.addAttribute("checkTrungTenTaiKhoan", "Tên tài khoản đã tồn tại");
            return "dang-ky";
        } else {
            KhachHang userInfo = new KhachHang();

            String url = request.getRequestURL().toString();
            System.out.println(url);
            url = url.replace(request.getServletPath(), "");
            System.out.println(url);
            // //http://localhost:8080/verify?code=3453sdfsdcsadcsc
            userInfo.setTenTaiKhoan(username);
            userInfo.setEmail(email);
            try {
                // Create a SimpleDateFormat object with the desired date pattern
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                // Parse the string and set the ngaySinh attribute
                userInfo.setNgaySinh(dateFormat.parse("01-01-1900"));
            } catch (ParseException e) {
                // Handle the exception if the date string is not in the expected format
                e.printStackTrace(); // Or log the error
            }
            userInfo.setHoVaTen(username);
            userInfo.setSoDienThoai("0300000000");
            userInfo.setGioiTinh(-1);
            userInfo.setMatKhau(password);
            userInfo.setNgaySua(new Date());
            userInfo.setNgayTao(new Date());
            String random2 = ranDom();
            System.out.println("gửi mã xác nhận");
            khachHangService.sendEmail(userInfo, url, random2);
            userInfoStorage = userInfo;
            ranDomMa = random2;
            return "redirect:/xac-minh";
        }
    }

    @GetMapping("/verify")
    public String verifyAccount() {

        return "xac-nhan-quen-mat-khau";

    }


    @PostMapping("/reset-mat-khau")
    public String resetMatKhau(
            @RequestParam("matKhauCu") String matKhauCu,
            @RequestParam("matKhauMoi") String matKhauMoi,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Optional<KhachHang> taiKhoanOpt = khachHangRepository.findByEmail(emailKiemTra);
        System.out.println(emailKiemTra);
        if (taiKhoanOpt.isPresent()) {
            KhachHang taiKhoan = taiKhoanOpt.get();
            if (matKhauCu.equals(matKhauMoi)) {
                taiKhoan.setMatKhau(matKhauMoi);
                khachHangService.updateUser(taiKhoan);
                System.out.println("Thay đổi mk thành công ");
                redirectAttributes.addFlashAttribute("checkThongBao","quenMK");
                redirectAttributes.addFlashAttribute("tenTaiKhoan", taiKhoan.getTenTaiKhoan());
                redirectAttributes.addFlashAttribute("matKhauMoi", matKhauMoi);
                return "redirect:/login";
            } else {
                System.out.println("Mật khẩu không trung khớp");
                model.addAttribute("thongBao", "Mật khẩu không trùng khớp");
                return "xac-nhan-quen-mat-khau";
            }
        }
        return "redirect:/login";

    }

    @GetMapping("/quen-mat-khau")
    public String quenMatKhau() {
        return "quen-mat-khau";
    }

    @PostMapping("/quen-mat-khau")
    public String quenMatKau(
            @RequestParam("email") String email, Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        emailKiemTra = email;
        String url = request.getRequestURL().toString();
        Optional<KhachHang> taiKhoanOpt = khachHangRepository.findByEmail(emailKiemTra);
        url = url.replace(request.getServletPath(), "");
        if (taiKhoanOpt.isPresent()) {
            khachHangService.sendEmailKhoiPhuc(taiKhoanOpt.get(), url);
            System.out.println("thanh cong");
            model.addAttribute("email", email);
            return "xac-minh-email";
        } else if (email.isEmpty()) {
            model.addAttribute("message", "Bạn chưa nhập email");
            System.out.println("Email null");
            return "quen-mat-khau";
        } else {
            model.addAttribute("message", "Email nay sai hoặc  chưa được đăng ký");
            System.out.println("Email nay không tồn tại");
            return "quen-mat-khau";
        }

    }


    public String ranDom() {
        // Khai báo một mảng chứa 6 số nguyên ngẫu nhiên
        String ran = "";
        int[] randomNumbers = new int[6];

        // Tạo một đối tượng Random
        Random random = new Random();

        // Đổ số nguyên ngẫu nhiên vào mảng
        for (int i = 0; i < 6; i++) {
            randomNumbers[i] = random.nextInt(100); // Giới hạn số ngẫu nhiên từ 0 đến 99
        }

        // In ra các số nguyên ngẫu nhiên trong mảng
        System.out.println("Dãy 6 số nguyên ngẫu nhiên:");
        for (int number : randomNumbers) {
            ran = ran + number;
            System.out.println(number);
        }
        return ran;
    }
}
