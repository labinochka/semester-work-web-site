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

public interface BeerDao {

    List<Beer> getBeersBySort(String sort) throws DbException;

    Beer getById(UUID uuid) throws DbException;

    Beer extract(ResultSet result) throws DbException;

}
