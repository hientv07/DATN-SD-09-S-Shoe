package com.datnsd09.Datnsd09.config;//package com.datnsd09.Datnsd09.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class MailConfig {
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");  // Thay thế bằng SMTP server của bạn
//        mailSender.setPort(587);  // Thay thế bằng cổng SMTP của bạn
//        mailSender.setUsername("sshoeshopshoes@gmail.com");  // Thay thế bằng địa chỉ email của bạn
//        mailSender.setPassword("fptsshoe123");  // Thay thế bằng mật khẩu email của bạn
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");  // Đặt thành true nếu bạn muốn xem log debug
//
//        return mailSender;
//    }
//}
