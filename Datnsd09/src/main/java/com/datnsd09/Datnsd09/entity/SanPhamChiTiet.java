package com.datnsd09.Datnsd09.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "chi_tiet_san_pham")
//
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_ctsp")
    private Long id;

    @Column(name = "so_luong")
    @NotNull(message = "Số lượng ko được để trống")
    @Min(value = 0, message = "Số lượng nhỏ nhất là 0")
    @Max(value = 99999, message = "Số lượng lớn nhất là 99999")
    private Integer soLuong;

    @Column(name = "gia")
    @NotNull(message = "Giá ko được để trống")
    @Min(value = 1, message = "Giá nhỏ nhất là 1")
    @Max(value = 1000000000, message = "Giá lớn nhất là 1000000000")
    private Long giaHienHanh;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySua;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "san_pham_id", referencedColumnName = "id_sp")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kich_co_id", referencedColumnName = "id_kich_co")
    private KichCo kichCo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mau_sac_id", referencedColumnName = "id_ms")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loai_de_id", referencedColumnName = "id_ld")
    private LoaiDe loaiDe;

    public static String formatCurrency(long giaHienHanh) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(giaHienHanh) + " VND";
    }
}
