package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.service.MauSacService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/admin/mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping()
    public String getAll(Model model){
        MauSac mauSac = new MauSac();
        model.addAttribute("listMauSac", mauSacService.getAll());
        model.addAttribute("mauSac",new MauSac());

        return "/admin/mau_sac/mau_sac";
    }

    @PostMapping("/add")
    public String add(@Valid @RequestParam("maMau") String maMau,
                      @RequestParam("tenMau") String tenMau,
                      @RequestParam("ngayTao") String ngayTao,
                      @RequestParam("ngaySua") String ngaySua,
                      @RequestParam("trangThai") String trangThai){
        MauSac mauSac =MauSac.builder()
                .maMau(maMau)
                .tenMau(tenMau)
                .ngayTao(new Date())
                .ngaySua(new Date())
                .trangThai(Integer.valueOf(trangThai))
                .build();
        mauSacService.add(mauSac);
        return "redirect:/admin/mau-sac";
    }
}
