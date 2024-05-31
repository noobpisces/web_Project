package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Control.HVControl;
import Data_Structure.MyList;
import Model.HOCVIEN;
/**
 * Servlet implementation class Xuat_TKB
 */
@WebServlet(urlPatterns = { "/Xuat_TKB" })
public class Xuat_TKB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HVControl hv_c = new HVControl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Xuat_TKB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		HOCVIEN hv = (HOCVIEN)session.getAttribute("HV");
		String MaHV = hv.getMaHocVien();
		MyList DSDaDK = hv_c.HV_Load_DS_DaDK(MaHV);
		// TODO Auto-generated method stub
		 try {
	            Document document = new Document();
	            PdfWriter.getInstance(document, response.getOutputStream());
	            document.open();

	            // Add content to the PDF
	            addTableToPDF(document,DSDaDK);

	            document.close();
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
		 response.sendRedirect(request.getContextPath() + "/TKB" );
	}
	private void addTableToPDF(Document document, MyList DSDaDK) throws DocumentException, UnsupportedEncodingException {
	    try {
	        // Specify the path to the vuArial.ttf file
	        String fontFilePath = "font/vuArial.ttf";

	        // Create a BaseFont using the specified font file
	        BaseFont baseFont = BaseFont.createFont(fontFilePath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

	        // Create a custom font using the BaseFont
	        Font customFont = new Font(baseFont);

	        // Create a table with custom font
	        PdfPTable table = new PdfPTable(6);
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
	        table.addCell(new Phrase("Thứ", customFont));
	        table.addCell(new Phrase("Tiết", customFont));
	        table.addCell(new Phrase("Phòng", customFont));
	        table.addCell(new Phrase("Giảng viên", customFont));

	        // Add table rows
	        for (int i = 0; i < DSDaDK.getList_LH().size(); i++) {
	            table.addCell(new Phrase(DSDaDK.getList_MH().get(i).getTenMonHoc(), customFont));
	            table.addCell(new Phrase(String.valueOf(DSDaDK.getList_MH().get(i).getSoTinChi()), customFont));
	            table.addCell(new Phrase(DSDaDK.getList_LH().get(i).getThu(), customFont));
	            table.addCell(new Phrase(DSDaDK.getList_LH().get(i).getTiet(), customFont));
	            table.addCell(new Phrase(DSDaDK.getList_LH().get(i).getMaPhong(), customFont));
	            table.addCell(new Phrase(DSDaDK.getList_GV().get(i).getHoTen(), customFont));
	        }

	        document.add(table);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}