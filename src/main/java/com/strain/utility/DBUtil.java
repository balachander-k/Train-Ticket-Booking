package com.strain.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.strain.beans.TrainException;
import com.strain.constant.ResponseCode;

public class DBUtil {
    private static Connection con;

    static {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // Establish connection using SQLite connection string
            String dbUrl = "jdbc:sqlite:C:\\Users\\HP\\MySQLiteDB";
            con = DriverManager.getConnection(dbUrl);
            System.out.println("SQLite Connection Success!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws TrainException {
        if (con == null)
            throw new TrainException(ResponseCode.DATABASE_CONNECTION_FAILURE);
        return con;
    }
}
