package Control;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.text.Normalizer;
import java.util.regex.Pattern;

import Model.GIANGVIEN;
import Model.HOCVIEN;
import Model.LOPHOC;
import conn.DBConnection;




public class AdminControl {


	public AdminControl() {
		
	}
	
	
	
	public static List<HOCVIEN> listHocVien(Connection conn) throws SQLException {
		
		String sql="SELECT * FROM fu_load_HocVien()";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs=pstm.executeQuery();
		
		List<HOCVIEN> list = new ArrayList<HOCVIEN>();
		
		while (rs.next()) {
			 	String maHocVien = rs.getString("MaHocVien");
	         	String hoTen = rs.getString("HoTen");
	            Date ngaySinh = rs.getDate("NgaySinh");
	            String diaChi = rs.getString("DiaChi");
	            String queQuan = rs.getString("QueQuan");
	            String maKhoa = rs.getString("MaKhoa");
	            String matKhau = rs.getString("MatKhau");

			
	            HOCVIEN hv = new HOCVIEN();
				hv.setMaHocVien(maHocVien);
				hv.setHoTen(hoTen);
				hv.setNgaySinh(ngaySinh);
				hv.setDiaChi(diaChi);
				hv.setQueQuan(queQuan);
				hv.setMaKhoa(maKhoa);
				hv.setMatKhau(matKhau);
				list.add(hv);
		}
		
		return list;
	}

	public boolean adminDangNhap(String taiKhoan, String matKhau) throws ClassNotFoundException {
		Connection connection = null;
	    String username = taiKhoan;
	    String password = matKhau;
	    ResultSet rs = null;
	    PreparedStatement stmt = null;
	    
	    try {
	    	connection = DBConnection.getConnection();
	        String query = "SELECT dbo.fu_Check_DangNhap_ADMIN(?, ?)";
	        stmt = connection.prepareStatement(query);
	        stmt.setString(1, username);
	        stmt.setString(2, password);

	        double check = 0;
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            Object result = rs.getObject(1);
	            if (result != null) {
	                check = (Double) result;
	            }
	        }

	        if (check == 1) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	    	try {
	           
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	      
	}
	
	public static String deleteHocVien(Connection conn, String MaHocVien){
		String rs = "Thanhcong";
		try {
			String sql = "{call DeleteHocVien(?)}";
			CallableStatement statement = null;
		    statement = conn.prepareCall(sql);
		    statement.setString(1, MaHocVien);
		    int a =  statement.executeUpdate();
		    if (a == 0)
		    {
		    	while (statement.getMoreResults() || statement.getUpdateCount() != -1) {
	                try (ResultSet res = statement.getResultSet()) {
	                    if (res != null && res.next()) {
	                        rs = "Error: " + res.getString(1);
	                    }
	                }
	            }
		    }
		}catch(SQLException e) {
            // Bắt ngoại lệ SQLException từ trigger
            String errorMessage = "Lỗi khi xóa học viên: " + e.getMessage();
            rs = errorMessage;
        }
	    return rs;				
	}
	
	
	public static void insertHocVien(Connection conn, HOCVIEN hv) throws SQLException {
        String sql = "{call InsertHocVien(?, ?, ?, ?, ?, ?)}"; // Gọi procedure InsertHocVien

        CallableStatement statement = null;
     
        statement = conn.prepareCall(sql);

        statement.setString(1, hv.getMaHocVien());
        statement.setString(2, hv.getHoTen());
        statement.setDate(3, hv.getNgaySinh());
        statement.setString(4, hv.getDiaChi());
        statement.setString(5, hv.getQueQuan());
        statement.setString(6, hv.getMaKhoa());

        statement.executeUpdate();
            
	}
	
	public static HOCVIEN findHocVien(Connection conn, String maHV) throws SQLException {
		/*String sql = "SELECT MaHocVien, HoTen, NgaySinh, DiaChi, QueQuan, MaKhoa, MatKhau\n" +
                "FROM dbo.func_getHocVienByMaHocVien(?)";
		*/
		String sql ="SELECT MaHocVien, HoTen, NgaySinh, DiaChi, QueQuan, MaKhoa, MatKhau\r\n"
				+ "    FROM HOCVIEN LEFT JOIN TAIKHOAN ON MaHocVien = TaiKhoan\r\n"
				+ "    WHERE MaHocVien = ?";
		PreparedStatement pstm = conn.prepareStatement(sql); 
		pstm.setString(1, maHV);
		ResultSet rs = pstm.executeQuery();
		HOCVIEN hv = new HOCVIEN();
		if (rs.next()) {
			String maHocVien = rs.getString("MaHocVien");
         	String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChi = rs.getString("DiaChi");
            String queQuan = rs.getString("QueQuan");
            String maKhoa = rs.getString("MaKhoa");
            String matKhau = rs.getString("MatKhau");
            
			
			
			hv.setMaHocVien(maHocVien);
			hv.setHoTen(hoTen);
			hv.setNgaySinh(ngaySinh);
			hv.setDiaChi(diaChi);
			hv.setQueQuan(queQuan);
			hv.setMaKhoa(maKhoa);
			hv.setMatKhau(matKhau);
			return hv;
		}	
			
		return null;
	} 
	
	
	
	public static void updateHocVien(Connection conn, HOCVIEN hv) throws SQLException {
		String sql = "{call UpdateHocVien(?, ?, ?, ?, ?, ?)}";
        CallableStatement statement = null;
        
        statement = conn.prepareCall(sql);
      
        statement.setString(1, hv.getMaHocVien());
        statement.setString(2, hv.getHoTen());
        statement.setDate(3, hv.getNgaySinh());
        statement.setString(4, hv.getDiaChi());
        statement.setString(5, hv.getQueQuan());
        statement.setString(6, hv.getMaKhoa());
      
        statement.executeUpdate();
	}
   
	
	public static void taoTaiKhoan(Connection conn, String u, String p) throws SQLException {
	    
		String sql = "{call proc_TaoTK(?, ?)}";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, u);
		pstm.setString(2, p);
		pstm.executeUpdate();
	}
	
	
	public static int countHV (Connection conn,String txtSearch) {
		try {
			String sql = null;
			if(txtSearch.length() >= 2 && (txtSearch.substring(0,2).equals("HV")))
			{
				 sql = "select count(*) from HOCVIEN where MaHocVien like ?";
			} else {
				 		sql = "select count(*) from HOCVIEN where HoTen like ?";
		    		}
			 System.out.println("alo trong control "+txtSearch);
			
			 PreparedStatement pstm = conn.prepareStatement(sql);
			 pstm.setString(1, "%"+txtSearch+"%");
			 ResultSet rs = pstm.executeQuery();
			 while(rs.next()) {
				 return rs.getInt(1);
			 }
			 
		}catch(Exception e) {}
		
		return 0;
	}
	
	
	
	public static List<HOCVIEN> FindlistHocVien(Connection conn, String txtSearch, int index, int size) throws SQLException {
	    String sql = null;
	    if (txtSearch.length() >= 2 && txtSearch.substring(0, 2).equals("HV")) {
	        sql = "SELECT *\r\n" +
	            "FROM (\r\n" +
	            "    SELECT HV.MaHocVien, HV.HoTen, HV.NgaySinh, HV.DiaChi, HV.QueQuan, HV.MaKhoa, TK.MatKhau,\r\n" +
	            "           ROW_NUMBER() OVER (ORDER BY HV.MaHocVien) AS RowNum\r\n" +
	            "    FROM HOCVIEN HV\r\n" +
	            "    LEFT JOIN TAIKHOAN TK ON TK.TaiKhoan = HV.MaHocVien\r\n" +
	            "    WHERE HV.MaHocVien COLLATE Vietnamese_CI_AI LIKE N'%' + ? + N'%'\r\n" +
	            ") AS Temp\r\n" +
	            "WHERE RowNum BETWEEN (? - 1) * ? + 1 AND ? * ?;";
	    } else {
	        sql = "SELECT *\r\n" +
	            "FROM (\r\n" +
	            "    SELECT HV.MaHocVien, HV.HoTen, HV.NgaySinh, HV.DiaChi, HV.QueQuan, HV.MaKhoa, TK.MatKhau,\r\n" +
	            "           ROW_NUMBER() OVER (ORDER BY HV.MaHocVien) AS RowNum\r\n" +
	            "    FROM HOCVIEN HV\r\n" +
	            "    LEFT JOIN TAIKHOAN TK ON TK.TaiKhoan = HV.MaHocVien\r\n" +
	            "    WHERE HV.HoTen COLLATE Vietnamese_CI_AI LIKE N'%' + ? + N'%'\r\n" +
	            ") AS Temp\r\n" +
	            "WHERE RowNum BETWEEN (? - 1) * ? + 1 AND ? * ?;";
	    }
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, txtSearch);
	    pstm.setInt(2, index);
	    pstm.setInt(3, size);
	    pstm.setInt(4, index);
	    pstm.setInt(5, size);

	    ResultSet rs = pstm.executeQuery();

	    List<HOCVIEN> list = new ArrayList<HOCVIEN>();

	    while (rs.next()) {
	    	String maHocVien = rs.getString("MaHocVien");
         	String hoTen = rs.getString("HoTen");
            Date ngaySinh = rs.getDate("NgaySinh");
            String diaChi = rs.getString("DiaChi");
            String queQuan = rs.getString("QueQuan");
            String maKhoa = rs.getString("MaKhoa");
            String matKhau = rs.getString("MatKhau");
            
			
            HOCVIEN hv = new HOCVIEN();
			hv.setMaHocVien(maHocVien);
			hv.setHoTen(hoTen);
			hv.setNgaySinh(ngaySinh);
			hv.setDiaChi(diaChi);
			hv.setQueQuan(queQuan);
			hv.setMaKhoa(maKhoa);
			hv.setMatKhau(matKhau);
	        list.add(hv);
	    }

	    return list;
	}
	
	//GIẢNG VIÊN
	
	

	
	public static List<GIANGVIEN> listGiangVien(Connection conn) throws SQLException {
		
		String sql="SELECT * FROM fu_load_GV()";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs=pstm.executeQuery();
		
		List<GIANGVIEN> list = new ArrayList<GIANGVIEN>();
		
		while (rs.next()) {
			 	String maGiangVien = rs.getString("MaGiangVien");
	         	String hoTen = rs.getString("HoTen");
	            String hocVi = rs.getString("HocVi");
	            String hocHam = rs.getString("HocHam");
	            String diaChi = rs.getString("Diachi");
	            String sdt = rs.getString("Sdt");
	            String maKhoa = rs.getString("MaKhoa");
	            String matKhau = rs.getString("MatKhau");

			
	            GIANGVIEN gv = new GIANGVIEN();
	            gv.setMaGiangVien(maGiangVien);
	            gv.setHoTen(hoTen);
				gv.setHocVi(hocVi);
				gv.setHocHam(hocHam);
				gv.setDiaChi(diaChi);
				gv.setSdt(sdt);
				gv.setMaKhoa(maKhoa);
				gv.setMatKhau(matKhau);
				list.add(gv);
		}
		
		return list;
	}  
	public static String deleteGiangVien(Connection conn, String MaGiangVien)   {
		String rs = "Thanhcong";
		try {
		String sql = "{call DeleteGiangVien(?)}";
		CallableStatement statement = null;
	    statement = conn.prepareCall(sql);
	    statement.setString(1, MaGiangVien);
	    int a =  statement.executeUpdate();
	    if (a == 0)
	    {
	    	while (statement.getMoreResults() || statement.getUpdateCount() != -1) {
                try (ResultSet res = statement.getResultSet()) {
                    if (res != null && res.next()) {
                        rs = "Error: " + res.getString(1);
                    }
                }
            }
	    }
	    }catch(SQLException e) {
            // Bắt ngoại lệ SQLException từ trigger
            String errorMessage = "Lỗi khi xóa giảng viên: " + e.getMessage();
            rs = errorMessage;
        }
	    return rs;
	}
	
	
	
	public static void insertGiangVien(Connection conn, GIANGVIEN gv) throws SQLException {
        String sql = "{call InsertGiangVien(?, ?, ?, ?, ?, ?,?)}"; // Gọi procedure InsertHocVien

        CallableStatement statement = null;
     
        statement = conn.prepareCall(sql);

        statement.setString(1, gv.getMaGiangVien());
        statement.setString(2, gv.getHoTen());
        statement.setString(3, gv.getHocVi());
        statement.setString(4, gv.getHocHam());
        statement.setString(5, gv.getDiaChi());
        statement.setString(6, gv.getSdt());
        statement.setString(7, gv.getMaKhoa());

        statement.executeUpdate();
            
	}
	
	
	public static GIANGVIEN findGiangVien(Connection conn, String maGV) throws SQLException {
	    String sql = "SELECT MaGiangVien, HoTen, HocVi, HocHam, DiaChi, SDT, MaKhoa, MatKhau " +
	                 "FROM GIANGVIEN LEFT JOIN TAIKHOAN ON MaGiangVien = TaiKhoan " +
	                 "WHERE MaGiangVien = ?";
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, maGV);
	    ResultSet rs = pstm.executeQuery();
	    GIANGVIEN gv = new GIANGVIEN();
	    if (rs.next()) {
	        String maGiangVien = rs.getString("MaGiangVien");
	        String hoTen = rs.getString("HoTen");
	        String hocVi = rs.getString("HocVi");
	        String hocHam = rs.getString("HocHam");
	        String diaChi = rs.getString("DiaChi");
	        String sdt = rs.getString("SDT");
	        String maKhoa = rs.getString("MaKhoa");
	        String matKhau = rs.getString("MatKhau");

	        gv.setMaGiangVien(maGiangVien);
	        gv.setHoTen(hoTen);
	        gv.setHocVi(hocVi);
	        gv.setHocHam(hocHam);
	        gv.setDiaChi(diaChi);
	        gv.setSdt(sdt);
	        gv.setMaKhoa(maKhoa);
	        gv.setMatKhau(matKhau);
	        return gv;
	    }
	   return null;
	}
	
	public static void UpdateGiangVien(Connection conn, GIANGVIEN gv) throws SQLException {
		String sql = "{call UpdateGiangVien(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement statement = null;
        
        statement = conn.prepareCall(sql);
      
        statement.setString(1, gv.getMaGiangVien());
        statement.setString(2, gv.getHoTen());
        statement.setString(3, gv.getHocVi());
        statement.setString(4, gv.getHocHam());
        statement.setString(5, gv.getDiaChi());
        statement.setString(6, gv.getSdt());
        statement.setString(7, gv.getMaKhoa());
        statement.executeUpdate();
	}

	public static int count (Connection conn,String txtSearch) {
		try {
			String sql = null;
			if(txtSearch.length() >= 2 && (txtSearch.substring(0,2).equals("GV")))
			{
				 sql = "select count(*) from GIANGVIEN where MaGiangVien like ?";
			} else {
				 		sql = "select count(*) from GIANGVIEN where HoTen like ?";
		    		}
			 System.out.println("alo trong control "+txtSearch);
			
			 PreparedStatement pstm = conn.prepareStatement(sql);
			 pstm.setString(1, "%"+txtSearch+"%");
			 ResultSet rs = pstm.executeQuery();
			 while(rs.next()) {
				 return rs.getInt(1);
			 }
			 
		}catch(Exception e) {}
		
		return 0;
	}
	
	public static List<GIANGVIEN> FindlistGiangVien(Connection conn, String txtSearch, int index, int size) throws SQLException {
	    
		String sql =null;
		if(txtSearch.length() >= 2 && (txtSearch.substring(0,2).equals("GV")))
			{
			 sql = "SELECT *\r\n"
		    		+ "FROM (\r\n"
		    		+ "    SELECT GV.MaGiangVien, GV.HoTen, GV.HocVi, GV.HocHam, GV.Diachi, GV.Sdt, GV.MaKhoa, TK.MatKhau,\r\n"
		    		+ "           ROW_NUMBER() OVER (ORDER BY GV.MaGiangVien) AS RowNum\r\n"
		    		+ "    FROM GIANGVIEN GV\r\n"
		    		+ "    LEFT JOIN TAIKHOAN TK ON TK.TaiKhoan = GV.MaGiangVien\r\n"
		    		+ "    WHERE GV.MaGiangVien COLLATE Vietnamese_CI_AI LIKE N'%' + ? + N'%'\r\n"
		    		+ ") AS Temp\r\n"
		    		+ "WHERE RowNum BETWEEN (? - 1) * ? + 1 AND ? * ?;";
			} else {
			 sql = "SELECT *\r\n"
		    		+ "FROM (\r\n"
		    		+ "    SELECT GV.MaGiangVien, GV.HoTen, GV.HocVi, GV.HocHam, GV.Diachi, GV.Sdt, GV.MaKhoa, TK.MatKhau,\r\n"
		    		+ "           ROW_NUMBER() OVER (ORDER BY GV.MaGiangVien) AS RowNum\r\n"
		    		+ "    FROM GIANGVIEN GV\r\n"
		    		+ "    LEFT JOIN TAIKHOAN TK ON TK.TaiKhoan = GV.MaGiangVien\r\n"
		    		+ "    WHERE GV.HoTen COLLATE Vietnamese_CI_AI LIKE N'%' + ? + N'%'\r\n"
		    		+ ") AS Temp\r\n"
		    		+ "WHERE RowNum BETWEEN (? - 1) * ? + 1 AND ? * ?;";
		    		}
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, txtSearch);
	    pstm.setInt(2, index);
	    pstm.setInt(3, size);
	    pstm.setInt(4, index);
	    pstm.setInt(5, size);

	    ResultSet rs = pstm.executeQuery();

	    List<GIANGVIEN> list = new ArrayList<GIANGVIEN>();

	    while (rs.next()) {
	        String maGiangVien = rs.getString("MaGiangVien");
	        String hoTen = rs.getString("HoTen");
	        String hocVi = rs.getString("HocVi");
	        String hocHam = rs.getString("HocHam");
	        String diaChi = rs.getString("Diachi");
	        String sdt = rs.getString("Sdt");
	        String maKhoa = rs.getString("MaKhoa");
	        String matKhau = rs.getString("MatKhau");

	        GIANGVIEN gv = new GIANGVIEN();
	        gv.setMaGiangVien(maGiangVien);
	        gv.setHoTen(hoTen);
	        gv.setHocVi(hocVi);
	        gv.setHocHam(hocHam);
	        gv.setDiaChi(diaChi);
	        gv.setSdt(sdt);
	        gv.setMaKhoa(maKhoa);
	        gv.setMatKhau(matKhau);
	        list.add(gv);
	    }
	    System.out.println("list trong control "+list);
	    return list;
	}
	//LỚP HỌC
	
public static List<LOPHOC> listLopHoc(Connection conn) throws SQLException {
		
		String sql="SELECT * FROM DanhSachLopHoc()";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs=pstm.executeQuery();
		
		List<LOPHOC> list = new ArrayList<LOPHOC>();
		
		while (rs.next()) {
			 	String maLop = rs.getString("MaLop");
	         	String tenLop = rs.getString("TenLop");
	            String maGiangVien = rs.getString("MaGiangVien");
	            String maMon = rs.getString("MaMon");
	            String maHocKy = rs.getString("MaHocKy");
	            int soHocVienDangKy = rs.getInt("SoHocVienDangKy");
	            String maPhong = rs.getString("MaPhong");
	            String thu = rs.getString("Thu");
	            String tiet = rs.getString("Tiet");
			
	            LOPHOC lh= new LOPHOC();
	            lh.setMaLop(maLop);
	            lh.setTenLop(tenLop);
	            lh.setMaGiangVien(maGiangVien);
	            lh.setMaMon(maMon);
	            lh.setMaHocKy(maHocKy);
	            lh.setSoHocVienDangKy(soHocVienDangKy);
	            lh.setMaPhong(maPhong);
	            lh.setThu(thu);
	            lh.setTiet(tiet);

	            list.add(lh);
		}
		
		return list;
	}


	public static String deleteLopHoc(Connection conn, String MaLop) {
		String rs = "Thanhcong";
		try {
			String sql = "{call XoaLopHoc(?)}";
			CallableStatement statement = null;
			statement = conn.prepareCall(sql);
			statement.setString(1, MaLop);
	    int a =  statement.executeUpdate();
	    if (a == 0)
	    {
	    	while (statement.getMoreResults() || statement.getUpdateCount() != -1) {
                try (ResultSet res = statement.getResultSet()) {
                    if (res != null && res.next()) {
                        rs = "Error: " + res.getString(1);
                    }
                }
            }
	    }
	    }catch(SQLException e) {
            // Bắt ngoại lệ SQLException từ trigger
            String errorMessage = "Lỗi khi xóa lớp học: " + e.getMessage();
            rs = errorMessage;
        }
	    return rs;
   }
	

	
	public static String insertLopHoc(Connection conn, LOPHOC lopHoc) throws SQLException {
	    String sql = "{call ThemLopHoc(?, ?, ?, ?, ?, ?, ?, ?, ?)}"; // Gọi stored procedure ThemLopHoc
	    CallableStatement statement = null;
	    statement = conn.prepareCall(sql);
	        statement.setString(1, lopHoc.getMaLop());
	        statement.setString(2, lopHoc.getTenLop());
	        statement.setString(3, lopHoc.getMaGiangVien());
	        statement.setString(4, lopHoc.getMaMon());
	        statement.setString(5, lopHoc.getMaHocKy());
	        statement.setInt(6, lopHoc.getSoHocVienDangKy());
	        statement.setString(7, lopHoc.getMaPhong());
	        statement.setString(8, lopHoc.getThu());
	        statement.setString(9, lopHoc.getTiet());
	        String result = "Success";
//	        statement.executeUpdate();
	        boolean success = statement.execute();
	        if (!success) {
	            while (statement.getMoreResults() || statement.getUpdateCount() != -1) {
	                try (ResultSet rs = statement.getResultSet()) {
	                    if (rs != null && rs.next()) {
	                        result = "Error: " + rs.getString(1);
	                    }
	                }
	            }
	        }
	  return result;
	}
	
	
	public static LOPHOC findLopHoc(Connection conn, String ml) throws SQLException {
	    String sql = "SELECT * FROM LayThongTinLopHocPhan(?)";
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, ml);
	    ResultSet rs = pstm.executeQuery();
	    LOPHOC lh = new LOPHOC();
	    if (rs.next()) {
	        String maLop = rs.getString("MaLop");
	        String tenLop = rs.getString("TenLop");
	        String maGiangVien = rs.getString("MaGiangVien");
	        String maMon = rs.getString("MaMon");
	        String maHocKy = rs.getString("MaHocKy");
	        int soHocVienDangKy = rs.getInt("SoHocVienDangKy");
	        String maPhong = rs.getString("MaPhong");
	        String Thu = rs.getString("Thu");
	        String Tiet = rs.getString("Tiet");

	        lh.setMaLop(maLop);
	        lh.setTenLop(tenLop);
	        lh.setMaGiangVien(maGiangVien);
	        lh.setMaMon(maMon);
	        lh.setMaHocKy(maHocKy );
	        lh.setSoHocVienDangKy(soHocVienDangKy);
	        lh.setMaPhong(maPhong);
	        lh.setThu(Thu);
	        lh.setTiet(Tiet);
	        return lh;
	    }
	    return null;
	}
	
	
	public static void updateLopHoc(Connection conn, LOPHOC lh) throws SQLException {
	    String sql = "{call UpdateLopHoc(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	    CallableStatement statement = null;
	    
	    statement = conn.prepareCall(sql);
	  
	    statement.setString(1, lh.getMaLop());
	    statement.setString(2, lh.getTenLop());
	    statement.setString(3, lh.getMaGiangVien());
	    statement.setString(4, lh.getMaMon());
	    statement.setString(5, lh.getMaHocKy());
	    statement.setInt(6, lh.getSoHocVienDangKy());
	    statement.setString(7, lh.getMaPhong());
	    statement.setString(8, lh.getThu());
	    statement.setString(9, lh.getTiet());
	    
	    statement.executeUpdate();
	}
	public static boolean containsAccent(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{M}");
        return pattern.matcher(normalized).find();
    }

	public static int countLH (Connection conn,String txtSearch) {
		try {
			String sql = null;
			if(txtSearch.length() >= 2 && (txtSearch.substring(0,2).equals("GV")))
			{
				 sql = "select count(*) from LOPHOC where MaGiangVien like ?";
			} else if(containsAccent(txtSearch)) {
				 		sql = "select count(*) from LOPHOC where TenLop like ?";
		    		} else {sql = "select count(*) from LOPHOC where Malop like ?";}
			 System.out.println("alo trong control "+txtSearch);
			
			 PreparedStatement pstm = conn.prepareStatement(sql);
			 pstm.setString(1, "%"+txtSearch+"%");
			 ResultSet rs = pstm.executeQuery();
			 while(rs.next()) {
				 return rs.getInt(1);
			 }
			 
		}catch(Exception e) {}
		
		return 0;
	}
	
public static List<LOPHOC> FindlistLopHoc(Connection conn, String txtSearch, int index, int size) throws SQLException {
	    
		String sql =null;
		System.out.println("txtsearch toi control "+txtSearch);
		System.out.println("index toi control "+index);
		if(txtSearch.length() >= 2 && (txtSearch.substring(0,2).equals("GV")))
			{
			System.out.println("Da chay vo day ");
			 sql = "SELECT *\r\n"
			 		+ "FROM (\r\n"
			 		+ "    SELECT *,\r\n"
			 		+ "           ROW_NUMBER() OVER (ORDER BY MaGiangVien) AS RowNum\r\n"
			 		+ "    FROM LOPHOC\r\n"
			 		+ "    WHERE MaGiangVien COLLATE Vietnamese_CI_AI LIKE N'%' + ? + N'%'\r\n"
			 		+ ") AS Temp\r\n"
			 		+ "WHERE RowNum BETWEEN (?- 1) * ? + 1 AND ? * ?;";
			} else if(containsAccent(txtSearch)) {
				sql = "SELECT *\r\n"
						+ "FROM (\r\n"
						+ "    SELECT *,\r\n"
						+ "           ROW_NUMBER() OVER (ORDER BY MaLop) AS RowNum\r\n"
						+ "    FROM LOPHOC\r\n"
						+ "    WHERE TenLop COLLATE Vietnamese_CI_AI LIKE N'%' + ? + N'%'\r\n"
						+ ") AS Temp\r\n"
						+ "WHERE RowNum BETWEEN (? - 1) * ? + 1 AND ? * ?;";
			 
		    		} else {sql = "SELECT *\r\n"
					 		+ "FROM (\r\n"
					 		+ "    SELECT *,\r\n"
					 		+ "           ROW_NUMBER() OVER (ORDER BY MaLop) AS RowNum\r\n"
					 		+ "    FROM LOPHOC\r\n"
					 		+ "    WHERE MaLop COLLATE Vietnamese_CI_AI LIKE N'%' + ? + N'%'\r\n"
					 		+ ") AS Temp\r\n"
					 		+ "WHERE RowNum BETWEEN (? - 1) * ? + 1 AND ? * ?;";}
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, txtSearch);
	    pstm.setInt(2, index);
	    pstm.setInt(3, size);
	    pstm.setInt(4, index);
	    pstm.setInt(5, size); 
	    ResultSet rs = pstm.executeQuery();

	    List<LOPHOC> list = new ArrayList<LOPHOC>();

	    while (rs.next()) {
		 	String maLop = rs.getString("MaLop");
         	String tenLop = rs.getString("TenLop");
            String maGiangVien = rs.getString("MaGiangVien");
            String maMon = rs.getString("MaMon");
            String maHocKy = rs.getString("MaHocKy");
            int soHocVienDangKy = rs.getInt("SoHocVienDangKy");
            String maPhong = rs.getString("MaPhong");
            String thu = rs.getString("Thu");
            String tiet = rs.getString("Tiet");
		
            LOPHOC lh= new LOPHOC();
            lh.setMaLop(maLop);
            lh.setTenLop(tenLop);
            lh.setMaGiangVien(maGiangVien);
            lh.setMaMon(maMon);
            lh.setMaHocKy(maHocKy);
            lh.setSoHocVienDangKy(soHocVienDangKy);
            lh.setMaPhong(maPhong);
            lh.setThu(thu);
            lh.setTiet(tiet);

            list.add(lh);
	}
	System.out.println("list trong control "+list);
	return list;
	}
}

