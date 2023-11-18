package com.abdrabbo.customer;

import com.abdrabbo.exception.DuplicateResourceException;
import com.abdrabbo.exception.RequestValidationException;
import com.abdrabbo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }
    public Customer getCustomer(Integer id) {
         return customerDao.selectCustomerById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Customer id [%s] not found".formatted(id)));
    }
    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        if (customerDao.existsPersonWithEmail(customerRegistrationRequest.email()))
            throw new DuplicateResourceException("email already taken");

        Customer customer = new Customer(customerRegistrationRequest.name()
                ,customerRegistrationRequest.email()
                ,customerRegistrationRequest.age());

        customerDao.insertCustomer(customer);
    }

    public Customer findCustomerByEmail(String email) {
        return customerDao.selectCustomerByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User with email [%s] not found".formatted(email)));
    }

    public void removeCustomer(CustomerDeletionRequest customerDeletionRequest) {
        if (!customerDao.existsPersonWithEmail(customerDeletionRequest.email()))
            throw new ResourceNotFoundException("Customer with email [%s] not found"
                    .formatted(customerDeletionRequest.email()));

        Customer customer = findCustomerByEmail(customerDeletionRequest.email());
        customerDao.deleteCustomer(customer);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest updateRequest) {

        Customer customer = getCustomer(customerId);
        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;
        }

        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
            if (customerDao.existsPersonWithEmail(updateRequest.email()))
                throw new RequestValidationException("Email already taken");
            customer.setEmail(updateRequest.email());
            changes = true;
        }
        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            changes = true;
        }
        if (changes = false)
            throw new ResourceNotFoundException("No changes were found");
        else
            customerDao.updateCustomer(customer);
    }
}