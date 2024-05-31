package Servlet;
import Control.HVControl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Model.HOCVIEN;

/**
 * Servlet implementation class HV_XoaDKLop
 */
@WebServlet(urlPatterns = { "/HV_XoaDK" })
public class HV_XoaDKLop extends HttpServlet {
	private HVControl hv_c = new HVControl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HV_XoaDKLop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HOCVIEN h = (HOCVIEN)session.getAttribute("HV");
		String MaLop = request.getParameter("MaLop");
		hv_c.HV_XoaDKLop(h.getMaHocVien(), MaLop);
		session.setAttribute("HV",h );
		response.setContentType("text/html ; charset=UTF-8");
		response.sendRedirect(request.getContextPath() + "/HV_start");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
