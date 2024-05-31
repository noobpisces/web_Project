package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import Control.AdminControl;
import conn.DBConnection;
import Data_Structure.MyList;
import java.util.List;
import java.util.ResourceBundle.Control;

import Model.GIANGVIEN;
import Model.HOCVIEN;
@WebServlet(name="hocvienList",urlPatterns = {"/ADMIN_HV"})
//@WebServlet(urlPatterns = { "/ADMIN_HV" })
public class ADMIN_HVservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ADMIN_HVservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        //GIANGVIEN gv = null;
        int hv =0;
        String maHocVien=null;
        List<HOCVIEN> listHVsearch = new ArrayList<>();
        try {
        	 
            conn = DBConnection.getConnection();
            maHocVien="HV";
            String indexString = "1";
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
            //List<GIANGVIEN> list = new ArrayList<>();
            //list.add(gv);
			System.out.println("alo alo co truyen qua nha");
            request.setAttribute("hocvienList", listHVsearch);
        } else {
            String errorString = "Không tìm thấy học viên !";
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
