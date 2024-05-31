package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Control.AdminControl;
import Model.LOPHOC;
import conn.DBConnection;

@WebServlet(name="findLopHoc",urlPatterns = { "/findLopHoc" })
public class FindLH_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindLH_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        LOPHOC lh = null;
        String maLop = (String) request.getParameter("maLop");
        try {
            conn = DBConnection.getConnection();
            System.out.println(maLop);
            lh = AdminControl.findLopHoc(conn, maLop);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        if (lh != null) {
            List<LOPHOC> list = new ArrayList<>();
            list.add(lh);
            request.setAttribute("lophocList", list);
        } else {
            String errorString = "Không tìm thấy lớp học !";
            request.setAttribute("errorString", errorString);
        }

        response.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_LH.jsp");
        dispatcher.forward(request, response);
	} */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        int lh =0;
        String maLopHoc=null;
        List<LOPHOC> listLHsearch = new ArrayList<>();
        try {
        	 
            conn = DBConnection.getConnection();
             maLopHoc = (String) request.getParameter("maLopHoc");
            // System.out.println("Ma lop hoc trong tim kiem "+maLopHoc);
            String indexString = request.getParameter("index");
            System.out.println("Index trong tim kiem "+indexString);
            int index = Integer.parseInt(indexString);
            lh = AdminControl.countLH(conn, maLopHoc);
            
          
           int pageSize = 3;
           int endPage = 0;
           
           endPage= lh/ pageSize;
           if(lh % pageSize !=0) {
        	   endPage++;
           }
           
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

        response.setContentType("text/html; charset=UTF-8"); 
        request.setAttribute("maLopHoc", maLopHoc);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_LH.jsp");
        dispatcher.forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
