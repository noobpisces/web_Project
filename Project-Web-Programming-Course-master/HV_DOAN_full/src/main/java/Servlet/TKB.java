package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.HOCVIEN;
import java.io.IOException;
import Control.HVControl;
import Data_Structure.MyList;
/**
 * Servlet implementation class TKB
 */
@WebServlet(urlPatterns = { "/TKB" })
public class TKB extends HttpServlet {
	private HVControl hv_c = new HVControl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		HOCVIEN hv = (HOCVIEN)session.getAttribute("HV");
		String MaHV = hv.getMaHocVien();
		MyList DSDaDK = hv_c.HV_Load_DS_DaDK(MaHV);
		request.setAttribute("DSDaDK", DSDaDK);
		response.setContentType("text/html ; charset=UTF-8");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/HV_TKB.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
