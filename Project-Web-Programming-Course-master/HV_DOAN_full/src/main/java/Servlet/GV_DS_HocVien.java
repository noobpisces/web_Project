package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import Control.GVControl;
import Data_Structure.MyList;
import Model.HOCVIEN;
import Model.MONHOC;
import Model.GIANGVIEN;
/**
 * Servlet implementation class GV_DS_HocVien
 */
@WebServlet(urlPatterns = { "/GV_DS_HV" })
public class GV_DS_HocVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GVControl gvc = new GVControl();

    public GV_DS_HocVien() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String err = (String)session.getAttribute("err");
		session.removeAttribute("err");
		String MaLop = request.getParameter("MaLop");
		MyList ml = gvc.GV_Load_DS_HV(MaLop);
		request.setAttribute("DS_HV", ml);
		request.setAttribute("err",err);
		response.setContentType("text/html ; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/GV_DS_HV.jsp");
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
