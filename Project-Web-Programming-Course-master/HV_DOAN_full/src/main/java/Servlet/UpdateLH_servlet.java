package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import Control.AdminControl;
import Model.LOPHOC;
import conn.DBConnection;

@WebServlet(urlPatterns = { "/updateLopHoc" })
public class UpdateLH_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateLH_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			
			conn = DBConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String maLop = (String) request.getParameter("MaLop");
		
		LOPHOC lh = null;
		String errorString = null;
		
		try {
			lh= AdminControl.findLopHoc(conn, maLop);

		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();

		}
		if (errorString != null && lh == null) {
			response.sendRedirect(request.getContextPath() + "/ADMIN_LH");
			return;
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("lophoc", lh);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/Views/UpdateLH_view.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String maLop =  (String)request.getParameter("maLop");
	     String tenLop = (String) request.getParameter("tenLop");
	     String maGiangVien = (String)request.getParameter("maGiangVien");
	     String maMon = (String) request.getParameter("maMon");
	     String maHocKy = (String)request.getParameter("maHocKy");
	     //int soHocVienDangKy = Integer.parseInt(request.getParameter("SoHocVienDangKy"));
	     
	     String soHocVienDangKyString = request.getParameter("soHocVienDangKy");
	     int soHocVienDangKy = 0; // Giá trị mặc định nếu không thể chuyển đổi chuỗi thành số nguyên

	     if (soHocVienDangKyString != null && !soHocVienDangKyString.isEmpty()) {
	         try {
	             soHocVienDangKy = Integer.parseInt(soHocVienDangKyString);
	         } catch (NumberFormatException e) {
	             // Xử lý lỗi khi chuỗi không chuyển đổi thành số nguyên hợp lệ
	             // Ví dụ: Ghi log, thông báo lỗi cho người dùng, ...
	             e.printStackTrace();
	         }
	     }
	     String maPhong = (String)request.getParameter("maPhong");
	     String Thu = (String)request.getParameter("thu");
	     String Tiet = (String)request.getParameter("tiet");
	        
	     
	     LOPHOC lh = new LOPHOC();
	     
	     lh.setMaLop(maLop);
	     lh.setTenLop(tenLop);
	     lh.setMaGiangVien(maGiangVien);
	     lh.setMaMon(maMon);
	     lh.setMaHocKy(maHocKy );
	     lh.setSoHocVienDangKy(soHocVienDangKy);
	     lh.setMaPhong(maPhong);
	     lh.setThu(Thu);
	     lh.setTiet(Tiet);
	     String errorString = null;
	        
	        try {
	        	AdminControl.updateLopHoc(conn, lh);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();	
			}
	        request.setAttribute("errorString", errorString);
			request.setAttribute("lophoc", lh);
			if (errorString != null) {
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/Views/UpdateLH_view.jsp");
				dispatcher.forward(request, response);

			} else {
				response.sendRedirect(request.getContextPath() + "/ADMIN_LH");
			}
	}

}
