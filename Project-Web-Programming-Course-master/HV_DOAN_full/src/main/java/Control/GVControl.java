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
public class GVControl {

	public GVControl() {
		// TODO Auto-generated constructor stub
	}
	public Object GV_Login(String taiKhoan, String matKhau) {
	    Connection connection = null;
	    CallableStatement callableStatement = null;
	    ResultSet resultSet = null;
	    GIANGVIEN gv = new GIANGVIEN();

	    try {
	        String storedProcedure = "{call proc_GV_DN(?, ?)}";
	        connection = DBConnection.getConnection();

	        callableStatement = connection.prepareCall(storedProcedure);
	        callableStatement.setString(1, taiKhoan);
	        callableStatement.setString(2, matKhau);
	        callableStatement.execute();

	        resultSet = callableStatement.getResultSet();
	        if (resultSet != null && resultSet.next()) {
	        	String maGV = resultSet.getString("MaGiangVien");
	        	String hocVi = resultSet.getString("HocVi");
	            String hoTen = resultSet.getString("HoTen");
	            String maKhoa = resultSet.getString("MaKhoa");
	            String sdt = resultSet.getString("Sdt");
	            String diaChi = resultSet.getString("DiaChi");
	            gv = new GIANGVIEN(maGV, hoTen, hocVi, null, diaChi, sdt, maKhoa,null);
	            return gv; 
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

	public MyList GV_Load_DSLopDay(String MaGV)
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
	        String query = "SELECT * FROM v_DSLopDay WHERE MaGiangVien = ?";
	        
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, MaGV);    
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	String maLop = resultSet.getString("MaLop");
                String tenMonHoc = resultSet.getString("TenMonHoc");
                String tiet = resultSet.getString("Tiet");
                String thu = resultSet.getString("Thu");
                String phong = resultSet.getString("Phong");
                String maMon = resultSet.getString("MaMon");
                MONHOC mh = new MONHOC(maMon,tenMonHoc,0,null);
                monhocList.add(mh);
	        	GIANGVIEN gv = new GIANGVIEN(MaGV,null,null,null,null,null,null,null);
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
	public MyList GV_Load_DS_HV(String MaLop)
	{

		List<HOCVIEN> hocvienList = new ArrayList<>();
		List<LOPHOC> lophocList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    MyList mylist = new MyList();
		try {
	        connection = DBConnection.getConnection();
	        String query = "SELECT * FROM v_DSHV WHERE MaLop = ?";
	        
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, MaLop);    
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	String maLop = resultSet.getString("MaLop");
	        	String maHocVien = resultSet.getString("MaHocVien");
	        	String hoTen = resultSet.getString("HoTen");
                
                
	        	HOCVIEN hv = new HOCVIEN(maHocVien,hoTen,null,null,null,null,null);
	        	hocvienList.add(hv);
	        	LOPHOC lh = new LOPHOC(maLop,null,null,null,null,0,null,null,null);
	        	lophocList.add(lh);
	        }
	        mylist.setList_HV(hocvienList);
	        mylist.setList_LH(lophocList); 
	        
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

	public String GV_DeXuat(String MaGV, String MaMon, String SoHocVien)
	{
		Connection connection = null;
	    CallableStatement callableStatement = null;
	    String result = "Success";
	    try {
	        String storedProcedure = "{call proc_GuiYeuCau(?, ?, ?)}";
	        connection = DBConnection.getConnection();

	        callableStatement = connection.prepareCall(storedProcedure);
	        callableStatement.setString(1, MaGV);
	        callableStatement.setString(2, MaMon);
	        callableStatement.setString(3, SoHocVien);
	        boolean success = callableStatement.execute();
	        if (!success) {
	            // Check for additional results (errors or messages)
	            while (callableStatement.getMoreResults() || callableStatement.getUpdateCount() != -1) {
	                try (ResultSet rs = callableStatement.getResultSet()) {
	                    if (rs != null && rs.next()) {
	                        // Process the error message or additional results
	                        result = "Error: " + rs.getString(1);
	                        System.out.println(result);
	                    }
	                }
	            }
	        }

	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        result =  "Error: " + e.getMessage();

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
}
