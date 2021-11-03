package servlet.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "InsertBoardCharacterEncodingFilter",
        initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class InsertBoardCharacterEncodingFilter implements Filter {
    private String boardEncoding;
    public InsertBoardCharacterEncodingFilter() {
        System.out.println("===> InsertBoardCharacterEncodingFilter created...");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        boardEncoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("set CharEncoding : "+boardEncoding);
        servletRequest.setCharacterEncoding(boardEncoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
