package Filter;

import java.io.IOException;

import Model.GIANGVIEN;
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
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/GV_DeXuat","/GV_start","/GV_DS_HV" })
public class Filter_GV extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Lấy HttpSession từ HttpServletRequest
		HttpSession session = httpRequest.getSession();
		Object gv = session.getAttribute("GV");
    
		if (gv instanceof GIANGVIEN)
		{
			chain.doFilter(request, response);
		}else
		{
			response.setContentType("text/html ; charset=UTF-8");
			request.setAttribute("err", "Vui lòng đăng nhập lại. Vì hết session");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/Login.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
