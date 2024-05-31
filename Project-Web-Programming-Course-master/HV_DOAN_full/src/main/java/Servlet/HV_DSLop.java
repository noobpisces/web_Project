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

import Control.HVControl;

/**
 * Servlet implementation class HV_DSLop
 */
@WebServlet(urlPatterns = { "/DS_Lop" })
public class HV_DSLop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HVControl hvc = new HVControl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HV_DSLop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String err = (String)session.getAttribute("err");
		session.removeAttribute("err");
		HOCVIEN h = (HOCVIEN)session.getAttribute("HV");
		String MaMon = request.getParameter("MaMon");
		String action = request.getParameter("action");
		MyList DSLopDK = hvc.HV_Load_DSLopDK(MaMon);
		request.setAttribute("DSLopDK", DSLopDK);
		request.setAttribute("HV", h);
		session.setAttribute("MaMon", MaMon);
		response.setContentType("text/html ; charset=UTF-8");
		if ("chuyenlop".equals(action))
		{
			String MaLopHienTai = request.getParameter("from");
			request.setAttribute("button_name","Chuyển lớp");
			request.setAttribute("MaLopHienTai",MaLopHienTai);
			session.setAttribute("MaLopHienTai", MaLopHienTai);
		}
		else
		{
			request.setAttribute("button_name","Đăng ký");
		}
		request.setAttribute("err",err);
		session.setAttribute("action",action);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/DSLop_DK.jsp");
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
