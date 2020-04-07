package com.joshua.sqlextended.umuzi;

import com.joshua.sqlextended.umuzi.tables.QueryOutput;

import java.sql.*;

/**
 * A simple vanilla java program to query the database.
 * @author Joshua Thotsana Mabotsa
 */

public class Main {

    // queries 1 to 7.
    static final String QUERY_1 = "SELECT * FROM \"Customers\"";
    static final String QUERY_2 = "SELECT \"FirstName\" FROM \"Customers\"";
    static final String QUERY_3 = "SELECT \"FirstName\" FROM \"Customers\" WHERE \"CustomerID\"=1";
    static final String QUERY_4 = "UPDATE \"Customers\" SET \"FirstName\"='Lerato',\"LastName\"='Mabitso' WHERE \"CustomerID\"=1";
    static final String QUERY_5 = "DELETE FROM \"Customers\" WHERE \"CustomerID\"=2";
    static final String QUERY_6a = "SELECT DISTINCT(\"Status\") FROM \"Orders\"";
    static final String QUERY_6b = "SELECT COUNT(DISTINCT\"Status\") FROM \"Orders\"";
    static final String QUERY_7 = "SELECT MAX(\"Amount\") FROM \"Payments\"";


    public static void main(String[] args) {

        try (
                Connection connection = DatabaseUtility.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

                ResultSet resultSet = statement.executeQuery(QUERY_1)
        ) {

            ResultSetMetaData data = resultSet.getMetaData();

            QueryOutput.displayQueryOutPut(resultSet,data);

        } catch (SQLException e) {
            DatabaseUtility.processException(e);
        }

    }
}

