package Servlet;
import Control.HVControl;
import Data_Structure.MyList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import Model.HOCVIEN;

/**
 * Servlet implementation class HV_DKLop
 */
@WebServlet(urlPatterns = { "/DK_Lop" })
public class HV_DKLop extends HttpServlet {
	private HVControl hv_c = new HVControl();
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HV_DKLop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		HOCVIEN h = (HOCVIEN)session.getAttribute("HV");
		String MaMon = (String)session.getAttribute("MaMon");
		String MaLop = request.getParameter("MaLop");
		String action = (String)session.getAttribute("action");
		String result = null;
		String MaLopHienTai = (String)session.getAttribute("MaLopHienTai");
		if (action.equals("chuyenlop"))
		{
			result = hv_c.HV_ChuyenLop(h.getMaHocVien(), MaLopHienTai, MaLop);
		}
		else
		{
			result = hv_c.HV_DKLop(h.getMaHocVien(), MaLop);
		}

		if (result.equals("Success"))
		{
//			response.setContentType("text/html ; charset=UTF-8");
//			response.sendRedirect(request.getContextPath() + "/HV_start");
			if (action.equals("chuyenlop"))
			{
				session.setAttribute("err", "Chuyển lớp thành công");
				response.sendRedirect(request.getContextPath() + "/DS_Lop?MaMon=" +MaMon + "&action=" + action + "&from=" + MaLop);
			}
			else
			{
				session.setAttribute("err", "Đăng ký thành công");
				response.sendRedirect(request.getContextPath() + "/DS_Lop?MaMon=" +MaMon + "&action=" + action);
				
			}
		}
		else
		{
			session.setAttribute("err", result);
			response.setContentType("text/html ; charset=UTF-8");
			
			if (action.equals("chuyenlop"))
			{
				
				
				response.sendRedirect(request.getContextPath() + "/DS_Lop?MaMon=" +MaMon + "&action=" + action + "&from=" + MaLopHienTai);
			}
			else
			{
				response.sendRedirect(request.getContextPath() + "/DS_Lop?MaMon=" +MaMon + "&action=" + action);
				
			}

			
		}
				
		
	}

}
