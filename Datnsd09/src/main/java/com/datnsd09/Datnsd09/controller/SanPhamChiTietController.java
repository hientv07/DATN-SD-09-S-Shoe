package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.config.PrincipalCustom;
import com.datnsd09.Datnsd09.config.UserInfoUserDetails;
import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.repository.KichCoRepository;
import com.datnsd09.Datnsd09.repository.LoaiDeRepository;
import com.datnsd09.Datnsd09.repository.MauSacRepository;
import com.datnsd09.Datnsd09.repository.SanPhamChiTietRepository;
import com.datnsd09.Datnsd09.repository.SanPhamRepository;
import com.datnsd09.Datnsd09.repository.ThuongHieuRepository;
import com.datnsd09.Datnsd09.service.HinhAnhSanPhamSerivce;
import com.datnsd09.Datnsd09.service.KichCoService;
import com.datnsd09.Datnsd09.service.LoaiDeService;
import com.datnsd09.Datnsd09.service.MauSacService;
import com.datnsd09.Datnsd09.service.SanPhamChiTietService;
import com.datnsd09.Datnsd09.service.SanPhamService;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/san-pham-chi-tiet")
public class SanPhamChiTietController {

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private HinhAnhSanPhamSerivce hinhAnhSanPhamSerivce;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private LoaiDeService loaiDeService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private KichCoRepository kichCoRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private LoaiDeRepository loaiDeRepository;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping()
    public String getAll(Model model){

        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listCTSP", sanPhamChiTietService.getAllCTSPOneSanPham());
        getString(model);
        model.addAttribute("sanPham", new SanPham());
        return "/admin/san-pham-chi-tiet/san-pham-chi-tiet";
    }

    private Model getString(Model model) {
        model.addAttribute("listSanPham", sanPhamService.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.getAllDangHoatDong());
        model.addAttribute("listKichCo", kichCoService.getAll());
        model.addAttribute("listMauSac", mauSacService.getAllDangHoatDong());
        model.addAttribute("listLoaiDe", loaiDeService.getAllDangHoatDong());

        return model;
    }
}
