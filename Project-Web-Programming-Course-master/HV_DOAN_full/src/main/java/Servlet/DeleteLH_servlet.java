package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import Control.AdminControl;

import conn.DBConnection;

@WebServlet(urlPatterns = { "/deleteLopHoc" })
public class DeleteLH_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteLH_servlet() {
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
		String maLH = request.getParameter("MaLop");

		//String errorString = null;
		String rs = AdminControl.deleteLopHoc(conn,maLH);
		if(rs.equals("Thanhcong")){
			response.sendRedirect(request.getContextPath() + "/ADMIN_LH");
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("ee",rs);
			response.sendRedirect(request.getContextPath() + "/ADMIN_LH");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
