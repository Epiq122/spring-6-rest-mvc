package com.gleasondev.spring6restmvc.services;import com.gleasondev.spring6restmvc.model.Beer;import java.util.List;import java.util.UUID;public interface BeerService {    List<Beer> listBeers();    Beer getBeerById(UUID id);    Beer saveNewBeer(Beer beer);    void updateBeerById(UUID beerId, Beer beer);    default void deleteBeerById(UUID beerId) {    }}