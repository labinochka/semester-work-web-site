package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.BeerDao;
import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.service.BeerService;
import ru.kpfu.itis.util.DbException;

import java.util.List;
import java.util.UUID;

public class BeerServiceImpl implements BeerService {

    private BeerDao beerDao;

    public BeerServiceImpl(BeerDao beerDao) {
        this.beerDao = beerDao;
    }

    @Override
    public List<Beer> getBeers(String type) {
        try {
            return beerDao.getBeersBySort(type);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Beer getById(UUID uuid) {
        try {
            return beerDao.getById(uuid);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}
