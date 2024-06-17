package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.KichCo;
import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.service.KichCoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/admin/kich-co")
public class KichCoController {
    @Autowired
    private KichCoService kichCoService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("listKichCo", kichCoService.getAll());
        model.addAttribute("kichCo", new KichCo());
        return "/admin/kich-co/kich-co";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("kichCo") KichCo kichCo,
                      BindingResult result, Model model,
                      RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thatBai");
            model.addAttribute("listKichCo", kichCoService.getAll());
            return "/admin/kich-co/kich-co";
        }else if (!kichCoService.checkTenTrung(kichCo.getTen())) {
            model.addAttribute("checkModal","modal");
            model.addAttribute("checkThongBao","thatBai");
            model.addAttribute("checkTenTrung","Kích cỡ đã tồn tại");
            model.addAttribute("listKichCo", kichCoService.getAll());
            return "/admin/kich-co/kich-co";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            kichCo.setNgayTao(new Date());
            kichCo.setNgaySua(new Date());
            kichCo.setTrangThai(0);
            kichCoService.add(kichCo);
            return "redirect:/admin/kich-co";
        }
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Long id, Model model) {
        KichCo kichCo = kichCoService.getById(id);
        model.addAttribute("listKichCo", kichCoService.getAll());
        model.addAttribute("kichCo", kichCo);
        return "/admin/kich-co/sua-kich-co";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("kichCo") KichCo kichCo,
                         BindingResult result, Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thatBai");
            model.addAttribute("listKichCo", kichCoService.getAll());
            return "/admin/kich-co/sua-kich-co";
        }else if (!kichCoService.checkTenTrungSua(kichCo.getId(), kichCo.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Kích cỡ đã tồn tại");
            model.addAttribute("listKichCo", kichCoService.getAll());
            return "/admin/kich-co/sua-kich-co";
        }else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            KichCo kt = kichCoService.getById(kichCo.getId());
            kichCo.setNgayTao(kt.getNgayTao());
            kichCo.setNgaySua(new Date());
            kichCo.setTrangThai(0);
            kichCoService.update(kichCo);
            return "redirect:/admin/kich-co";
        }
    }

    @GetMapping("/dang-hoat-dong")
    public String dangHienDong(Model model){
        model.addAttribute("listKichCo", kichCoService.getAllDangHoatDong());
        model.addAttribute("kichCo", new KichCo());
        return "/admin/kich-co/kich-co";
    }

    @GetMapping("/ngung-hoat-dong")
    public String ngungHoatDong(Model model){
        model.addAttribute("listKichCo", kichCoService.getAllNgungHoatDong());
        model.addAttribute("kichCo",new KichCo());
        return "/admin/kich-co/kich-co";
    }
}
