package org.apache.ignite;

import java.sql.*;

/**
 * This example demonstrates the usage of the Apache Ignite JDBC driver.
 *
 * <p>Find instructions on how to run the example in the README.md file located in the "examples" directory root.
 */
public class SqlJdbcExample {
    /**
     * Main method of the example.
     *
     * @param args The command line arguments.
     * @throws Exception If failed.
     */
    public static void main(String[] args) throws Exception {
        //--------------------------------------------------------------------------------------
        //
        // Creating a JDBC connection to connect to the cluster.
        //
        //--------------------------------------------------------------------------------------

        System.out.println("\nConnecting to server...");
        Class.forName("org.apache.ignite.jdbc.IgniteJdbcDriver");
        try (Connection conn = DriverManager.getConnection("jdbc:ignite:thin://172.16.70.250:10800/DATA_PUBLIC")) {
//        try (Connection conn = DriverManager.getConnection("jdbc:ignite:thin://localhost:20800/DATA_PUBLIC")) {

            //--------------------------------------------------------------------------------------
            //
            // Creating tables.
            //
            //--------------------------------------------------------------------------------------

            try (Statement stmt = conn.createStatement()) {
                //创建schema
                String createSchemaSql = "CREATE SCHEMA IF NOT EXISTS DATA_PUBLIC";
                stmt.executeUpdate(createSchemaSql);

                System.out.println("=====");
                //查询所有的表
                String showTablesSql = "SELECT * FROM SYSTEM.TABLES ";
                try (ResultSet rs = stmt.executeQuery(showTablesSql)) {
                    while (rs.next()) {
                        int columnCount = rs.getMetaData().getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.println("    " + rs.getMetaData().getColumnName(i) + ":" + rs.getString(i));
                        }
                        System.out.println("========");
                    }
                }
                System.out.println("=====");
                //查询所有的schema
                String showSchemasSql = "SELECT * FROM SYSTEM.SYSTEM_VIEWS ";

                try (ResultSet rs = stmt.executeQuery(showSchemasSql)) {
                    while (rs.next()) {
                        //获取所有列
                        int columnCount = rs.getMetaData().getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.println("    " + rs.getMetaData().getColumnName(i) + ":" + rs.getString(i));
                        }
                    }
                }
                stmt.executeUpdate(
                    "CREATE TABLE CITIES ("
                        + "ID   INT PRIMARY KEY,"
                        + "NAME VARCHAR)"
                );

                stmt.executeUpdate(
                    "CREATE TABLE ACCOUNTS ("
                        + "    ACCOUNT_ID INT PRIMARY KEY,"
                        + "    CITY_ID    INT,"
                        + "    FIRST_NAME VARCHAR,"
                        + "    LAST_NAME  VARCHAR,"
                        + "    BALANCE    DOUBLE)"
                );
            }

            //--------------------------------------------------------------------------------------
            //
            // Populating 'CITIES' table.
            //
            //--------------------------------------------------------------------------------------

            System.out.println("\nPopulating 'CITIES' table...");

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO CITIES (ID, NAME) VALUES (?, ?)")) {
                stmt.setInt(1, 1);
                stmt.setString(2, "Forest Hill");
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setString(2, "Denver");
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setString(2, "St. Petersburg");
                stmt.executeUpdate();
            }

            //--------------------------------------------------------------------------------------
            //
            // Populating 'ACCOUNTS' table.
            //
            //--------------------------------------------------------------------------------------

            System.out.println("\nPopulating 'ACCOUNTS' table...");

            try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO ACCOUNTS (ACCOUNT_ID, CITY_ID, FIRST_NAME, LAST_NAME, BALANCE) values (?, ?, ?, ?, ?)")) {
                stmt.setInt(1, 1);
                stmt.setInt(2, 1);
                stmt.setString(3, "John");
                stmt.setString(4, "Doe");
                stmt.setDouble(5, 1000.0d);
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setInt(2, 1);
                stmt.setString(3, "Jane");
                stmt.setString(4, "Roe");
                stmt.setDouble(5, 2000.0d);
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setInt(2, 2);
                stmt.setString(3, "Mary");
                stmt.setString(4, "Major");
                stmt.setDouble(5, 1500.0d);
                stmt.executeUpdate();

                stmt.setInt(1, 4);
                stmt.setInt(2, 3);
                stmt.setString(3, "Richard");
                stmt.setString(4, "Miles");
                stmt.setDouble(5, 1450.0d);
                stmt.executeUpdate();
            }

            //--------------------------------------------------------------------------------------
            //
            // Requesting information about all account owners.
            //
            //--------------------------------------------------------------------------------------

            System.out.println("\nAll accounts:");

            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(
                    "SELECT a.FIRST_NAME, a.LAST_NAME, c.NAME FROM ACCOUNTS a "
                        + "INNER JOIN CITIES c on c.ID = a.CITY_ID ORDER BY a.ACCOUNT_ID")) {
                    while (rs.next()) {
                        System.out.println("    "
                            + rs.getString(1) + ", "
                            + rs.getString(2) + ", "
                            + rs.getString(3));
                    }
                }
            }

            //--------------------------------------------------------------------------------------
            //
            // Requesting accounts with balances lower than 1,500.
            //
            //--------------------------------------------------------------------------------------

            System.out.println("\nAccounts with balance lower than 1,500:");

            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(
                    "SELECT a.FIRST_NAME, a.LAST_NAME, a.BALANCE FROM ACCOUNTS a WHERE a.BALANCE < 1500.0 "
                        + "ORDER BY a.ACCOUNT_ID")) {
                    while (rs.next()) {
                        System.out.println("    "
                            + rs.getString(1) + ", "
                            + rs.getString(2) + ", "
                            + rs.getDouble(3));
                    }
                }
            }

            //--------------------------------------------------------------------------------------
            //
            // Deleting one of the accounts.
            //
            //--------------------------------------------------------------------------------------

            System.out.println("\nDeleting one of the accounts...");

            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM ACCOUNTS WHERE ACCOUNT_ID = ?")) {
                stmt.setInt(1, 1);
                stmt.executeUpdate();
            }

            //--------------------------------------------------------------------------------------
            //
            // Requesting information about all account owners once again
            // to verify that the account was actually deleted.
            //
            //--------------------------------------------------------------------------------------

            System.out.println("\nAll accounts:");

            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(
                    "SELECT a.FIRST_NAME, a.LAST_NAME, c.NAME FROM ACCOUNTS a "
                        + "INNER JOIN CITIES c on c.ID = a.CITY_ID ORDER BY a.ACCOUNT_ID")) {
                    while (rs.next()) {
                        System.out.println("    "
                            + rs.getString(1) + ", "
                            + rs.getString(2) + ", "
                            + rs.getString(3));
                    }
                }
            }

            System.out.println("\nDropping the tables...");

//            try (Statement stmt = conn.createStatement()) {
//                stmt.executeUpdate("DROP TABLE ACCOUNTS");
//                stmt.executeUpdate("DROP TABLE CITIES");
//            }
        }
    }
}
