package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(urlPatterns = { "/ADMIN_main" })
public class Admin_mainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_mainServlet() {
        super();
  
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html ; charset=UTF-8");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_main.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
 
        if (action != null) {
            switch (action) {
                case "1":
                	response.setContentType("text/html; charset=UTF-8");
         	        HttpSession session = request.getSession();
         	        response.sendRedirect(request.getContextPath() + "/ADMIN_GV");
                    break;
                case "2":
                	response.setContentType("text/html; charset=UTF-8");
         	        HttpSession session2 = request.getSession();
         	        response.sendRedirect(request.getContextPath() + "/ADMIN_HV");
                    break;
                case "3":
                	response.setContentType("text/html; charset=UTF-8");
         	        HttpSession session3 = request.getSession();
         	        response.sendRedirect(request.getContextPath() + "/ADMIN_LH");
                    break;
                case "4":
                    response.sendRedirect(request.getContextPath() + "/LogOut");
                    break;
            }
        }
    }

}
