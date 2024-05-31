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
import conn.DBConnection;

@WebServlet(urlPatterns = { "/insertGiangVien" })
public class InsertGV_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public InsertGV_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/Views/InsertGV_view.jsp");
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
            	AdminControl.insertGiangVien(conn, gv);
            } 
            catch(SQLException e){
    			e.printStackTrace();
    			errorString = e.getMessage();
    			
    		} 
            
            request.setAttribute("errorString", errorString);

            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/Views/InsertGV_view.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/ADMIN_GV");
            
	}
	}
}
