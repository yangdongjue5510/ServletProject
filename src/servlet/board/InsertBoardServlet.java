package servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastcampus.biz.board.BoardDAO;
import com.fastcampus.biz.board.BoardVO;

@WebServlet(urlPatterns = "/insertBoard.do")
public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ����� �Է����� ����
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// 2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setTitle(title);   
		vo.setWriter(writer);
		vo.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.insertBoard(vo);
		
		// 3. ȭ�� �׺���̼�
		RequestDispatcher dispatcher = request.getRequestDispatcher("getBoardList.do");
		dispatcher.forward(request, response);
	}

}
