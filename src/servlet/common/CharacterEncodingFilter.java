package servlet.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = "*.do", 
           initParams = {@WebInitParam(name = "encoding", value = "EUC-KR"), 
        		         @WebInitParam(name = "message", value = "Hello!!!")})
public class CharacterEncodingFilter implements Filter {
	private String boardEncoding;

    public CharacterEncodingFilter() {
    	System.out.println("===> CharacterEncodingFilter created...");
    }

	public void init(FilterConfig fConfig) throws ServletException {
		boardEncoding = fConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("set CharEncoding : "+boardEncoding);
		request.setCharacterEncoding(boardEncoding);
		
		chain.doFilter(request, response);		
	}

	public void destroy() {}
}
