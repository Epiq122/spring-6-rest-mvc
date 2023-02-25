package com.gleasondev.spring6restmvc.controller;import com.gleasondev.spring6restmvc.model.Beer;import com.gleasondev.spring6restmvc.services.BeerService;import com.gleasondev.spring6restmvc.services.BeerServiceImpl;import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;import org.springframework.boot.test.mock.mockito.MockBean;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.hamcrest.core.Is.is;import static org.mockito.BDDMockito.given;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;@WebMvcTest(BeerController.class)class BeerControllerTest {    @Autowired    MockMvc mockMvc;    @MockBean    BeerService beerService;    BeerServiceImpl beerServiceImpl = new BeerServiceImpl();    @Test    void testListBeers() throws Exception {        given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());        mockMvc.perform(get("/api/v1/beer")                       .accept(MediaType.APPLICATION_JSON))               .andExpect(status().isOk())               .andExpect(content().contentType(MediaType.APPLICATION_JSON))               .andExpect(jsonPath("$.length()", is(3))); // checking to make sure there is 3 items in the list    }    @Test    void getBeerById() throws Exception {        Beer testBeer = beerServiceImpl.listBeers().get(0); // asks for the first beer        given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer); // checking the id of the first beer        mockMvc.perform(get("/api/v1/beer/" + testBeer.getId())                       .accept(MediaType.APPLICATION_JSON))               .andExpect(status().isOk())               .andExpect(content().contentType(MediaType.APPLICATION_JSON))               .andExpect(jsonPath("$.id", is(testBeer.getId().toString()))) // checks to make sure there is a ID               .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName()))); // checks to make sure is a beer name    }}