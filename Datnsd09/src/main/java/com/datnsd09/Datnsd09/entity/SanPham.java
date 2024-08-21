package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "san_pham")
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sp")
    private Long id;

    @Column(name = "ma_sp", length = 50 , nullable = false)
    private String ma;

    @Column(name = "ten_sp", length = 255)
    @NotBlank(message = "Tên sản phẩm ko được để trống")
    private String ten;

    @Column(name = "mo_ta")
    @NotBlank(message = "Mô tả sản phẩm ko được để trống")
    private String moTa;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "nguoi_tao", length = 100)
    private String nguoiTao;

    @Column(name = "nguoi_sua", length = 100)
    private String nguoiSua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thuong_hieu_id", referencedColumnName = "id_thuong_hieu")
    private ThuongHieu thuongHieu;

    @OneToMany(mappedBy = "sanPham",cascade = CascadeType.ALL)
    private List<HinhAnhSanPham> listHinhAnhSanPham = new ArrayList<>();

//    @OneToMany(mappedBy = "sanPham")
//    private List<SanPhamChiTiet> sanPhamChiTiet;

    @OneToMany(mappedBy = "sanPham",cascade = CascadeType.ALL)
    private List<SanPhamChiTiet> listCTSP = new ArrayList<>();

}
