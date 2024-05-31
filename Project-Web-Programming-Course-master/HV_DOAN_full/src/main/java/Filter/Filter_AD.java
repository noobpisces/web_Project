package Filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Model.ADMIN;
import Model.GIANGVIEN;

@WebFilter(urlPatterns = {"/ADMIN_main","/Add_TK_servlet", "/Add_TKGV_servlet","/ADMIN_GV",
		"/ADMIN_HV","/ADMIN_LH","/deleteGiangVien","/deleteHocVien","/deleteLopHoc",
		"/findGiangVien","/findHocVien","/findLopHoc","/insertGiangVien","/insertHocVien","/insertLopHoc",
		"/updateGiangVien" ,"/updateHocVien" ,"/updateLopHoc"  })
public class Filter_AD extends HttpFilter implements Filter {
    
	private static final long serialVersionUID = 1L;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpSession session = httpRequest.getSession();
		String ad = null;
		ad = (String)session.getAttribute("admin");
    
		if (ad == null )
		{
			response.setContentType("text/html ; charset=UTF-8");
			request.setAttribute("err", "Vui lòng đăng nhập lại. Vì hết session");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/Login.jsp");
			dispatcher.forward(request, response);
		}else
		{
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
