package Model;

import java.sql.Date;


public class HOCVIEN {
    private String mahocvien;
    private String hoten;
    private Date ngaysinh;
    private String diachi;
    private String quequan;
    private String makhoa;
    private String matkhau;

    public HOCVIEN() {}

    public HOCVIEN(String maHocVien, String hoTen,Date ngaySinh, String diaChi, String queQuan, String maKhoa, String matKhau) {
        this.mahocvien = maHocVien;
        this.hoten = hoTen;
        this.ngaysinh = ngaySinh;
        this.diachi = diaChi;
        this.quequan = queQuan;
        this.makhoa = maKhoa;
        this.matkhau = matKhau;
    }

    public void setMaHocVien(String mahocvien) {
        this.mahocvien = mahocvien;
    }

    public String getMaHocVien() {
        return mahocvien;
    }

    public void setHoTen(String hoten) {
        this.hoten = hoten;
    }

    public String getHoTen() {
        return hoten;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaysinh = ngaySinh;
    }

    public Date getNgaySinh() {
        return ngaysinh;
    }

    public void setDiaChi(String diachi) {
        this.diachi = diachi;
    }

    public String getDiaChi() {
        return diachi;
    }

    public void setQueQuan(String quequan) {
        this.quequan = quequan;
    }

    public String getQueQuan() {
        return quequan;
    }

    public void setMaKhoa(String makhoa) {
        this.makhoa = makhoa;
    }

    public String getMaKhoa() {
        return makhoa;
    }
    
    public void setMatKhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getMatKhau() {
        return matkhau;
    }
	
}