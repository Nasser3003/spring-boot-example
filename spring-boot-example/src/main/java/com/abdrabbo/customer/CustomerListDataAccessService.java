package com.abdrabbo.customer;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {

    // creating the database
    private final List<Customer> customers;
    {
        customers = new ArrayList<>();

        Customer alex = new Customer("Alex" , "Alex@gmail.com", 25);
        customers.add(alex);

        Customer jamila = new Customer("Jamila" , "Jamila@gmail.com", 28);
        customers.add(jamila);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Customer> selectCustomerByEmail(String email) {
        return customers.stream().filter(c -> c.getEmail().equals(email)).findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equals(email));
    }
}