package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BeerDAO {

    private ConnectionProvider connectionProvider;

    //language=sql
    final String SQL_GET_BY_SORT = "select * from beer where sort = ?";

    public BeerDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Beer> getBeers(String sort) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_SORT);
            preparedStatement.setString(1, sort);
            return extractBeer(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }

    public static List<Beer> extractBeer(ResultSet result) throws DbException {
        List<Beer> beers = new ArrayList<>();
        try {
            while (result.next()) {
                Beer beer = new Beer((UUID) result.getObject("uuid"),
                        result.getString("sort"),
                        result.getString("type"),
                        result.getString("image"),
                        result.getString("content"));
                beers.add(beer);
            }
            System.out.println(beers);
            return beers;

        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }
}
