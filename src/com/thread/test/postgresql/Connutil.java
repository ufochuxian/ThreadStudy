package com.thread.test.postgresql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connutil {
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://172.18.0.6:5432/postgres";
//            String url = "jdbc:postgresql://81.68.189.100:5080/postgres";
            try {
                System.out.println("尝试连接数据库");
                conn = DriverManager.getConnection(url, "postgres", "tgm2020");
                System.out.println("获取连接成功");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

}