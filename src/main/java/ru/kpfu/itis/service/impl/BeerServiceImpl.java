package ru.kpfu.itis.service.impl;

import ru.kpfu.itis.dao.BeerDao;
import ru.kpfu.itis.model.Beer;
import ru.kpfu.itis.service.BeerService;
import ru.kpfu.itis.util.DbException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

public class BeerServiceImpl implements BeerService {

    private BeerDao beerDao;

    private final String TYPE_REGEX = "[\\p{IsCyrillic}- ]+";

    public BeerServiceImpl(BeerDao beerDao) {
        this.beerDao = beerDao;
    }

    @Override
    public List<Beer> getBeers(String sort) {
        try {
            return beerDao.getBeersBySort(sort);
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

    @Override
    public Beer getByType(String type) {
        try {
            return beerDao.getByType(type);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Beer beer, HttpServletRequest req) {
        try {
            if (getByType(beer.type()) != null) {
                req.setAttribute("error", "Этот тип уже существует");
                return false;
            } else if (!beer.type().matches(TYPE_REGEX)) {
                req.setAttribute("error", "Название должно состоять только из букв");
                return false;
            } else {
                beerDao.save(beer);
                return true;
            }
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Beer newBeer, Beer oldBeer, HttpServletRequest req) {
        try {
            if (!newBeer.type().equals(oldBeer.type()) && getByType(newBeer.type()) != null) {
                req.setAttribute("error", "Этот тип уже существует");
                req.setAttribute("beer", newBeer);
                return false;
            } else if (!newBeer.type().matches(TYPE_REGEX)) {
                req.setAttribute("error", "Название должно состоять только из букв");
                req.setAttribute("beer", newBeer);
                return false;
            } else {
                beerDao.update(newBeer);
                return true;
            }
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Beer beer) {
        try {
            beerDao.delete(beer);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}

