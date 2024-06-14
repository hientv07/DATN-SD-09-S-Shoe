package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "phuong_thuc_thanh_toan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class PhuongThucThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pttt")
    private Long id;

    @Column(name = "ma_phuong_thuc")
    private String maPhuongThuc;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
