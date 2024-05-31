package Servlet;
import Data_Structure.MyList;
import Model.HOCVIEN;
import Model.MONHOC;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import Control.HVControl;

/**
 * Servlet implementation class HV_web_start
 */
@WebServlet(urlPatterns = { "/HV_start" })
public class HV_web_start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HVControl hvc = new HVControl();
    public HV_web_start() {
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
		
		session.setAttribute("HV",h );
		List<MONHOC> l_M = hvc.HV_Load_DSMon(h.getMaKhoa()); 
		MyList DSDaDK = hvc.HV_Load_DS_DaDK(h.getMaHocVien());
		request.setAttribute("HV", h);
		request.setAttribute("DS_MON", l_M);
		request.setAttribute("DSDaDK", DSDaDK);
		response.setContentType("text/html ; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/HV_start.jsp");
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
