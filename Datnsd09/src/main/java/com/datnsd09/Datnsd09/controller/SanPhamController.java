package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.SanPham;

import com.datnsd09.Datnsd09.service.HinhAnhSanPhamSerivce;
import com.datnsd09.Datnsd09.service.SanPhamService;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private HinhAnhSanPhamSerivce hinhAnhSanPhamSerivce;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("listSanPham", sanPhamService.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.getAll());
        model.addAttribute("sanPham", new SanPham());
        return "/admin/san-pham/san-pham";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("sanPham") SanPham sanPham,
                      BindingResult result, Model model,
                      @RequestParam("fileImage") List<MultipartFile> multipartFiles,
                      RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listSanPham", sanPhamService.getAll());
            model.addAttribute("listThuongHieu", thuongHieuService.getAll());
            return "/admin/san-pham/san-pham";
        }else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            sanPham.setMa("SP" + sanPhamService.genMaTuDong());
            sanPham.setNgayTao(new Date());
            sanPham.setNgaySua(new Date());
            sanPham.setTrangThai(0);
            sanPhamService.add(sanPham);

            hinhAnhSanPhamSerivce.saveImage(multipartFiles,sanPham);
            return "redirect:/admin/san-pham";
        }
    }
}
