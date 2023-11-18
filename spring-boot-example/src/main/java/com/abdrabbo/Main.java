package com.abdrabbo;

import com.abdrabbo.customer.Customer;
import com.abdrabbo.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {

        return args ->  {
            Customer alex = new Customer("Alex" , "Alex@gmail.com", 25);
            Customer jamila = new Customer("Jamila" , "Jamila@gmail.com", 28);

            List<Customer> customers = List.of(alex,jamila);
            customerRepository.saveAll(customers);
        };

    }

}
