package servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	

	protected void service(HttpServletRequest request, 
			               HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> service() 호출");
		System.out.println("Method: " + request.getMethod());
		System.out.println("URI : " + request.getRequestURI());
		System.out.println("protocol : " + request.getProtocol());
		System.out.println("header(User-Agent) : " + request.getHeader("User-Agent"));
		System.out.println(request.getParameter("id"));		
	}
	

}
