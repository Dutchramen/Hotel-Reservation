package model;

public class Driver {
    public static void main(String[] args) {
        Customer customer = new Customer("First", "Second", "j@domain.com");
        System.out.println(customer);
        Customer customer1 = new Customer("first", "second", "email");
        System.out.println(customer1);


    }
}
