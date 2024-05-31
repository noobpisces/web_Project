package Model;
public class ADMIN {
	
    private String TenTK;
    private String MatKhau;

    public ADMIN(String TenTK, String MatKhau) {

        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
     
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
}