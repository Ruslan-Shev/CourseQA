package data;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHelper {
    private static final String DB_URL = System.getProperty("db.url");
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "pass";

    private DataBaseHelper() {
    }

    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @SneakyThrows
    public static String getStatusCreditRequest() {
        String query = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (Connection conn = getConn();
             Statement statement = conn.createStatement();
             var resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        }
        return null;
    }

    @SneakyThrows
    public static String getStatusPaymentRequest() {
        String query = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (Connection conn = getConn();
             Statement statement = conn.createStatement();
             var resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        }
        return null;
    }
     @SneakyThrows
    public static int getCountOrderEntity() {
        String query = "SELECT COUNT(*) AS total FROM order_entity;";
        try (Connection conn = getConn();
             Statement statement = conn.createStatement();
             var resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        }
        return 0;
    }

    @SneakyThrows
    public static void cleanDataBase() {
        try (Connection connection = getConn()) {
            runner.execute(connection, "DELETE FROM credit_request_entity");
            runner.execute(connection, "DELETE FROM order_entity");
            runner.execute(connection, "DELETE FROM payment_entity");
        }
    }

}
