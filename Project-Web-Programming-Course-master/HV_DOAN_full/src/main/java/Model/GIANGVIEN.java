package Model;

public class GIANGVIEN {
    private String maGiangVien;
    private String hoTen;
    private String hocVi;
    private String hocHam;
    private String diaChi;
    private String sdt;
    private String maKhoa;
	private String matKhau;

    // Constructors
    public GIANGVIEN() {
        // Default constructor
    }
    public GIANGVIEN(String maGiangVien, String hoTen, String hocVi, String hocHam,
            String diaChi, String sdt, String maKhoa,  String matKhau) {
    		this.maGiangVien = maGiangVien;
    		this.hoTen = hoTen;
    		this.hocVi = hocVi;
    		this.hocHam = hocHam;
    		this.diaChi = diaChi;
    		this.sdt = sdt;
    		this.maKhoa = maKhoa;
    		this.matKhau=matKhau;
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
    
    public String getMatKhau() {
        return matKhau;
    }
	public void setMatKhau(String matKhau) {
		this.matKhau=matKhau;
	}
}