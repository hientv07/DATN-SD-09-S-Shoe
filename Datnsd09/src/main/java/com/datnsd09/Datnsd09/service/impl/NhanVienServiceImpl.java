package com.datnsd09.Datnsd09.service.impl;

import com.datnsd09.Datnsd09.entity.NhanVien;
import com.datnsd09.Datnsd09.repository.NhanVienRepository;
import com.datnsd09.Datnsd09.service.NhanVienService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

//    Add tai khoan nhan vien

//    @Override
//    public String addUser(NhanVien userInfo) {
//        userInfo.setTrangThai(0);
//        userInfo.setMatKhau(passwordEncoder.encode(userInfo.getMatKhau()));
//        repository.save(userInfo);
//        return "user added to system";
//    }
//
//    @Override
//    public String updateUser(NhanVien userInfo) {
//        userInfo.setTrangThai(0);
//        userInfo.setMatKhau(passwordEncoder.encode(userInfo.getMatKhau()));
//        repository.save(userInfo);
//        return "user added to system";
//    }
//
////       Cac phương thức để  email xác nhận
//    @Override
//    public void sendEmail(NhanVien taiKhoan, String url) {
//        String from = "sshoeshopshoes@gmail.com";
//        String to = taiKhoan.getEmail();
//        String subject = "Khôi Phục Mật Khẩu Tài Khoản S-Shoe của Bạn";
//        String content = "<p class=\"email-content\" style=\"font-family: 'Arial', sans-serif;font-size: 16px;color: #333;line-height: 1.5;\">\n" +
//                "Chào [[name]], <br>\n" +
//                "Chúc mừng! Bạn đã yêu cầu hướng dẫn khôi phục mật khẩu cho tài khoản của mình trên S-Shoe. Để tiếp tục quá trình này, vui lòng nhấn vào liên kết dưới đây:\n" +
//                "</p>\n" +
//
//                "<p class=\"email-content\">\n" +
//                "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" +
//                "</p>\n" +
//
//                "<p class=\"email-content\">\n" +
//                "<p>Tên tài khoản của bạn: " + taiKhoan.getTenTaiKhoan() + "</p>" +
//                "<p>Email của bạn: " + taiKhoan.getEmail() + "</p>" +
//                "Nếu bạn không yêu cầu hướng dẫn khôi phục mật khẩu hoặc không nhớ việc này, hãy bỏ qua email này. Liên kết xác nhận sẽ hết hạn sau 24 giờ.\n" +
//                "<br>\n" +
//                "Chân thành cảm ơn,\n" +
//                "<br>\n" +
//                "Đội ngũ S-Shoe\n" +
//                "</p>";
//        try {
//
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//
//            helper.setFrom(from, "S-Shoe");
//            helper.setTo(to);
//            helper.setSubject(subject);
//
//            content = content.replace("[[name]]", taiKhoan.getTenTaiKhoan());
//            String siteUrl = url + "/verify?code=" + taiKhoan.getMatKhau();
//
//            System.out.println(siteUrl);
//
//            content = content.replace("[[URL]]", siteUrl);
//
//            helper.setText(content, true);
//
//            javaMailSender.send(message);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void sendEmail1(NhanVien taiKhoan, String url, String random) {
//
//    }
//
//    @Override
//    public boolean verifyAccount(String verificationPassWord, String resetPass) {
//        NhanVien user = repository.findByMatKhau(verificationPassWord);
//
//        if (user == null) {
//            return false;
//        } else {
//            user.setMatKhau(resetPass);
//            repository.save(user);
//            return true;
//        }
//    }
//
//    @Override
//    public NhanVien saveUser(NhanVien user, String url) {
//        String password = passwordEncoder.encode(user.getMatKhau());
//        user.setMatKhau(password);
//        VaiTro vaiTro = new VaiTro();
//        vaiTro.setId(Long.valueOf(2));
//        user.setVaiTro(vaiTro);
//
//        user.setTrangThai(0);
//        NhanVien newuser = repository.save(user);
//
//        if (newuser != null) {
//            sendEmail(newuser, url);
//        }
//
//        return newuser;
//    }
//
//    @Override
//    public NhanVien getNhanVienByName(String name) {
//
//        return repository.findByTenTaiKhoan(name).orElse(null);
//
//    }

    //add thong tin nhan vien

    @Override
    public List<NhanVien> getAll() {

        return repository.fillAllNhanVien();

    }

    @Override
    public List<NhanVien> getAllDangHoatDong() {

        return repository.fillAllDangHoatDong();

    }

    @Override
    public List<NhanVien> getAllNgungHoatDong() {

        return repository.fillAllNgungHoatDong();

    }

    @Override
    public NhanVien add(NhanVien nhanVien) {

        return repository.save(nhanVien);

    }

    @Override
    public NhanVien update(NhanVien nhanVien) {

        return repository.save(nhanVien);
    }

    @Override
    public void remove(Long id) {

        repository.deleteById(id);

    }

    @Override
    public NhanVien getById(Long id) {

        return repository.findById(id).get();

    }

    @Override
    public boolean checkTenTrung(String ten) {

        for (NhanVien nv : repository.findAll()) {
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

        for (NhanVien nv : repository.findAll()) {
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

        for (NhanVien sp : repository.findAll()) {
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

        for (NhanVien sp : repository.findAll()) {
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

        for (NhanVien sp : repository.findAll()) {
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
        for (NhanVien sp : repository.findAll()) {
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
    public void sendEmail(NhanVien taiKhoan, String path, String random) {
        String from = "sshoeshopshoes@gmail.com";
        String to = taiKhoan.getEmail();
        String subject = "Chào mừng bạn đến với S-Shoe - Tài khoản Nhân viên mới đã được tạo";
        String content =
                "Chào bạn," + "<br>" +
                        "Chúc mừng! Tài khoản nhân viên mới của bạn tại S-Shoe đã được tạo thành công. Dưới đây là thông tin đăng nhập của bạn:" + "<br>" +
                        "- Tài khoản:  " + taiKhoan.getTenTaiKhoan() + "<br>" +
                        "- Mật khẩu:   " + random +
                        "<br>" +
                        "Cảm ơn bạn đã gia nhập đội ngũ của chúng tôi! Nếu bạn có bất kỳ câu hỏi hoặc cần sự hỗ trợ, đừng ngần ngại liên hệ với chúng tôi.";
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
}
