package com.mycompany.assessment_2.utils;

import java.sql.*;

/**
 * The DBUtils
 *  id：21146528
 * @author Xuanhao Wang
 */
public class DBUtils {
    private static DBUtils instance;

    private static Connection connection;

    private DBUtils() {
    }

    /**
     * The getInstance of DBUtils
     * @return
     */
    public static DBUtils getInstance() {
        if (instance == null) {
            instance = new DBUtils();
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                // Connect to the database
                connection = DriverManager.getConnection("jdbc:derby:myDB;create=true");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    /**
     * Close a statement
     * @param stmt
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // handle exception
            }
        }
    }

    /**
     * Close the result set
     * @param rs
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // handle exception
            }
        }
    }

    /**
     * Get the connection
     * @return
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Execute a sql statement
     * @param sql
     * @return
     */
    public static int executeUpdate(String sql) {
        Statement stmt = null;
        int result = 0;

        try {
            stmt = connection.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            closeStatement(stmt);
            System.err.println(e.getMessage());
            //e.printStackTrace();
        }

        return result;
    }

    /**
     * Execute a sql statement
     * @param sql
     * @return
     */
    public static ResultSet executeQuery(String sql) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            closeResultSet(rs);
            closeStatement(stmt);
            System.err.println(e.getMessage());
            //e.printStackTrace();
        }

        return rs;
    }

    /**
     * Close the connection
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
