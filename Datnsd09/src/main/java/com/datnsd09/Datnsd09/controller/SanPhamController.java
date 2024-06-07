package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.HinhAnhSanPham;
import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.entity.ThuongHieu;
import com.datnsd09.Datnsd09.service.HinhAnhSanPhamSerivce;
import com.datnsd09.Datnsd09.service.SanPhamService;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private ThuongHieuService thuongHieuService;

//    @Autowired
//    private HinhAnhSanPhamSerivce hinhAnhSanPhamSerivce;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("listSanPham", sanPhamService.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.getAll());
        model.addAttribute("sanPham", new SanPham());
        return "/admin/san-pham/san-pham";
    }

    @PostMapping("/add")
    public String add(@Valid @RequestParam("maSP") String maSP,
                      @RequestParam("tenSP") String tenSP,
                      @RequestParam("moTa") String moTa,
                      @RequestParam("ngayTao") String ngayTao,
                      @RequestParam("ngaySua") String ngaySua,
                      @RequestParam("nguoiTao") String nguoiTao,
                      @RequestParam("nguoiSua") String nguoiSua,
                      @RequestParam("trangThai") String trangThai) {
        SanPham sanPham = SanPham.builder()
                .maSP(maSP)
                .tenSP(tenSP)
                .moTa(moTa)
                .ngayTao(new Date())
                .ngaySua(new Date())
                .nguoiTao(nguoiTao)
                .nguoiSua(nguoiSua)
                .trangThai(Integer.valueOf(trangThai))
                .build();
        sanPhamService.add(sanPham);
        return "redirect:/admin/san-pham";
    }
}
