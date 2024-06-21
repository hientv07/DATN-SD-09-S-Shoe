package com.datnsd09.Datnsd09.config;

import com.datnsd09.Datnsd09.entity.KhachHang;
import com.datnsd09.Datnsd09.entity.NhanVien;
import com.datnsd09.Datnsd09.repository.KhachHangRepository;
import com.datnsd09.Datnsd09.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private NhanVienRepository repository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<NhanVien> nhanVienOpt = repository.findByTenTaiKhoan(username);
        if (nhanVienOpt.isPresent()){
            NhanVien nhanVien = nhanVienOpt.get();
            return new UserInfoUserDetails(nhanVien);
        }

        Optional<KhachHang> khachHangOpt = khachHangRepository.findByTenTaiKhoan(username);
        if (khachHangOpt.isPresent()){
            KhachHang khachHang = khachHangOpt.get();
            return new UserInfoUserDetails(khachHang);
        }
        throw  new UsernameNotFoundException("user not found " + username);

    }


}
