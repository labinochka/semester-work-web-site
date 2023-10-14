package ru.kpfu.itis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static ConnectionProvider _instance;

    public static ConnectionProvider getInstance() throws DbException {
        if (_instance == null) {
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    final String DRIVER = "org.postgresql.Driver";
    final String HOST = "jdbc:postgresql://localhost:5432/beer_site";
    final String USER = "postgres";
    final String PASS = "password";

    private Connection connection;

    private ConnectionProvider() throws DbException {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, USER, PASS);
        } catch (SQLException | ClassNotFoundException e){
            throw new DbException("Can't connect to DB", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
