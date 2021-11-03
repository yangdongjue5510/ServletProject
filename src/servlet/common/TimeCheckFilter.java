package servlet.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "*.do")
public class TimeCheckFilter implements Filter {

    public TimeCheckFilter() { 
        System.out.println("===> TimeCheckFilter called");
    }
    
	public void init(FilterConfig fConfig) throws ServletException {}                              

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		long startTime = System.currentTimeMillis();

		chain.doFilter(request, response);    
		
		long endTime = System.currentTimeMillis();
		System.out.println(path + " time consumed : " + (endTime - startTime) + "(ms)sec");
	}

	public void destroy() {	}

}
