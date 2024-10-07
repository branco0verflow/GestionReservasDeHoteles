package org.example.DAO;

import java.sql.*;

public class ConnectionDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_obligatorio";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public boolean executeUpdate(String query, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            int rowsAffected = statement.executeUpdate();
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public ResultSet executeQuery(String query) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            // Ejecución de la consulta
            ResultSet resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}

