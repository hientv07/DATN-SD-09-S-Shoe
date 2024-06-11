package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.KichCo;
import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.service.KichCoService;
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
@RequestMapping("/admin/kich-co")
public class KichCoController {
    @Autowired
    private KichCoService kichCoService;

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("listKichCo", kichCoService.getAll());
        model.addAttribute("kichCo",new KichCo());
        return "/admin/kich-co/kich-co";
    }

    @PostMapping("/add")
    public String add(@Valid @RequestParam("ten") String ten,
                      @RequestParam("ngayTao") String ngayTao,
                      @RequestParam("ngaySua") String ngaySua,
                      @RequestParam("trangThai") String trangThai) {
        KichCo kichCo = KichCo.builder()
                .ten(ten)
                .ngayTao(new Date())
                .ngaySua(new Date())
                .trangThai(Integer.valueOf(trangThai))
                .build();
        kichCoService.add(kichCo);
        return "redirect:/admin/kich-co";
    }
}
