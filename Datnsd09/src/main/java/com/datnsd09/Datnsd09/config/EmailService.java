package com.datnsd09.Datnsd09.config;//package com.datnsd09.Datnsd09.config;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//    @Autowired
//    private JavaMailSender emailSender;
//
//    public void sendSimpleMessage(String to, String subject, String text) {
//        try {
//            MimeMessage message = emailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text, true); // true để bật định dạng HTML
//
//            emailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            // Xử lý ngoại lệ khi gửi email không thành công
//        }
//    }
//}
