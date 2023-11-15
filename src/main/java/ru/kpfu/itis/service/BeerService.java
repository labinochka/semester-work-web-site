package ru.kpfu.itis.service;


import ru.kpfu.itis.model.Beer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> getBeers(String type);

    Beer getById(UUID uuid);

    Beer getByType(String type);

    boolean save(Beer beer, HttpServletRequest req);

    boolean update(Beer newBeer, Beer oldBeer, HttpServletRequest req);

    void delete(Beer beer);
}
