package ru.kpfu.itis.service;

import ru.kpfu.itis.dao.BeerDao;
import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public class BeerService {
    private BeerDao beerDao;

    public BeerService() {
        try {
            beerDao = new BeerDao(ConnectionProvider.getInstance());
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Beer> getBeers(String type)  {
        try {
            return beerDao.getBeersBySort(type);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public Beer getDetails(UUID uuid) {
        try {
            return beerDao.getDetail(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
