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
import Model.GIANGVIEN;
import Model.HOCVIEN;
import conn.DBConnection;

@WebServlet(name="findHocVien",urlPatterns = { "/findHocVien" })
public class FindHV_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public FindHV_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
        HOCVIEN hv = null;
        String maHocVien = (String) request.getParameter("maHocVien");
        try {
            conn = DBConnection.getConnection();
            System.out.println(maHocVien);
            hv = AdminControl.findHocVien(conn, maHocVien);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        if (hv != null) {
            List<HOCVIEN> list = new ArrayList<>();
            list.add(hv);
            request.setAttribute("hocvienList", list);
        } else {
            String errorString = "Không tìm thấy học viên !";
            request.setAttribute("errorString", errorString);
        }

        response.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_HV.jsp");
        dispatcher.forward(request, response);
	} */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;

        int hv =0;
        String maHocVien=null;
        List<HOCVIEN> listHVsearch = new ArrayList<>();
        try {
        	 
            conn = DBConnection.getConnection();
             maHocVien = (String) request.getParameter("maHocVien");
            String indexString = request.getParameter("index");
            int index = Integer.parseInt(indexString);
            hv = AdminControl.countHV(conn, maHocVien);
            
           int pageSize = 3;
           int endPage = 0;
           
           endPage= hv/ pageSize;
           if(hv % pageSize !=0) {
        	   endPage++;
           }
           
           listHVsearch= AdminControl.FindlistHocVien(conn, maHocVien, index, pageSize);
           
           	for(HOCVIEN o : listHVsearch) {
           		System.out.println(o);
           	}
           
           request.setAttribute("end", endPage);
           
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
		if (hv != 0) {
            request.setAttribute("hocvienList", listHVsearch);
        } else {
            String errorString = "Không tìm thấy hoc viên !";
            request.setAttribute("errorString", errorString);
        } 

        response.setContentType("text/html; charset=UTF-8"); 
        request.setAttribute("maHocVien", maHocVien);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_HV.jsp");
        dispatcher.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
