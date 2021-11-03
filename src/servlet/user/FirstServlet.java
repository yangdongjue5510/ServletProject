package servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/first.do")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	

	protected void service(HttpServletRequest request, 
			               HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		request.setAttribute("userName", name);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/second.do");
		dispatcher.forward(request, response);
		
		// response.sendRedirect("second.do");
	}
	

}
