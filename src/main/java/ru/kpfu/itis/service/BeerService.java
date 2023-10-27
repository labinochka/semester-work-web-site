package ru.kpfu.itis.service;


import ru.kpfu.itis.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> getBeers(String type);

    Beer getById(UUID uuid);
}
