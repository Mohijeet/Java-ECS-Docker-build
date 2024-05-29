package com.mastek.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    static final String jdbcUrl = "jdbc:oracle:thin:@(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.ap-mumbai-1.oraclecloud.com))(connect_data=(service_name=g3036dfda7bc583_tz5csy4cb5h4vgom_high.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))";
    static final String username = "admin";
    static final String password = "Mohijeetsinh@6353";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Log the error
            throw new SQLException("Database driver not found", e);
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error
            throw e; // Re-throw the exception
        }
    }
}
