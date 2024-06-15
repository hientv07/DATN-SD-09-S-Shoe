package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voucher")
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voucher")
    private Long id;

    @Column(name = "ma_voucher")
    @NotBlank(message = "Không được trống mã")
    @Size(max = 20, message = "Mã voucher không được quá 20 ký tự")
    private String maVoucher;

    @Column(name = "ten_voucher")
    @NotBlank(message = "Không được trống tên")
    @Size(max = 50, message = "Tên voucher không được quá 50 ký tự")
    private String tenVoucher;

    @Column(name = "ngay_bat_dau")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Ngày bắt đầu không được trống")
    private LocalDateTime ngayBatDau;


    @Column(name = "ngay_ket_thuc")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Ngày kết thúc không được trống")
    private LocalDateTime ngayKetThuc;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySua;

    @Column(name = "so_luong")
    @NotNull(message = "Số lượng không để trống")
    @DecimalMin(value = "0", inclusive = true,message = "Số lượng  không nhỏ hơn 0")
    @DecimalMax(value = "100000", inclusive = true,message = "Số lượng  không lớn hơn 100,000")
    private BigDecimal soLuong;

    @Column(name = "phan_tram_giam")
    @NotNull(message = " Phần trăm giảm không để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Phần trăm giảm phải lớn hơn 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "Phần trăm giảm không được vượt quá 100")
    private BigDecimal phanTramGiam;

    @Column(name = "giam_toi_da")
    @NotNull(message = " Giá trị giảm tối đa không để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá trị giảm tối đa phải lớn hơn 0")
    @DecimalMax(value = "100000000", inclusive = true, message = "Giá trị giảm tối đa không được vượt quá 100,000,000")
    private Long giamToiDa;

    @Column(name = "gia_tri_don_toi_thieu")
    @NotNull(message = "Giá trị tối thiểu không để trống")
    @DecimalMin(value = "10000", inclusive = true,message = "Giá trị tối thiểu không nhỏ hơn 10,000")
    @DecimalMax(value = "100000000", inclusive = true,message = "Giá trị tối thiểu không lớn hơn 100,000,000")
    private Long giaTriDonToiThieu;

    @Column(name = "trang_thai")
    private Integer trangThai;


}

