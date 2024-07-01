package com.example.Security1.Service;

import com.example.Security1.EntityModel.Customer;

import java.util.List;


public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    void deleteCustomer(Long id);

}
