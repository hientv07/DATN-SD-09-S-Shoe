package com.datnsd09.Datnsd09.controller;

import com.datnsd09.Datnsd09.entity.HoaDon;
import com.datnsd09.Datnsd09.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/vnpay")
public class VNPayController {
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("/callback")
    public String vnPayCallback(@RequestParam Map<String, String> allParams, RedirectAttributes redirectAttributes) {
        String vnp_ResponseCode = allParams.get("vnp_ResponseCode");
        String vnp_TxnRef = allParams.get("vnp_TxnRef");

        HoaDon hd = hoaDonService.findByMa(vnp_TxnRef);

        if ("00".equals(vnp_ResponseCode)) { // Kiểm tra mã phản hồi thành công từ VNPay
            hd.setTrangThai(3); // Cập nhật trạng thái thành công
            hd.setNgayThanhToan(new Date());
            hd.setNgaySua(new Date());
            hoaDonService.saveOrUpdate(hd);

            redirectAttributes.addFlashAttribute("message", "Thanh toán thành công!");
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        } else {
            hd.setTrangThai(-1); // Cập nhật trạng thái thất bại
            hoaDonService.saveOrUpdate(hd);

            redirectAttributes.addFlashAttribute("message", "Thanh toán thất bại. Vui lòng thử lại.");
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + hd.getId();
        }
    }
}
