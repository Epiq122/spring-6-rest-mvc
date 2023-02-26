package com.gleasondev.spring6restmvc.services;import com.gleasondev.spring6restmvc.mappers.CustomerMapper;import com.gleasondev.spring6restmvc.model.CustomerDTO;import com.gleasondev.spring6restmvc.repositories.CustomerRepository;import lombok.AllArgsConstructor;import org.springframework.context.annotation.Primary;import org.springframework.stereotype.Service;import java.util.List;import java.util.Optional;import java.util.UUID;import java.util.stream.Collectors;@Service@Primary@AllArgsConstructorpublic class CustomerServiceJPA implements CustomerService {    CustomerRepository customerRepository;    CustomerMapper customerMapper;    @Override    public List<CustomerDTO> listCustomers() {        return customerRepository.findAll().stream()                                 .map(customerMapper::customerToCustomerDto)                                 .collect(Collectors.toList());    }    @Override    public Optional<CustomerDTO> getCustomerById(UUID id) {        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository.findById(id)                                                                                          .orElse(null)));    }    @Override    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {        return null;    }    @Override    public void updateCustomerById(UUID customerId, CustomerDTO customerDTO) {    }    @Override    public void deleteCustomerById(UUID customerId) {    }    @Override    public void patchCustomerById(UUID customerId, CustomerDTO customerDTO) {    }}