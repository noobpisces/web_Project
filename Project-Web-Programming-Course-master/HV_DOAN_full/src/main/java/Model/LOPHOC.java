package Model;

public class LOPHOC {
    private String maLop;
    private String tenLop;
    private String maGiangVien;
    private String maMon;
    private String maHocKy;
    private int soHocVienDangKy;
    private String maPhong;
    private String thu;
    private String tiet;

    // Constructors
    public LOPHOC() {
        // Default constructor
    }
    public LOPHOC(String maLop, String tenLop, String maGiangVien, String maMon, String maHocKy,
            int soHocVienDangKy, String maPhong, String thu, String tiet) {
  this.maLop = maLop;
  this.tenLop = tenLop;
  this.maGiangVien = maGiangVien;
  this.maMon = maMon;
  this.maHocKy = maHocKy;
  this.soHocVienDangKy = soHocVienDangKy;
  this.maPhong = maPhong;
  this.thu = thu;
  this.tiet = tiet;
}


    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getMaHocKy() {
        return maHocKy;
    }

    public void setMaHocKy(String maHocKy) {
        this.maHocKy = maHocKy;
    }

    public int getSoHocVienDangKy() {
        return soHocVienDangKy;
    }

    public void setSoHocVienDangKy(int soHocVienDangKy) {
        this.soHocVienDangKy = soHocVienDangKy;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getTiet() {
        return tiet;
    }

    public void setTiet(String tiet) {
        this.tiet = tiet;
    }
}
