package com.example.crm.service;

import com.example.crm.model.Customer;
import com.example.crm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {

        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(customerDetails.getFirstName());
                    customer.setLastName(customerDetails.getLastName());
                    customer.setEmail(customerDetails.getEmail());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    customerDetails.setId(id);
                    return customerRepository.save(customerDetails);
                });
    }

    public void deleteCustomer(Long id) {

        customerRepository.deleteById(id);
    }
}
