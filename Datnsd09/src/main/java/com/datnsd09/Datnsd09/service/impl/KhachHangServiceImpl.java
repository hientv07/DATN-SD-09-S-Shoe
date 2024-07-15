package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.GioHang;
import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.entity.NhanVien;
import com.datnsd09.Datnsd09.repository.KhachHangRepository;
import com.datnsd09.Datnsd09.repository.NhanVienRepository;
import com.datnsd09.Datnsd09.service.GioHangService;
import com.datnsd09.Datnsd09.service.KhachHangService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<KhachHang> getAll() {
        return repository.fillAllKhachHang();
    }

    @Override
    public KhachHang save(KhachHang khachHang) {
        return repository.save(khachHang);
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
                        "- Mật khẩu:   " + taiKhoan.getMatKhau() + "<br>" +
                        "- Mã xác minh:   " + random +
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

    @Override
    public String addUser(KhachHang userInfo) {
        userInfo.setTrangThai(0);
        userInfo.setMatKhau(passwordEncoder.encode(userInfo.getMatKhau()));
        repository.save(userInfo);
        GioHang gioHang = new GioHang();
        Date currentDate = new Date();
        gioHang.setMaGioHang("GH" + gioHangService.genMaTuDong());
        gioHang.setGhiChu("");
        gioHang.setNgayTao(currentDate);
        gioHang.setNgaySua(currentDate);
        gioHang.setKhachHang(KhachHang.builder().id(userInfo.getId()).build());
        gioHang.setTrangThai(0);
        gioHangService.save(gioHang);
        return "user added to system";
    }


    @Override
    public String updateUser(KhachHang userInfo) {
        userInfo.setTrangThai(0);
        userInfo.setMatKhau(passwordEncoder.encode(userInfo.getMatKhau()));
        repository.save(userInfo);
        return "user added to system";
    }

    // Ngọc Hiếu
    @Override
    public void guiLieuHe(String hoTen, String email, String chuDe, String tinNhan) {
        String from = email;
        String to = "sshoeshopshoes@gmail.com";
        String subject = chuDe;
        String content = tinNhan;
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from, hoTen);
            helper.setTo(to);
            helper.setSubject(subject);

            helper.setText(content,true);

            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void sendEmailKhoiPhuc(KhachHang taiKhoan, String url) {
        String from = "sshoeshopshoes@gmail.com";
        String to = taiKhoan.getEmail();
        String subject = "Khôi Phục Mật Khẩu Tài Khoản S-Shoe của Bạn";
        String content = "<p class=\"email-content\" style=\"font-family: 'Arial', sans-serif;font-size: 16px;color: #333;line-height: 1.5;\">\n" +
                "Chào [[name]], <br>\n" +
                "Chúc mừng! Bạn đã yêu cầu hướng dẫn khôi phục mật khẩu cho tài khoản của mình trên S-Shoe. Để tiếp tục quá trình này, vui lòng nhấn vào liên kết dưới đây:\n" +
                "</p>\n" +

                "<p class=\"email-content\">\n" +
                "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" +
                "</p>\n" +

                "<p class=\"email-content\">\n" +
                "<p>Tên tài khoản của bạn: " + taiKhoan.getTenTaiKhoan() + "</p>" +
                "<p>Email của bạn: " + taiKhoan.getEmail() + "</p>" +
                "Nếu bạn không yêu cầu hướng dẫn khôi phục mật khẩu hoặc không nhớ việc này, hãy bỏ qua email này. Liên kết xác nhận sẽ hết hạn sau 24 giờ.\n" +
                "<br>\n" +
                "Chân thành cảm ơn,\n" +
                "<br>\n" +
                "Đội ngũ S-shoe\n" +
                "</p>";
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "S-Shoe");
            helper.setTo(to);
            helper.setSubject(subject);

            content = content.replace("[[name]]", taiKhoan.getTenTaiKhoan());
            String siteUrl = url + "/verify?code=" + taiKhoan.getMatKhau();

            System.out.println(siteUrl);

            content = content.replace("[[URL]]", siteUrl);

            helper.setText(content, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public KhachHang findKhachLe() {
        return repository.findKhachLe();
    }

    @Override
    public void addKhachLe() {
        repository.addKhachLe();
    }
}
