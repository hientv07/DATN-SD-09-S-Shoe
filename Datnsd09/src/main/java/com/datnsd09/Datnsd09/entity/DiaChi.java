package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dia_chi")
@Builder
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dia_chi")
    private Long id;

    @Column(name = "thanh_pho", length = 50)
    @NotBlank(message = "Thành phố không được trống")
    private String thanhPho;

    @Column(name = "quan_huyen", length = 50)
    @NotBlank(message = "Quận huyện không được trống")
    private String quanHuyen;

    @Column(name = "phuong_xa", length = 50)
    @NotBlank(message = "Phường xã không được trống")
    private String phuongXa;

    @Column(name = "dia_chi_cu_the", length = 100)
    @NotBlank(message = "Địa chỉ không được trống")
    private String diaChiCuThe;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "khach_hang_id", referencedColumnName = "id_kh")
    private  KhachHang khachHang;

}

