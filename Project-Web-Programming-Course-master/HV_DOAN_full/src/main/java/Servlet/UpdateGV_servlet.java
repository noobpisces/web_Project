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
import java.text.SimpleDateFormat;
import java.util.Date;

import Control.AdminControl;
import Model.GIANGVIEN;
import Model.HOCVIEN;
import conn.DBConnection;

@WebServlet(urlPatterns = { "/updateGiangVien" })
public class UpdateGV_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UpdateGV_servlet() {
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
		
		String maGiangVien = (String) request.getParameter("MaGiangVien");
		
		GIANGVIEN gv = null;
		String errorString = null;
		
		try {
			 System.out.println(maGiangVien);
			gv= AdminControl.findGiangVien(conn, maGiangVien);

		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();

		}
		if (errorString != null && gv == null) {
			response.sendRedirect(request.getContextPath() + "/ADMIN_GV");
			return;
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("giangvien", gv);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/Views/UpdateGV_view.jsp");
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
		
		String maGiangVien = (String)request.getParameter("maGiangVien");
        String hoTen =new String (request.getParameter("hoTen").getBytes("UTF-8"), "UTF-8");
        String hocVi = (String) request.getParameter("hocVi");
        String hocHam = (String) request.getParameter("hocHam");
        String diaChi = new String (request.getParameter("diaChi").getBytes("UTF-8"), "UTF-8");
        String sdt = new String (request.getParameter("sdt").getBytes("UTF-8"), "UTF-8");
        String maKhoa = (String) request.getParameter("maKhoa");
        
    
        
        GIANGVIEN gv = new GIANGVIEN();
        gv.setMaGiangVien(maGiangVien);
        gv.setHoTen(hoTen);
        gv.setHocHam(hocHam);
        gv.setHocVi(hocVi);
        gv.setDiaChi(diaChi);
        gv.setSdt(sdt);
        gv.setMaKhoa(maKhoa);
        String errorString = null;
        
        try {
        	AdminControl.UpdateGiangVien(conn, gv);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();	
		}
        request.setAttribute("errorString", errorString);
		request.setAttribute("giangvien", gv);
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/Views/UpdateGV_view.jsp");
			dispatcher.forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/ADMIN_GV");
		}
	}

}
