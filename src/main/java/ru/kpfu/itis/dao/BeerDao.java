package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.util.DbException;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface BeerDao {

    List<Beer> getBeersBySort(String sort) throws DbException;

    Beer getById(UUID uuid) throws DbException;

    Beer extract(ResultSet result) throws DbException;

}
