package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Control.AdminControl;
import conn.DBConnection;

@WebServlet(urlPatterns = { "/deleteGiangVien" })
public class DeleteGV_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteGV_servlet() {
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
		String maGV = request.getParameter("MaGiangVien");
		//String errorString = null;
		String rs = AdminControl.deleteGiangVien(conn, maGV);
		if (rs.equals("Thanhcong"))
		{
			response.sendRedirect(request.getContextPath() + "/ADMIN_GV");
		}
		else
		{
			//System.out.println("testttttttt");
			HttpSession session = request.getSession();
			session.setAttribute("ee",rs);
			response.sendRedirect(request.getContextPath() + "/ADMIN_GV");
			
		}
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}



		
