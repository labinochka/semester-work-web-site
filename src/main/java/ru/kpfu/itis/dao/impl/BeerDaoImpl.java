package ru.kpfu.itis.dao.impl;

import ru.kpfu.itis.dao.BeerDao;
import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BeerDaoImpl implements BeerDao {

    private ConnectionProvider connectionProvider;

    //language=sql
    final String SQL_GET_BY_SORT = "select * from beer where sort = ?";

    //language=sql
    final String SQL_GET_BY_TYPE = "select * from beer where type = ?";

    //language=sql
    final String SQL_GET_BY_UUID = "select * from beer where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_SAVE = "insert into beer values (cast(? as uuid), ?, ?, ?, ?)";

    //language=sql
    final String SQL_UPDATE = "update beer set sort = ?, type = ?, image = ?, content = ? " +
            "where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_UPDATE_WITHOUT_IMAGE = "update beer set sort = ?, type = ?, content = ? " +
            "where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_DELETE_BY_ID = "delete from beer where uuid = cast(? as uuid)";

    public BeerDaoImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public List<Beer> getBeersBySort(String sort) throws DbException {
        List<Beer> beers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_SORT);
            preparedStatement.setString(1, sort);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                beers.add(extract(resultSet));
            }
            return beers;
        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }

    @Override
    public Beer getByType(String type) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_TYPE);
            preparedStatement.setString(1, type);
            ResultSet result = preparedStatement.executeQuery();
            boolean hasOne = result.next();
            if (hasOne) {
                return extract(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }

    @Override
    public Beer getById(UUID uuid) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_BY_UUID);
            preparedStatement.setString(1, String.valueOf(uuid));
            ResultSet result = preparedStatement.executeQuery();
            boolean hasOne = result.next();
            if (hasOne) {
                return extract(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get beer from db.", e);
        }
    }

    @Override
    public Beer extract(ResultSet result) throws DbException {
        Beer beer;
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

    @Override
    public void save(Beer beer) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_SAVE);
            preparedStatement.setObject(1, beer.uuid());
            preparedStatement.setString(2, beer.sort());
            preparedStatement.setString(3, beer.type());
            preparedStatement.setString(4, beer.image());
            preparedStatement.setString(5, beer.content());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Can't save beer", e);
        }
    }

    @Override
    public void update(Beer beer) throws DbException {
        try {
            if (beer.image() != null) {
                PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                        .prepareStatement(SQL_UPDATE);
                preparedStatement.setString(1, beer.sort());
                preparedStatement.setString(2, beer.type());
                preparedStatement.setString(3, beer.image());
                preparedStatement.setString(4, beer.content());
                preparedStatement.setObject(5, beer.uuid());
                preparedStatement.executeUpdate();

            } else {
                PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                        .prepareStatement(SQL_UPDATE_WITHOUT_IMAGE);
                preparedStatement.setString(1, beer.sort());
                preparedStatement.setString(2, beer.type());
                preparedStatement.setString(3, beer.content());
                preparedStatement.setObject(4, beer.uuid());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DbException("Can't update beer from db.", e);
        }
    }

    @Override
    public void delete(Beer beer) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setObject(1, beer.uuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't delete beer", e);
        }
    }
}
