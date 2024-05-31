package Data_Structure;
import Model.LOPHOC;
import Model.GIANGVIEN;
import Model.MONHOC;
import Model.HOCVIEN;
import java.util.List;
public class MyList {

    private List<MONHOC> list_MH;
    private List<GIANGVIEN> list_GV;
    private List<LOPHOC> list_LH;
    private List<HOCVIEN> list_HV;
    private List<Integer> list_HocPhi;
    public MyList() {
    }
    
    public List<HOCVIEN> getList_HV() {
        return list_HV;
    }

    public void setList_HV(List<HOCVIEN> list_HV) {
        this.list_HV = list_HV;
    }
    
    public List<Integer> getList_HocPhi()
    {
    	return list_HocPhi;
    }
    public void setList_HocPhi(List<Integer> list_HocPhi)
    {
    	this.list_HocPhi = list_HocPhi;
    }
    public List<MONHOC> getList_MH() {
        return list_MH;
    }

    public void setList_MH(List<MONHOC> list_MH) {
        this.list_MH = list_MH;
    }

    public List<GIANGVIEN> getList_GV() {
        return list_GV;
    }

    public void setList_GV(List<GIANGVIEN> list_GV) {
        this.list_GV = list_GV;
    }

    public List<LOPHOC> getList_LH() {
        return list_LH;
    }

    public void setList_LH(List<LOPHOC> list_LH) {
        this.list_LH = list_LH;
    }
}
