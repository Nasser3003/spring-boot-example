package com.abdrabbo.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping("email/{customerEmail}")
    public Customer getCustomerByEmail(@PathVariable("customerEmail") String customerEmail) {
        return customerService.findCustomerByEmail(customerEmail);
    }

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest requestRegister) {
        customerService.addCustomer(requestRegister);
    }

    @PostMapping("/email/delete")
    public void deleteCustomerByEmail(@RequestBody CustomerDeletionRequest requestDelete ) {
        customerService.removeCustomer(requestDelete);
    }
    @PutMapping("{customerId}")
    public void updateCustomerInformation(
            @PathVariable("customerId") Integer id,
            @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        customerService.updateCustomer(id, customerUpdateRequest);
    }
}
