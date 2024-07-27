package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.config.UserInfoUserDetails;
import com.datnsd09.Datnsd09.service.HoaDonChiTietService;
import com.datnsd09.Datnsd09.service.HoaDonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/hoa-don")
public class HoaDonController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    HttpServletRequest request;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping("/quan-ly")
    public String quanLyHoaDon() {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            request.setAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        request.setAttribute("lstHdctAll", hoaDonService.findAllOrderByNgaySua());
        request.setAttribute("lstHdChoXacNhan", hoaDonService.find5ByTrangThai(0));
        request.setAttribute("lstHdChoGiao", hoaDonService.find5ByTrangThai(1));
        request.setAttribute("lstHdDangGiao", hoaDonService.find5ByTrangThai(2));
        request.setAttribute("lstHdHoanThanh", hoaDonService.find5ByTrangThai(3));
        request.setAttribute("lstHdChoThanhToan", hoaDonService.find5ByTrangThai(4));
        request.setAttribute("lstHdHuy", hoaDonService.find5ByTrangThai(5));
        request.setAttribute("lstHdTra", hoaDonService.find5ByTrangThai(6));

        return "/admin/hoa-don";
    }


}
