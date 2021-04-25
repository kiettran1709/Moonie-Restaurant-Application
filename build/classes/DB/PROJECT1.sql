create database Project_1_test1
go
use Project_1_test1
go

drop database Project_1_test1

CREATE TABLE NHANVIEN(
	MANV VARCHAR(10) NOT NULL PRIMARY KEY, -- MÃ NHÂN VIÊN
	MATKHAU VARCHAR(50) NOT NULL, -- MẬT KHẨU ĐỪNG BỎ NẾU BỎ THÌ LÀM FORM LOGIN UỔNG
	TENNV NVARCHAR(50) NOT NULL, -- TÊN NHÂN VIÊN
	NGAYSINH DATE NOT NULL, -- NGÀY SINH
	GIOITINH BIT NOT NULL, -- GIỚI TÍNH
	SDT VARCHAR(20) NOT NULL, -- SỐ ĐIỆN THOẠI
	EMAIL VARCHAR(50) NOT NULL,
	LOAI BIT NOT NULL DEFAULT 0, -- LOẠI PARTTIME OR FULLTIME
	HINH VARCHAR(200), -- HÌNH
	QRCODE VARCHAR(200) NOT NULL, -- QUICK RESPONSE CODE
	CHUCVU BIT NOT NULL DEFAULT 0, -- ADMIN
	DIACHI NVARCHAR(255) NOT NULL, -- ĐỊA CHỈ
);

CREATE TABLE LOAITHEKHACHHANG(
	MALOAIKHACHHANG INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENLOAI NVARCHAR(50) NOT NULL
)

DBCC CHECKIDENT(LOAITHEKHACHHANG, RESEED, 0)

CREATE TABLE THEKHACHHANG(
	MAKH INT IDENTITY(1,1) NOT NULL PRIMARY KEY , -- MÃ KHÁCH HÀNG --  HAY MÃ THẺ TÙY NHA TẠI TAO THÍCH MÃ KHÁCH HÀNG ĐỂ CHỮ MÃ THẺ NÓ TỤC
	TENKH NVARCHAR(50) NOT NULL, -- TÊN KHÁCH HÀNG
	SDT VARCHAR(20), -- SỐ ĐIỆN THOẠI
	EMAIL VARCHAR(255), -- EMAIL
	TICHDIEM INT DEFAULT 0,
	MALOAIKHACHHANG INT NOT NULL,
	CONSTRAINT FK_LOAITHEKHACHHANG FOREIGN KEY (MALOAIKHACHHANG) REFERENCES LOAITHEKHACHHANG(MALOAIKHACHHANG)
);

SELECT * FROM THEKHACHHANG

CREATE TABLE LOAISANPHAM(
	MALOAISANPHAM nchar(5) NOT NULL PRIMARY KEY,
	TENLOAI NVARCHAR(50) NOT NULL,
	UNIQUE(TENLOAI),
)

CREATE TABLE SANPHAM(
	MASP int IDENTITY(1,1) NOT NULL, -- MÃ SẢN PHẨM
	MALOAISANPHAM nchar(5) NOT NULL,
	TENSP NVARCHAR(50) NOT NULL, -- TÊN SẢN PHẨM
	SOLUONG INT NOT NULL DEFAULT 0, -- SỐ LƯỢNG
	DONGIA INT NOT NULL DEFAULT 0, -- ĐƠN GIÁ
	HINH VARCHAR(200) NOT NULL, -- HÌNH SẢN PHẨM
	GHICHU NVARCHAR(255), -- GHI CHÚ
	PRIMARY KEY (MASP),
	CHECK (SOLUONG > 0 AND DONGIA >= 0), -- KIỂM TRA SỐ LƯỢNG ĐƠN GIÁ > 0
	CONSTRAINT FK_LOAISANPHAM FOREIGN KEY (MALOAISANPHAM) REFERENCES LOAISANPHAM(MALOAISANPHAM) ON UPDATE CASCADE
);

CREATE TABLE DONHANG(
	MADH VARCHAR(100) NOT NULL PRIMARY KEY, -- MÃ ĐƠN HÀNG
	MAKH INT,
	TENKH NVARCHAR(50),-- Cho thành viên 
	NGAYBAN DATE NOT NULL, -- NGÀY GIAO DỊCH ĐƠN HÀNG
	TONGTIEN INT NOT NULL,
	MANV VARCHAR(10) NOT NULL, -- KHÓA NGOẠI -- AI THANH TOÁN ĐƠN HÀNG NÀY
	CONSTRAINT FK_NHANVIEN_BANHANG FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV),
);


CREATE TABLE CHITIETDONHANG(
	MACTDH INT IDENTITY(1,1), -- KHÓA CHÍNH -- MÃ ĐƠN HÀNG
	MADH VARCHAR(100) NOT NULL,
	MASP int NOT NULL,
	TENSP NVARCHAR(50) NOT NULL, -- TÊN SẢN PHẨM
	SOLUONG INT NOT NULL, -- SỐ LƯỢNG
	DONGIA INT NOT NULL, -- ĐƠN GIÁ
	GIAMGIA INT NOT NULL, -- GIẢM GIÁ
	THANHTIEN INT NOT NULL, -- ĐƠN GIÁ
	
	MOTA NVARCHAR(255), -- MÔ TẢss
	CONSTRAINT PK_CHITIETDONHANG PRIMARY KEY (MACTDH),
	CONSTRAINT FK_DONHANG_CTDH FOREIGN KEY (MADH) REFERENCES DONHANG(MADH),
	CONSTRAINT FK_SANPHAM_CTDH FOREIGN KEY (MASP) REFERENCES SANPHAM(MASP)
);

--PROC ngày
CREATE PROC SP_THONGKEDOANHTHUNGAY
AS BEGIN
	SELECT NGAYBAN, SUM(TONGTIEN) as TONGTIEN
	FROM DONHANG
	GROUP BY NGAYBAN
END

EXECUTE SP_THONGKEDOANHTHUNGAY

--Thống kê tháng
CREATE PROC SP_THONGKEDOANHTHUTHANG(@YEAR INT)
AS BEGIN
	SELECT MONTH(NGAYBAN) THANG, SUM(TONGTIEN) TONGTIEN
	FROM DONHANG
	WHERE YEAR(NGAYBAN) = @YEAR
	GROUP BY MONTH(NGAYBAN) 
END

EXECUTE SP_THONGKEDOANHTHUTHANG 2021
--thống kê năm
CREATE PROC SP_THONGKEDOANHTHUNAM
AS BEGIN
	SELECT YEAR(NGAYBAN) THANG, SUM(TONGTIEN) TONGTIEN
	FROM DONHANG
	GROUP BY YEAR(NGAYBAN)
END

EXECUTE SP_THONGKEDOANHTHUNAM 