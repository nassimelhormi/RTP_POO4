package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDatabase {

    // url de connexion à la db
    private static String url = "jdbc:mysql://localhost:3306/omega";

    // user
    private static String user = "root";

    // password
    private static String passwd = "root";

    // Object Connection
    private static Connection connect;

    // Singleton
    public static Connection getInstance()
    {
        if(connect == null)
        {
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
