package com.abdrabbo.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer id);
    Optional<Customer> selectCustomerByEmail(String email);
    void insertCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    void  updateCustomer(Customer customerToUpdate);
    boolean existsPersonWithEmail(String email);
}
