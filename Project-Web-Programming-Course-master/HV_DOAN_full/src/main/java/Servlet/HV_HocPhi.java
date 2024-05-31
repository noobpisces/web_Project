package Servlet;
import Model.HOCVIEN;
import java.io.IOException;
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

import Data_Structure.MyList;

/**
 * Servlet implementation class HV_HocPhi
 */
@WebServlet(urlPatterns = { "/HV_HocPhi" })
public class HV_HocPhi extends HttpServlet {
	private HVControl hv_c = new HVControl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HV_HocPhi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HOCVIEN hv = (HOCVIEN)session.getAttribute("HV");
		String MaHV = hv.getMaHocVien();
		String MaKhoa = hv.getMaKhoa();
		MyList DS_HocPhi = hv_c.HV_Load_DSHocPhi(MaHV, MaKhoa);
		int Tong_HP = hv_c.HV_Tong_HP(MaHV, MaKhoa);
		System.out.println(Tong_HP);
		request.setAttribute("Tong_HocPhi", Tong_HP);
		request.setAttribute("DS_HP", DS_HocPhi);
		session.setAttribute("Tong_HocPhi",Tong_HP);
		response.setContentType("text/html ; charset=UTF-8");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/HV_HocPhi.jsp");
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
