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
import Model.HOCVIEN;
import conn.DBConnection;

@WebServlet(urlPatterns = { "/insertHocVien" })
public class InsertHV_servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertHV_servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/Views/InsertHV_view.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
        }catch(ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
		}
            String maHocVien = (String)request.getParameter("maHocVien");
            String hoTen =new String (request.getParameter("hoTen").getBytes("UTF-8"), "UTF-8");
            String ngaySinhString =  request.getParameter("ngaySinh");
            String diaChi = new String (request.getParameter("diaChi").getBytes("UTF-8"), "UTF-8");
            String queQuan = new String (request.getParameter("queQuan").getBytes("UTF-8"), "UTF-8");
            String maKhoa = (String) request.getParameter("maKhoa");
            
            Date ngaySinh = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Chuyển đổi java.util.Date sang java.sql.Date
            

            // Chuyển đổi chuỗi ngày tháng sang kiểu Date
			try {
				ngaySinh = dateFormat.parse(ngaySinhString);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            java.sql.Date ngaySinhSQL = new java.sql.Date(ngaySinh.getTime()); 
            
            HOCVIEN hv = new HOCVIEN();
            hv.setMaHocVien(maHocVien);
            hv.setHoTen(hoTen);
            hv.setNgaySinh(ngaySinhSQL);
            hv.setDiaChi(diaChi);
            hv.setQueQuan(queQuan);
            hv.setMaKhoa(maKhoa);
            String errorString = null;
            try {
            	AdminControl.insertHocVien(conn, hv);
            } 
            catch(SQLException e){
    			e.printStackTrace();
    			errorString = e.getMessage();
    			
    		} 
            
            request.setAttribute("errorString", errorString);

            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/Views/InsertHV_view.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/ADMIN_HV");
            }
   
    }
}