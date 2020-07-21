package DAO;

import java.sql.*;

public class DBUtil {

    public static Connection connect(String dbName) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8",
                    "root",
                    "coderslab");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}