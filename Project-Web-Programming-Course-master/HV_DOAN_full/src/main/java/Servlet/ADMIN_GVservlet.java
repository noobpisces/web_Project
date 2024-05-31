package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Control.AdminControl;
import Model.GIANGVIEN;
import conn.DBConnection;

@WebServlet(name="giangvienList",urlPatterns = { "/ADMIN_GV" })
public class ADMIN_GVservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ADMIN_GVservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 /*  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		List<GIANGVIEN> list = null;

		try {
			conn = DBConnection.getConnection();
			list = admincontrol.listGiangVien(conn);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		HttpSession session = request.getSession();
		String ee = (String)session.getAttribute("ee");
		request.setAttribute("giangvienList", list);

		// Forward sang /WEB-INF/views/productListView.jsp

		response.setContentType("text/html ; charset=UTF-8");
		request.setAttribute("errorString", ee);
		session.removeAttribute("ee");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_GV.jsp") ;

		dispatcher. forward(request, response);
		}  */
    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        int gv =0;
        String maGiangVien=null;
        List<GIANGVIEN> listGVsearch = new ArrayList<>();
        try {
        	 
            conn = DBConnection.getConnection();
           // System.out.println(maGiangVien);
             //maGiangVien = (String) request.getParameter("maGiangVien");
               maGiangVien="GV";
          //  System.out.println(maGiangVien);
            //String indexString = request.getParameter("index");
               String indexString = "1";
            //System.out.println("index neeee: "+indexString);
            int index = Integer.parseInt(indexString);
            //System.out.println("index neeee: "+index);
            gv = AdminControl.count(conn, maGiangVien);
          // System.out.println(gv);
          // System.out.println("index neeee: "+index);
           int pageSize = 3;
           int endPage = 0;
           
           endPage= gv/ pageSize;
           if(gv % pageSize !=0) {
        	   endPage++;
           }
           
           listGVsearch= AdminControl.FindlistGiangVien(conn, maGiangVien, index, pageSize);
           
           	for(GIANGVIEN o : listGVsearch) {
           		System.out.println(o);
           	}
           
           request.setAttribute("end", endPage);
           
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
       
		if (gv != 0) {
            //List<GIANGVIEN> list = new ArrayList<>();
            //list.add(gv);
            request.setAttribute("giangvienList", listGVsearch);
        } else {
            String errorString = "Không tìm thấy giảng viên !";
            request.setAttribute("errorString", errorString);
        } 
		HttpSession session = request.getSession();
		String ee = (String)session.getAttribute("ee");

        response.setContentType("text/html; charset=UTF-8"); 
    	request.setAttribute("errorString", ee);
		session.removeAttribute("ee");
        request.setAttribute("maGiangVien", maGiangVien);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_GV.jsp");
        dispatcher.forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
