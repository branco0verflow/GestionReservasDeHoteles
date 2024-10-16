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




    public ResultSet executeQuery(String query, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof Integer) {
                    statement.setInt(i + 1, (Integer) params[i]);
                } else {
                    statement.setObject(i + 1, params[i]);
                }
            }

            return statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    public int executeUpdateAndReturnGeneratedKey(String query, Object... params) {
        try (PreparedStatement stmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]); // Asigna los parámetros
            }

            int affectedRows = stmt.executeUpdate(); // Ejecuta la actualización

            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar la fila.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Devuelve el ID generado
                } else {
                    throw new SQLException("No se pudo obtener el ID generado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Indica error
        }
    }



}

