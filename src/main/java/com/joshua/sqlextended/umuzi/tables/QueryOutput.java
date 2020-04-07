package com.joshua.sqlextended.umuzi.tables;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * This class is responsible for printing the results of a Query in a
 * table format. (Just like it would appear if you used a terminal to query a database)
 */
public class QueryOutput {

    // Calculates the lengths of longest string in each and every column.
    static int[] longestColumnEntry = {};

    /**
     * This methods prints the query output in a table format.
     * @param resultSet ResultSet object.
     * @param data ResultSetMetaData object.
     * @throws SQLException throws an appropriate SQL exception.
     */
    public static void displayQueryOutPut(ResultSet resultSet, ResultSetMetaData data) throws SQLException {

        longestStringColumn(resultSet,data); // calculate the lengths of the longest string in each and every column and put the results in an array.

        frameText();

        int entryLength;                    // The length of a field in a table.

        for (int i = 1; i <= longestColumnEntry.length; i++) {
            entryLength = data.getColumnName(i).length();
            System.out.print("| " + data.getColumnName(i));
            for (int j = 0; j < longestColumnEntry[i-1] - entryLength + 1; j++) {
                System.out.print(" ");
            }
        }

        System.out.println("|");

        frameText();

        while (resultSet.next()) {
            for (int i = 1; i <= longestColumnEntry.length; i++) {
                entryLength = resultSet.getString(data.getColumnName(i)).length();
                System.out.print("| " + resultSet.getString(data.getColumnName(i)));
                for (int j = 0; j < longestColumnEntry[i-1] - entryLength + 1; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println("|");

            frameText();
        }

    }

    /**
     * A method for framing texts with "+" and "-" characters.
     */
    private static void frameText() {

        for (int value : longestColumnEntry) {
            System.out.print("+");
            for (int j = 0; j < value + 2; j++) {
                System.out.print("-");
            }
        }

        System.out.println("+");
    }

    /**
     * This method calculates the lengths of longest string in each and every column.
     * The resulting lengths are put in an array.
     * @param resultSet ResultSet object.
     * @param data ResultSetMetaData object.
     * @throws SQLException throws an appropriate SQL exception.
     */
    private static void longestStringColumn(ResultSet resultSet, ResultSetMetaData data) throws SQLException {

        int columnCount = data.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {

            longestColumnEntry = Arrays.copyOf(longestColumnEntry,longestColumnEntry.length+1);

            if (data.getColumnName(i).length() > longestColumnEntry[i-1]) {
                longestColumnEntry[i-1] = data.getColumnName(i).length();
            }
        }

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (resultSet.getString(data.getColumnName(i)).length() > longestColumnEntry[i-1]) {
                    longestColumnEntry[i-1] = resultSet.getString(data.getColumnName(i)).length();
                }
            }
        }
        resultSet.beforeFirst();
    }

}
