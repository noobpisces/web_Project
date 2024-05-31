package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import conn.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import Control.AdminControl;
import Model.HOCVIEN;


@WebServlet(urlPatterns = { "/deleteHocVien" })
public class DeleteHV_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteHV_servlet() {
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
		String maHV = request.getParameter("MaHocVien");
		//String errorString = null;
		String rs = AdminControl.deleteHocVien(conn, maHV);
		if (rs.equals("Thanhcong"))
		{
			response.sendRedirect(request.getContextPath() + "/ADMIN_HV");
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("ee",rs);
			response.sendRedirect(request.getContextPath() + "/ADMIN_HV");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
