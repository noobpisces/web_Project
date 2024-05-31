package Servlet;
import Control.Admin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ChangePass
 */

@WebServlet(urlPatterns = { "/ChangePass" })
public class ChangePass extends HttpServlet {
	
	Admin ad = new Admin();
	private static final long serialVersionUID = 1L;       
    public ChangePass() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String err = (String)session.getAttribute("err");
		request.setAttribute("err", err);
		session.invalidate();
		response.setContentType("text/html ; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ChangePass.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u = request.getParameter("username");
		String op = request.getParameter("oldpassword");
		String np = request.getParameter("newpassword");
		String result = ad.ChangePass(u, op, np);
		if(result.equals("Success"))
		{
			request.setAttribute("err", "Đổi mật khẩu thành công");
			response.setContentType("text/html ; charset=UTF-8");
	        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ChangePass.jsp");
	        dispatcher.forward(request, response);
			
		}else
		{
			HttpSession session = request.getSession();	
			session.setAttribute("err", result);
			response.sendRedirect(request.getContextPath() + "/ChangePass");
		}
		
	}

}