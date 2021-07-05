package com.thread.test.postgresql;

import java.sql.*;

public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        DriverManager.setLoginTimeout(8);
        Connection conn = Connutil.getConn();
        System.out.println("获取到数据库连接");
        String sql = "select * from student";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
