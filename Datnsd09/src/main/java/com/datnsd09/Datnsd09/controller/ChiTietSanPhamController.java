package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.SanPham;
import com.datnsd09.Datnsd09.repository.ChiTietSanPhamRepository;
import com.datnsd09.Datnsd09.repository.KichCoRepository;
import com.datnsd09.Datnsd09.repository.LoaiDeRepository;
import com.datnsd09.Datnsd09.repository.MauSacRepository;
import com.datnsd09.Datnsd09.repository.SanPhamRepository;
import com.datnsd09.Datnsd09.service.ChiTietSanPhamService;
import com.datnsd09.Datnsd09.service.HinhAnhSanPhamSerivce;
import com.datnsd09.Datnsd09.service.KichCoService;
import com.datnsd09.Datnsd09.service.LoaiDeService;
import com.datnsd09.Datnsd09.service.MauSacService;
import com.datnsd09.Datnsd09.service.SanPhamService;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private HinhAnhSanPhamSerivce hinhAnhSanPhamSerivce;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private LoaiDeService loaiDeService;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private KichCoRepository kichCoRepository;

    @Autowired
    private LoaiDeRepository loaiDeRepository;


    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("listCTSP", chiTietSanPhamService.getAllCTSPOneSanPham());
        model.addAttribute("sanPham", new SanPham());
        return "/admin/chi-tiet-san-pham/chi-tiet-san-pham";
    }

    @PostMapping("/update")
    public String update(
            @RequestParam("listIdChiTietSp") List<String> listIdChiTietSp,
            @RequestParam("listSanPham") List<String> listSanPham,
            @RequestParam("listKichCo") List<String> listKichCo,
            @RequestParam("listMauSac") List<String> listMauSac,
            @RequestParam("listLoaiDe") List<String> listLoaiDe,
            @RequestParam("listTrangThai") List<String> listTrangThai,
            @RequestParam("listSoLuong") List<String> listSoLuong,
            @RequestParam("listDonGia") List<String> listDonGia,
            RedirectAttributes attributes

    ) {
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        chiTietSanPhamService.updateAllCTSP(listIdChiTietSp, listSanPham, listKichCo, listMauSac,
                listLoaiDe, listTrangThai, listSoLuong, listDonGia);
        return "redirect:/admin/chi-tiet-san-pham";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("listSanPham") List<String> listSanPham,
            @RequestParam("listKichCo") List<String> listKichCo,
            @RequestParam("listMauSac") List<String> listMauSac,
            @RequestParam("listLoaiDe") List<String> listLoaiDe,
            @RequestParam("listSoLuong") List<String> listSoLuong,
            @RequestParam("listDonGia") List<String> listDonGia,
            RedirectAttributes attributes
    ) {
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        chiTietSanPhamService.add(listSanPham, listKichCo, listMauSac, listLoaiDe, listSoLuong, listDonGia);
        return "redirect:/admin/san-pham-chi-tiet";
    }
}
