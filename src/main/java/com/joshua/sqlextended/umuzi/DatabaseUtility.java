package com.joshua.sqlextended.umuzi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class will be used to connect to the database.
 */
public class DatabaseUtility {

    // Connection parameters.
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String CONN_STRING = "jdbc:postgresql://localhost:5432/Umuzi";

    /**
     * This method is used to call the DriverManager class and use the getConnection method in it.
     * @return Returns an instance of the connection interface.
     * @throws SQLException Throws an exception if the getConnection method fails.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
    }

    /**
     * This method catch the thrown exception and prints Error message, Error code and SQL state:
     * @param e SQLException parameter.
     */
    public static void processException(SQLException e) {
        System.err.println("Error message: " + e.getMessage());
        System.err.println("Postgres Error code: " + e.getErrorCode());
        System.err.println("SQL state: " + e.getSQLState());
    }

}
