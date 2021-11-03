package db;

import java.sql.*;

public class JDBCUtil {

    public static Connection getConnection()  {
        Connection conn = null;

        try{
            //JDBC 1단계 : 드라이버 로딩
            Driver d = new org.h2.Driver();
            DriverManager.registerDriver(d);
            //JDBC 2단계 : Connection 연결(획득) (고속도로)
//            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

        } catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Statement stmt, Connection conn){
        //JDBC 5단계 : Connection 종료
        try {
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        //JDBC 5단계 : Connection 종료
        if(conn==null) System.out.println(conn);
        if(stmt==null)System.out.println(stmt);
        if(rs==null)System.out.println(rs);
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
