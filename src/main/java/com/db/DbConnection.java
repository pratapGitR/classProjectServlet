package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String DB_URL;
    private  static Connection connection;
    static {
        // read properties file and set values on db url , username & password.
        DB_URL="jdbc:mysql://localhost:3306/pratap_site_database";
    }

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection =  DriverManager.getConnection(DB_URL,"root","WorkIsLife@004");
        return connection;
    }
}

