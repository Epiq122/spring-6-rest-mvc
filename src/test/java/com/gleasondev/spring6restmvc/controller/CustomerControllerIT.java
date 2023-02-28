package com.gleasondev.spring6restmvc.controller;import com.gleasondev.spring6restmvc.entities.Customer;import com.gleasondev.spring6restmvc.mappers.CustomerMapper;import com.gleasondev.spring6restmvc.model.CustomerDTO;import com.gleasondev.spring6restmvc.repositories.CustomerRepository;import jakarta.transaction.Transactional;import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.http.HttpStatusCode;import org.springframework.http.ResponseEntity;import org.springframework.test.annotation.Rollback;import java.util.List;import java.util.UUID;import static org.assertj.core.api.Assertions.assertThat;import static org.junit.jupiter.api.Assertions.assertThrows;@SpringBootTestclass CustomerControllerIT {    @Autowired    CustomerController customerController;    @Autowired    CustomerRepository customerRepository;    @Autowired    CustomerMapper customerMapper;    @Test    void testListCustomers() {        List<CustomerDTO> dtos = customerController.listCustomers();        assertThat(dtos.size()).isEqualTo(3);    }    @Rollback    @Transactional    @Test    void testEmptyList() {        customerRepository.deleteAll();        List<CustomerDTO> dtos = customerController.listCustomers();        assertThat(dtos.size()).isEqualTo(0);    }    @Test    void testGetCustomerById() {        CustomerDTO dto = customerController.getCustomerById(customerRepository.findAll().get(0).getId());        assertThat(dto).isNotNull();    }    @Test    void testCustomerNotFound() {        assertThrows(NotFoundException.class,                () -> customerController.getCustomerById(UUID.randomUUID()));    }    @Test    void testUpdateNotFound() {        assertThrows(NotFoundException.class,                () -> customerController.updateCustomerByID(UUID.randomUUID(), CustomerDTO.builder().build()));    }    @Rollback    @Transactional    @Test    void testListAllEmptyList() {        customerRepository.deleteAll();        List<CustomerDTO> dtos = customerController.listCustomers();        assertThat(dtos.size()).isEqualTo(0);    }    @Test    void testGetByIdNotFound() {        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(UUID.randomUUID()));    }    @Rollback    @Transactional    @Test    void deleteByIdFound() {        Customer customer = customerRepository.findAll().get(0);        ResponseEntity responseEntity = customerController.deleteByCustomerId(customer.getId());        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));    }    @Rollback    @Transactional    @Test    void saveNewCustomerTest() {        CustomerDTO customerDTO = CustomerDTO.builder().name("New Customer").build();        ResponseEntity responseEntity = customerController.handlePost(customerDTO);        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");        UUID uuid = UUID.fromString(locationUUID[4]);        Customer customer = customerRepository.findById(uuid).get();        assertThat(customer).isNotNull();    }    @Rollback    @Transactional    @Test    void updateCustomerById() {        Customer customer = customerRepository.findAll().get(0);        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);        customerDTO.setId(null);        customerDTO.setVersion(null);        final String customerName = "UPDATED CUSTOMER";        customerDTO.setName(customerName);        ResponseEntity responseEntity = customerController.updateCustomerByID(customer.getId(), customerDTO);        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();        assertThat(updatedCustomer.getName()).isEqualTo(customerName);    }}