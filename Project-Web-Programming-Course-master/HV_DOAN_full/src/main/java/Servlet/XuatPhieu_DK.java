package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.itextpdf.text.Phrase;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Control.HVControl;
import Data_Structure.MyList;
import Model.HOCVIEN;
/**
 * Servlet implementation class XuatPhieu_DK
 */
@WebServlet(urlPatterns = { "/XuatPhieu_DK" })
public class XuatPhieu_DK extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HVControl hvc = new HVControl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XuatPhieu_DK() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		HOCVIEN hv = (HOCVIEN)session.getAttribute("HV");
		String MaHV = hv.getMaHocVien();
		String MaKhoa = hv.getMaKhoa();
		MyList DS_HocPhi = hvc.HV_Load_DSHocPhi(MaHV, MaKhoa);
		int tongHocPhi = (int)session.getAttribute("Tong_HocPhi");
		// TODO Auto-generated method stub
		 try {
	            Document document = new Document();
	            PdfWriter.getInstance(document, response.getOutputStream());
	            document.open();

	            // Add content to the PDF
	            addTableToPDF(document, DS_HocPhi,tongHocPhi);

	            document.close();
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	}
	
    
	private void addTableToPDF(Document document, MyList DS_HP, int tongHocPhi) throws DocumentException, UnsupportedEncodingException {
	    // Create a custom font
	    try {
	        Font customFont = new Font(BaseFont.createFont("font/vuArial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
	        
	        // Create a table with custom font
	        PdfPTable table = new PdfPTable(5);
	        table.getDefaultCell().setPadding(5);
	        table.setWidthPercentage(100);

	        // Set font for the table
	        table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
	        table.getDefaultCell().setBorder(0);
	        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
	        table.getDefaultCell().setBorderWidthBottom(1);

	        // Add table headers
	        table.addCell(new Phrase("Tên môn", customFont));
	        table.addCell(new Phrase("Số tín chỉ", customFont));
	        table.addCell(new Phrase("Giảng viên", customFont));
	        table.addCell(new Phrase("Mã môn", customFont));
	        table.addCell(new Phrase("Số tiền", customFont));

	        // Add table rows
	        for (int i = 0; i < DS_HP.getList_LH().size(); i++) {
	            table.addCell(new Phrase(DS_HP.getList_MH().get(i).getTenMonHoc(), customFont));
	            table.addCell(new Phrase(String.valueOf(DS_HP.getList_MH().get(i).getSoTinChi()), customFont));
	            table.addCell(new Phrase(DS_HP.getList_GV().get(i).getHoTen(), customFont));
	            table.addCell(new Phrase(DS_HP.getList_LH().get(i).getMaMon(), customFont));
	            table.addCell(new Phrase(String.valueOf(DS_HP.getList_HocPhi().get(i)), customFont));
	        }

	        document.add(table);
	        document.add(new Paragraph("Tổng học phí: " + tongHocPhi, customFont));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

 // ...

 
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}