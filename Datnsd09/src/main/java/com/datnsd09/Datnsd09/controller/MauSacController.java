package com.datnsd09.Datnsd09.controller;


import com.datnsd09.Datnsd09.entity.LoaiDe;
import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.service.MauSacService;
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
@RequestMapping("/admin/mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping()
    public String hienThi(
            Model model) {
        model.addAttribute("listMauSac", mauSacService.getAll());
        model.addAttribute("mauSac", new MauSac());
        return "/admin/mau-sac/mau-sac";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("mauSac") MauSac mauSac,
                      BindingResult result, Model model,
                      RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listMauSac", mauSacService.getAll());
            return "/admin/mau-sac/mau-sac";
        } else if (!mauSacService.checkTenTrung(mauSac.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Màu sắc đã tồn tại");
            model.addAttribute("listMauSac", mauSacService.getAll());
            return "/admin/mau-sac/mau-sac";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            mauSac.setNgayTao(new Date());
            mauSac.setNgaySua(new Date());
            mauSac.setTrangThai(0);
            mauSacService.add(mauSac);
            return "redirect:/admin/mau-sac";
        }
    }


    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(
            Model model
    ) {
        model.addAttribute("listMauSac", mauSacService.getAllDangHoatDong());
        model.addAttribute("mauSac", new MauSac());
        return "/admin/mau-sac/mau-sac";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model
    ) {
        model.addAttribute("listMauSac", mauSacService.getAllDungHoatDong());
        model.addAttribute("mauSac", new MauSac());
        return "/admin/mau-sac/mau-sac";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        MauSac mauSac = mauSacService.getById(id);
        model.addAttribute("mauSac", mauSac);
        return "/admin/mau-sac/sua-mau-sac";
    }

    @PostMapping("/update")
    public String update(@Valid
                         @ModelAttribute("mauSac") MauSac mauSac,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            return "/admin/mau-sac/sua-mau-sac";
        } else if (!mauSacService.checkTenTrungSua(mauSac.getId(), mauSac.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Màu sắc đã tồn tại");
            return "/admin/mau-sac/sua-mau-sac";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            MauSac mauSac1 = mauSacService.getById(mauSac.getId());
            mauSac.setNgayTao(mauSac1.getNgayTao());
            mauSac.setNgaySua(new Date());
            mauSacService.update(mauSac);
            return "redirect:/admin/mau-sac";
        }

    }


}
