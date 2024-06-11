package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.KichCo;
import com.datnsd09.Datnsd09.entity.LoaiDe;
import com.datnsd09.Datnsd09.service.LoaiDeService;
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
@RequestMapping("/admin/loai-de")
public class LoaiDeController {
    @Autowired
    private LoaiDeService loaiDeService;

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("listLoaiDe", loaiDeService.getAll());
        model.addAttribute("loaiDe", new LoaiDe());
        return "/admin/loai-de/loai-de";
    }

    @PostMapping("/add")
    public String add(@Valid @RequestParam("ten") String ten,
                      @RequestParam("ngayTao") String ngayTao,
                      @RequestParam("ngaySua") String ngaySua,
                      @RequestParam("trangThai") String trangThai) {
        LoaiDe loaiDe=LoaiDe.builder()
                .ten(ten)
                .ngayTao(new Date())
                .ngaySua(new Date())
                .trangThai(Integer.valueOf(trangThai))
                .build();
        loaiDeService.add(loaiDe);
        return "redirect:/admin/loai-de";
    }
}
