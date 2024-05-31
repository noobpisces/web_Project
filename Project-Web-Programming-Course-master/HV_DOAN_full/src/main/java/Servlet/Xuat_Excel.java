package Servlet;

import Data_Structure.MyList;
import Control.GVControl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet(urlPatterns = { "/Xuat_Excel" })
public class Xuat_Excel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GVControl gvc = new GVControl();
    public Xuat_Excel() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String MaLop = (String)request.getParameter("MaLop");
    	MyList DS_HV = gvc.GV_Load_DS_HV(MaLop);
    	String filename = "DS_HocVien_"+MaLop;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("HV_Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Stt","Mã lớp", "Mã học viên", "Họ tên"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Populate data rows
        if (DS_HV.getList_HV().size() > 0) {
            for (int i = 0; i < DS_HV.getList_HV().size(); i++) {
                Row row = sheet.createRow(i + 1); // Start from the second row
                row.createCell(0).setCellValue(i+1);
                row.createCell(1).setCellValue(DS_HV.getList_LH().get(i).getMaLop());
                row.createCell(2).setCellValue(DS_HV.getList_HV().get(i).getMaHocVien());
                row.createCell(3).setCellValue(DS_HV.getList_HV().get(i).getHoTen());
            }
        }

        // Write the workbook content to a file
        int co = 0;
        try (FileOutputStream fileOut = new FileOutputStream("D:/" + filename + ".xlsx")) {
            workbook.write(fileOut);
            co = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        workbook.close();
        HttpSession session = request.getSession();
        // Close the workbook to release resources
        if(co == 1)
        {
        	
        	session.setAttribute("err", "Xuất file thành công, file đã được lưu ở ổ D:/");
        	response.sendRedirect(request.getContextPath() +"/GV_DS_HV?MaLop=" + MaLop );
        }else
        {
        	session.setAttribute("err", "Xuất file không thành công");
        	response.sendRedirect(request.getContextPath() +"/GV_DS_HV?MaLop=" + MaLop );
        }
        
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
