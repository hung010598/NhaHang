CREATE Database QuanLyNhaHangUIT
CREATE TABLE User1
(
MaUser integer PRIMARY KEY,
TenUser nvarchar(100),
PassWord1  nvarchar(50),
Administrator nvarchar(100)

)

Insert into User1(MaUser,TenUser,PassWord1,Administrator) Values
(1,'Admin','Admin','Admin'),
(2,'NV','NV','NV'),
(3,'Bep','Bep','Bep')

CREATE TABLE DanhMuc
(
MaDM integer PRIMARY KEY,
TenDM nvarchar(100)
)

CREATE TABLE SanPham
(
MaSP integer PRIMARY KEY,
TenSP nvarchar(100),
DVT nvarchar(50),
DonGia integer,
MaDM integer
ConStraint FR_SP FOREIGN KEY(MaDM) REFERENCES DanhMuc(MaDM)
)

CREATE TABLE DanhMucBan
(
MaDMB integer PRIMARY KEY,
TenDMB nvarchar(100)
)

CREATE TABLE Ban
(
MaBan nvarchar(10) PRIMARY KEY,
TenBan nvarchar(100),
SoCho integer,
MaDMB integer,
Status2 integer DEFAULT 0
ConStraint FR_DMBAN FOREIGN KEY (MaDMB) REFERENCES DanhMucBan(MaDMB)
)


CREATE TABLE NhanVien
(
	MaNV varchar(20) PRIMARY KEY,
	TenNV nvarchar(100),
	GioiTinh nvarchar(20),
	DiaChi nvarchar(200),
	SDT nvarchar(20),
	NgayVaoLam nvarchar(20),
	CongViec nvarchar(100)
)

CREATE TABLE HoaDon
(
	MaHD integer PRIMARY KEY,
	MaBan nvarchar(10),
	MaNV  varchar(20),
	NgayHD nvarchar(200),
	Status1 nvarchar(20),
	ConStraint FR_NV FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV),
	ConStraint FR_BAN FOREIGN KEY (MaBan) REFERENCES Ban(MaBan)
)

CREATE TABLE ChiTietHoaDon
(
	MaHD integer,
	MaSP integer,
	Soluong  integer,
	ThanhTien nvarchar(200)
	ConStraint PK PRIMARY KEY(MaHD,MaSP),
	ConStraint FR_HD FOREIGN KEY(MaHD) REFERENCES HoaDon(MaHD),
	ConStraint FR_SPP FOREIGN KEY(MaSP) REFERENCES SanPham(MaSP),

)
ALTER TABLE NhanVien
ADD Image nvarchar(100);
Insert into DanhMucBan(MaDMB,TenDMB) values
(1,'VIP'),
(2,'THƯỜNG')

 Delete From Ban
 
ALTER TABLE SanPham
ADD Image nvarchar(100);
Alter table SanPham
Drop Column Image;
Alter Table SanPham
Add Image nvarchar(100);
Alter table SanPham
Drop Column Image;
Alter Table SanPham
Add ImageSP nvarchar(100);
CREATE TABLE KhachHang
(
	MaKH varchar(20) PRIMARY KEY,
	TenKH nvarchar(100),
	GioiTinh nvarchar(20),
	DiaChi nvarchar(200),
	SDT nvarchar(20),
	NgayDK nvarchar(20),
)
CREATE TABLE HoaDonTiec
(
	MaHD integer PRIMARY KEY,
	TenKH nvarchar(100),
	SoDT integer,
	DiaChi nvarchar(100),
	MaNV  varchar(20),
	NgayHD nvarchar(200),
	NgayGiao nvarchar(200),
	Status1 nvarchar(20),
	ConStraint FR_NVT FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV),
	
)
CREATE TABLE ChiTietHoaDonTiec
(
	MaHD integer,
	MaSP integer,
	Soluong  integer,
	ThanhTien nvarchar(200)
	ConStraint PKT PRIMARY KEY(MaHD,MaSP),
	ConStraint FR_HDT FOREIGN KEY(MaHD) REFERENCES HoaDonTiec(MaHD),
	ConStraint FR_SPPT FOREIGN KEY(MaSP) REFERENCES SanPham(MaSP),

)
ALTER TABLE HoaDon
ADD MaKH varchar(20),
ConStraint FR_KH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);
ALTER TABLE HoaDonTiec
ADD MaKH varchar(20),
ConStraint FR_KHT FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);
ALTER TABLE HoaDonTiec
DROP ConStraint FR_KHT;
ALTER TABLE HoaDonTiec
DROP Column MaKH;
ALTER TABLE HoaDon
ADD ThanhTien integer;
ALTER TABLE HoaDonTiec
ADD ThanhTien integer;
ALTER TABLE HoaDon
ALTER COLUMN ThanhTien Float;
ALTER TABLE HoaDonTiec
ALTER COLUMN ThanhTien Float;

CREATE TABLE ChucVu
(
	MaCV nvarchar(100) PRIMARY KEY,
	TenCV nvarchar(100),
	Luong Float,
)

ALTER TABLE NhanVien
ADD ConStraint FR_CV FOREIGN KEY(CongViec) REFERENCES ChucVu(MaCV);

Insert into ChucVu(MaCV,TenCV,Luong) values
('CV1','Nhân Viên',4000000)