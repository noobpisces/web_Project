package Filter;
import java.io.IOException;
import Model.HOCVIEN;
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

/**
 * Servlet Filter implementation class Filter_HV
 */

@WebFilter(urlPatterns = {"/DK_Lop","/DS_Lop","/HV_start","/HV_XoaDK","/TKB","/HV_HocPhi" })

public class Filter_HV extends HttpFilter implements Filter {
       

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		HttpServletRequest httpRequest = (HttpServletRequest) request;

	    HttpSession session = httpRequest.getSession();
	    Object hv = session.getAttribute("HV");
	    
	    if (hv instanceof HOCVIEN)
	    {
	    	chain.doFilter(request, response);
	    }else
	    {
	    	response.setContentType("text/html ; charset=UTF-8");
	    	request.setAttribute("err", "Vui lòng đăng nhập lại");
	        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/Views/Login.jsp");
	        dispatcher.forward(request, response);
	    }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
