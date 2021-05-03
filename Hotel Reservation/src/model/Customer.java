package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String email;
    private String firstName;
    private String lastName;


    public Customer(String email, String firstName, String lastName) throws IllegalArgumentException {
        if (firstName == null || lastName == null){
            throw new IllegalArgumentException("A complete First and Last name must be entered.");
        }
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error, invalid email");
        }
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer details: " + "Email: " + email + ", First Name: " + firstName +
                ", Last Name: " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return email.equals(customer.email) && firstName.equals(customer.firstName) && lastName.equals(customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName);
    }
}
