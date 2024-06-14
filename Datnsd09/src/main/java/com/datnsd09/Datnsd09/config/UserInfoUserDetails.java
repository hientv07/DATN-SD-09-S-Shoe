package com.datnsd09.Datnsd09.config;

import com.datnsd09.Datnsd09.entity.NhanVien;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {

    private Long id;
    private String ten_tai_khoan;
    private String matKhau;
    private String hoVaTen;
    private String email;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(NhanVien userInfo) {
        id = userInfo.getId();
        ten_tai_khoan = userInfo.getTenTaiKhoan();
        matKhau = userInfo.getMatKhau();
        hoVaTen = userInfo.getHoVaTen();
        email = userInfo.getEmail();
        authorities = Arrays.stream(userInfo.getVaiTro().getTenVaiTro().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long id() {
        return id;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return ten_tai_khoan;
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
