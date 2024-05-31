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
import Model.GIANGVIEN;

/**
 * Servlet implementation class GV_DeXuat
 */
@WebServlet(urlPatterns = { "/GV_DeXuat" })
public class GV_DeXuat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GVControl gvc = new GVControl();
       

    public GV_DeXuat() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html ; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/GV_DeXuat.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		GIANGVIEN GV = (GIANGVIEN)session.getAttribute("GV");
		String MaGV = GV.getMaGiangVien();
		String MaMon = request.getParameter("MaMon");
		String SoHocVien = request.getParameter("SoHocVien");
		String result = gvc.GV_DeXuat(MaGV, MaMon, SoHocVien);
		if(result.equals("Success"))
		{
			request.setAttribute("result", "Đề xuất thành công");
		}else
		{
			request.setAttribute("result", "Bạn đã đề xuất mở môn này trước đó hoặc bạn đã nhập sai mã môn");
		}
		response.setContentType("text/html ; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/GV_DeXuat.jsp");
        dispatcher.forward(request, response);
	}

}
