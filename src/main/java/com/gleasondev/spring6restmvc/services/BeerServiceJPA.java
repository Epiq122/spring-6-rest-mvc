package com.gleasondev.spring6restmvc.services;import com.gleasondev.spring6restmvc.mappers.BeerMapper;import com.gleasondev.spring6restmvc.model.BeerDTO;import com.gleasondev.spring6restmvc.repositories.BeerRepository;import lombok.RequiredArgsConstructor;import org.springframework.context.annotation.Primary;import org.springframework.stereotype.Service;import java.util.List;import java.util.Optional;import java.util.UUID;@Service@Primary@RequiredArgsConstructorpublic class BeerServiceJPA implements BeerService {    private final BeerRepository beerRepository;    private final BeerMapper beerMapper;    @Override    public List<BeerDTO> listBeers() {        return null;    }    @Override    public Optional<BeerDTO> getBeerById(UUID id) {        return Optional.empty();    }    @Override    public BeerDTO saveNewBeer(BeerDTO beerDTO) {        return null;    }    @Override    public void updateBeerById(UUID beerId, BeerDTO beerDTO) {    }    @Override    public void deleteBeerById(UUID beerId) {        BeerService.super.deleteBeerById(beerId);    }    @Override    public void patchBeerById(UUID beerId, BeerDTO beerDTO) {    }}