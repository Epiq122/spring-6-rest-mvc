package com.gleasondev.spring6restmvc.controller;import com.gleasondev.spring6restmvc.entities.Beer;import com.gleasondev.spring6restmvc.model.BeerDTO;import com.gleasondev.spring6restmvc.repositories.BeerRepository;import jakarta.transaction.Transactional;import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.annotation.Rollback;import java.util.List;import java.util.UUID;import static org.assertj.core.api.Assertions.assertThat;import static org.junit.jupiter.api.Assertions.assertThrows;@SpringBootTestclass BeerControllerIT {    @Autowired    BeerController beerController;    @Autowired    BeerRepository beerRepository;    @Test    void testListBeers() {        List<BeerDTO> dtos = beerController.listBeers();        assertThat(dtos.size()).isEqualTo(3);    }    // Want the test to roll back to the state before the test    @Rollback    @Transactional    @Test    void testEmptyList() {        beerRepository.deleteAll();        List<BeerDTO> dtos = beerController.listBeers();        assertThat(dtos.size()).isEqualTo(0);    }    //want this specfic not found exception to be thrown when  the beerId is not found    @Test    void testBeerNotFound() {        assertThrows(NotFoundException.class, () -> beerController.getBeerById(UUID.randomUUID()));    }    @Test    void testGetBeerById() {        Beer beer = beerRepository.findAll().get(0);        BeerDTO dto = beerController.getBeerById(beer.getId());        assertThat(dto).isNotNull();    }}