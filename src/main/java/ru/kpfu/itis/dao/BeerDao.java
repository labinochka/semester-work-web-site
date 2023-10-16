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

public class BeerDao {

    private ConnectionProvider connectionProvider;

    //language=sql
    final String SQL_GET_BY_SORT = "select * from beer where sort = ?";

    //language=sql
    final String SQL_GET_BY_UUID = "select * from beer where uuid = cast(? as uuid)";

    public BeerDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Beer> getBeersBySort(String sort) throws DbException {
        List<Beer> beers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_SORT);
            preparedStatement.setString(1, sort);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beers.add(extractBeer(resultSet));
            }
            return beers;
        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }

    public Beer getDetail(UUID uuid) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_UUID);
            preparedStatement.setString(1, String.valueOf(uuid));
            ResultSet result = preparedStatement.executeQuery();
            boolean hasOne = result.next();
            if (hasOne) {
                return extractBeer(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }

    public Beer extractBeer(ResultSet result) throws DbException {
        Beer beer = null;
        try {
            beer = new Beer((UUID) result.getObject("uuid"),
                    result.getString("sort"),
                    result.getString("type"),
                    result.getString("image"),
                    result.getString("content"));
            return beer;

        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }
}
