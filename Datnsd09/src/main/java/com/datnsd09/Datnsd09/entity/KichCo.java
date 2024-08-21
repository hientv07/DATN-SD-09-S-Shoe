package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Table(name = "kich_co")
@Entity
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class KichCo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_kich_co")
    private Long id;

    @Column(name = "ten_kich_co")
    @NotNull(message = "Không được để trống kích cỡ")
    @Min(value = 35, message = "Kích cỡ nhỏ nhất là 35")
    @Max(value = 45, message = "Kích cỡ lớn nhất là 45")
    private Integer ten;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
