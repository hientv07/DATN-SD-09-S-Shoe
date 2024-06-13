package com.datnsd09.Datnsd09.controller;


import com.datnsd09.Datnsd09.entity.MauSac;
import com.datnsd09.Datnsd09.service.MauSacService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    ///lấy hết dữ liệu ra
//    @GetMapping()
//    public ResponseEntity<?> getAll(){
//        return ResponseEntity.ok(mauSacService.getAll());
//    }

    ///Thêm dữ liệu
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MauSac mauSac){
        return ResponseEntity.ok(mauSacService.add(mauSac));
    }
    ///update dữ liệu
    @PutMapping("/update/{ma}")
    public ResponseEntity<?> update(@PathVariable Long ma,
                                    @RequestBody MauSac mauSac){
        return ResponseEntity.ok(mauSacService.update(mauSac, ma));
    }
    ////xoa
    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<?> delete(@PathVariable Long ma){
        //return ResponseEntity.ok(mauSacService.deleteById(ma));
        boolean deleted = mauSacService.deleteById(ma);

        if (deleted) {
            return ResponseEntity.ok().build(); // Trả về 200 OK nếu xóa thành công
        } else {
            return ResponseEntity.notFound().build(); // Trả về 404 Not Found nếu không tìm thấy đối tượng cần xóa
        }
    }


    @GetMapping()
    public String hienThi(
            Model model) {
        model.addAttribute("listMauSac", mauSacService.getAll());
        model.addAttribute("mauSac", new MauSac());
        return "/admin/mau-sac/mau-sac";
    }

//    @GetMapping()
//    public String getAll(Model model){
//        MauSac mauSac = new MauSac();
//        model.addAttribute("listMauSac", mauSacService.getAll());
//        model.addAttribute("mauSac",new MauSac());
//
//        return "/admin/mau-sac/mau-sac";
//    }

//    @PostMapping("/add")
//    public String add(@Valid @RequestParam("maMau") String maMau,
//                      @RequestParam("tenMau") String tenMau,
//                      @RequestParam("ngayTao") String ngayTao,
//                      @RequestParam("ngaySua") String ngaySua,
//                      @RequestParam("trangThai") String trangThai){
//        MauSac mauSac =MauSac.builder()
//                .maMau(maMau)
//                .tenMau(tenMau)
//                .ngayTao(new Date())
//                .ngaySua(new Date())
//                .trangThai(Integer.valueOf(trangThai))
//                .build();
//        mauSacService.add(mauSac);
//        return "redirect:/admin/mau-sac";
//    }
}
