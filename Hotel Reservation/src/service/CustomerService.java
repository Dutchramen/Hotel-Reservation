package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerService {
    private static CustomerService customerService;
    public static Collection<Customer> customers = new ArrayList<>();

    //private constructor to facilitate the Singleton Pattern
    // for "There can be only One!!!!" instance of this class
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
                customers.forEach(System.out::println);
        }
        return customers;
    }

    //method to add Customers
    public void addCustomer(String email, String firstName, String lastName) {
        Customer newCustomer = new Customer(email, firstName, lastName);
        customers.add(newCustomer);
    }

    //method to retrieve customers from Customer Collection
    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if (!customerEmail.equals(customer.getEmail()))
                System.out.println(customerEmail);
                return customer;
        }
        return null;
    }
}
