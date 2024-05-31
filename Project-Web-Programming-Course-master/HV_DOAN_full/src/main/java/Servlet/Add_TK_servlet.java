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
import conn.DBConnection;

@WebServlet(urlPatterns = { "/Add_TK_servlet" })
public class Add_TK_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Add_TK_servlet() {
        super();
        // TODO Auto-generated constructor stub0
    }

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String maHocVien = (String) request.getParameter("MaHocVien");
	        request.setAttribute("maHocVien", maHocVien);
	     RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/Views/AddHV_view.jsp");
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
	       
	        String u = (String)request.getParameter("maHocVien");
	        String p = (String)request.getParameter("password");
	      
	        String errorString = null;
            try {
            	
            	AdminControl.taoTaiKhoan(conn, u, p);
            } 
            catch(SQLException e){
    			e.printStackTrace();
    			errorString = e.getMessage();
    			
    		} 
	        
            request.setAttribute("errorString", errorString);

            if (errorString != null) {
            	
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/Views/AddHV_view.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/ADMIN_HV");
            }
	        
	}

}
