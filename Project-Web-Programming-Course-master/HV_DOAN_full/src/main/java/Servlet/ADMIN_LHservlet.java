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
import Model.LOPHOC;
import conn.DBConnection;

@WebServlet(name="lophocList",urlPatterns = { "/ADMIN_LH" })
public class ADMIN_LHservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ADMIN_LHservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		List<LOPHOC> list = null;

		try {
			conn = DBConnection.getConnection();
			list = admincontrol.listLopHoc(conn);

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		HttpSession session = request.getSession();
		String ee = (String)session.getAttribute("ee");
		request.setAttribute("lophocList", list);
		// Forward sang /WEB-INF/views/productListView.jsp
		
		response.setContentType("text/html ; charset=UTF-8");
		request.setAttribute("errorString", ee);
		session.removeAttribute("ee");
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_LH.jsp") ;
		dispatcher. forward(request, response);
		}
*/	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        int lh =0;
        String maLopHoc=null;
        List<LOPHOC> listLHsearch = new ArrayList<>();
        try {
            conn = DBConnection.getConnection();
            //System.out.println(maLopHoc);
            maLopHoc="GV";
            String indexString = "1";
            int index = Integer.parseInt(indexString);
            lh = AdminControl.countLH(conn, maLopHoc);
    
            System.out.println(lh);
            System.out.println("index neeee: "+index);
            
           int pageSize = 3;
           int endPage = 0;
           
           endPage= lh/ pageSize;
           if(lh % pageSize !=0) {
        	   endPage++;
           }
           System.out.println("Ma Lop Hoc ne: "+maLopHoc);
           
           listLHsearch= AdminControl.FindlistLopHoc(conn, maLopHoc, index, pageSize);
           
           	for(LOPHOC o : listLHsearch) {
           		System.out.println(o);
           	}
           
           request.setAttribute("end", endPage);
           
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
       
		if (lh != 0) {
            //List<GIANGVIEN> list = new ArrayList<>();
            //list.add(gv);
            request.setAttribute("lophocList", listLHsearch);
        } else {
            String errorString = "Không tìm thấy lớp học !";
            request.setAttribute("errorString", errorString);
        } 
		HttpSession session = request.getSession();
		String ee = (String)session.getAttribute("ee");

        response.setContentType("text/html; charset=UTF-8"); 
    	request.setAttribute("errorString", ee);
		session.removeAttribute("ee");
        request.setAttribute("maLopHoc", maLopHoc);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_LH.jsp");
        dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
