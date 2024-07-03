package com.example.Security1.Controller;

import com.example.Security1.EntityModel.Customer;
import com.example.Security1.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

//    @GetMapping("/login")
//    public String loginForm(){
//        return "login";
//    }

//    @PostMapping("/login")
//    public String loginSubmit(@RequestParam String username, @RequestParam String password) {
//
//        System.out.println("Username: " + username);
//        System.out.println("Password: " + password);
//
//        return "redirect:/home";
//    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/get")
    public String  check() {
        return "it's working";
    }

}
