package Model;

public class MONHOC {

	public MONHOC() {
		// TODO Auto-generated constructor stub
	}
	public MONHOC(String maMon, String tenMonHoc, int soTinChi, String maKhoa) {
        this.maMon = maMon;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.maKhoa = maKhoa;
    }
	private String maMon;
    private String tenMonHoc;
    private int soTinChi;
    private String maKhoa;

    // Getters and Setters
    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
}
