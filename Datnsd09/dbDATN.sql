DROP DATABASE IF EXISTS DATN_SD09 ;

create database DATN_SD09
go

use DATN_SD09 
go

CREATE TABLE mau_sac (
	id_ms			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_mau		VARCHAR(30) NULL,			
	ten_mau			NVARCHAR(100) DEFAULT NULL,
	ngay_tao	DATETIME NULL,
	ngay_sua	DATETIME NULL,
	trang_thai	INT DEFAULT 0
)
GO

-- Tạo bảng Loại đế
CREATE TABLE loai_de (
	id_ld			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ten_ld			NVARCHAR(100) DEFAULT NULL,
	ngay_tao	DATETIME NULL,
	ngay_sua	DATETIME NULL,
	trang_thai	INT DEFAULT 0
)
GO

--Tạo bảng Thương hiệu
CREATE TABLE thuong_hieu (
	id_thuong_hieu			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ten_thuong_hieu			NVARCHAR(100) DEFAULT NULL,
	ngay_tao	DATETIME NULL,
	ngay_sua	DATETIME NULL,
	trang_thai	INT DEFAULT 0
)
GO

-- Tạo bảng Sản phẩm
CREATE TABLE san_pham (
	id_sp				BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_sp				VARCHAR(50) NOT NULL,	
	ten_sp				NVARCHAR(255) NULL,
	mo_ta			NVARCHAR(MAX) NULL,
	ngay_tao		DATETIME NULL,
	ngay_sua		DATETIME NULL,
	nguoi_tao		NVARCHAR(100) NULL,
	nguoi_sua		NVARCHAR(100) NULL,
	trang_thai		INT DEFAULT 0,
	thuong_hieu_id	BIGINT	
		REFERENCES thuong_hieu(id_thuong_hieu),
)
GO

-- Tạo bảng Hình ảnh sản phẩm
CREATE TABLE hinh_anh_san_pham (
	id_hinh_anh			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	url			NVARCHAR(255),
	ngay_tao	DATETIME NULL,
	ngay_sua	DATETIME NULL,
	trang_thai	INT DEFAULT 0,
	uu_tien		INT DEFAULT 0,
	san_pham_id BIGINT
		REFERENCES san_pham(id_sp)
)
GO

CREATE TABLE kich_co(
	id_kich_co			BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
	ten_kich_co			INT NULL,
	ngay_tao	DATETIME NULL,
	ngay_sua	DATETIME NULL,
	trang_thai	INT DEFAULT 0
)
GO

--Tạo bảng Chi tiết sản phẩm
CREATE TABLE chi_tiet_san_pham (
	id_ctsp			BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	so_luong	INT NULL,
	gia	MONEY NULL,
	trang_thai	INT DEFAULT 0,
	ngay_tao		DATETIME NULL,
	ngay_sua		DATETIME NULL,

	san_pham_id BIGINT
		REFERENCES san_pham(id_sp),

	kich_co_id BIGINT
		REFERENCES kich_co(id_kich_co),

	mau_sac_id	BIGINT	
		REFERENCES mau_sac(id_ms),

	loai_de_id	BIGINT	
		REFERENCES loai_de(id_ld),

)
GO

-- Tạo bảng Vai trò
CREATE TABLE vai_tro (
	id_vai_tro			BIGINT IDENTITY(1,1) PRIMARY KEY,
	ten_vai_tro			NVARCHAR(100) DEFAULT NULL
)
GO
--drop table vai_tro

---- Tạo bảng Tài khoản
--CREATE TABLE tai_khoan(
--	id_tk					BIGINT IDENTITY(1,1) PRIMARY KEY,
--	ho_va_ten			NVARCHAR(100) DEFAULT NULL,
--	ngay_sinh			DATE NULL,
--	gioi_tinh			int,
--	so_dien_thoai		VARCHAR(15) NULL,
--	email				VARCHAR(255) NULL,
--	ten_tai_khoan		NVARCHAR(100) NULL,
--	mat_khau			VARCHAR(300),
--	ngay_tao			DATETIME NULL,
--	ngay_sua			DATETIME NULL,
--	trang_thai			INT DEFAULT 0,


--	vai_tro_id	BIGINT	
--		REFERENCES vai_tro(id_vai_tro)

--)
--GO

-- Tạo bảng Nhân viên
CREATE TABLE nhan_vien(
	id_nv BIGINT IDENTITY(1,1) PRIMARY KEY,
	ho_va_ten			NVARCHAR(100) DEFAULT NULL,
	ngay_sinh			DATE NULL,
	gioi_tinh			int,
	so_dien_thoai		VARCHAR(15) NULL,
	email				VARCHAR(255) NULL,
	ten_tai_khoan		NVARCHAR(100) NULL,
	mat_khau			VARCHAR(300),
	ngay_tao			DATETIME NULL,
	ngay_sua			DATETIME NULL,
	trang_thai			INT DEFAULT 0,
	vai_tro_id	BIGINT	
		REFERENCES vai_tro(id_vai_tro)
)

--Khách hàng
CREATE TABLE khach_hang(
	id_kh BIGINT IDENTITY(1,1) PRIMARY KEY,
	ho_va_ten			NVARCHAR(100) DEFAULT NULL,
	ngay_sinh			DATE NULL,
	gioi_tinh			int,
	so_dien_thoai		VARCHAR(15) NULL,
	email				VARCHAR(255) NULL,
	ten_tai_khoan		NVARCHAR(100) NULL,
	mat_khau			VARCHAR(300),
	ngay_tao			DATETIME NULL,
	ngay_sua			DATETIME NULL,
	ten_vai_tro			NVARCHAR(100) DEFAULT NULL,
	trang_thai			INT DEFAULT 0,
	)


-- Địa chỉ
CREATE TABLE dia_chi(
	id_dia_chi				BIGINT IDENTITY(1,1) PRIMARY KEY,
	thanh_pho		NVARCHAR(50) NULL,
	quan_huyen		NVARCHAR(50) NULL,
	phuong_xa		NVARCHAR(50) NULL,
	dia_chi_cu_the	NVARCHAR(100) NULL,
	ngay_tao		DATETIME NULL,
	ngay_sua		DATETIME NULL,
	trang_thai		INT DEFAULT 0,

	khach_hang_id	BIGINT 
		REFERENCES khach_hang(id_kh)
)
GO

-- Voucher
CREATE TABLE voucher(
	id_voucher					BIGINT IDENTITY(1,1) PRIMARY KEY,
	ma_voucher			VARCHAR(50) NULL,
	ten_voucher			NVARCHAR(50) NULL,
	ngay_bat_dau		DATETIME NULL,
	ngay_ket_thuc		DATETIME NULL,
	ngay_tao			DATETIME NULL,
	ngay_sua			DATETIME NULL,
	so_luong	        INT NULL,
	phan_tram_giam		int NULL,
	giam_toi_da			MONEY NULL,
	gia_tri_don_toi_thieu	MONEY NULL,
	trang_thai			INT,
)
GO

--Phương thức thanh toán
CREATE TABLE phuong_thuc_thanh_toan(
	id_pttt				BIGINT IDENTITY(1,1) PRIMARY KEY,
	ma_phuong_thuc	VARCHAR(20) NULL,
	ten				NVARCHAR(50) NULL,
	ghi_chu			NVARCHAR(255) NULL,
	trang_thai		INT,
)
GO

-- Hóa đơn 
CREATE TABLE hoa_don(
	id_hd					BIGINT IDENTITY(1,1) PRIMARY KEY,
	ma_hoa_don			VARCHAR(20) NULL,
	ngay_thanh_toan		DATETIME NULL,
	loai_hoa_don		INT NULL,
	phi_ship			MONEY NULL,
	tien_giam			MONEY NULL,
	tong_tien			MONEY NULL,
	tong_tien_khi_giam	MONEY NULL,
	ghi_chu				NVARCHAR(255) NULL,
	nguoi_nhan			NVARCHAR(100) NULL,
	sdt_nguoi_nhan		VARCHAR(15) NULL,
	thanh_pho		NVARCHAR(50) NULL,
	quan_huyen		NVARCHAR(50) NULL,
	phuong_xa		NVARCHAR(50) NULL,
	dia_chi_nguoi_nhan	NVARCHAR(100) NULL,
	email_nguoi_nhan	VARCHAR(100) NULL,
	ngay_nhan			DATETIME NULL,
	ngay_mong_muon		DATETIME NULL,
	ngay_tao			DATETIME NULL,
	ngay_sua			DATETIME NULL,
	trang_thai			INT,

	voucher_id BIGINT 
		REFERENCES voucher(id_voucher),

	nhan_vien_id BIGINT 
		REFERENCES nhan_vien(id_nv),

	khach_hang_id BIGINT 
		REFERENCES khach_hang(id_kh),

	phuong_thuc_thanh_toan_id BIGINT 
		REFERENCES phuong_thuc_thanh_toan(id_pttt)
)
GO



--Hóa đơn chi tiết
CREATE TABLE hoa_don_chi_tiet(
	id_hdct			BIGINT IDENTITY(1,1) PRIMARY KEY,
	so_luong	INT NULL,
	don_gia		MONEY NULL,
	ghi_chu		NVARCHAR(255) NULL,
	ngay_tao	DATETIME NULL,
	ngay_sua	DATETIME NULL,
	nguoi_tao	NVARCHAR(100) NULL,
	nguoi_sua	NVARCHAR(100) NULL,
	trang_thai	INT,

	hoa_don_id BIGINT 
		REFERENCES hoa_don(id_hd),

	chi_tiet_san_pham_id BIGINT 
		REFERENCES chi_tiet_san_pham(id_ctsp)

)
GO

--Giỏ hàng
CREATE TABLE gio_hang(
	id_gio_hang			BIGINT IDENTITY(1,1) PRIMARY KEY,
	ma_gio_hang	VARCHAR(20) NULL,
	--so_luong	INT NULL,
	ghi_chu		NVARCHAR(255) NULL,
	ngay_tao	DATETIME NULL,
	ngay_sua	DATETIME NULL,
	trang_thai	INT,

	--nhan_vien_id BIGINT 
	--	REFERENCES nhan_vien(id_nv),

	khach_hang_id BIGINT 
		REFERENCES khach_hang(id_kh),

	--chi_tiet_san_pham_id BIGINT 
	--	REFERENCES chi_tiet_san_pham(id_ctsp)

)
GO

--Giỏ hàng chi tiet
CREATE TABLE gio_hang_chi_tiet(
	id_ghct			BIGINT IDENTITY(1,1) PRIMARY KEY,
	so_luong	INT NULL,
	ghi_chu 	NVARCHAR(255) NULL,
	ngay_tao	DATETIME NULL,
	ngay_sua	DATETIME NULL,
	trang_thai	INT,

	gio_hang_id BIGINT 
		REFERENCES gio_hang(id_gio_hang),

	chi_tiet_san_pham_id BIGINT 
		REFERENCES chi_tiet_san_pham(id_ctsp)

)
GO

-- Lịch sử hóa đơn
--CREATE TABLE lich_su_hoa_don(
--	id_lshd			BIGINT	IDENTITY(1,1) PRIMARY KEY,
--	ngay_tao	DATETIME NULL,
--	ngay_sua	DATETIME NULL,
--	ghi_chu		NVARCHAR(255) NULL,
--	trang_thai	INT,
--	nguoi_sua	NVARCHAR(70),

--	hoa_don_id  BIGINT 
--		REFERENCES hoa_don(id_hd)
--)
--GO

use DATN_SD09 
go

SET IDENTITY_INSERT [dbo].[mau_sac] ON 

INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (1, N'MS1', N'Đen', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (2, N'MS2', N'Trắng', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (3, N'MS3', N'Đỏ', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (4, N'MS4', N'Xanh Dương', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (5, N'MS5', N'Vàng', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (6, N'MS6', N'Hồng', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (7, N'MS7', N'Xanh Lá', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (8, N'MS8', N'Cam', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (9, N'MS9', N'Xám', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[mau_sac] ([id_ms], [ma_mau], [ten_mau], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (10, N'MS10', N'Nâu', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[mau_sac] OFF
GO

SET IDENTITY_INSERT [dbo].[loai_de] ON 

INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (1, N'Đế Cao Su', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (2, N'Đế Bằng', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (3, N'Đế Gỗ', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (4, N'Đế Cao Su Nén', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (5, N'Đế EVA', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (6, N'Đế Đinh Tán', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (7, N'Đế Đinh Tán Cao Su', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (8, N'Đế Đinh Tán Gỗ', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (9, N'Đế Mềm', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[loai_de] ([id_ld], [ten_ld], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (10, N'Đế Cứng', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[loai_de] OFF
GO

SET IDENTITY_INSERT [dbo].[thuong_hieu] ON 

INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (1, N'Adidas', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (2, N'Nike', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (3, N'Puma', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (4, N'Converse', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (5, N'Reebok', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (6, N'Vans', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (7, N'New Balance', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (8, N'Under Armour', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (9, N'Balenciaga', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[thuong_hieu] ([id_thuong_hieu], [ten_thuong_hieu], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (10, N'Gucci', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[thuong_hieu] OFF
GO
SET IDENTITY_INSERT [dbo].[san_pham] ON 

INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (1, N'SP1', N'Balance 574', N'Đôi giày chạy hiệu suất cao, thoải mái và trả năng lượng.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (2, N'SP2', N'Balance 480', N'Đôi giày chạy hiệu suất cao, thoải mái và trả năng lượng.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (3, N'SP3', N'New Balance FW 574', N'Đôi giày chạy hiệu suất cao, thoải mái và trả năng lượng.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (4, N'SP4', N'Balance 909', N'Thời trang và thoải mái với công nghệ khí hiển thị.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (5, N'SP5', N'Shifted 327', N'Thời trang và thoải mái với công nghệ khí hiển thị.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (6, N'SP6', N'New Balance - WL574ZSG', N'Thời trang và thoải mái với công nghệ khí hiển thị.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 1)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (7, N'SP7', N'Fresh Foam X 1080V14', N'Thời trang và thoải mái với công nghệ khí hiển thị.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (8, N'SP8', N' Fresh Foam X 1080V13', N'Thời trang và thoải mái với công nghệ khí hiển thị.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (9, N'SP9', N'Trail More v3', N'Thời trang và thoải mái với công nghệ khí hiển thị.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (10, N'SP10', N' Fresh Foam X 1080v12', N'Phong cách bất diệt, kinh điển và đa dụng.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 0, 1)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (11, N'SP11', N'Newbalance10', N'Phong cách bất diệt, kinh điển và đa dụng.', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 1, 7)
INSERT [dbo].[san_pham] ([id_sp], [ma_sp], [ten_sp], [mo_ta], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [thuong_hieu_id]) VALUES (12, N'SP12', N'Gucci A01', N'Hịn', CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), NULL, NULL, 1, 10)
SET IDENTITY_INSERT [dbo].[san_pham] OFF
GO

SET IDENTITY_INSERT [dbo].[kich_co] ON 

INSERT [dbo].[kich_co] ([id_kich_co], [ten_kich_co], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (1, 37, CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[kich_co] ([id_kich_co], [ten_kich_co], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (2, 38, CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[kich_co] ([id_kich_co], [ten_kich_co], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (3, 39, CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[kich_co] ([id_kich_co], [ten_kich_co], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (4, 40, CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[kich_co] ([id_kich_co], [ten_kich_co], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (5, 41, CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
INSERT [dbo].[kich_co] ([id_kich_co], [ten_kich_co], [ngay_tao], [ngay_sua], [trang_thai]) VALUES (6, 42, CAST(N'2024-06-10 17:03:50' AS DateTime), CAST(N'2024-06-10 17:03:50' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[kich_co] OFF
GO

SET IDENTITY_INSERT [dbo].[chi_tiet_san_pham] ON 

INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (1, 5, 550000.0000, 0, CAST(N'2024-06-01T18:39:44.560' AS DateTime), CAST(N'2024-06-18T22:13:50.073' AS DateTime), 1, 1, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (2, 94, 550000.0000, 0, CAST(N'2024-06-01T18:39:44.560' AS DateTime), CAST(N'2024-06-18T22:13:50.113' AS DateTime), 1, 2, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (3, 60, 550000.0000, 0, CAST(N'2024-06-01T18:39:44.560' AS DateTime), CAST(N'2024-06-18T22:13:50.133' AS DateTime), 1, 3, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (4, 106, 550000.0000, 0, CAST(N'2024-06-01T18:39:44.560' AS DateTime), CAST(N'2024-06-18T22:13:50.140' AS DateTime), 1, 4, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (5, 119, 550000.0000, 0, CAST(N'2024-06-01T18:39:44.560' AS DateTime), CAST(N'2024-06-18T22:13:50.153' AS DateTime), 1, 5, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (61, 130, 600000.0000, 0, CAST(N'2024-06-01T18:49:58.643' AS DateTime), CAST(N'2024-06-01T18:51:17.047' AS DateTime), 2, 2, 2, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (62, 130, 600000.0000, 0, CAST(N'2024-06-01T18:49:58.643' AS DateTime), CAST(N'2024-06-01T18:51:17.053' AS DateTime), 2, 3, 2, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (63, 130, 600000.0000, 0, CAST(N'2024-06-01T18:49:58.643' AS DateTime), CAST(N'2024-06-01T18:51:17.067' AS DateTime), 2, 4, 2, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (64, 130, 620000.0000, 0, CAST(N'2024-06-01T18:49:58.643' AS DateTime), CAST(N'2024-06-01T18:51:17.083' AS DateTime), 2, 2, 3, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (65, 130, 620000.0000, 0, CAST(N'2024-06-01T18:49:58.643' AS DateTime), CAST(N'2024-06-01T18:51:17.097' AS DateTime), 2, 3, 3, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (70, 88, 450000.0000, 0, CAST(N'2024-06-01T21:28:09.387' AS DateTime), CAST(N'2024-06-01T21:28:09.387' AS DateTime), 3, 1, 2, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (71, 89, 450000.0000, 0, CAST(N'2024-06-01T21:28:09.447' AS DateTime), CAST(N'2024-06-01T21:28:09.447' AS DateTime), 3, 2, 2, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (72, 90, 450000.0000, 0, CAST(N'2024-06-01T21:28:09.447' AS DateTime), CAST(N'2024-06-01T21:28:09.447' AS DateTime), 3, 3, 2, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (73, 90, 450000.0000, 0, CAST(N'2024-06-01T21:28:09.453' AS DateTime), CAST(N'2024-06-01T21:28:09.453' AS DateTime), 3, 4, 2, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (74, 89, 450000.0000, 0, CAST(N'2024-06-01T21:28:09.453' AS DateTime), CAST(N'2024-06-01T21:28:09.453' AS DateTime), 3, 1, 3, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (93, 250, 1200000.0000, NULL, CAST(N'2024-06-16T19:33:03.040' AS DateTime), CAST(N'2024-06-18T22:12:21.707' AS DateTime), 5, 4, 3, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (94, 250, 250000.0000, NULL, CAST(N'2024-06-16T19:33:03.040' AS DateTime), CAST(N'2024-06-18T22:12:21.717' AS DateTime), 5, 1, 4, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (95, 250, 250000.0000, NULL, CAST(N'2024-06-16T19:33:03.040' AS DateTime), CAST(N'2024-06-18T22:12:21.727' AS DateTime), 5, 2, 4, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (96, 250, 250000.0000, NULL, CAST(N'2024-06-16T19:33:03.040' AS DateTime), CAST(N'2024-06-18T22:12:21.730' AS DateTime), 5, 3, 4, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (97, 250, 250000.0000, NULL, CAST(N'2024-06-16T19:33:03.040' AS DateTime), CAST(N'2024-06-18T22:12:21.737' AS DateTime), 5, 4, 4, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (98, 200, 320000.0000, 0, CAST(N'2024-06-16T19:33:31.143' AS DateTime), CAST(N'2024-06-16T19:33:31.143' AS DateTime), 8, 1, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (99, 199, 320000.0000, 0, CAST(N'2024-06-16T19:33:31.153' AS DateTime), CAST(N'2024-06-16T19:33:31.153' AS DateTime), 8, 2, 1, 1)
GO
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (100, 200, 320000.0000, 0, CAST(N'2024-06-16T19:33:31.170' AS DateTime), CAST(N'2024-06-16T19:33:31.170' AS DateTime), 8, 3, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (101, 200, 320000.0000, 0, CAST(N'2024-06-16T19:33:31.177' AS DateTime), CAST(N'2024-06-16T19:33:31.177' AS DateTime), 8, 4, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (102, 200, 320000.0000, 0, CAST(N'2024-06-16T19:33:31.183' AS DateTime), CAST(N'2024-06-16T19:33:31.183' AS DateTime), 8, 5, 1, 1)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (118, 1000, 1250000.0000, 0, CAST(N'2024-06-16T19:34:16.587' AS DateTime), CAST(N'2024-06-16T19:34:16.587' AS DateTime), 9, 1, 1, 3)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (119, 1000, 1250000.0000, 0, CAST(N'2024-06-16T19:34:16.597' AS DateTime), CAST(N'2024-06-16T19:34:16.597' AS DateTime), 9, 2, 1, 3)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (120, 1000, 1250000.0000, 0, CAST(N'2024-06-16T19:34:16.603' AS DateTime), CAST(N'2024-06-16T19:34:16.603' AS DateTime), 9, 3, 1, 3)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (121, 1000, 1250000.0000, 0, CAST(N'2024-06-16T19:34:16.607' AS DateTime), CAST(N'2024-06-16T19:34:16.607' AS DateTime), 9, 4, 1, 3)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (122, 1000, 1250000.0000, 0, CAST(N'2024-06-16T19:34:16.613' AS DateTime), CAST(N'2024-06-16T19:34:16.613' AS DateTime), 9, 5, 1, 3)
GO
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (201, 450, 950000.0000, 0, CAST(N'2024-06-16T19:35:13.333' AS DateTime), CAST(N'2024-06-16T19:35:13.333' AS DateTime), 4, 6, 9, 4)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (202, 450, 950000.0000, 0, CAST(N'2024-06-16T19:35:13.337' AS DateTime), CAST(N'2024-06-16T19:35:13.337' AS DateTime), 4, 3, 10, 4)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (203, 450, 950000.0000, 0, CAST(N'2024-06-16T19:35:13.340' AS DateTime), CAST(N'2024-06-16T19:35:13.340' AS DateTime), 4, 4, 10, 4)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (204, 450, 950000.0000, 0, CAST(N'2024-06-16T19:35:13.343' AS DateTime), CAST(N'2024-06-16T19:35:13.343' AS DateTime), 4, 5, 10, 4)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (205, 450, 950000.0000, 0, CAST(N'2024-06-16T19:35:13.347' AS DateTime), CAST(N'2024-06-16T19:35:13.347' AS DateTime), 4, 6, 10, 4)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (206, 350, 2800000.0000, 0, CAST(N'2024-06-16T19:37:05.717' AS DateTime), CAST(N'2024-06-16T19:37:05.717' AS DateTime), 10, 1, 1, 5)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (207, 350, 2800000.0000, 0, CAST(N'2024-06-16T19:37:05.727' AS DateTime), CAST(N'2024-06-16T19:37:05.727' AS DateTime), 10, 2, 1, 5)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (208, 350, 2800000.0000, 0, CAST(N'2024-06-16T19:37:05.733' AS DateTime), CAST(N'2024-06-16T19:37:05.733' AS DateTime), 10, 3, 1, 5)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (209, 350, 2800000.0000, 0, CAST(N'2024-06-16T19:37:05.737' AS DateTime), CAST(N'2024-06-16T19:37:05.737' AS DateTime), 10, 4, 1, 5)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (210, 350, 2800000.0000, 0, CAST(N'2024-06-16T19:37:05.743' AS DateTime), CAST(N'2024-06-16T19:37:05.743' AS DateTime), 10, 5, 1, 5)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (266, 250, 4800000.0000, 0, CAST(N'2024-06-16T19:38:51.847' AS DateTime), CAST(N'2024-06-18T22:14:11.317' AS DateTime), 7, 1, 1, 2)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (267, 250, 4800000.0000, 1, CAST(N'2024-06-16T19:38:51.847' AS DateTime), CAST(N'2024-06-18T22:14:11.327' AS DateTime), 7, 2, 1, 2)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (268, 250, 4800000.0000, 0, CAST(N'2024-06-16T19:38:51.847' AS DateTime), CAST(N'2024-06-18T22:14:11.353' AS DateTime), 7, 3, 1, 2)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (269, 250, 4800000.0000, 0, CAST(N'2024-06-16T19:38:51.847' AS DateTime), CAST(N'2024-06-18T22:14:11.360' AS DateTime), 7, 4, 1, 2)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (270, 250, 4800000.0000, 0, CAST(N'2024-06-16T19:38:51.847' AS DateTime), CAST(N'2024-06-18T22:14:11.373' AS DateTime), 7, 5, 1, 2)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (271, 300, 3000000.0000, 0, CAST(N'2024-07-12T11:38:51.847' AS DateTime), CAST(N'2024-07-18T22:14:11.373' AS DateTime), 6, 2, 1, 9)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (272, 300, 3000000.0000, 0, CAST(N'2024-07-12T11:38:51.847' AS DateTime), CAST(N'2024-07-18T22:14:11.373' AS DateTime), 6, 3, 2, 9)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (273, 300, 3000000.0000, 0, CAST(N'2024-07-12T11:38:51.847' AS DateTime), CAST(N'2024-07-18T22:14:11.373' AS DateTime), 6, 4, 3, 9)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (274, 300, 3000000.0000, 0, CAST(N'2024-07-12T11:38:51.847' AS DateTime), CAST(N'2024-07-18T22:14:11.373' AS DateTime), 6, 5, 4, 9)
INSERT [dbo].[chi_tiet_san_pham] ([id_ctsp], [so_luong], [gia], [trang_thai], [ngay_tao], [ngay_sua], [san_pham_id], [kich_co_id], [mau_sac_id], [loai_de_id]) VALUES (275, 300, 3000000.0000, 0, CAST(N'2024-07-12T11:38:51.847' AS DateTime), CAST(N'2024-07-18T22:14:11.373' AS DateTime), 6, 6, 1, 9)
SET IDENTITY_INSERT [dbo].[chi_tiet_san_pham] OFF
GO

SET IDENTITY_INSERT [dbo].[vai_tro] ON 

INSERT [dbo].[vai_tro] ([id_vai_tro], [ten_vai_tro]) VALUES (1, N'ROLE_ADMIN')
INSERT [dbo].[vai_tro] ([id_vai_tro], [ten_vai_tro]) VALUES (2, N'ROLE_NHANVIEN')
SET IDENTITY_INSERT [dbo].[vai_tro] OFF
GO

SET IDENTITY_INSERT [dbo].[hinh_anh_san_pham] ON 

INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (1, N'newbalance1_Balance 574  (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 1)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (2, N'newbalance1_Balance 574  (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 1)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (3, N'newbalance1_Balance 574  (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 1)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (4, N'newbalance1_Balance 574  (5).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 1)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (5, N'newbalance1_Balance 574 .jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 1)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (6, N'newbalance2_ Balance 480 (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 2)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (7, N'newbalance2_ Balance 480 .jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 2)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (8, N'newbalance2_ Balance 480.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 2)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (9, N'newbalance3_ New Balance FW 574  (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 3)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (10, N'newbalance3_ New Balance FW 574 (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 3)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (11, N'newbalance3_ New Balance FW 574 (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 3)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (12, N'newbalance3_ New Balance FW 574 (5).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 3)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (13, N'newbalance3_ New Balance FW 574.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 3)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (14, N'newbalance4_newbalance_ Fresh Foam X More V4 (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 4)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (15, N'newbalance4_newbalance_ Fresh Foam X More V4 (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 4)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (16, N'newbalance4_newbalance_ Fresh Foam X More V4 (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 4)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (17, N'newbalance4_newbalance_ Fresh Foam X More V4.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 4)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (18, N'newbalance5_Shifted 327 (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 5)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (19, N'newbalance5_Shifted 327 (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 5)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (20, N'newbalance5_Shifted 327 (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 5)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (21, N'newbalance5_Shifted 327 (5).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 5)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (22, N'newbalance5_Shifted 327.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 5)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (23, N'newbalance6_New Balance - WL574ZSG (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 6)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (24, N'newbalance6_New Balance - WL574ZSG (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 6)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (25, N'newbalance6_New Balance - WL574ZSG (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 6)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (26, N'newbalance6_New Balance - WL574ZSG (5).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 6)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (27, N'newbalance6_New Balance - WL574ZSG.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 6)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (28, N'newbalance7_ Fresh Foam X 1080V13 (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 7)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (29, N'newbalance7_ Fresh Foam X 1080V13 (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 7)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (30, N'newbalance7_ Fresh Foam X 1080V13 (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 7)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (31, N'newbalance7_ Fresh Foam X 1080V13 (5).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 7)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (32, N'newbalance7_ Fresh Foam X 1080V13.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 7)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (33, N'newbalance7_ Fresh Foam X 1080V13 (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 8)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (34, N'newbalance7_ Fresh Foam X 1080V13 (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 8)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (35, N'newbalance7_ Fresh Foam X 1080V13 (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 8)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (36, N'newbalance7_ Fresh Foam X 1080V13 (5).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 8)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (37, N'newbalance7_ Fresh Foam X 1080V13.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 8)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (38, N'newbalance8_Trail More v3 . (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 9)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (39, N'newbalance8_Trail More v3 . (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 9)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (40, N'newbalance8_Trail More v3 . (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 9)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (41, N'newbalance8_Trail More v3 ..jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 9)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (42, N'newbalance8_Trail More v3 .jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 9)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (43, N'newbalance9_ Fresh Foam X 1080v12  (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 10)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (44, N'newbalance9_ Fresh Foam X 1080v12  (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 10)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (45, N'newbalance9_ Fresh Foam X 1080v12  (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 10)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (46, N'newbalance9_ Fresh Foam X 1080v12  (5).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 10)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (47, N'newbalance9_ Fresh Foam X 1080v12 .jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 10)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (48, N'newbalance10_1080 Laceles (2).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 11)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (49, N'newbalance10_1080 Laceles (3).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 11)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (50, N'newbalance10_1080 Laceles (4).jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 11)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (51, N'newbalance10_1080 Laceles.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 11)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (52, N'newbalance10_1080 Laceless.jpg', CAST(N'2024-06-01T18:01:12.463' AS DateTime), CAST(N'2024-06-01T18:01:12.463' AS DateTime), 0, 0, 11)
INSERT [dbo].[hinh_anh_san_pham] ([id_hinh_anh], [url], [ngay_tao], [ngay_sua], [trang_thai], [uu_tien], [san_pham_id]) VALUES (53, N'newbalance3_ New Balance FW 574 (4).jpg', CAST(N'2024-06-01T18:30:56.233' AS DateTime), CAST(N'2024-06-01T18:30:56.233' AS DateTime), 0, 0, 12)
SET IDENTITY_INSERT [dbo].[hinh_anh_san_pham] OFF
GO

SET IDENTITY_INSERT [dbo].[voucher] ON 

INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (1, N'WELCOME10', N'Welcome Discount', CAST(N'2024-05-11T00:00:00.000' AS DateTime), CAST(N'2024-07-04T00:00:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-18T22:36:56.600' AS DateTime), 99, 10, 50000.0000, 1000000.0000, 0)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (2, N'SALE15OFF', N'Sale 15% Off', CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-07-07T00:00:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), 146, 15, 75000.0000, 500000.0000, 0)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (3, N'FREESHIP', N'Free Shipping', CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-07-20T00:00:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), 200, 20, 200000.0000, 2000000.0000, 0)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (4, N'SUMMER20', N'Summer Sale 20%', CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-07-17T00:00:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), 120, 12, 60000.0000, 120000.0000, 1)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (5, N'SPRING18', N'Spring Special 18%', CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-07-05T00:00:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-18T19:39:50.380' AS DateTime), 180, 18, 90000.0000, 300000.0000, 1)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (6, N'VIP25OFF', N'VIP Customer 25% Off', CAST(N'2024-05-01T04:03:00.000' AS DateTime), CAST(N'2024-07-05T02:02:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), 160, 16, 20000.0000, 160000.0000, 1)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (7, N'HOLIDAY30', N'Holiday Discount 30%', CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-07-11T04:03:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), 140, 14, 50000.0000, 200000.0000, 0)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (8, N'BACKTOSCHOOL', N'Back to School Promo', CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-07-02T03:03:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), 130, 13, 65000.0000, 330000.0000, 0)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (9, N'FALL20', N'Fall Season 20% Off', CAST(N'2024-05-18T00:00:00.000' AS DateTime), CAST(N'2024-07-25T00:00:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-18T19:40:57.173' AS DateTime), 109, 20, 85000.0000, 750000.0000, 0)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (10, N'OCTOBER10', N'October Special 10%', CAST(N'2024-05-01T00:00:00.000' AS DateTime), CAST(N'2024-07-31T00:00:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), 110, 11, 55000.0000, 110000.0000, 0)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (11, N'SALE25/12', N'SALE25/12', CAST(N'2024-05-25T13:54:00.000' AS DateTime), CAST(N'2024-07-30T22:51:00.000' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), CAST(N'2024-05-16T22:42:37.540' AS DateTime), 20, 30, 200000.0000, 1200000.0000, 2)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (12, N'SUMMER50', N'SUMMER50', CAST(N'2024-01-17T23:01:00.000' AS DateTime), CAST(N'2024-07-20T17:01:00.000' AS DateTime), CAST(N'2024-05-16T23:01:29.427' AS DateTime), CAST(N'2024-05-16T23:01:29.427' AS DateTime), 50, 10, 50000.0000, 150000.0000, 2)
INSERT [dbo].[voucher] ([id_voucher], [ma_voucher], [ten_voucher], [ngay_bat_dau], [ngay_ket_thuc], [ngay_tao], [ngay_sua], [so_luong], [phan_tram_giam], [giam_toi_da], [gia_tri_don_toi_thieu], [trang_thai]) VALUES (13, N'FLASHSALE5P', N'FLASHSALE5P', CAST(N'2024-05-16T23:05:00.000' AS DateTime), CAST(N'2024-07-16T23:10:00.000' AS DateTime), CAST(N'2024-05-16T00:00:00.000' AS DateTime), CAST(N'2024-05-18T19:38:55.853' AS DateTime), 35, 5, 60000.0000, 120000.0000, 1)
SET IDENTITY_INSERT [dbo].[voucher] OFF
GO

SET IDENTITY_INSERT [dbo].[phuong_thuc_thanh_toan] ON 

INSERT [dbo].[phuong_thuc_thanh_toan] ([id_pttt], [ma_phuong_thuc], [ten], [ghi_chu], [trang_thai]) VALUES (3, N'PTTT001', N'Thanh toán qua thẻ ngân hàng', N'Hỗ trợ nhiều ngân hàng khác nhau', 0)
INSERT [dbo].[phuong_thuc_thanh_toan] ([id_pttt], [ma_phuong_thuc], [ten], [ghi_chu], [trang_thai]) VALUES (4, N'PTTT002', N'Thanh toán khi nhận hàng', N'Thanh toán khi nhận hàng tại cửa hàng', 0)
SET IDENTITY_INSERT [dbo].[phuong_thuc_thanh_toan] OFF
GO

SET IDENTITY_INSERT [dbo].[nhan_vien] ON 
INSERT [dbo].[nhan_vien] ([id_nv],[ho_va_ten] ,[ngay_sinh] ,[gioi_tinh],[so_dien_thoai],[email],[ten_tai_khoan],[mat_khau],[ngay_tao],[ngay_sua],[trang_thai],[vai_tro_id]) VALUES (1, N'Trịnh Văn Hiển', CAST(N'2003-10-07' AS Date), 0, N'09615151329', N'hientvph27694@fpt.edu.vn', N'hientv', N'123', CAST(N'2024-06-01T00:00:00.000' AS DateTime), CAST(N'2024-07-16T18:01:48.380' AS DateTime), 0, 1)
INSERT [dbo].[nhan_vien] ([id_nv],[ho_va_ten] ,[ngay_sinh] ,[gioi_tinh],[so_dien_thoai],[email],[ten_tai_khoan],[mat_khau],[ngay_tao],[ngay_sua],[trang_thai],[vai_tro_id]) VALUES (2, N'Trịnh Văn Hiển', CAST(N'2003-10-07' AS Date), 0, N'09615151329', N'hien06781@gmail.com', N'trinhhien', N'123', CAST(N'2024-06-01T00:00:00.000' AS DateTime), CAST(N'2024-07-16T18:01:48.380' AS DateTime), 0, 2)

SET IDENTITY_INSERT [dbo].[nhan_vien] OFF
GO

SET IDENTITY_INSERT [dbo].[khach_hang] ON 
INSERT [dbo].[khach_hang] ([id_kh],[ho_va_ten] ,[ngay_sinh] ,[gioi_tinh],[so_dien_thoai],[email],[ten_tai_khoan],[mat_khau],[ngay_tao],[ngay_sua],[ten_vai_tro],[trang_thai]) VALUES (1, N'Trịnh Văn Hiển', CAST(N'2003-10-07' AS Date), 0, N'09615151329', N'hientvph27694@fpt.edu.vn', N'hientv', N'123', CAST(N'2024-06-01T00:00:00.000' AS DateTime), CAST(N'2024-07-16T18:01:48.380' AS DateTime),N'ROLE_USER', 0)
INSERT [dbo].[khach_hang] ([id_kh],[ho_va_ten] ,[ngay_sinh] ,[gioi_tinh],[so_dien_thoai],[email],[ten_tai_khoan],[mat_khau],[ngay_tao],[ngay_sua],[ten_vai_tro],[trang_thai]) VALUES (2, N'Trịnh Văn Hiển', CAST(N'2003-10-07' AS Date), 0, N'09615151329', N'hien06781@gmail.com', N'trinhhien', N'123', CAST(N'2024-06-01T00:00:00.000' AS DateTime), CAST(N'2024-07-16T18:01:48.380' AS DateTime),N'ROLE_USER', 0)
INSERT [dbo].[khach_hang] ([id_kh], [ho_va_ten], [ngay_sinh], [gioi_tinh], [so_dien_thoai], [email], [ten_tai_khoan], [mat_khau], [ngay_tao], [ngay_sua],[ten_vai_tro], [trang_thai]) VALUES (3, N'Khách lẻ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,NULL, -1)

SET IDENTITY_INSERT [dbo].[khach_hang] OFF
GO

SET IDENTITY_INSERT [dbo].[hoa_don] ON 

INSERT [dbo].[hoa_don] ([id_hd], [ma_hoa_don], [ngay_thanh_toan], [loai_hoa_don], [phi_ship], [tong_tien], [tong_tien_khi_giam], [ghi_chu], [nguoi_nhan], [sdt_nguoi_nhan], [thanh_pho], [quan_huyen], [phuong_xa], [dia_chi_nguoi_nhan], [email_nguoi_nhan], [ngay_nhan], [ngay_mong_muon], [ngay_tao], [ngay_sua], [trang_thai], [voucher_id], nhan_vien_id, [khach_hang_id], [phuong_thuc_thanh_toan_id], [tien_giam]) VALUES (1, N'HD1', NULL, 2, 41502.0000, 5050000.0000, 4975000.0000, N'', N'Nguyễn Tuấn Anh', N'0377463664', N'201', N'1808', N'1B1909', N'Số 47/5', NULL, NULL, NULL, CAST(N'2024-06-01T19:42:17.997' AS DateTime), CAST(N'2024-06-01T19:45:56.900' AS DateTime), 3, 2, 2,3, NULL, 75000.0000)
INSERT [dbo].[hoa_don] ([id_hd], [ma_hoa_don], [ngay_thanh_toan], [loai_hoa_don], [phi_ship], [tong_tien], [tong_tien_khi_giam], [ghi_chu], [nguoi_nhan], [sdt_nguoi_nhan], [thanh_pho], [quan_huyen], [phuong_xa], [dia_chi_nguoi_nhan], [email_nguoi_nhan], [ngay_nhan], [ngay_mong_muon], [ngay_tao], [ngay_sua], [trang_thai], [voucher_id], nhan_vien_id,[khach_hang_id], [phuong_thuc_thanh_toan_id], [tien_giam]) VALUES (2, N'HD2', CAST(N'2024-06-01T19:46:13.817' AS DateTime), 2, 0.0000, 1750000.0000, 1750000.0000, NULL, N'Khách lẻ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2024-06-01T19:46:02.677' AS DateTime), CAST(N'2024-06-01T19:46:13.817' AS DateTime), 3, NULL, 1,3, NULL, 0.0000)
INSERT [dbo].[hoa_don] ([id_hd], [ma_hoa_don], [ngay_thanh_toan], [loai_hoa_don], [phi_ship], [tong_tien], [tong_tien_khi_giam], [ghi_chu], [nguoi_nhan], [sdt_nguoi_nhan], [thanh_pho], [quan_huyen], [phuong_xa], [dia_chi_nguoi_nhan], [email_nguoi_nhan], [ngay_nhan], [ngay_mong_muon], [ngay_tao], [ngay_sua], [trang_thai], [voucher_id], nhan_vien_id,[khach_hang_id], [phuong_thuc_thanh_toan_id], [tien_giam]) VALUES (3, N'HD3', CAST(N'2024-06-01T19:46:39.013' AS DateTime), 2, 0.0000, 4500000.0000, 4415000.0000, NULL, N'Dương Quang Hào', N'0987654321', NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2024-06-01T19:46:16.273' AS DateTime), CAST(N'2024-06-01T19:46:39.013' AS DateTime), 3, 9, 2,2, NULL, 85000.0000)
INSERT [dbo].[hoa_don] ([id_hd], [ma_hoa_don], [ngay_thanh_toan], [loai_hoa_don], [phi_ship], [tong_tien], [tong_tien_khi_giam], [ghi_chu], [nguoi_nhan], [sdt_nguoi_nhan], [thanh_pho], [quan_huyen], [phuong_xa], [dia_chi_nguoi_nhan], [email_nguoi_nhan], [ngay_nhan], [ngay_mong_muon], [ngay_tao], [ngay_sua], [trang_thai], [voucher_id], nhan_vien_id,[khach_hang_id],  [phuong_thuc_thanh_toan_id], [tien_giam]) VALUES (4, N'HD4', NULL, 2, 54003.0000, 1200000.0000, 1115000.0000, N'', N'Nguyễn Thị Lan', N'0912345678', N'249', N'1767', N'190607', N'Số 12', NULL, NULL, NULL, CAST(N'2024-06-01T19:47:33.540' AS DateTime), CAST(N'2024-06-01T19:48:16.723' AS DateTime), 5, 9, 1,1, NULL, 85000.0000)
SET IDENTITY_INSERT [dbo].[hoa_don] OFF
GO

SET IDENTITY_INSERT [dbo].[hoa_don_chi_tiet] ON 

INSERT [dbo].[hoa_don_chi_tiet] ([id_hdct], [so_luong], [don_gia], [ghi_chu], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [hoa_don_id], [chi_tiet_san_pham_id]) VALUES (1, 1, 550000.0000, NULL, NULL, NULL, NULL, NULL, 0, 1, 1)
INSERT [dbo].[hoa_don_chi_tiet] ([id_hdct], [so_luong], [don_gia], [ghi_chu], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [hoa_don_id], [chi_tiet_san_pham_id]) VALUES (2, 1, 4500000.0000, NULL, NULL, NULL, NULL, NULL, 0, 2, 2)
INSERT [dbo].[hoa_don_chi_tiet] ([id_hdct], [so_luong], [don_gia], [ghi_chu], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [hoa_don_id], [chi_tiet_san_pham_id]) VALUES (3, 1, 1200000.0000, NULL, NULL, NULL, NULL, NULL, 0, 3, 3)
INSERT [dbo].[hoa_don_chi_tiet] ([id_hdct], [so_luong], [don_gia], [ghi_chu], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [hoa_don_id], [chi_tiet_san_pham_id]) VALUES (4, 1, 550000.0000, NULL, NULL, NULL, NULL, NULL, 0, 4, 4)
INSERT [dbo].[hoa_don_chi_tiet] ([id_hdct], [so_luong], [don_gia], [ghi_chu], [ngay_tao], [ngay_sua], [nguoi_tao], [nguoi_sua], [trang_thai], [hoa_don_id], [chi_tiet_san_pham_id]) VALUES (5, 1, 4500000.0000, NULL, NULL, NULL, NULL, NULL, 0, 1, 5)
SET IDENTITY_INSERT [dbo].[hoa_don_chi_tiet] OFF
go

--SET IDENTITY_INSERT [dbo].[gio_hang] ON 

--INSERT [dbo].[gio_hang] ([id_gio_hang],[ma_gio_hang], [ghi_chu], [ngay_tao], [ngay_sua], [trang_thai], [khach_hang_id]) VALUES (1, 1, NULL, CAST(N'2024-01-17T23:55:06.707' AS DateTime), CAST(N'2024-01-17T23:55:06.707' AS DateTime), 0, 1)
--INSERT [dbo].[gio_hang] ([id_gio_hang],[ma_gio_hang], [ghi_chu], [ngay_tao], [ngay_sua], [trang_thai], [khach_hang_id]) VALUES (2, 2, NULL, CAST(N'2024-01-17T23:57:26.430' AS DateTime), CAST(N'2024-01-17T23:57:29.087' AS DateTime), 0, 1)
--INSERT [dbo].[gio_hang] ([id_gio_hang],[ma_gio_hang], [ghi_chu], [ngay_tao], [ngay_sua], [trang_thai], [khach_hang_id]) VALUES (3, 3, NULL, CAST(N'2024-01-17T23:41:59.073' AS DateTime), CAST(N'2024-01-17T23:41:59.073' AS DateTime), 0, 1)
--INSERT [dbo].[gio_hang] ([id_gio_hang],[ma_gio_hang],  [ghi_chu], [ngay_tao], [ngay_sua], [trang_thai], [khach_hang_id]) VALUES (4, 4, NULL, CAST(N'2024-01-17T23:58:43.227' AS DateTime), CAST(N'2024-01-17T23:58:43.227' AS DateTime), 0, 1)
--INSERT [dbo].[gio_hang] ([id_gio_hang],[ma_gio_hang],  [ghi_chu], [ngay_tao], [ngay_sua], [trang_thai], [khach_hang_id]) VALUES (5, 5, NULL, CAST(N'2024-01-17T23:59:21.800' AS DateTime), CAST(N'2024-01-17T23:59:21.800' AS DateTime), 0, 1)
--SET IDENTITY_INSERT [dbo].[gio_hang] OFF
--GO

SET IDENTITY_INSERT [dbo].[dia_chi] ON 

INSERT [dbo].[dia_chi] ([id_dia_chi], [thanh_pho], [quan_huyen], [phuong_xa], [dia_chi_cu_the], [ngay_tao], [ngay_sua], [trang_thai], [khach_hang_id]) VALUES (1, N'201', N'1808', N'1B1909', N'Số 47/2', CAST(N'2024-06-01T22:28:29.827' AS DateTime), CAST(N'2024-06-01T22:28:29.827' AS DateTime), 0, 1)
INSERT [dbo].[dia_chi] ([id_dia_chi], [thanh_pho], [quan_huyen], [phuong_xa], [dia_chi_cu_the], [ngay_tao], [ngay_sua], [trang_thai], [khach_hang_id]) VALUES (2, N'249', N'1767', N'190607', N'Số 12', CAST(N'2024-06-01T19:48:19.760' AS DateTime), CAST(N'2024-06-01T19:48:19.760' AS DateTime), 1, 2)
INSERT [dbo].[dia_chi] ([id_dia_chi], [thanh_pho], [quan_huyen], [phuong_xa], [dia_chi_cu_the], [ngay_tao], [ngay_sua], [trang_thai], [khach_hang_id]) VALUES (3, N'260', N'3278', N'390906', N'Số 12', CAST(N'2024-06-01T23:12:34.323' AS DateTime), CAST(N'2024-06-01T23:12:34.323' AS DateTime), 0, 3)
SET IDENTITY_INSERT [dbo].[dia_chi] OFF
GO

SET IDENTITY_INSERT [dbo].[nhan_vien] ON 
INSERT [dbo].[nhan_vien] ([id_nv], [ho_va_ten], [ngay_sinh], [gioi_tinh], [so_dien_thoai], [email], [ten_tai_khoan], [mat_khau], [ngay_tao], [ngay_sua], [trang_thai], [vai_tro_id]) VALUES (3, N'Trinh Van Hien', CAST(N'2003-10-07' AS Date), 0, N'0961515329', N'zpm05009@omeie.com', N'hien', N'$2a$10$kNeKIfNsxvCNA/Un0G0sc.R9d6r6pBKMvyfhb8cOR4Zq5j/.VSX5i', CAST(N'2024-06-13T22:34:53.153' AS DateTime), CAST(N'2024-06-13T22:34:53.153' AS DateTime), 0, 1)
SET IDENTITY_INSERT [dbo].[nhan_vien] OFF
GO


SET IDENTITY_INSERT [dbo].[khach_hang] ON 
INSERT [dbo].[khach_hang] ([id_kh],[ho_va_ten] ,[ngay_sinh] ,[gioi_tinh],[so_dien_thoai],[email],[ten_tai_khoan],[mat_khau],[ngay_tao],[ngay_sua],[ten_vai_tro],[trang_thai]) VALUES (6, N'Lê Trần Bình', CAST(N'2003-10-07' AS Date), 0, N'09615151329', N'binh27487@gmail.com', N'binh', N'$2a$10$kNeKIfNsxvCNA/Un0G0sc.R9d6r6pBKMvyfhb8cOR4Zq5j/.VSX5i', CAST(N'2024-06-01T00:00:00.000' AS DateTime), CAST(N'2024-07-16T18:01:48.380' AS DateTime),N'ROLE_USER', 0)
SET IDENTITY_INSERT [dbo].[khach_hang] OFF
GO

select * from gio_hang_chi_tiet where id_ghct = 1
                       and trang_thai = 0 order by ngay_sua desc
select * from gio_hang_chi_tiet where id_ghct = :idGioHang
                       and trang_thai = 0 order by ngay_sua desc

