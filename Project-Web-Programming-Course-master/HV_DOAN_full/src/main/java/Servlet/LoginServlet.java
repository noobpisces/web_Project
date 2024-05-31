package Servlet;
import Control.HVControl;
import Control.AdminControl;
import Control.GVControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import Model.HOCVIEN;
import Model.GIANGVIEN;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HVControl hvc = new HVControl();
	private GVControl gvc = new GVControl();
	private AdminControl admincontrol = new AdminControl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String err = (String)session.getAttribute("err");
		session.invalidate();
		request.setAttribute("err", err);
		response.setContentType("text/html ; charset=UTF-8");		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/Login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Who = request.getParameter("Who");
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		if(Who.equals("HV"))
		{
			Object h = hvc.HV_Login(u, p);
		    if (h instanceof HOCVIEN) {
		        // Đăng nhập thành công, chuyển hướng đến trang chính
		    	response.setContentType("text/html ; charset=UTF-8");
		    	HttpSession session = request.getSession();	    	
		    	session.setAttribute("HV",h );
		    	response.sendRedirect(request.getContextPath() + "/HV_start");
		    } 
		    else {
		        // Đăng nhập thất bại, chuyển hướng đến trang login để hiển thị thông báo
//		        response.setContentType("text/html ; charset=UTF-8");
//		        request.setAttribute("err", h.toString()); // Lưu ý: ở đây chúng ta chuyển Exception thành String
//		        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/Login.jsp");
//		        dispatcher.forward(request, response);
		    	HttpSession session = request.getSession();
		    	session.setAttribute("err", h.toString());
		    	response.sendRedirect(request.getContextPath() + "/login");
		    }
		}
		if(Who.equals("GV"))
		{
			Object g = gvc.GV_Login(u, p);
		    if (g instanceof GIANGVIEN) {
		        // Đăng nhập thành công, chuyển hướng đến trang chính
		    	response.setContentType("text/html ; charset=UTF-8");
		    	HttpSession session = request.getSession();	    	
		    	session.setAttribute("GV",g );
		    	response.sendRedirect(request.getContextPath() + "/GV_start");
		    } 
		    else {
		    	// Đăng nhập thất bại, chuyển hướng đến trang login để hiển thị thông báo
		        //response.setContentType("text/html ; charset=UTF-8");
		        //request.setAttribute("err", g.toString()); // Lưu ý: ở đây chúng ta chuyển Exception thành String
		        //RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/Login.jsp");
		        //dispatcher.forward(request, response);
		    	HttpSession session = request.getSession();
		    	session.setAttribute("err", g.toString());
		    	response.sendRedirect(request.getContextPath() + "/login");

		    }
		}
		if(Who.equals("AD"))
		{
			boolean result = false;
			try {
				result = admincontrol.adminDangNhap( u, p);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if (result) {
		        // Đăng nhập thành công, chuyển hướng đến trang chính
		        response.setContentType("text/html; charset=UTF-8");
		        HttpSession session = request.getSession();
		        session.setAttribute("admin", "admin");
		        response.sendRedirect(request.getContextPath() + "/ADMIN_main");
		   
		    } else {
		        // Đăng nhập thất bại, chuyển hướng đến trang đăng nhập để hiển thị thông báo
		    	HttpSession session = request.getSession();
		    	session.setAttribute("err", "Sai Tài khoản hoặc Mật khẩu");
		    	response.sendRedirect(request.getContextPath() + "/login");
		    }
		}
		
	}
 
		
}