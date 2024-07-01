package com.example.Security1.RepositoryImpl;

import com.example.Security1.EntityModel.Customer;
import com.example.Security1.Repository.CustomerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CustomerRepositoryImpl extends CustomerRepository {

    @Override
    public List<Customer> findByName(String name);
}
