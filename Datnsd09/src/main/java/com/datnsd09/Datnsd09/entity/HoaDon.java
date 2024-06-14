package com.datnsd09.Datnsd09.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hoa_don")

public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hoa_don")
    private Long id;

    @Column(name = "ma_hoa_don", length = 20)
    private String maHoaDon;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "loai_hoa_don")
    private Integer loaiHoaDon;

    @Column(name = "phi_ship")
    private Long phiShip;

    @Column(name = "tien_giam")
    private Long tienGiam;

    @Column(name = "tong_tien")
    private Long tongTien;

    @Column(name = "tong_tien_khi_giam")
    private Long tongTienKhiGiam;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @Column(name = "nguoi_nhan", length = 100)
    private String nguoiNhan;

    @Column(name = "sdt_nguoi_nhan", length = 15)
    private String sdtNguoiNhan;
    @Column(name = "thanh_pho", length = 50)
    private String thanhPho;

    @Column(name = "quan_huyen", length = 50)
    private String quanHuyen;

    @Column(name = "phuong_xa", length = 50)
    private String phuongXa;

    @Column(name = "dia_chi_nguoi_nhan", length = 100)
    private String diaChiNguoiNhan;

    @Column(name = "email_nguoi_nhan", length = 100)
    private String emailNguoiNhan;

    @Column(name = "ngay_nhan")
    private Date ngayNhan;

    @Column(name = "ngay_mong_muon")
    private Date ngayMongMuon;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "HH:mm dd/MM/yyyy")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phuong_thuc_thanh_toan_id", referencedColumnName = "id_pttt")
    private PhuongThucThanhToan phuongThucThanhToan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhan_vien_id", referencedColumnName = "id_nv")
    private NhanVien nhanVien;
}
