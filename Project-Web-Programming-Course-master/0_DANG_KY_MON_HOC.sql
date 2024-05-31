CREATE DATABASE DANG_KY_MON_HOC;
GO

USE DANG_KY_MON_HOC;
GO

CREATE TABLE HOCKY (
	MaHocKy CHAR(9) NOT NULL, --HK1_22-23, HK2_22-23,..
	NgayKhaiGiang DATE NOT NULL, 
	NgayBeGiang DATE NOT NULL,
	CONSTRAINT PK_HOCKY PRIMARY KEY (MaHocKy),
);
GO

CREATE TABLE KHOA (
	MaKhoa VARCHAR(5) NOT NULL, --FIT, FCFT, FCEAM, ITE,...
	TenKhoa NVARCHAR(MAX) NOT NULL,
	CONSTRAINT PK_KHOA PRIMARY KEY (MaKhoa),
);
GO

CREATE TABLE GIANGVIEN (
	MaGiangVien CHAR(6) NOT NULL, --GV0001, GV0002,...
	HoTen NVARCHAR(MAX) NOT NULL,
	HocVi NVARCHAR(10) NOT NULL, --Thạc sĩ, Tiến sĩ, Cử nhân,...
	HocHam NVARCHAR(12), --Phó Giáo sư, Giáo sư
	DiaChi NVARCHAR(MAX),
	Sdt CHAR(10) CHECK(len(Sdt) = 10) NOT NULL,
	MaKhoa VARCHAR(5) NOT NULL,
	CONSTRAINT PK_GIANGVIEN PRIMARY KEY (MaGiangVien),
	CONSTRAINT FK_GIANGVIEN_KHOA FOREIGN KEY (MaKhoa) REFERENCES KHOA(MaKhoa),
);
GO

CREATE TABLE MONHOC (
	MaMon CHAR(10) NOT NULL, --WEPR330479, DBMS330284,...
	TenMonHoc NVARCHAR(30) NOT NULL,
	SoTinChi INT NOT NULL, --(từ 1 đến 4)
	MaKhoa VARCHAR(5) NOT NULL, --FIT, FCFT, FCEAM, ITE,...
	CONSTRAINT PK_MONHOC PRIMARY KEY (MaMon),
	CONSTRAINT FK_MONHOC_KHOA FOREIGN KEY (MaKhoa) REFERENCES KHOA(MaKhoa),
);
GO

CREATE TABLE HOCVIEN (
	MaHocVien CHAR(10)  NOT NULL, --HV00000001, HV00000011,...
	HoTen NVARCHAR(40) NOT NULL, --Lê Hoàng Hiếu Nghĩa Đệ Nhất Thương Tâm Nhân (tên dài nhất VN),...
	NgaySinh DATE NOT NULL,
	DiaChi NVARCHAR(MAX) NOT NULL,
	QueQuan NVARCHAR(20) NOT NULL, --Bà Rịa - Vũng Tàu, Thừa Thiên - Huế,...
	MaKhoa VARCHAR(5) NOT NULL, --FIT, FCFT, FCEAM, ITE,...
	CONSTRAINT PK_HOCVIEN PRIMARY KEY (MaHocVien),
	CONSTRAINT FK_HOCVIEN_KHOA FOREIGN KEY (MaKhoa) REFERENCES KHOA(MaKhoa),
);
GO

CREATE TABLE PHONGHOC (
	MaPhong VARCHAR(10) NOT NULL, --A5-203, A121, B108
	LoaiPhong NVARCHAR(40) NOT NULL, --Phòng lý thuyết, Phòng máy tính, Phòng thực hành, Phòng thí nghiệm,...
	CONSTRAINT PK_PHONGHOC PRIMARY KEY (MaPhong),
);
GO

CREATE TABLE LOPHOC (
	MaLop CHAR(18) NOT NULL, --DBMS330284_23_1_05, WEPR330479_23_1_11
	TenLop NVARCHAR(MAX) NOT NULL, 
	MaGiangVien CHAR(6) NOT NULL, --GV0001, GV0002,...
	MaMon CHAR(10) NOT NULL, --WEPR330479, DBMS330284,...
	MaHocKy CHAR(9) NOT NULL, --HK1_22-23, HK2_22-23,..
	SoHocVienDangKy INT NOT NULL CHECK (SoHocVienDangKy <= 75),
	MaPhong VARCHAR(10) NOT NULL, --A5-203, A121, B108
	Thu NVARCHAR(10) NOT NULL,
	Tiet NVARCHAR(10) NOT NULL,
	CONSTRAINT PK_LOPHOC PRIMARY KEY (MaLop),
	CONSTRAINT FK_LOPHOC_GIANGVIEN FOREIGN KEY (MaGiangVien) REFERENCES GIANGVIEN(MaGiangVien),
	CONSTRAINT FK_LOPHOC_MONHOC FOREIGN KEY (MaMon) REFERENCES MONHOC(MaMon),
	CONSTRAINT FK_LOPHOC_HOCKY FOREIGN KEY (MaHocKy) REFERENCES HOCKY(MaHocKy),
	CONSTRAINT FK_LOPHOC_PHONGHOC FOREIGN KEY (MaPhong) REFERENCES PHONGHOC(MaPhong),
);
GO

CREATE TABLE DKMH (
	MaHocVien CHAR(10) NOT NULL,
	MaLop CHAR(18) NOT NULL,
	CONSTRAINT PK_DKMH PRIMARY KEY (MaHocVien, MaLop),
	CONSTRAINT FK_DKHP_LOPHOC FOREIGN KEY (MaLop) REFERENCES LOPHOC(MaLop),
	CONSTRAINT FK_DKHP_HOCVIEN FOREIGN KEY (MaHocVien) REFERENCES HOCVIEN(MaHocVien),
);
GO

CREATE TABLE YEUCAU (
	MaGiangVien CHAR(6) NOT NULL,
	MaMon CHAR(10) NOT NULL,
	SoHocVienDuocDangKy INT NOT NULL,
	CONSTRAINT PK_YEUCAU PRIMARY KEY (MaGiangVien, MaMon),
	CONSTRAINT FK_YEUCAU_GIANGVIEN FOREIGN KEY (MaGiangVien) REFERENCES GIANGVIEN(MaGiangVien),
	CONSTRAINT FK_YEUCAU_MONHOC FOREIGN KEY (MaMon) REFERENCES MONHOC(MaMon),
);
GO

CREATE TABLE TAIKHOAN (
    TaiKhoan NVARCHAR(10) NOT NULL,
    MatKhau NVARCHAR(20) NOT NULL,
    CONSTRAINT PK_TAIKHOAN_TAIKHOAN PRIMARY KEY (TaiKhoan)
);
GO
--view 1*/
CREATE VIEW v_DSLopHoc AS
SELECT lh.MaLop, mh.TenMonHoc, mh.SoTinChi, lh.Tiet, lh.Thu, p.MaPhong Phong, 
gv.HoTen AS 'Giảng viên', mh.MaKhoa, mh.MaMon, lh.SoHocVienDangKy, lh.MaHocKy
FROM dbo.MONHOC mh
	JOIN dbo.LOPHOC lh ON lh.MaMon = mh.MaMon
	JOIN dbo.GIANGVIEN gv ON lh.MaGiangVien = gv.MaGiangVien
	JOIN dbo.PHONGHOC p ON  lh.MaPhong = p.MaPhong;
GO

--2. view xem danh sách các môn đã đăng kí
--3.3.
--5 b
/*
Chức năng: + xem danh sách các môn học sinh đã đăng kí
(SELECT những gì cần hiển thị
FROM view2
WHERE mã sinh viên đó)
+ Xem thời khóa biểu
(SELECT thu,tiet,phong
FROM view2
WHERE mã sinh viên đó)
+ Xem học phí
(SELECT 
FROM view2
WHERE mã sinh viên đó)
*/
-- view2 danh sach đã đk
CREATE VIEW v_DSDaDangKi AS
SELECT lh.MaLop, mh.TenMonHoc, mh.SoTinChi, lh.Tiet, lh.Thu, p.MaPhong Phong, 
gv.HoTen AS GiangVien, dk.MaHocVien, lh.MaMon
FROM dbo.MONHOC mh
	JOIN dbo.LOPHOC lh ON lh.MaMon = mh.MaMon
	JOIN dbo.GIANGVIEN gv ON lh.MaGiangVien = gv.MaGiangVien
	JOIN dbo.PHONGHOC p ON lh.MaPhong = p.MaPhong
	JOIN dbo.DKMH dk ON dk.MaLop = lh.MaLop;
GO

--3. view xem danh sách dạy lớp nào
--4.2.
--5 c
/*
chức năng + để gv coi mình dyaj lướp nào
(SELECT những gì cần hiển thị
FROM view3
WHERE mã gv )
*/---drop view v_DSLopDay
CREATE VIEW v_DSLopDay AS
SELECT lh.MaGiangVien, mh.TenMonHoc, lh.MaLop, mh.MaMon, lh.Tiet, lh.Thu, lh.MaPhong Phong, lh.MaHocKy
FROM dbo.LOPHOC lh 
	JOIN dbo.MONHOC mh ON mh.MaMon = lh.MaMon;
GO

--4. view xem sinh viên nào đăng kí lớp nào
--4.3.
--5 d
/*
để gv có thể xem lướp mình dạy có những sinh viên nào đăng kí
SELECT * FROM view 4 WHERE MÃ lớp học
*/
--View4 xem danh sách hv
CREATE VIEW v_DSHV AS
SELECT lh.MaLop, hv.MaHocVien, hv.HoTen
FROM dbo.DKMH dk
	JOIN dbo.LOPHOC lh ON dk.MaLop = lh.MaLop
	JOIN dbo.HOCVIEN hv ON dk.MaHocVien = hv.MaHocVien;
GO
INSERT INTO HOCKY (MaHocKy, NgayKhaiGiang, NgayBeGiang)
VALUES ('HK1_22-23', '2022-08-20', '2022-12-30'),
	   ('HK2_22-23', '2023-01-20', '2023-05-30'),
       ('HK3_22-23', '2023-06-20', '2023-07-30'),
	   ('HK1_23-24', '2023-08-20', '2024-01-10'),
	   ('HK2_23-24', '2024-01-20', '2024-05-30'), 
	   ('HK3_23-24', '2024-06-20', '2024-07-30');
GO

INSERT INTO KHOA (MaKhoa, TenKhoa)
VALUES ('FPI', N'Khoa Chính trị và Luật'),
	   ('FME', N'Khoa Cơ khí Chế tạo máy'),
	   ('FAE', N'Khoa Cơ khí động lực'),
	   ('FCFT', N'Khoa Công nghệ hoá học và thực phẩm'),
	   ('FIT', N'Khoa Công nghệ thông tin'),
	   ('FIE', N'Khoa Đào tạo Quốc tế'),
	   ('FEEE', N'Khoa Điện - Điện tử'),
	   ('FGAM', N'Khoa In và Truyền thông'),
	   ('FAS', N'Khoa Khoa học ứng dụng'),
	   ('FE', N'Khoa Kinh tế'),
	   ('FFL', N'Khoa Ngoại ngữ'),
	   ('FGTFD', N'Khoa Thời trang và Du lịch'),
	   ('FCEAM', N'Khoa Xây dựng'),
	   ('FHQ',N'Khoa Đào tạo Chất lượng cao'),
	   ('ITE', N'Viện Sư phạm');
GO

INSERT INTO GIANGVIEN (MaGiangVien, HoTen, HocVi, HocHam, DiaChi, Sdt, MaKhoa)
VALUES ('GV0001', N'Nguyễn Văn A', N'Tiến sĩ', N'Giáo sư', N'123 Trần Hưng Đạo, Quận 1', '0987654321', 'FIT'),
	   ('GV0002', N'Trần Thị B', N'Thạc sĩ', NULL, N'456 Lê Lợi, Quận 3', '0123456789', 'FIT'),
	   ('GV0003', N'Lê Văn C', N'Cử nhân', NULL, N'789 Nguyễn Trãi, Quận 5', '0912345678', 'FIT'),
	   ('GV0004', N'Phạm Thị D', N'Tiến sĩ', NULL, N'321 Lý Thường Kiệt, Quận 10', '0901234567', 'FPI'),
	   ('GV0005', N'Đỗ Văn E', N'Thạc sĩ', NULL, N'654 Nguyễn Thị Minh Khai, Quận 1', '0998765432', 'FFL'),
	   ('GV0006', N'Nguyễn Thị F', N'Tiến sĩ', NULL, N'147 Phan Đình Phùng, Quận 3', '0965432109', 'FE'),
	   ('GV0007', N'Trần Văn G', N'Thạc sĩ', NULL, N'258 Nguyễn Văn Cừ, Quận 5', '0943210987', 'FE'),
	   ('GV0008', N'Lê Thị H', N'Cử nhân', NULL, N'369 Điện Biên Phủ, Quận 10', '0921098765', 'FAS'),
	   ('GV0009', N'Phạm Văn I', N'Tiến sĩ', N'Phó Giáo sư', N'480 Cách Mạng Tháng Tám, Quận 3','0921098765','FGTFD'),
	   ('GV0010', N'Đỗ Thị J', N'Thạc sĩ', N'Phó Giáo sư', N'591 Lê Đại Hành, Quận 11','0954321098','FCEAM');
GO

INSERT INTO MONHOC (MaMon, TenMonHoc, SoTinChi, MaKhoa) 
VALUES ('WEPR330479', N'Lập trình web', 3, 'FIT'), 
	   ('DBMS330284', N'Cơ sở dữ liệu', 4, 'FIT'), 
	   ('MATH330123', N'Toán rời rạc', 3, 'FIT'), 
	   ('CHEM330567', N'Hóa học vô cơ', 4, 'FCFT'), 
	   ('PHYS330678', N'Vật lý đại cương', 3, 'FCFT'), 
	   ('BIO330789', N'Sinh học phân tử', 4, 'FCFT'), 
	   ('ECON330901', N'Kinh tế vi mô', 3, 'FCEAM'), 
	   ('ACCT330012', N'Kế toán quản trị', 4, 'FCEAM'), 
	   ('FINA330345', N'Tài chính doanh nghiệp', 3, 'FCEAM'), 
	   ('ENGL330456', N'Tiếng Anh giao tiếp', 2, 'ITE'), 
	   ('JAPN330567', N'Tiếng Nhật cơ bản', 2, 'ITE'), 
	   ('CHIN330678', N'Tiếng Trung giao tiếp', 2, 'ITE'), 
	   ('STAT330789', N'Thống kê và xác suất', 3, 'ITE'), 
	   ('COMP330901', N'Tin học đại cương', 3, 'ITE'), 
	   ('SOFT330012', N'Kỹ thuật phần mềm', 4, 'ITE');
GO

INSERT INTO HOCVIEN (MaHocVien, HoTen, NgaySinh, DiaChi, QueQuan, MaKhoa)
VALUES
    ('HV00000001', N'Lê Hoàng Hiếu Nghĩa', '1990-01-01', N'123 Đường ABC, Quận XYZ, TP.HCM', N'Bà Rịa - Vũng Tàu', 'FIT'),
    ('HV00000002', N'Nguyễn Thị Mai', '1995-02-15', N'456 Đường XYZ, Quận ABC, Hà Nội', N'Hà Nội', 'FCFT'),
    ('HV00000003', N'Trần Văn An', '1992-03-20', N'789 Đường DEF, Quận PQR, Đà Nẵng', N'Đà Nẵng', 'FIT'),
    ('HV00000004', N'Phạm Thị Thu', '1993-04-10', N'101 Đường GHI, Quận JKL, Hồ Chí Minh', N'Hồ Chí Minh', 'FCFT'),
    ('HV00000005', N'Huỳnh Văn Long', '1998-05-05', N'202 Đường MNO, Quận STU, Hà Nội', N'Hà Nội', 'FCEAM'),
    ('HV00000006', N'Võ Thị Lan', '1997-06-30', N'303 Đường UVW, Quận XYZ, Đà Lạt', N'Đà Lạt', 'FIT'),
    ('HV00000007', N'Lê Thanh Tùng', '1994-07-22', N'404 Đường DEF, Quận PQR, Hồ Chí Minh', N'Hồ Chí Minh', 'FCFT'),
    ('HV00000008', N'Nguyễn Đức Hải', '1991-08-12', N'505 Đường GHI, Quận JKL, Hà Nội', N'Hà Nội', 'FCEAM'),
    ('HV00000009', N'Phạm Thị Lan', '1996-09-03', N'606 Đường ABC, Quận STU, Hà Nội', N'Hà Nội', 'FIT'),
    ('HV00000010', N'Trần Văn Tâm', '1999-10-18', N'707 Đường XYZ, Quận PQR, Đà Nẵng', N'Đà Nẵng', 'FCFT'),
	('HV00000011', N'Nguyễn Văn A', '1998-11-11', N'1111 Đường DEF, Quận XYZ, TP.HCM', N'TP.HCM', 'FIT'),
    ('HV00000012', N'Lê Thị B', '1997-10-12', N'1212 Đường XYZ, Quận ABC, Hà Nội', N'Hà Nội', 'FCFT'),
    ('HV00000013', N'Trần Văn C', '1996-09-13', N'1313 Đường DEF, Quận PQR, Đà Nẵng', N'Đà Nẵng', 'FIT'),
    ('HV00000014', N'Phạm Thị D', '1995-08-14', N'1414 Đường GHI, Quận JKL, Hồ Chí Minh', N'Hồ Chí Minh', 'FCFT'),
    ('HV00000015', N'Huỳnh Văn E', '1994-07-15', N'1515 Đường MNO, Quận STU, Hà Nội', N'Hà Nội', 'FCEAM'),
    ('HV00000016', N'Võ Thị F', '1993-06-16', N'1616 Đường UVW, Quận XYZ, Đà Lạt', N'Đà Lạt', 'FIT'),
    ('HV00000017', N'Lê Thị G', '1992-05-17', N'1717 Đường DEF, Quận PQR, Hồ Chí Minh', N'Hồ Chí Minh', 'FCFT'),
    ('HV00000018', N'Nguyễn Văn H', '1991-04-18', N'1818 Đường GHI, Quận JKL, Hà Nội', N'Hà Nội', 'FCEAM'),
    ('HV00000019', N'Phạm Đình I', '1990-03-19', N'1919 Đường ABC, Quận STU, Hà Nội', N'Hà Nội', 'FIT'),
    ('HV00000020', N'Trần Văn J', '1989-02-20', N'2020 Đường XYZ, Quận PQR, Đà Nẵng', N'Đà Nẵng', 'FCFT');
GO

INSERT INTO PHONGHOC (MaPhong, LoaiPhong) 
VALUES ('A5-203', N'Phòng lý thuyết'), 
	   ('A121', N'Phòng máy tính'), 
	   ('B108', N'Phòng thực hành'), 
	   ('C201', N'Phòng thí nghiệm'), 
	   ('D301', N'Phòng hội thảo'), 
	   ('E401', N'Phòng đa năng'), 
	   ('F501', N'Phòng âm nhạc'), 
	   ('G601', N'Phòng mỹ thuật'), 
	   ('H701', N'Phòng thể dục'), 
	   ('I801', N'Phòng ngoại ngữ');
GO

INSERT INTO LOPHOC (MaLop, TenLop, MaGiangVien, MaMon, MaHocKy, SoHocVienDangKy, MaPhong, Thu, Tiet)
VALUES
    ('DBMS330284_23_1_25', N'Lớp Cơ sở dữ liệu HK1 22-23', 'GV0005', 'DBMS330284', 'HK1_22-23', 30, 'B108', N'Thứ 2', N'Tiết 10-11'),
    ('WEPR330479_23_1_22', N'Lớp Lập trình web HK1 22-23', 'GV0005', 'WEPR330479', 'HK1_22-23', 40, 'A121', N'Thứ 6', N'Tiết 8-9'),
    ('WEPR330479_23_1_54', N'Lớp Lập trình web HK1 22-23', 'GV0007', 'WEPR330479', 'HK1_22-23', 35, 'B108', N'Thứ 2', N'Tiết 1-3'),
    ('DBMS330284_23_1_23', N'Lớp Cơ sở dữ liệu HK1 22-23', 'GV0006', 'DBMS330284', 'HK1_22-23', 30, 'A5-203', N'Thứ 2', N'Tiết 4-6'),
    ('MATH330123_23_1_52', N'Lớp Toán rời rạc HK1 22-23', 'GV0004', 'MATH330123', 'HK1_22-23', 25, 'A121', N'Thứ 6', N'Tiết 1-3'),
    ('CHEM330567_23_1_23', N'Lớp Hóa học vô cơ HK1 22-23', 'GV0001', 'CHEM330567', 'HK1_22-23', 20, 'B108', N'Thứ 7', N'Tiết 4-6'),
    ('PHYS330678_23_1_91', N'Lớp Vật lý đại cương HK1 22-23', 'GV0004', 'PHYS330678', 'HK1_22-23', 25, 'A5-203', N'Thứ 5', N'Tiết 1-3'),
    ('BIO330789_23_1_99', N'Lớp Sinh học phân tử HK1 22-23', 'GV0005', 'BIO330789', 'HK1_22-23', 30, 'A121', N'Thứ 4', N'Tiết 4-6'),
    ('ECON330901_23_1_34', N'Lớp Kinh tế vi mô HK1 22-23', 'GV0006', 'ECON330901', 'HK1_22-23', 35, 'A121', N'Thứ 5', N'Tiết 1-3'),
    ('ACCT330012_23_1_64', N'Lớp Kế toán quản trị HK1 22-23', 'GV0006', 'ACCT330012', 'HK1_22-23', 40, 'A5-203', N'Thứ 7', N'Tiết 1-2'),
	('DBMS330284_23_1_05', N'Lớp Cơ sở dữ liệu HK1 22-23', 'GV0001', 'DBMS330284', 'HK1_22-23', 30, 'A5-203', N'Thứ 2', N'Tiết 1-3'),
    ('WEPR330479_23_1_11', N'Lớp Lập trình web HK1 22-23', 'GV0002', 'WEPR330479', 'HK1_22-23', 40, 'A121', N'Thứ 3', N'Tiết 4-6'),
    ('WEPR330479_23_1_12', N'Lớp Lập trình web HK1 22-23', 'GV0003', 'WEPR330479', 'HK1_22-23', 35, 'B108', N'Thứ 4', N'Tiết 1-3'),
    ('DBMS330284_23_1_13', N'Lớp Cơ sở dữ liệu HK1 22-23', 'GV0004', 'DBMS330284', 'HK1_22-23', 30, 'A5-203', N'Thứ 5', N'Tiết 4-6'),
    ('MATH330123_23_1_05', N'Lớp Toán rời rạc HK1 22-23', 'GV0005', 'MATH330123', 'HK1_22-23', 25, 'A121', N'Thứ 2', N'Tiết 1-3'),
    ('CHEM330567_23_1_11', N'Lớp Hóa học vô cơ HK1 22-23', 'GV0006', 'CHEM330567', 'HK1_22-23', 20, 'B108', N'Thứ 3', N'Tiết 4-6'),
    ('PHYS330678_23_1_12', N'Lớp Vật lý đại cương HK1 22-23', 'GV0007', 'PHYS330678', 'HK1_22-23', 25, 'A5-203', N'Thứ 4', N'Tiết 1-3'),
    ('BIO330789_23_1_13', N'Lớp Sinh học phân tử HK1 22-23', 'GV0008', 'BIO330789', 'HK1_22-23', 30, 'A121', N'Thứ 5', N'Tiết 4-6'),
    ('ECON330901_23_1_05', N'Lớp Kinh tế vi mô HK1 22-23', 'GV0009', 'ECON330901', 'HK1_22-23', 35, 'B108', N'Thứ 2', N'Tiết 1-3'),
    ('ACCT330012_23_1_11', N'Lớp Kế toán quản trị HK1 22-23', 'GV0010', 'ACCT330012', 'HK1_22-23', 40, 'A5-203', N'Thứ 3', N'Tiết 4-6');
GO

INSERT INTO DKMH (MaHocVien, MaLop)
VALUES
    ('HV00000001', 'DBMS330284_23_1_05'),
    ('HV00000002', 'WEPR330479_23_1_11'),
    ('HV00000003', 'WEPR330479_23_1_12'),
    ('HV00000004', 'DBMS330284_23_1_13'),
    ('HV00000005', 'MATH330123_23_1_05'),
    ('HV00000006', 'CHEM330567_23_1_11'),
    ('HV00000007', 'PHYS330678_23_1_12'),
    ('HV00000008', 'BIO330789_23_1_13'),
    ('HV00000009', 'ECON330901_23_1_05'),
    ('HV00000010', 'ACCT330012_23_1_11'),
    ('HV00000011', 'DBMS330284_23_1_05'),
    ('HV00000012', 'WEPR330479_23_1_11'),
    ('HV00000013', 'WEPR330479_23_1_12'),
    ('HV00000014', 'DBMS330284_23_1_13'),
    ('HV00000015', 'MATH330123_23_1_05'),
    ('HV00000016', 'CHEM330567_23_1_11'),
    ('HV00000017', 'PHYS330678_23_1_12'),
    ('HV00000018', 'BIO330789_23_1_13'),
    ('HV00000019', 'ECON330901_23_1_05'),
    ('HV00000020', 'ACCT330012_23_1_11');
GO

INSERT INTO YEUCAU (MaGiangVien, MaMon, SoHocVienDuocDangKy)
VALUES
    ( 'GV0001', 'WEPR330479', 20),
    ( 'GV0002', 'DBMS330284', 15),
    ( 'GV0003', 'MATH330123', 25),
    ('GV0004', 'CHEM330567', 10),
    ( 'GV0005', 'PHYS330678', 30),
    ( 'GV0006', 'BIO330789', 25),
    ( 'GV0007', 'ECON330901', 20),
    ( 'GV0008', 'ACCT330012', 35),
    ( 'GV0009', 'FINA330345', 30),
    ( 'GV0010', 'ENGL330456', 40);
GO

--6 a
--DROP TRIGGER Trigger_TrungLichDangKyLop
CREATE TRIGGER Trigger_TrungLichDangKyLop --Trigger ngăn chặn học viên đăng ký lớp học trùng lịch, môn, hoặc quá 35 tín chỉ
ON DKMH
INSTEAD OF INSERT
AS
BEGIN
	DECLARE @I_MaHocVien CHAR(10), @I_MaLop CHAR(18) --Khai báo 2 biến chưa MaHocVien và Malop của học viên đăng kí
	SELECT @I_MaHocVien = i.MaHocVien, @I_MaLop = i.MaLop
	FROM inserted i

	DECLARE @TimeTable1 TABLE ( --bảng lưu thời gian của các lớp mà học viên đó đã đăng ký
		MaLop CHAR(18),
		NamHoc INT,
		SoHocKy INT,
		Thu INT, 
		TietBD INT,
		TietKT INT
		);

	DECLARE @TimeTable2 TABLE ( --bảng lưu thời gian lớp học mà học viên đó sắp đăng ký
		MaLop CHAR(18),
		NamHoc INT,
		SoHocKy INT,
		Thu INT, 
		TietBD INT,
		TietKT INT
		);

	INSERT INTO @TimeTable1 (MaLop, NamHoc, SoHocKy, Thu, TietBD, TietKT) 
	SELECT	MaLop,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 1, 2) AS INT) AS NamHoc,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 4, 1) AS INT) AS SoHocKy,
			CAST(SUBSTRING(Thu, CHARINDEX(' ', Thu) + 1, LEN(Thu)) AS INT) AS Thu, --biến đổi thứ sang kiểu int để dễ xử lý
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) - 2, 2) AS INT) AS TietBD, --biến đổi tiết sang kiểu int trở thành tiết bắt đầu
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) + 1, LEN(Tiet)) AS INT) AS TietKT -- và tiết kết thúc để dễ xử lý
	FROM (
		SELECT MaLop, Thu, Tiet
		FROM dbo.v_DSDaDangKi ds
		WHERE ds.MaHocVien = @I_MaHocVien) A --A chứa thông tin thời gian học của Học viên
	
	INSERT INTO @TimeTable2 (MaLop, NamHoc, SoHocKy, Thu, TietBD, TietKT) 
	SELECT	MaLop,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 1, 2) AS INT) AS NamHoc,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 4, 1) AS INT) AS SoHocKy,
			CAST(SUBSTRING(Thu, CHARINDEX(' ', Thu) + 1, LEN(Thu)) AS INT) AS Thu, --biến đổi thứ sang kiểu int để dễ xử lý
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) - 2, 2) AS INT) AS TietBD, --biến đổi tiết sang kiểu int trở thành tiết bắt đầu
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) + 1, LEN(Tiet)) AS INT) AS TietKT -- và tiết kết thúc để dễ xử lý
	FROM (
		SELECT MaLop, Thu, Tiet
		FROM LOPHOC lh
		WHERE lh.MaLop = @I_MaLop ) A --A chứa thông tin thời gian học lớp sắp đăng kí
	

	IF EXISTS (SELECT 1 FROM DKMH WHERE MaLop = @I_MaLop and MaHocVien = @I_MaHocVien)
		BEGIN
			RAISERROR (N'Đã đăng kí môn đó', 16, 1)
		END
	ELSE IF (
			SELECT COUNT(ds.MaMon) --kiểm tra xem học viên đã đăng kí môn học đó ở lớp học khác chưa
			FROM v_DSDaDangKi ds
			WHERE ds.MaHocVien = @I_MaHocVien and ds.MaMon = (SELECT MaMon FROM LOPHOC WHERE MaLop = @I_MaLop)
		) >= 1
		BEGIN
			RAISERROR (N'Đã đăng kí môn đó', 16, 1)
		END
	ELSE IF ( --kiểm tra xem số tín chỉ có vượt quá cho phép chưa
		SELECT SUM(SoTinChi)
		FROM v_DSDaDangKi
		WHERE MaHocVien = @I_MaHocVien
		) > 35
		BEGIN
			RAISERROR (N'Số tín chỉ vượt quá giới hạn cho phép.', 16, 1)
		END
	ELSE IF EXISTS ( --kiểm tra xem có trùng lịch với môn nào hay không
		SELECT 1 
		FROM (SELECT * FROM @TimeTable1 ) AS tt1, (SELECT * FROM @TimeTable2 ) AS tt2
		WHERE (tt1.NamHoc = tt2.NamHoc AND tt1.SoHocKy = tt2.SoHocKy) AND 
		(tt1.Thu = tt2.Thu AND ((tt1.TietBD <= tt2.TietBD AND tt1.TietKT >= tt2.TietBD) OR (tt1.TietBD <= tt2.TietKT AND tt1.TietKT >= tt2.TietKT)))
		)
		BEGIN
			RAISERROR(N'Trùng lịch', 16, 1)
		END
	ELSE IF  ( --kiểm tra xem lướp đó đủ số lượng đăng kí chưa
		(SELECT SoHocVienDangKy
		FROM LOPHOC
		WHERE MaLop = @I_MaLop) = 75
		)
		BEGIN
			RAISERROR(N'Đã đủ số lượng đăng kí', 16, 1)
		END
	ELSE 
		BEGIN
			INSERT INTO DKMH (MaHocVien, MaLop)
			VALUES(@I_MaHocVien, @I_MaLop)
		END
END;
GO

--6 b
--DROP TRIGGER Trigger_TrungLichChuyenLop;
CREATE TRIGGER Trigger_TrungLichChuyenLop --Trigger ngăn trùng lịch học khi học viên chuyển lớp
ON DKMH
INSTEAD OF UPDATE
AS
BEGIN
	DECLARE @I_MaLop CHAR(18), @I_MaHocVien CHAR(10)
	SELECT  @I_MaLop = MaLop, @I_MaHocVien = MaHocVien FROM inserted
	
	DECLARE @D_MaLop CHAR(18)
	SELECT  @D_MaLop = MaLop FROM deleted
	DECLARE @TimeTable1 TABLE ( --bảng lưu thời gian của các môn mà học viên đó đã đăng ký
		MaLop CHAR(18),
		NamHoc INT,
		SoHocKy INT,
		Thu INT, 
		TietBD INT,
		TietKT INT
		);
		DECLARE @TimeTable2 TABLE ( --bảng lưu thời gian của các môn mà học viên đó đã đăng ký
		MaLop CHAR(18),
		NamHoc INT,
		SoHocKy INT,
		Thu INT, 
		TietBD INT,
		TietKT INT
		);
	INSERT INTO @TimeTable1 (MaLop, NamHoc, SoHocKy, Thu, TietBD, TietKT) 
	SELECT	MaLop,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 1, 2) AS INT) AS NamHoc,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 4, 1) AS INT) AS SoHocKy,
			CAST(SUBSTRING(Thu, CHARINDEX(' ', Thu) + 1, LEN(Thu)) AS INT) AS Thu, --biến đổi thứ sang kiểu int để dễ xử lý
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) - 2, 2) AS INT) AS TietBD, --biến đổi tiết sang kiểu int trở thành tiết bắt đầu
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) + 1, LEN(Tiet)) AS INT) AS TietKT -- và tiết kết thúc để dễ xử lý
	FROM (
		SELECT MaLop, Thu, Tiet
		FROM dbo.v_DSDaDangKi ds
		WHERE ds.MaHocVien = @I_MaHocVien and ds.MaLop != @D_MaLop) A --A chứa thông tin thời gian học của Học viên
	
	INSERT INTO @TimeTable2 (MaLop, NamHoc, SoHocKy, Thu, TietBD, TietKT) 
	SELECT	MaLop,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 1, 2) AS INT) AS NamHoc,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 4, 1) AS INT) AS SoHocKy,
			CAST(SUBSTRING(Thu, CHARINDEX(' ', Thu) + 1, LEN(Thu)) AS INT) AS Thu, --biến đổi thứ sang kiểu int để dễ xử lý
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) - 2, 2) AS INT) AS TietBD, --biến đổi tiết sang kiểu int trở thành tiết bắt đầu
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) + 1, LEN(Tiet)) AS INT) AS TietKT -- và tiết kết thúc để dễ xử lý
	FROM (
		SELECT MaLop, Thu, Tiet
		FROM LOPHOC lh
		WHERE lh.MaLop = @I_MaLop ) A --A chứa thông tin thời gian học lớp sắp đăng kí

	IF (@D_MaLop = @I_MaLop)
		BEGIN
			RAISERROR (N'Đã đăng kí lớp này', 16, 1)
			ROLLBACK;
		END
	ELSE IF EXISTS ( --kiểm tra xem có trùng lịch với môn nào hay không
		SELECT 1 
		FROM (SELECT * FROM @TimeTable1 ) AS tt1, (SELECT * FROM @TimeTable2 ) AS tt2
		WHERE (tt1.NamHoc = tt2.NamHoc AND tt1.SoHocKy = tt2.SoHocKy) AND 
		(tt1.Thu = tt2.Thu AND ((tt1.TietBD <= tt2.TietBD AND tt1.TietKT >= tt2.TietBD) OR (tt1.TietBD <= tt2.TietKT AND tt1.TietKT >= tt2.TietKT)))
		)
		BEGIN
			RAISERROR(N'Trùng lịch', 16, 1)
		END
	ELSE IF ( --kiểm tra xem lướp đó đủ số lượng đăng kí chưa
		(SELECT SoHocVienDangKy
		FROM LOPHOC
		WHERE MaLop = @I_MaLop) = 75
		)
		BEGIN
			RAISERROR(N'Đã đủ số lượng đăng kí', 16, 1)
			ROLLBACK;
		END
	ELSE
		BEGIN
			UPDATE DKMH 
			SET MaLop = @I_MaLop
			WHERE MaHocVien = @I_MaHocVien and MaLop = @D_MaLop;
		END
END;
GO

--6 c
--DROP TRIGGER Trigger_MoLop;
CREATE TRIGGER Trigger_MoLop --Trigger hỗ trợ người quản lý xem xét mở lớp học mới
ON LOPHOC
INSTEAD OF INSERT
AS
BEGIN
	DECLARE @TimeTable1 TABLE ( --lưu thời gian các lớp học phần đang mở
		MaLop CHAR(18),
		MaGiangVien CHAR(10),
		MaPhong CHAR(10),
		NamHoc INT,
		SoHocKy INT,
		Thu INT, 
		TietBD INT,
		TietKT INT);
	INSERT INTO @TimeTable1 (MaLop, MaGiangVien, MaPhong, NamHoc, SoHocKy, Thu, TietBD, TietKT) 
	SELECT	MaLop,
			MaGiangVien,
			MaPhong,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 1, 2) AS INT) AS NamHoc,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 4, 1) AS INT) AS SoHocKy,
			CAST(SUBSTRING(Thu, CHARINDEX(' ', Thu) + 1, LEN(Thu)) AS INT) AS Thu, 
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) - 2, 2) AS INT) AS TietBD,
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) + 1, LEN(Tiet)) AS INT) AS TietKT
	FROM dbo.LOPHOC

	DECLARE @TimeTable2 TABLE ( --lưu thời gian lớp học phần thêm vào (insert)
		MaLop CHAR(18),
		MaGiangVien CHAR(10),
		MaPhong CHAR(10),
		NamHoc INT,
		SoHocKy INT,
		Thu INT, 
		TietBD INT,
		TietKT INT);
	INSERT INTO @TimeTable2 (MaLop, MaGiangVien, MaPhong, NamHoc, SoHocKy, Thu, TietBD, TietKT) 
	SELECT	MaLop,
			MaGiangVien,
			MaPhong,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 1, 2) AS INT) AS NamHoc,
			CAST(SUBSTRING(MaLop, CHARINDEX('_', MaLop) + 4, 1) AS INT) AS SoHocKy,
			CAST(SUBSTRING(Thu, CHARINDEX(' ', Thu) + 1, LEN(Thu)) AS INT) AS Thu, 
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) - 2, 2) AS INT) AS TietBD,
			CAST(SUBSTRING(Tiet, CHARINDEX('-', Tiet) + 1, LEN(Tiet)) AS INT) AS TietKT
		   FROM inserted

	DECLARE @I_MaLop CHAR(18)
    SELECT @I_MaLop = i.MaLop
	FROM inserted i
    IF EXISTS (
        SELECT 1
        FROM LOPHOC lh,inserted i
        WHERE lh.MaLop = i.MaLop
    )
    BEGIN
        RAISERROR (N'Lớp học đã tồn tại trong bảng lớp học.', 16, 1)
    END
    ELSE IF EXISTS ( --chú ý
			SELECT 1
			FROM @TimeTable1 tt1 ,@TimeTable2 AS tt2
			WHERE (tt1.NamHoc = tt2.NamHoc AND tt1.SoHocKy = tt2.SoHocKy) AND 
			(tt1.MaGiangVien = tt2.MaGiangVien AND tt1.Thu = tt2.Thu AND ((tt1.TietBD <= tt2.TietBD AND tt1.TietKT >= tt2.TietBD) OR (tt1.TietBD <= tt2.TietKT AND tt1.TietKT >= tt2.TietKT)))
		)
		BEGIN
			RAISERROR (N'Giảng viên đã dạy lớp học khác trong thời gian diễn ra lớp học.', 16, 1)
		END
    ELSE IF EXISTS ( --chú ý
			SELECT 1
			FROM @TimeTable1 tt1 ,@TimeTable2 AS tt2
			WHERE (tt1.NamHoc = tt2.NamHoc AND tt1.SoHocKy = tt2.SoHocKy) AND 
			(tt1.MaPhong = tt2.MaPhong AND tt1.Thu = tt2.Thu AND ((tt1.TietBD <= tt2.TietBD AND tt1.TietKT >= tt2.TietBD) OR (tt1.TietBD <= tt2.TietKT AND tt1.TietKT >= tt2.TietKT)))
		)
		BEGIN
			RAISERROR (N'Phòng học đã phục vụ lớp học khác trong thời gian diễn ra lớp học.', 16, 1)
		END
    ELSE
		BEGIN
			INSERT INTO LOPHOC (MaLop, MaGiangVien, TenLop, SoHocVienDangKy, MaMon, MaHocKy, MaPhong, Tiet, Thu)
			SELECT MaLop, MaGiangVien,TenLop, SoHocVienDangKy, MaMon, MaHocKy, MaPhong, Tiet,Thu
			FROM inserted i;
			PRINT N'Đã mở'
		END
END;
GO

--6 d
--DROP TRIGGER Trigger_XoaLopItHocVien
CREATE TRIGGER Trigger_XoaLopItHocVien --Trigger hỗ trợ người quản lý xóa lớp không đủ điều kiện
ON LOPHOC
INSTEAD OF DELETE
AS
BEGIN
	DECLARE @D_MaLop CHAR(18)
	SELECT @D_MaLop = d.MaLop 
	FROM deleted d

	IF (
		SELECT lh.SoHocVienDangKy
		FROM dbo.LOPHOC lh
		WHERE lh.MaLop = @D_MaLop
	) < 10
		BEGIN
			DELETE dbo.LOPHOC
			WHERE dbo.LOPHOC.MaLop = @D_MaLop;
		END
	ELSE 
		BEGIN
			RAISERROR (N'Lớp học không thể xoá', 16, 1)
		END
END;
GO

--2.2.3.
--6 e
--DROP TRIGGER Trigger_XoaGV
CREATE TRIGGER Trigger_XoaGV --Trigger hỗ trợ người quản lý xóa Giang vien (Chỉ xóa khi giảng viên đó ko dạy lớp nào)
ON GIANGVIEN
INSTEAD OF DELETE
AS
BEGIN
	DECLARE @D_MaGV CHAR(18)
	SELECT @D_MaGV = d.MaGiangVien 
	FROM deleted d

    IF NOT EXISTS (SELECT 1 FROM LOPHOC WHERE MaGiangVien = @D_MaGV)
	BEGIN
		DELETE FROM GIANGVIEN WHERE MaGiangVien = @D_MaGV
	END
	ELSE 
		BEGIN
			RAISERROR (N'Giảng viên đang dạy 1 lớp', 16, 1)
		END
END;
GO

--6 f
--DROP TRIGGER Trigger_CheckYeuCau
CREATE TRIGGER Trigger_CheckYeuCau -- Trigger kiểm tra yêu cầu của giáo viên
ON YEUCAU
INSTEAD OF INSERT 
AS
BEGIN
	DECLARE @I_MaGV CHAR(18), @I_MaMon CHAR(10)
	SELECT @I_MaGV = i.MaGiangVien, @I_MaMon = i.MaMon
	FROM inserted i

	IF NOT EXISTS (SELECT 1 FROM YEUCAU WHERE MaGiangVien = @I_MaGV and MaMon = @I_MaMon)
	BEGIN
		INSERT INTO YEUCAU (MaGiangVien, MaMon, SoHocVienDuocDangKy)
		SELECT i.MaGiangVien, i.MaMon, i.SoHocVienDuocDangKy
		FROM inserted i
	END
	ELSE 
		BEGIN
			RAISERROR (N'Giảng viên đã yêu cầu mở lớp cho môn này trước đó', 16, 1)
		END
END;
GO

--6 g
--DROP TRIGGER Trigger_TaoTaiKhoan
CREATE TRIGGER Trigger_TaoTaiKhoan 
ON TAIKHOAN
INSTEAD OF INSERT
AS
	DECLARE @userName NVARCHAR(30), @passWord NVARCHAR(10)
	SELECT @userName = nl.TaiKhoan, @passWord = nl.MatKhau
	FROM inserted nl
	IF EXISTS (SELECT 1 FROM TAIKHOAN WHERE TaiKhoan = @userName)
	BEGIN
		DECLARE @err NVARCHAR(MAX)
		SELECT @err = N'Lỗi: Tài khoản đã tồn tại' 
		RAISERROR(@err, 16, 1)
	END
	ELSE
	BEGIN
		INSERT INTO TaiKhoan (TaiKhoan, MatKhau) VALUES (@userName, @passWord)
		DECLARE @sqlString NVARCHAR(2000)

		SET @sqlString = 'CREATE LOGIN [' + @userName + '] WITH PASSWORD=''' + @passWord + ''', DEFAULT_DATABASE=[DANG_KY_MON_HOC], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF'
		EXEC (@sqlString)

		SET @sqlString = 'CREATE USER ' + @userName +' FOR LOGIN '+ @userName
		EXEC (@sqlString)

		IF (LEFT(@userName, 2) = 'HV')
			SET @sqlString = 'ALTER ROLE HV ADD MEMBER ' + @userName;
		ELSE IF (LEFT(@userName, 2) = 'GV')
			SET @sqlString = 'ALTER ROLE GV ADD MEMBER ' + @userName;
		ELSE
			SET @sqlString = 'ALTER SERVER ROLE sysadmin' + ' ADD MEMBER '+ @userName;
		EXEC (@sqlString)
	END;
GO

--6 h
--DROP TRIGGER Trigger_DoiMatKhau
CREATE TRIGGER Trigger_DoiMatKhau 
ON TAIKHOAN
INSTEAD OF UPDATE
AS
	DECLARE @userName NVARCHAR(30), @N_passWord NVARCHAR(20), @O_passWord NVARCHAR(10)
	SELECT @userName = nl.TaiKhoan, @N_passWord = nl.MatKhau
	FROM inserted nl
	SELECT @O_passWord = d.MatKhau
	FROM deleted d

	IF NOT EXISTS (SELECT 1 FROM TAIKHOAN WHERE TaiKhoan = @userName and MatKhau = @O_passWord)
	BEGIN
		DECLARE @err NVARCHAR(MAX)
		SELECT @err = N'Lỗi: Tên tài khoản hoặc mật khẩu không chính xác' 
		RAISERROR(@err, 16, 1)
	END
	ELSE
	BEGIN
		UPDATE TAIKHOAN
		SET TaiKhoan = @userName,MatKhau = @N_passWord
		WHERE TaiKhoan = @userName and MatKhau = @O_passWord

		DECLARE @sqlString NVARCHAR(2000)
		SET @sqlString = 'ALTER LOGIN [' + @userName + '] WITH PASSWORD = ''' + @N_passWord+ ''''
		EXEC (@sqlString)
	END;
GO
--2.1.
--AdminDAO.cs
--DROP FUNCTION [dbo].[fu_Check_DangNhap_ADMIN] --bỏ proceduce 2.1 proc_login thay thành cái này để tạo ra hàm tar về gái trị nhưu ý thầy
CREATE FUNCTION [dbo].[fu_Check_DangNhap_ADMIN](@user NVARCHAR(50), @pass NVARCHAR(50))
RETURNS FLOAT
AS 
BEGIN
    DECLARE @result FLOAT;
	IF EXISTS (SELECT (1) FROM TAIKHOAN WHERE TaiKhoan = @user
											AND MatKhau = @pass  
											AND @user NOT IN (SELECT MaHocVien FROM HOCVIEN)
											AND @user NOT IN (SELECT MaGiangVien FROM GIANGVIEN))
	BEGIN
		SET @result = 1
	END
	ELSE SET @result = 0
	RETURN @result
END;
GO

--2.4.1.
--FUNCTION lấy dữ liệu từ table GiangVien
--DROP FUNCTION fu_load_GV
CREATE FUNCTION fu_load_GV( ) 
RETURNS TABLE 
AS RETURN (
	SELECT MaGiangVien, HoTen, HocVi, HocHam, Diachi, Sdt, MaKhoa, MatKhau 
	FROM GIANGVIEN LEFT JOIN TAIKHOAN ON TaiKhoan = MaGiangVien
);
GO

--2.4.2.
--DROP FUNCTION [dbo].[func_getGiangVienByMaGiangVien]
CREATE FUNCTION [dbo].[func_getGiangVienByMaGiangVien] (@maGiangVien CHAR(6))
RETURNS TABLE
AS
RETURN
(
    SELECT MaGiangVien, HoTen, HocVi, HocHam, Diachi, Sdt, MaKhoa, MatKhau  
	FROM GIANGVIEN LEFT JOIN TAIKHOAN ON TaiKhoan = MaGiangVien
    WHERE MaGiangVien = @maGiangVien
);
GO

--2.5.1.
--FUNCTION lấy dữ liệu từ table SinhViên --
--DROP FUNCTION fu_load_HocVien
CREATE FUNCTION fu_load_HocVien ( )
RETURNS TABLE 
AS RETURN (
	SELECT MaHocVien, HoTen, NgaySinh, DiaChi, QueQuan, MaKhoa, MatKhau
	FROM HOCVIEN LEFT JOIN TAIKHOAN ON MaHocVien = TaiKhoan
);
GO

--2.5.2.
--FUNCTION tìm thông tin của một sinh viên bằng Mã Sinh viên
--DROP FUNCTION [dbo].[func_getHocVienByMaHocVien]
CREATE FUNCTION [dbo].[func_getHocVienByMaHocVien] (@maHocVien CHAR(10))
RETURNS TABLE
AS
RETURN
(
    SELECT MaHocVien, HoTen, NgaySinh, DiaChi, QueQuan, MaKhoa, MatKhau
	FROM HOCVIEN LEFT JOIN TAIKHOAN ON MaHocVien = TaiKhoan
    WHERE MaHocVien = @maHocVien
);
GO

--2.6.1.
--FUNCTION hiển thị danh sách lớp học
CREATE FUNCTION [dbo].[DanhSachLopHoc] ( )
RETURNS TABLE
AS
RETURN
(
    SELECT MaLop, TenLop, MaGiangVien, MaMon, MaHocKy, SoHocVienDangKy, MaPhong, Thu, Tiet
    FROM LOPHOC
);
GO

--2.6.2.
--FUNCTION Tìm kiếm một lớp học phần
CREATE FUNCTION [dbo].[LayThongTinLopHocPhan] (@MaLop CHAR(18))
RETURNS TABLE
AS
RETURN
(
    SELECT MaLop, TenLop, MaGiangVien, MaMon, MaHocKy, SoHocVienDangKy, MaPhong, Thu, Tiet
    FROM LOPHOC
    WHERE MaLop = @MaLop
);
GO

--3.2.
--DROP FUNCTION fu_load_MonDK
CREATE FUNCTION fu_load_MonDK (@MaKhoa VARCHAR(5)) --Hàm load các môn học cho học viên đăng ký
RETURNS TABLE 
AS RETURN (
	SELECT MaMon, TenMonHoc, SoTinChi 
	FROM MonHoc 
	WHERE MaKhoa = @MaKhoa
);
GO

--3.8.
--DROP FUNCTION fu_load_DSHocPhi
CREATE FUNCTION fu_load_DSHocPhi (@MaHocVien CHAR(10), @MaKhoa VARCHAR(5)) --Hàm danh sách học phí
RETURNS TABLE 
AS 
RETURN 
(
    SELECT lh.MaLop, mh.TenMonHoc, lh.Tiet, lh.Thu, p.MaPhong Phong, 
           gv.HoTen AS GiangVien, dk.MaHocVien, lh.MaMon, mh.SoTinChi, 
           CASE 
               WHEN @MaKhoa = 'FHQ' THEN mh.SoTinChi * 800000
               ELSE mh.SoTinChi * 600000
           END AS HocPhi
    FROM dbo.MONHOC mh
    JOIN dbo.LOPHOC lh ON lh.MaMon = mh.MaMon
    JOIN dbo.GIANGVIEN gv ON lh.MaGiangVien = gv.MaGiangVien
    JOIN dbo.PHONGHOC p ON  lh.MaPhong = p.MaPhong
    JOIN dbo.DKMH dk ON dk.MaLop = lh.MaLop
    WHERE dk.MaHocVien = @MaHocVien
);
GO

--3.10.
--DROP FUNCTION fu_load_DSTimKiem
CREATE FUNCTION fu_load_DSTimKiem(@string NVARCHAR(50)) --Hàm load các lớp học cho học viên đăng ký
RETURNS TABLE 
AS RETURN (
	SELECT * 
	FROM v_DSLopHoc 
	WHERE MaMon LIKE '%' + @string + '%' 
);
GO

--3.9.
--DROP FUNCTION fu_TongHocPhi
CREATE FUNCTION [dbo].[fu_TongHocPhi](@MaHocVien NVARCHAR(10), @MaKhoa VARCHAR(5))
RETURNS FLOAT
AS 
BEGIN
    DECLARE @tinchi FLOAT;
	DECLARE @HocPhi FLOAT;

    -- Sử dụng SELECT để gán giá trị cho biến result
    SELECT @tinchi = SUM(SoTinChi) FROM v_DSDaDangKi WHERE MaHocVien = @MaHocVien;

    -- Nếu không có dữ liệu, gán giá trị mặc định cho biến result
    IF (@tinchi IS NULL)
        SET @tinchi = 0;

    -- Trả về giá trị
	IF ( @MaKhoa = 'FHQ')
	BEGIN
		SET @HocPhi = @tinchi * 800000
	END
	ELSE 
		BEGIN
			SET @HocPhi = @tinchi * 600000		
		END
    RETURN @HocPhi;
END;
GO
--2.2
--AdminDAO.cs
--DROP PROCEDURE proc_TaoTK
CREATE PROCEDURE proc_TaoTK(@TaiKhoan NVARCHAR(20), @MatKhau NVARCHAR(20))
AS
BEGIN
	INSERT INTO TaiKhoan(TaiKhoan, MatKhau) VALUES (@TaiKhoan, @MatKhau)
END;
GO

--2.3
--AdminDAO.cs
--DROP PROCEDURE proc_DoiMK
CREATE PROCEDURE proc_DoiMK(@TaiKhoan NVARCHAR(20), @MatKhauCu NVARCHAR(20), @MatKhauMoi NVARCHAR(20))
AS
BEGIN
	UPDATE TAIKHOAN
	SET TaiKhoan = @TaiKhoan, MatKhau = @MatKhauMoi
	WHERE TaiKhoan = @TaiKhoan AND MatKhau = @MatKhauCu
END;
GO

--2.4.3.
--Admin_GiangVien.cs
--GiangVienAdminDAO.cs
--DROP PROCEDURE DeleteGiangVien
CREATE PROCEDURE DeleteGiangVien
    @MaGiangVien CHAR(6)
AS
BEGIN
BEGIN TRANSACTION
	DECLARE @username VARCHAR(15);
	SELECT @username = MaGiangVien FROM GIANGVIEN WHERE MaGiangVien = @MaGiangVien
	DECLARE @sql VARCHAR(100)
	DECLARE @SessionID INT;
	SELECT @SessionID = session_id
	FROM sys.dm_exec_sessions
	WHERE login_name = @username;
	IF @SessionID IS NOT NULL
		BEGIN
			SET @sql = 'KILL ' + CONVERT(NVARCHAR(20), @SessionID)
			EXEC(@sql)
		END
	
	BEGIN TRY
		DELETE FROM GIANGVIEN
		WHERE MaGiangVien = @MaGiangVien;
		DELETE FROM TAIKHOAN 
		WHERE TaiKhoan = @MaGiangVien
		SET @sql = 'DROP USER '+ @username
		EXEC (@sql)

		SET @sql = 'DROP LOGIN '+ @username
		EXEC (@sql)
		COMMIT TRAN
	END TRY
	BEGIN CATCH
		ROLLBACK
		DECLARE @err NVARCHAR(MAX)
		SELECT @err = N'Lỗi ' + ERROR_MESSAGE()
		RAISERROR(@err, 16, 1)
	END CATCH
END;
GO

--2.4.4.
--DROP PROCEDURE [dbo].[InsertGiangVien]
CREATE PROCEDURE [dbo].[InsertGiangVien]
	@MaGiangVien CHAR(6),
	@HoTen NVARCHAR(MAX),
	@HocVi NVARCHAR(10),
	@HocHam NVARCHAR(12),
	@DiaChi NVARCHAR(MAX),
	@Sdt CHAR(10),
	@MaKhoa VARCHAR(5)
AS
BEGIN
	IF EXISTS (SELECT 1 FROM GIANGVIEN WHERE MaGiangVien = @MaGiangVien)
	BEGIN
			DECLARE @err NVARCHAR(MAX)
			SELECT @err = N'Giảng viên đã có trong cơ sở dữ liệu'
			RAISERROR(@err, 16, 1)
	END
	ELSE
	BEGIN
		INSERT INTO GIANGVIEN (MaGiangVien, HoTen, HocVi, HocHam, DiaChi, Sdt, MaKhoa)
		VALUES (@MaGiangVien, @HoTen, @HocVi, @HocHam, @DiaChi, @Sdt, @MaKhoa);
	END
END;
GO

--2.4.5.
--DROP PROCEDURE [dbo].[UpdateGiangVien]
CREATE PROCEDURE [dbo].[UpdateGiangVien]
	@MaGiangVien CHAR(6),
	@HoTen NVARCHAR(MAX),
	@HocVi NVARCHAR(10),
	@HocHam NVARCHAR(12),
	@DiaChi NVARCHAR(MAX),
	@Sdt CHAR(10),
	@MaKhoa VARCHAR(5)
AS
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION;

		IF EXISTS (SELECT 1 FROM GIANGVIEN WHERE MaGiangVien = @MaGiangVien)
		BEGIN
			UPDATE GIANGVIEN
			SET
				HoTen = @HoTen,
				HocVi = @HocVi,
				HocHam = @HocHam,
				DiaChi = @DiaChi,
				Sdt = @Sdt,
				MaKhoa = @MaKhoa
			WHERE MaGiangVien = @MaGiangVien;
		END
		ELSE
		BEGIN
			RAISERROR(N'Giảng viên với mã giảng viên không tồn tại.', 16, 1);
		END

		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION;

		DECLARE @err NVARCHAR(MAX);
		SELECT @err = N'Lỗi ' + ERROR_MESSAGE();
		RAISERROR(@err, 16, 1);
	END CATCH
END;
GO

--2.5.3.
--DROP PROCEDURE DeleteHocVien
CREATE PROCEDURE DeleteHocVien
    @MaHocVien CHAR(10)
AS
BEGIN
		BEGIN TRANSACTION
			DECLARE @username VARCHAR(15);
			SELECT @username = MaHocVien FROM HOCVIEN WHERE MaHocVien = @MaHocVien
			DECLARE @sql VARCHAR(100)
			DECLARE @SessionID INT;
			SELECT @SessionID = session_id
			FROM sys.dm_exec_sessions
			WHERE login_name = @username;
			IF @SessionID IS NOT NULL
			BEGIN
				SET @sql = 'KILL ' + CONVERT(NVARCHAR(20), @SessionID)
				EXEC(@sql)
			END
    
		BEGIN TRY
			UPDATE LOPHOC
			SET LOPHOC.SoHocVienDangKy = LOPHOC.SoHocVienDangKy - 1
			WHERE MaLop IN (SELECT MaLop FROM DKMH WHERE MaHocVien = @MaHocVien);
        -- Xóa dòng dữ liệu có chứa @MaHocVien trong bảng DKMH
			DELETE FROM DKMH
			WHERE MaHocVien = @MaHocVien;
        -- Xóa học viên từ bảng HOCVIEN
			DELETE FROM HOCVIEN
			WHERE MaHocVien = @MaHocVien;
		----
			DELETE FROM TAIKHOAN
			WHERE taikhoan = @MaHocVien
			SET @sql = 'DROP USER ' + @username
			EXEC (@sql)

			SET @sql = 'DROP LOGIN ' + @username
			EXEC (@sql)

			COMMIT;
		END TRY
		BEGIN CATCH
		 -- Nếu có lỗi, hủy giao dịch và hiển thị thông báo lỗi
			ROLLBACK;
			DECLARE @err NVARCHAR(MAX)
			SELECT @err = N'Không thể xóa, kiểm tra lại mã học viên'
			RAISERROR(@err, 16, 1)
		END CATCH;
END;
GO

--2.5.4.
--PROCEDURE Thêm một học viên
--Drop procedure [dbo].[InsertHocVien]
CREATE PROCEDURE [dbo].[InsertHocVien]
    @MaHocVien CHAR(10),
    @HoTen NVARCHAR(40),
    @NgaySinh DATE,
    @DiaChi NVARCHAR(MAX),
    @QueQuan NVARCHAR(20),
    @MaKhoa VARCHAR(5)
AS
BEGIN
    IF EXISTS (SELECT 1 FROM HOCVIEN WHERE MaHocVien = @MaHocVien)
    BEGIN
        DECLARE @err NVARCHAR(MAX)
		SELECT @err = N'Học viên đã tồn tại trong cơ sở dữ liệu' 
		RAISERROR(@err, 16, 1)
    END
	ELSE
	BEGIN
		INSERT INTO HOCVIEN (MaHocVien, HoTen, NgaySinh, DiaChi, QueQuan, MaKhoa)
		VALUES (@MaHocVien, @HoTen, @NgaySinh, @DiaChi, @QueQuan, @MaKhoa);
	END
END;
GO

--2.5.5.
--PROCEDURE cập nhật thông tin một học viên
--DROP PROCEDURE [dbo].[UpdateHocVien]
CREATE PROCEDURE [dbo].[UpdateHocVien]
    @MaHocVien CHAR(10),
    @HoTen NVARCHAR(MAX),
    @NgaySinh DATE,
    @DiaChi NVARCHAR(MAX),
    @QueQuan NVARCHAR(20),
    @MaKhoa VARCHAR(5)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;

        IF EXISTS (SELECT 1 FROM HOCVIEN WHERE MaHocVien = @MaHocVien)
        BEGIN
            UPDATE HOCVIEN
            SET
                HoTen = @HoTen,
                NgaySinh = @NgaySinh,
                DiaChi = @DiaChi,
                QueQuan = @QueQuan,
                MaKhoa = @MaKhoa
            WHERE MaHocVien = @MaHocVien;
        END
        ELSE
        BEGIN
            RAISERROR(N'!', 16, 1);
        END

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        DECLARE @err NVARCHAR(MAX);
        SELECT @err = N'Học viên với mã học viên đã tồn tại' + ERROR_MESSAGE();
        RAISERROR(@err, 16, 1);
    END CATCH
END;
GO

--2.6.3.
--DROP PROCEDURE [dbo].[XoaLopHoc]
CREATE PROCEDURE [dbo].[XoaLopHoc]
    @MaLop CHAR(18)
AS
BEGIN
	BEGIN TRANSACTION 
		BEGIN TRY
			DELETE FROM DKMH
			WHERE MaLop = @MaLop

			DELETE FROM LOPHOC
			WHERE MaLop = @MaLop;
		COMMIT TRAN
		END TRY
		BEGIN CATCH
			ROLLBACK
			DECLARE @err NVARCHAR(MAX)
			SELECT @err = N'Lỗi ' + ERROR_MESSAGE()
			RAISERROR(@err, 16, 1)
		END CATCH
END;
GO

--2.6.4.
--PROCEDURE thêm một lớp học phần 
--DROP PROCEDURE [dbo].[ThemLopHoc] 
CREATE PROCEDURE [dbo].[ThemLopHoc]
    @MaLop CHAR(18),
    @TenLop NVARCHAR(MAX),
    @MaGiangVien CHAR(6),
    @MaMon CHAR(10),
    @MaHocKy CHAR(9),
    @SoHocVienDangKy INT,
    @MaPhong VARCHAR(10),
    @Thu NVARCHAR(10),
    @Tiet NVARCHAR(10)
AS
BEGIN
    INSERT INTO LOPHOC (MaLop, TenLop, MaGiangVien, MaMon, MaHocKy, SoHocVienDangKy, MaPhong, Thu, Tiet)
    VALUES (@MaLop, @TenLop, @MaGiangVien, @MaMon, @MaHocKy, @SoHocVienDangKy, @MaPhong, @Thu, @Tiet);
END;
GO
/*
DROP PROCEDURE InsertLopHocFromYeuCau;
CREATE PROCEDURE InsertLopHocFromYeuCau
(
	@MaGiangVien CHAR(6),
    @MaMon CHAR(10),
    @MaPhong VARCHAR(10),
    @Thu NVARCHAR(10),
    @Tiet VARCHAR(10),
    @MaHocKy CHAR(9)
)
AS
BEGIN
    BEGIN TRANSACTION;
    BEGIN TRY
        DECLARE @SoHocKy INT;
        DECLARE @SoHocKyKep INT;
		DECLARE @TenLop NVARCHAR(MAX);

        -- Lấy số kỳ học dựa trên @HocKy
        SET @SoHocKy = SUBSTRING(@MaHocKy, 3, 1);

        -- Tìm số kế tiếp của lớp học dạy môn giống nhau
        SELECT @SoHocKyKep = MAX(CAST(RIGHT(MaLop, 2) AS INT))
        FROM LOPHOC
        WHERE MaMon = @MaMon
        AND MaHocKy = @MaHocKy;

        -- Nếu không tìm thấy số kế tiếp, sử dụng 1
        IF @SoHocKyKep IS NULL
            BEGIN
                SET @SoHocKyKep = 1;
            END
        ELSE
            BEGIN
                SET @SoHocKyKep = @SoHocKyKep + 1;
            END;

        -- Tạo MaLop theo định dạng
        DECLARE @MaLop CHAR(18);
        SET @MaLop = @MaMon + '_' + RIGHT(@MaHocKy, 2) + '_' + CAST(@SoHocKy AS CHAR(1)) + '_' + RIGHT('0' + CAST(@SoHocKyKep AS VARCHAR(2)), 2);

		SET @TenLop = (SELECT TenMonHoc FROM MONHOC WHERE MaMon = @MaMon) + '_Nhóm ' + RIGHT('0' + CAST(@SoHocKyKep AS VARCHAR(2)), 2);

        -- Chèn bản ghi vào bảng LOPHOC
        INSERT INTO LOPHOC (MaLop, TenLop, MaGiangVien, MaMon, MaHocKy, SoHocVienDangKy, MaPhong, Thu, Tiet)
        VALUES (@MaLop, @TenLop, @MaGiangVien, @MaMon, @MaHocKy, 0, @MaPhong, @Thu, @Tiet);
        
		-- Xoá bản ghi tương ứng trong bảng YEUCAU
        DELETE FROM YEUCAU
        WHERE MaGiangVien = @MaGiangVien
        AND MaMon = @MaMon;
        COMMIT;
    END TRY
    BEGIN CATCH
        ROLLBACK;
        DECLARE @err NVARCHAR(MAX);
        SELECT @err = N'Lỗi ' + ERROR_MESSAGE();
        RAISERROR(@err, 16, 1);
    END CATCH;
END;
GO

EXEC InsertLopHocFromYeuCau @MaPhong = 'B108', @Thu = N'Thu 2', @Tiet = 'Tiet 1-3', @MaHocKy = 'HK2_22-23', @MaGiangVien = 'GV0001', @MaMon = 'MATH330123';
*/
PRINT '';
GO

--2.6.5.
--DROP PROCEDURE [dbo].[UpdateLopHoc]
CREATE PROCEDURE [dbo].[UpdateLopHoc]
    @MaLop CHAR(18),
    @TenLop NVARCHAR(50),
    @MaGiangVien CHAR(10),
    @MaMon CHAR(10),
    @MaHocKy CHAR(10),
    @SoHocVienDangKy INT,
    @MaPhong CHAR(10),
    @Thu NVARCHAR(10),
    @Tiet NVARCHAR(50)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;

        IF EXISTS (SELECT 1 FROM LOPHOC WHERE MaLop = @MaLop)
        BEGIN
            UPDATE LOPHOC
            SET
                TenLop = @TenLop,
                MaGiangVien = @MaGiangVien,
                MaMon = @MaMon,
                MaHocKy = @MaHocKy,
                SoHocVienDangKy = @SoHocVienDangKy,
                MaPhong = @MaPhong,
                Thu = @Thu,
                Tiet = @Tiet
            WHERE MaLop = @MaLop;
        END
        ELSE
        BEGIN
            RAISERROR(N'Lớp học với mã lớp = %s không tồn tại.', 16, 1, @MaLop);
        END

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        DECLARE @err NVARCHAR(MAX);
        SELECT @err = N'Lỗi ' + ERROR_MESSAGE();
        RAISERROR(@err, 16, 1);
    END CATCH
END;
GO

--3.1.
--DROP PROCEDURE proc_HV_DN 
CREATE PROCEDURE proc_HV_DN
	@TaiKhoan CHAR(10),
	@MatKhau CHAR(18)
AS
BEGIN
	IF EXISTS (SELECT 1 FROM TAIKHOAN, HOCVIEN WHERE TaiKhoan = @TaiKhoan and MatKhau = @MatKhau and MaHocVien = @TaiKhoan)
	BEGIN
		SELECT HoTen, MaKhoa 
		FROM HOCVIEN 
		WHERE MaHocVien = @TaiKHoan
	END
	ELSE 
	BEGIN
		DECLARE @err NVARCHAR(MAX)
		SELECT @err = N'Sai Tài khoản hoặc Mật khẩu'
		RAISERROR(@err, 16, 1)
	END
END;
GO

--3.5.
--DROP PROCEDURE proc_DKLopHoc
CREATE PROCEDURE proc_DKLopHoc --Học viên đăng ký lớp học
	@MaHV CHAR(10),
	@MaLH CHAR(18)
AS
BEGIN
	BEGIN TRANSACTION 
		BEGIN TRY
			INSERT INTO DKMH (MaHocVien, MaLop)
			VALUES(@MaHV, @MaLH)

			UPDATE LOPHOC
			SET LOPHOC.SoHocVienDangKy = LOPHOC.SoHocVienDangKy + 1
			WHERE MaLop = @MaLH

		COMMIT TRAN
		END TRY
		BEGIN CATCH
			ROLLBACK
			DECLARE @err NVARCHAR(MAX)
			SELECT @err = N'Lỗi ' + ERROR_MESSAGE()
			RAISERROR(@err, 16, 1)
		END CATCH
END;
GO

--3.6.
--DROP PROCEDURE proc_Xoa_DKLopHoc 
CREATE PROCEDURE proc_Xoa_DKLopHoc --Học viên xoá đăng ký lớp học
	@MaHV CHAR(10),
	@MaLH CHAR(18)
AS
BEGIN
	BEGIN TRANSACTION 
		BEGIN TRY
			DELETE FROM DKMH WHERE MaHocVien = @MaHV AND MaLop = @MaLH

			UPDATE LOPHOC
			SET LOPHOC.SoHocVienDangKy = LOPHOC.SoHocVienDangKy - 1
			WHERE MaLop = @MaLH
		COMMIT TRAN
		END TRY
		BEGIN CATCH
			ROLLBACK
			DECLARE @err NVARCHAR(MAX)
			SELECT @err = N'Lỗi ' + ERROR_MESSAGE()
			RAISERROR(@err, 16, 1)
		END CATCH
END;
GO

--3.7.
--DROP PROCEDURE proc_ChuyenLopHoc 
CREATE PROCEDURE proc_ChuyenLopHoc --Học viên chuyển lớp học
	@MaHV CHAR(10),
	@MaLH CHAR(18), --lớp học hiện tại
	@MaLHDK CHAR(18) --lớp học đăng ký mới
AS
BEGIN
	BEGIN TRANSACTION 
		BEGIN TRY
			UPDATE DKMH 
			SET MaLop = @MaLHDK
			WHERE MaHocVien = @MaHV and MaLop = @MaLH;

			UPDATE LOPHOC
			SET LOPHOC.SoHocVienDangKy = LOPHOC.SoHocVienDangKy - 1
			WHERE MaLop = @MaLH
			UPDATE LOPHOC
			SET LOPHOC.SoHocVienDangKy = LOPHOC.SoHocVienDangKy + 1
			WHERE MaLop = @MaLHDK

		COMMIT TRAN
		END TRY
		BEGIN CATCH
			ROLLBACK
			DECLARE @err NVARCHAR(MAX)
			SELECT @err = N'Lỗi ' + ERROR_MESSAGE()
			RAISERROR(@err, 16, 1)
		END CATCH
END;
GO

--4.1.
--DROP PROCEDURE proc_GV_DN
CREATE PROCEDURE proc_GV_DN --Giảng viên đăng nhập
	@TaiKhoan CHAR(10),
	@MatKhau CHAR(18)
AS
BEGIN
	IF EXISTS (SELECT 1 FROM TAIKHOAN, GIANGVIEN WHERE MaGiangVien = @TaiKhoan AND TaiKhoan = @TaiKhoan AND MatKhau = @MatKhau)
	BEGIN
		SELECT *
		FROM GIANGVIEN 
		WHERE MaGiangVien = @TaiKhoan
	END
	ELSE 
	BEGIN
		DECLARE @err NVARCHAR(MAX)
		SELECT @err = N'Sai Tài khoản hoặc Mật khẩu'
		RAISERROR(@err, 16, 1)
	END
END;
GO

--4.4.
--drop PROCEDURE proc_GuiYeuCau
CREATE PROCEDURE proc_GuiYeuCau
	@MaGiangVien CHAR(6),
	@MaMon CHAR(10),
	@SoHocVienDuocDangKy INT
AS
BEGIN
BEGIN TRY
	INSERT INTO YEUCAU (MaGiangVien, MaMon, SoHocVienDuocDangKy)
	VALUES (@MaGiangVien, @MaMon, @SoHocVienDuocDangKy)
END TRY
BEGIN CATCH
	DECLARE @err NVARCHAR(MAX)
	SELECT @err = N'Lỗi ' + ERROR_MESSAGE()
	RAISERROR(@err, 16, 1)
END CATCH
END;
GO
CREATE ROLE HV
GRANT SELECT, INSERT, DELETE, UPDATE, REFERENCES ON DKMH TO HV
GRANT SELECT ON HOCKY TO HV
GRANT SELECT ON HOCVIEN TO HV
GRANT SELECT ON MONHOC TO HV
GRANT SELECT ON LOPHOC TO HV
GRANT SELECT ON KHOA TO HV
GRANT SELECT ON LOPHOC TO HV

GRANT EXECUTE TO HV
GRANT SELECT TO HV;

DENY EXECUTE ON DeleteGiangVien TO HV;
DENY EXECUTE ON InsertGiangVien TO HV;
DENY EXECUTE ON UpdateGiangVien TO HV;
DENY EXECUTE ON DeleteHocVien TO HV;
DENY EXECUTE ON InsertHocVien TO HV;
DENY EXECUTE ON UpdateHocVien TO HV;
DENY EXECUTE ON XoaLopHoc TO HV
DENY EXECUTE ON ThemLopHoc TO HV;
DENY EXECUTE ON UpdateLopHoc TO HV;
DENY EXECUTE ON proc_HV_DN TO HV;
DENY EXECUTE ON proc_GV_DN TO HV;
DENY EXECUTE ON proc_GuiYeuCau TO HV;
DENY EXECUTE ON proc_TaoTK TO HV;

CREATE ROLE GV

GRANT SELECT ON GIANGVIEN TO GV
GRANT SELECT ON HOCKY TO GV
GRANT SELECT ON HOCVIEN TO GV
GRANT SELECT ON KHOA TO GV
GRANT SELECT ON LOPHOC TO GV
GRANT SELECT ON LOPHOC TO GV
GRANT SELECT ON DKMH TO GV
GRANT SELECT ON PHONGHOC TO GV
GRANT SELECT, INSERT ON YEUCAU TO GV

GRANT EXECUTE TO GV
GRANT SELECT TO GV;

DENY EXECUTE ON DeleteGiangVien TO GV;
DENY EXECUTE ON InsertGiangVien TO GV;
DENY EXECUTE ON UpdateGiangVien TO GV;
DENY EXECUTE ON DeleteHocVien TO GV;
DENY EXECUTE ON InsertHocVien TO GV;
DENY EXECUTE ON UpdateHocVien TO GV;
DENY EXECUTE ON XoaLopHoc TO GV;
DENY EXECUTE ON ThemLopHoc TO GV;
DENY EXECUTE ON UpdateLopHoc TO GV;
DENY EXECUTE ON proc_HV_DN TO GV;
DENY EXECUTE ON proc_GV_DN TO GV;
DENY EXECUTE ON proc_TaoTK TO GV;
DENY EXECUTE ON proc_DKLopHoc TO GV;
DENY EXECUTE ON proc_Xoa_DKLopHoc TO GV;
DENY EXECUTE ON proc_ChuyenLopHoc TO GV;
DENY EXECUTE ON proc_TaoTK TO GV;
Go
INSERT INTO TAIKHOAN(TaiKhoan,MatKhau) VALUES ('admin1', '123');


