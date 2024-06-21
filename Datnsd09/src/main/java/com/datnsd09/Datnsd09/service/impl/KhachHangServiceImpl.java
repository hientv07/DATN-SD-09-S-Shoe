package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.entity.NhanVien;
import com.datnsd09.Datnsd09.repository.KhachHangRepository;
import com.datnsd09.Datnsd09.repository.NhanVienRepository;
import com.datnsd09.Datnsd09.service.KhachHangService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository  repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public List<KhachHang> getAll() {
        return repository.fillAllKhachHang();
    }

    @Override
    public List<KhachHang> getAllDangHoatDong() {
        return repository.fillAllDangHoatDong();
    }

    @Override
    public List<KhachHang> getAllNgungHoatDong() {
        return repository.fillAllNgungHoatDong();
    }

    @Override
    public KhachHang add(KhachHang khachHang) {
        return repository.save(khachHang);
    }

    @Override
    public KhachHang update(KhachHang khachHang) {
        return repository.save(khachHang);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public KhachHang getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean checkTenTrung(String ten) {
        for (KhachHang nv : repository.findAll()) {
            if (nv.getGioiTinh() == null) {
                continue;
            }
            if (nv.getTenTaiKhoan().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(String id, String ten) {
        for (KhachHang nv : repository.findAll()) {
            if (nv.getGioiTinh() == null) {
                continue;
            }
            if (nv.getTenTaiKhoan().equalsIgnoreCase(ten)) {
                if (!nv.getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTkTrungSua(Long id, String ten) {
        for (KhachHang sp : repository.findAll()) {
            if (sp.getGioiTinh() == null) {
                continue;
            }
            if (sp.getTenTaiKhoan().equalsIgnoreCase(ten)) {
                if (!sp.getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTaiKhoanTrung(String ten) {
        for (KhachHang sp : repository.findAll()) {
            if (sp.getGioiTinh() == null) {
                continue;
            }
            if (sp.getTenTaiKhoan().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkEmailSua(Long id, String email) {
        for (KhachHang sp : repository.findAll()) {
            if (sp.getGioiTinh() == null) {
                continue;
            }
            if (sp.getEmail().equalsIgnoreCase(email)) {
                if (!sp.getId().equals(id)) {
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email) {
        for (KhachHang sp : repository.findAll()) {
            if (sp.getGioiTinh() == null) {
                continue;
            }
            if (sp.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void sendEmail(KhachHang taiKhoan, String path, String random) {
        String from = "sshoeshopshoes@gmail.com";
        String to = taiKhoan.getEmail();
        String subject = "Chào mừng bạn đến với S-Shoe - Tài khoản Khách Hàng mới đã được tạo";
        String content =
                "Chào bạn," + "<br>" +
                        "Chúc mừng! Tài khoản Khách Hàng mới của bạn tại S-Shoe đã được tạo thành công. Dưới đây là thông tin đăng nhập của bạn:" + "<br>" +
                        "- Tài khoản:  " + taiKhoan.getTenTaiKhoan() + "<br>" +
                        "- Mật khẩu:   " + random +
                        "<br>" +
                        "Cảm ơn bạn đã chọn S-shoe! Nếu bạn có bất kỳ câu hỏi hoặc cần hỗ trợ, đừng ngần ngại liên hệ với chúng tôi.";
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from, "S-Shoe");
            helper.setTo(to);
            helper.setSubject(subject);
            content = content.replace("[[name]]", taiKhoan.getTenTaiKhoan());
            String siteUrl = "Mật khẩu" + random + "Tài khoản" + taiKhoan.getTenTaiKhoan();

            System.out.println(siteUrl);

            content = content.replace("[[URL]]", siteUrl);

            helper.setText(content, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public KhachHang getTaiKhoanByName(String name) {

        return repository.findByTenTaiKhoan(name).orElse(null);

    }
}
