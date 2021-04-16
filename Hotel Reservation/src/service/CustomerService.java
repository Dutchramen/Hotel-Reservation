package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;

public class CustomerService {
    private static CustomerService customerService;
    public Collection<Customer> customers = new HashSet<>();


    //private constructor to facilitate the Singleton Pattern
    // for the "There can be only One!!!!" instance of this class
    private CustomerService() {
    }

    //static reference for CustomerService class
    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public Collection<Customer> getAllCustomers() {
        if (!customers.isEmpty()) {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
            return customerService.getAllCustomers();
        }

        return null;
    }

    //method to add Customers
    public void addCustomer(String email, String firstName, String lastName) {
        Customer newCustomer = new Customer(email, firstName, lastName);
        customers.add(newCustomer);
    }

    //method to retrieve customers from Customer Collection
    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if (customerEmail.equals(customer.getEmail()))
                System.out.println(customer);
            {
                return customer;
            }
        }
        return null;
    }
}
