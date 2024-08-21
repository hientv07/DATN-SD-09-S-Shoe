package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.LoaiDe;
import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.entity.ThuongHieu;
import com.datnsd09.Datnsd09.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/admin/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping()
    public String hienThi(
            Model model) {
        model.addAttribute("listThuongHieu", thuongHieuService.getAll());
        model.addAttribute("thuongHieu", new ThuongHieu());
        return "/admin/thuong-hieu/thuong-hieu";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                      BindingResult result, Model model,
                      RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listThuongHieu", thuongHieuService.getAll());
            return "/admin/thuong-hieu/thuong-hieu";
        } else if (!thuongHieuService.checkTenTrung(thuongHieu.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Thương hiệu đã tồn tại");
            model.addAttribute("listThuongHieu", thuongHieuService.getAll());
            return "/admin/thuong-hieu/thuong-hieu";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            thuongHieu.setNgayTao(new Date());
            thuongHieu.setNgaySua(new Date());
            thuongHieu.setTrangThai(0);
            thuongHieuService.add(thuongHieu);
            return "redirect:/admin/thuong-hieu";
        }
    }
    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(
            Model model
    ) {
       model.addAttribute("listThuongHieu", thuongHieuService.getAllDangHoatDong());
       model.addAttribute("thuongHieu", new ThuongHieu());
       return "/admin/thuong-hieu/thuong-hieu";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model
    ) {
        model.addAttribute("listThuongHieu", thuongHieuService.getAllDungHoatDong());
        model.addAttribute("thuongHieu", new ThuongHieu());
        return "/admin/thuong-hieu/thuong-hieu";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        ThuongHieu thuongHieu = thuongHieuService.getById(id);
        model.addAttribute("thuongHieu", thuongHieu);
        return "/admin/thuong-hieu/sua-thuong-hieu";
    }

    @PostMapping("/update")
    public String update(@Valid
                         @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            return "/admin/thuong-hieu/sua-thuong-hieu";
        } else if (!thuongHieuService.checkTenTrungSua(thuongHieu.getId(), thuongHieu.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Thương hiệu đã tồn tại");
            return "/admin/thuong-hieu/sua-thuong-hieu";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            ThuongHieu thuongHieu1 = thuongHieuService.getById(thuongHieu.getId());
            thuongHieu.setNgayTao(thuongHieu1.getNgayTao());
            thuongHieu.setNgaySua(new Date());
            thuongHieuService.update(thuongHieu);
            return "redirect:/admin/thuong-hieu";
        }

    }
}
