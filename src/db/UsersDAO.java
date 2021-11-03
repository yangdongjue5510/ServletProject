package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    //SQL Strings
    private final String USERS_INSERT= "insert into users(id, password, name, role)"+
            "values (?, ?, ?, ?);";
    private final String USERS_GET = "select * from users where id = ? and password = ?";;
    private final String USERS_LIST = "select * from users order by name desc";
    private final String USERS_DELETE_BY_ID = "delete from USERS where id = ?";
    private final String USERS_UPDATE_BY_ID = "update USERS set password = ? where id = ?";

    //CRUD
    public void insertUser(UsersVO vo){
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USERS_INSERT);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            stmt.setString(3, vo.getName());
            stmt.setString(4, vo.getRole());
            int count = stmt.executeUpdate();
            System.out.println(count+"건 처리됐습니다.");
            System.out.println("insertUser done.");
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public UsersVO getUser(UsersVO vo) {
        UsersVO user = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USERS_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            rs = stmt.executeQuery();
            if(rs.next()) {
                user = new UsersVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRegDate(rs.getDate("REGDATE"));
                user.setRole(rs.getString("ROLE"));
            }
            System.out.println("getUser done.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return user;
    }

    public List<UsersVO> getUserList(){
        List<UsersVO> usersList = new ArrayList<>();
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USERS_LIST);
            rs = stmt.executeQuery();

            while(rs.next()){
                UsersVO user = new UsersVO();
                user.setId(rs.getString("ID"));
                user.setId(rs.getString("PASSWORD"));
                user.setId(rs.getString("NAME"));
                user.setId(rs.getString("REGDATE"));
                user.setId(rs.getString("ROLE"));
                usersList.add(user);
            }
            System.out.println("getUserList done.");
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,stmt, conn);
        }
        return usersList;
    }

    public void updateUserById(UsersVO vo){
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USERS_UPDATE_BY_ID);
            stmt.setString(1, vo.getPassword());
            stmt.setString(2, vo.getId());

            int count = stmt.executeUpdate();
            System.out.println(count+"건 처리됐습니다.");
            System.out.println("updateUserById done");
        } catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void deleteUserById(UsersVO vo){
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USERS_DELETE_BY_ID);
            stmt.setString(1, vo.getId());
            int count = stmt.executeUpdate();
            System.out.println(count + "건 삭제되었습니다.");
            System.out.println("deleteUserById done.");
        } catch(SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void rsPrint() throws SQLException {
        while(rs.next()) {//커서 이동(첫줄은 데이터가 없는 헤더) 읽을 데이터가 있으면 true 없으면 false
            System.out.print(rs.getString("ID") + " : ");
            System.out.print(rs.getString("PASSWORD") + " : ");
            System.out.print(rs.getString("NAME") + " : ");
            System.out.print(rs.getDate("REGDATE") + " : ");
            System.out.println(rs.getString("ROLE"));
        }
    }
}
