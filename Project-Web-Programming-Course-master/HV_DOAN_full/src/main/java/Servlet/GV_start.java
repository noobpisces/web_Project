package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import Control.GVControl;
import Data_Structure.MyList;
import Model.HOCVIEN;
import Model.MONHOC;
import Model.GIANGVIEN;
/**
 * Servlet implementation class GV_start
 */
@WebServlet(urlPatterns = { "/GV_start" })
public class GV_start extends HttpServlet {
	GVControl gvc = new GVControl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GV_start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		GIANGVIEN gv = (GIANGVIEN)session.getAttribute("GV");
		MyList DS_LopDay = gvc.GV_Load_DSLopDay(gv.getMaGiangVien());
		session.setAttribute("GV",gv );
		request.setAttribute("giangvien", gv);
		request.setAttribute("DS_LopDay", DS_LopDay);
		response.setContentType("text/html ; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/GV_start.jsp");
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
