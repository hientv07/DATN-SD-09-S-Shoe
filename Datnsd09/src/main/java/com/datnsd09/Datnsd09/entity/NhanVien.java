package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")

public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nv")
    private Long id;

    @Column(name = "ho_va_ten", length = 100)
    @NotBlank(message = "Họ và tên không được trống")
    private String hoVaTen;

    @Column(name = "ngay_sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày sinh không được trống")
    @PastOrPresent(message = "Phải là một ngày trong quá khứ hoặc hiện tại")
    private Date ngaySinh;

    // Kiem tra ngay sinh >= 1923
    public boolean isValidNgaySinh() {
        if (ngaySinh != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(ngaySinh);
            int year = cal.get(Calendar.YEAR);
            return year >= 1900;
        }
        return true; // Truong ngaySinh co the de trong
    }

    @Column(name = "gioi_tinh")
    @NotNull(message = "Giới tính không được để trống")
    private Integer gioiTinh;

    @Column(name = "so_dien_thoai", length = 15)
    @NotBlank(message = "Số điện thoai không được trống")
    @Pattern(regexp = "^(0[35789][0-9]{8,9})$", message = "Số điện thoại không hợp lệ")
    private String soDienThoai;

    @Column(name = "email", length = 255)
    @NotBlank(message = "Email không được trống")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email không hợp lệ")
    private String email;

    @Column(name = "ten_tai_khoan", length = 100)
    @NotBlank(message = "Tên tài khoản không được trống")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Tên tài khoản không hợp lệ")
    private String tenTaiKhoan;

    @Column(name = "mat_khau")
    @NotBlank(message = "Mật khẩu không được trống")
    private String matKhau;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vai_tro_id", referencedColumnName = "id_vai_tro")
    private VaiTro vaiTro;

}
