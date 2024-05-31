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
import Model.GIANGVIEN;
import Model.LOPHOC;
import conn.DBConnection;

@WebServlet(urlPatterns = { "/insertLopHoc" })
public class InsertLH_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertLH_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/Views/InsertLH_view.jsp");
	        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        try {
            conn = DBConnection.getConnection();
        }catch(ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
		}
        String maLop = (String) request.getParameter("maLop");
        String tenLop = (String)request.getParameter("tenLop");
        String maGiangVien = (String) request.getParameter("maGiangVien");
        String maMon = (String)request.getParameter("maMon");
        String maHocKy = (String)request.getParameter("maHocKy");
        String soHocVienDangKyStr = request.getParameter("soHocVienDangKy");
        int soHocVienDangKy = 0;
        if (soHocVienDangKyStr != null && !soHocVienDangKyStr.isEmpty()) {
            soHocVienDangKy = Integer.parseInt(soHocVienDangKyStr);
        }
        //int soHocVienDangKy = Integer.parseInt(request.getParameter("soHocVienDangKy "));
        String maPhong = (String)request.getParameter("maPhong");
        String thu = (String)request.getParameter("thu");
        String tiet = (String)request.getParameter("tiet");

        LOPHOC lopHoc = new LOPHOC();
        lopHoc.setMaLop(maLop);
        lopHoc.setTenLop(tenLop);
        lopHoc.setMaGiangVien(maGiangVien);
        lopHoc.setMaMon(maMon);
        lopHoc.setMaHocKy(maHocKy);
        lopHoc.setSoHocVienDangKy(soHocVienDangKy);
        lopHoc.setMaPhong(maPhong);
        lopHoc.setThu(thu);
        lopHoc.setTiet(tiet);
        String result = null;
            try {
            	result = AdminControl.insertLopHoc(conn, lopHoc);
            } 
            catch(SQLException e){
    			e.printStackTrace();
    			result = e.getMessage();
    			
    		} 

        if(result.equals("Success"))
        {
        	response.sendRedirect(request.getContextPath() + "/ADMIN_LH");
        }
        else
        {
        	request.setAttribute("errorString", result);
        	RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/Views/InsertLH_view.jsp");
            dispatcher.forward(request, response);
        }

	}}
