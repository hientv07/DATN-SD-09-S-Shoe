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
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuService thuongHieuService;

//    @GetMapping()
//    public ResponseEntity<?> getAll(){
//        return ResponseEntity.ok(thuongHieuService.getAll());
//    }

    ///Thêm dữ liệu
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ThuongHieu thuongHieu){
        return ResponseEntity.ok(thuongHieuService.add(thuongHieu));
    }
    //update dữ liệu
    @PutMapping("/update/{ma}")
    public ResponseEntity<?> update(@PathVariable Long ma,
                                    @RequestBody ThuongHieu thuongHieu){
        return ResponseEntity.ok(thuongHieuService.update(thuongHieu, ma));
    }

    @GetMapping()
    public String hienThi(
            Model model) {
        model.addAttribute("listThuongHieu", thuongHieuService.getAll());
        model.addAttribute("thuongHieu", new ThuongHieu());
        return "/admin/thuong-hieu/thuong-hieu";
    }

//    @GetMapping()
//    public String getAll(Model model){
//        model.addAttribute("listThuongHieu", thuongHieuService.getAll());
//        model.addAttribute("thuongHieu", new ThuongHieu());
//        return "/admin/thuong-hieu/thuong-hieu";
//    }
//
//    @PostMapping("/add")
//    public String add(@Valid @RequestParam("tenThuongHieu") String tenThuongHieu,
//                      @RequestParam("ngayTao") String ngayTao,
//                      @RequestParam("ngaySua") String ngaySua,
//                      @RequestParam("trangThai") String trangThai) {
//        ThuongHieu thuongHieu=ThuongHieu.builder()
//                .tenThuongHieu(tenThuongHieu)
//                .ngayTao(new Date())
//                .ngaySua(new Date())
//                .trangThai(Integer.valueOf(trangThai))
//                .build();
//        thuongHieuService.add(thuongHieu);
//        return "redirect:/admin/thuong-hieu";
//    }
}
