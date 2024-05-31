package Control;
import Model.LOPHOC;
import Model.GIANGVIEN;
import Model.MONHOC;
import Model.HOCVIEN;
import Data_Structure.MyList;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;

import conn.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HVControl {
	
	public HVControl() {
		// TODO Auto-generated constructor stub
	}
	public Object HV_Login(String taiKhoan, String matKhau) {
	    Connection connection = null;
	    CallableStatement callableStatement = null;
	    ResultSet resultSet = null;
	    HOCVIEN hv = new HOCVIEN();

	    try {
	        String storedProcedure = "{call proc_HV_DN(?, ?)}";
	        connection = DBConnection.getConnection();

	        callableStatement = connection.prepareCall(storedProcedure);
	        callableStatement.setString(1, taiKhoan);
	        callableStatement.setString(2, matKhau);
	        callableStatement.execute();

	        resultSet = callableStatement.getResultSet();
	        if (resultSet != null && resultSet.next()) {
	            String hoTen = resultSet.getString("HoTen");
	            String maKhoa = resultSet.getString("MaKhoa");
	            hv.setMaHocVien(taiKhoan);
	            hv.setMaKhoa(maKhoa);
	            hv.setHoTen(hoTen);
	            return hv; 
	        } else {
	            return "Sai tài khoản hoặc mật khẩu"; 
	        }

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return e.getMessage(); 

	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (callableStatement != null) {
	                callableStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public List<MONHOC> HV_Load_DSMon(String MaKhoa) {
	    List<MONHOC> monHocList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DBConnection.getConnection();
	        String query = "SELECT * FROM fu_load_MonDK(?)";
	        
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, MaKhoa);    
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            String maMon = resultSet.getString("MaMon");
	            String tenMonHoc = resultSet.getString("TenMonHoc");
	            int soTinChi = resultSet.getInt("SoTinChi");
	            
	            MONHOC monHoc = new MONHOC(maMon, tenMonHoc, soTinChi, MaKhoa);
	            monHocList.add(monHoc);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return monHocList;
	}

	public MyList HV_Load_DS_DaDK(String MaHV)
	{
		List<MONHOC> monhocList = new ArrayList<>();
		List<GIANGVIEN> giangvienList = new ArrayList<>();
		List<LOPHOC> lophocList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    MyList mylist = new MyList();
		try {
	        connection = DBConnection.getConnection();
	        String query = "SELECT * FROM v_DSDaDangKi WHERE MaHocVien = ?";
	        
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, MaHV);    
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	String maLop = resultSet.getString("MaLop");
                String tenMonHoc = resultSet.getString("TenMonHoc");
                int soTinChi = resultSet.getInt("SoTinChi");
                String tiet = resultSet.getString("Tiet");
                String thu = resultSet.getString("Thu");
                String phong = resultSet.getString("Phong");
                String giangVien = resultSet.getString("GiangVien");
                String maMon = resultSet.getString("MaMon");
                MONHOC mh = new MONHOC(maMon,tenMonHoc,soTinChi,null);
                monhocList.add(mh);
	        	GIANGVIEN gv = new GIANGVIEN(null,giangVien,null,null,null,null,null,null);
	        	giangvienList.add(gv);
	        	LOPHOC lh = new LOPHOC(maLop,null,null,maMon,null,0,phong,thu,tiet);
	        	lophocList.add(lh);
	        }
	        mylist.setList_MH(monhocList);
	        mylist.setList_LH(lophocList); 
	        mylist.setList_GV(giangvienList); 
	        
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return mylist;

	}
	
	public MyList HV_Load_DSLopDK(String MaMon)
	{
		List<MONHOC> monhocList = new ArrayList<>();
		List<GIANGVIEN> giangvienList = new ArrayList<>();
		List<LOPHOC> lophocList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    MyList mylist = new MyList();
		try {
	        connection = DBConnection.getConnection();
	        String query = "SELECT * FROM fu_load_DSTimKiem(?)";
	        
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, MaMon);    
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	String maLop = resultSet.getString("MaLop");
                String tenMonHoc = resultSet.getString("TenMonHoc");
                int soTinChi = resultSet.getInt("SoTinChi");
                String tiet = resultSet.getString("Tiet");
                String thu = resultSet.getString("Thu");
                String phong = resultSet.getString("Phong");
                String giangVien = resultSet.getString("Giảng viên");
                String maKhoa = resultSet.getString("MaKhoa");
                String maMon = resultSet.getString("MaMon");
                int soHocVienDangKi = resultSet.getInt("SoHocVienDangKy");
                MONHOC mh = new MONHOC(maMon,tenMonHoc,soTinChi,maKhoa);
                monhocList.add(mh);
	        	GIANGVIEN gv = new GIANGVIEN(null,giangVien,null,null,null,null,null,null);
	        	giangvienList.add(gv);
	        	LOPHOC lh = new LOPHOC(maLop,null,null,maMon,null,soHocVienDangKi,phong,thu,tiet);
	        	lophocList.add(lh);
	        }
	        mylist.setList_MH(monhocList);
	        mylist.setList_LH(lophocList); 
	        mylist.setList_GV(giangvienList); 
	        
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return mylist;

	}

	public String HV_DKLop(String MaHV,String MaLop)
	{
		Connection connection = null;
	    CallableStatement callableStatement = null;
	    String result = "Success";
	    try {
	        String storedProcedure = "{call proc_DKLopHoc(?, ?)}";
	        connection = DBConnection.getConnection();

	        callableStatement = connection.prepareCall(storedProcedure);
	        callableStatement.setString(1, MaHV);
	        callableStatement.setString(2, MaLop);
	        boolean success = callableStatement.execute();
	        if (!success) {
	            while (callableStatement.getMoreResults() || callableStatement.getUpdateCount() != -1) {
	                try (ResultSet rs = callableStatement.getResultSet()) {
	                    if (rs != null && rs.next()) {
	                        result = "Error: " + rs.getString(1);
	                    }
	                }
	            }
	        }

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        result =  e.getMessage();

	    } finally {
	        try {
	           
	            if (callableStatement != null) {
	                callableStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}
	public void HV_XoaDKLop(String MaHV,String MaLop)
	{
		Connection connection = null;
	    CallableStatement callableStatement = null;

	    try {
	        String storedProcedure = "{call proc_Xoa_DKLopHoc(?, ?)}";
	        connection = DBConnection.getConnection();

	        callableStatement = connection.prepareCall(storedProcedure);
	        callableStatement.setString(1, MaHV);
	        callableStatement.setString(2, MaLop);
	        callableStatement.execute();

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	           
	            if (callableStatement != null) {
	                callableStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public String HV_ChuyenLop(String MaHV,String MaLopHienTai,String MaLopCanChuyen)
	{
		Connection connection = null;
	    CallableStatement callableStatement = null;
	    String result = "Success";
	    try {
	        String storedProcedure = "{call proc_ChuyenLopHoc(?, ?, ?)}";
	        connection = DBConnection.getConnection();

	        callableStatement = connection.prepareCall(storedProcedure);
	        callableStatement.setString(1, MaHV);
	        callableStatement.setString(2, MaLopHienTai);
	        callableStatement.setString(3, MaLopCanChuyen);
	        boolean success = callableStatement.execute();
	        if (!success) {
	            // Check for additional results (errors or messages)
	            while (callableStatement.getMoreResults() || callableStatement.getUpdateCount() != -1) {
	                try (ResultSet rs = callableStatement.getResultSet()) {
	                    if (rs != null && rs.next()) {
	                        // Process the error message or additional results
	                        result = "Error: " + rs.getString(1);
	                    }
	                }
	            }
	        }

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        result =   e.getMessage();

	    } finally {
	        try {
	           
	            if (callableStatement != null) {
	                callableStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}

	public MyList HV_Load_DSHocPhi(String MaHV,String MaKhoa)
	{
		List<MONHOC> monhocList = new ArrayList<>();
		List<GIANGVIEN> giangvienList = new ArrayList<>();
		List<LOPHOC> lophocList = new ArrayList<>();
		List<Integer> hocphiList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    MyList mylist = new MyList();
		try {
	        connection = DBConnection.getConnection();
	        String query = "SELECT * FROM fu_load_DSHocPhi(?,?)";
	        
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, MaHV);
	        preparedStatement.setString(2, MaKhoa);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	String maLop = resultSet.getString("MaLop");
                String tenMonHoc = resultSet.getString("TenMonHoc");
                int soTinChi = resultSet.getInt("SoTinChi");
                String tiet = resultSet.getString("Tiet");
                String thu = resultSet.getString("Thu");
                String phong = resultSet.getString("Phong");
                String giangVien = resultSet.getString("GiangVien");
                String maMon = resultSet.getString("MaMon");
                MONHOC mh = new MONHOC(maMon,tenMonHoc,soTinChi,null);
                monhocList.add(mh);
	        	GIANGVIEN gv = new GIANGVIEN(null,giangVien,null,null,null,null,null,null);
	        	giangvienList.add(gv);
	        	LOPHOC lh = new LOPHOC(maLop,null,null,maMon,null,0,phong,thu,tiet);
	        	lophocList.add(lh);
	        	int hocphi = resultSet.getInt("HocPhi");
	        	hocphiList.add(hocphi);
	        	
	        }
	        mylist.setList_MH(monhocList);
	        mylist.setList_LH(lophocList); 
	        mylist.setList_GV(giangvienList); 
	        mylist.setList_HocPhi(hocphiList);
	        
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return mylist;

	}

	public int HV_Tong_HP(String MaHV,String MaKhoa)
	{
		Connection connection = null;
		CallableStatement callableStatement = null;
	    int result = 0;
		try {
	        connection = DBConnection.getConnection();
	        String query = "{? = call dbo.fu_TongHocPhi(?, ?)}";
	        
	        callableStatement = connection.prepareCall(query);
	        callableStatement.registerOutParameter(1, java.sql.Types.FLOAT);
	        callableStatement.setString(2, MaHV);
	        callableStatement.setString(3, MaKhoa);
	        callableStatement.execute();
	        result = (int)callableStatement.getFloat(1);
	        
	        
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            
	            if (callableStatement != null) {
	            	callableStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return result ;

	}
}
