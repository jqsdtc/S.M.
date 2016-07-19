package Util;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @Author: michael
 * @Date: 16-7-19 上午1:23
 * @Project: S.M.
 * @Package: Util
 */
public class SQLConnector {
    public static final String userName = "root";
    public static final String userPassword = "960627Xc!";
    public static final String DriverName = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/booklist?useUnicode=true&characterEncoding=UTF-8";
    public Connection con() {
        Connection con = null;
        try {
            Class.forName(DriverName);
            con = DriverManager.getConnection(URL, userName, userPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public ResultSet qurey (String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = this.con().createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("已取得结果集");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public void update(String sql) throws SQLException {
        try {
            Statement stmt = this.con().createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            throw e;
        }
    }
    public static void closeResultSet(ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
                rs = null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
