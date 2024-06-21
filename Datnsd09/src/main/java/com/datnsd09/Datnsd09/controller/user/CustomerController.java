package com.datnsd09.Datnsd09.controller.user;

//import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class CustomerController {

    private Long idKhachHang;

    @Autowired
    private KhachHangService khachHangService;

    private PrincipalCustom principalCustom = new PrincipalCustom();


    @GetMapping("/user/thong-tin-khach-hang")
    public String info(Model model) {
        if (principalCustom.getCurrentUserNameCustomer() != null) {
            KhachHang taiKhoan = khachHangService.getTaiKhoanByName(principalCustom.getCurrentUserNameCustomer());
            idKhachHang = taiKhoan.getId();}
            KhachHang khachHang = khachHangService.getById(idKhachHang);
            model.addAttribute("khachHang", khachHang);
//        model.addAttribute("soLuongSPGioHangCT",
//                gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
            return "/customer/thong-tin-khach-hang";
        }
    }
