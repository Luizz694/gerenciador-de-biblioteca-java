package org.exm.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexaoFactory {
    private static final String URL = "jdbc:sqlite:biblioteca.db";

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        }
    }
}
