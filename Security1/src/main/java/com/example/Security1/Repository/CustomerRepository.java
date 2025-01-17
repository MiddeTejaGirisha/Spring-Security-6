package com.example.Security1.Repository;

import com.example.Security1.EntityModel.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public List<Customer> findByName(String name);
}
