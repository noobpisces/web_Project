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
import conn.DBConnection;

@WebServlet(name="findGiangVien",urlPatterns = { "/findGiangVien" })
public class FindGV_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindGV_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }


 /*   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        GIANGVIEN gv = null;
        String maGiangVien = (String) request.getParameter("maGiangVien");
        try {
            conn = DBConnection.getConnection();
            System.out.println(maGiangVien);
            gv = AdminControl.findGiangVien(conn, maGiangVien);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        if (gv != null) {
            List<GIANGVIEN> list = new ArrayList<>();
            list.add(gv);
            request.setAttribute("giangvienList", list);
        } else {
            String errorString = "Không tìm thấy giảng viên !";
            request.setAttribute("errorString", errorString);
        }

        response.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_GV.jsp");
        dispatcher.forward(request, response);
    }  */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        //GIANGVIEN gv = null;
        int gv =0;
        String maGiangVien=null;
        List<GIANGVIEN> listGVsearch = new ArrayList<>();
        try {
        	 
            conn = DBConnection.getConnection();
             maGiangVien = (String) request.getParameter("maGiangVien");
            String indexString = request.getParameter("index");
            int index = Integer.parseInt(indexString);
            gv = AdminControl.count(conn, maGiangVien);
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

        response.setContentType("text/html; charset=UTF-8"); 
        request.setAttribute("maGiangVien", maGiangVien);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/ADMIN_GV.jsp");
        dispatcher.forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
