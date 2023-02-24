package com.gleasondev.spring6restmvc.services;import com.gleasondev.spring6restmvc.model.Customer;import org.springframework.stereotype.Service;import java.time.LocalDateTime;import java.util.*;@Servicepublic class CustomerServiceImpl implements CustomerService {    private final Map<UUID, Customer> customerMap;    public CustomerServiceImpl() {        this.customerMap = new HashMap<>();        Customer customer1 = Customer.builder()                                     .id(UUID.randomUUID())                                     .name("Rob Gleason")                                     .version(1)                                     .createdDate(LocalDateTime.now())                                     .lastModifiedDate(LocalDateTime.now())                                     .build();        Customer customer2 = Customer.builder()                                     .id(UUID.randomUUID())                                     .name("Dicky Roberts")                                     .version(1)                                     .createdDate(LocalDateTime.now())                                     .lastModifiedDate(LocalDateTime.now())                                     .build();        Customer customer3 = Customer.builder()                                     .id(UUID.randomUUID())                                     .name("Larza Mandalin")                                     .version(1)                                     .createdDate(LocalDateTime.now())                                     .lastModifiedDate(LocalDateTime.now())                                     .build();        customerMap.put(customer1.getId(), customer1);        customerMap.put(customer2.getId(), customer2);        customerMap.put(customer3.getId(), customer3);    }    @Override    public List<Customer> listCustomers() {        return new ArrayList<>(customerMap.values());    }    @Override    public Customer getCustomerById(UUID id) {        return customerMap.get(id);    }    @Override    public Customer saveNewCustomer(Customer customer) {        Customer savedCustomer = Customer.builder()                                         .id(UUID.randomUUID())                                         .version(customer.getVersion())                                         .name(customer.getName())                                         .createdDate(customer.getCreatedDate())                                         .lastModifiedDate(customer.getLastModifiedDate())                                         .build();        customerMap.put(savedCustomer.getId(), savedCustomer);        return savedCustomer;    }    @Override    public void updateCustomerById(UUID customerId, Customer customer) {        Customer existing = customerMap.get(customerId);        existing.setName(customer.getName());        customerMap.put(existing.getId(), existing);    }    @Override    public void deleteCustomerById(UUID customerId) {        customerMap.remove(customerId);    }}