package servlet.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "UpdateBoardCharacterEncodingFilter",
        initParams = @WebInitParam(name = "encoding", value = "EUC-KR"))
public class UpdateBoardCharacterEncodingFilter implements Filter {
    private String boardEncoding;
    public UpdateBoardCharacterEncodingFilter() {
        System.out.println("===> UpdateBoardCharacterEncodingFilter created...");
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
