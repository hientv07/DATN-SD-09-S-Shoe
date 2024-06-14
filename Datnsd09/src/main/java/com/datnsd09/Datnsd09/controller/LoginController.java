package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.entity.NhanVien;
import com.datnsd09.Datnsd09.repository.NhanVienRepository;
import com.datnsd09.Datnsd09.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    NhanVienService service;

    @Autowired
    NhanVienRepository nhanVienRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    NhanVien userInfoStorage = new NhanVien();

    String ranDomMa;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping("/login")
    public String formLogin() {
        if (principalCustom.getCurrentUserNameAdmin() != null) {
            return "redirect:/admin/nhan-vien";
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
}
