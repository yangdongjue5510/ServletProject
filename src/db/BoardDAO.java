package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Data Access Object : 실질적으로 DB연동을 담당하는 클래스
public class BoardDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String BOARD_INSERT = "insert into board(seq, title, writer, content) " +
            "values ((select nvl(max(seq), 0) +1 from board), "+
            "?, ?, ?)";
    private final String BOARD_DELETE ="delete from board " +
            "where seq = ?;";
    private final String BOARD_UPDATE = "UPDATE board SET title = ?, content = ? where seq = ?";
    private final String BOARD_GET = "select * from board where seq = ?";
    private final String BOARD_LIST = "select * from board order by seq desc";


    //BOARD 관련 CRUD create read update delete
    public BoardVO insertBoard(BoardVO vo){
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());
            int count = stmt.executeUpdate();
            System.out.println(count+"건 처리됐습니다.");
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return vo;
    }

    public void updateBoard(BoardVO vo){
        try{
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(BOARD_UPDATE);

            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.setInt(3, vo.getSeq());
            int count = stmt.executeUpdate();
            System.out.println(count+"건 처리 되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            JDBCUtil.close(stmt, conn);
        }
    }

    public void deleteBoard(BoardVO vo){
        try{
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            int count = stmt.executeUpdate();
            System.out.println(count+"건 처리됐습니다.");
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            //JDBC 5단계 : Connection 종료
            JDBCUtil.close(stmt, conn);
        }
    }

    public BoardVO getBoard(BoardVO vo){
        BoardVO board = new BoardVO();
        try{
            conn = JDBCUtil.getConnection();

            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, vo.getSeq());
            rs = stmt.executeQuery();

            //출력
            while(rs.next()) {//커서 이동(첫줄은 데이터가 없는 헤더) 읽을 데이터가 있으면 true 없으면 false
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setWriter(rs.getString("WRITER"));
                board.setContent(rs.getString("CONTENT"));
                board.setRegDate(rs.getDate("REQDATE"));
                board.setCnt(rs.getInt("CNT"));
            }
            System.out.println("getBoard done.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            JDBCUtil.close(rs, stmt, conn);
        }
        return board;
    }

    public List<BoardVO> getBoardList(BoardVO vo){
        List<BoardVO> boardVOList = new ArrayList<>();
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_LIST);
            rs = stmt.executeQuery();
            //출력
            while(rs.next()) {//커서 이동(첫줄은 데이터가 없는 헤더) 읽을 데이터가 있으면 true 없으면 false
                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setContent(rs.getString("CONTENT"));
                board.setWriter(rs.getString("WRITER"));
                board.setCnt(rs.getInt("CNT"));
                board.setRegDate(rs.getDate("REQDATE"));
                boardVOList.add(board);
            }
            System.out.println("getBoardList done.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            JDBCUtil.close(rs, stmt, conn);
        }
        return boardVOList;
    }
}
