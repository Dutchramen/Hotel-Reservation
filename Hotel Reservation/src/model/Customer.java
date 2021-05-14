package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String email;
    private String firstName;
    private String lastName;
    private final String emailRegex = "^(.+)@(.+).com$";
    private final Pattern pattern = Pattern.compile(emailRegex);


    public Customer(String email, String firstName, String lastName)  {
        super();
//        if (firstName.length() > 25 || lastName.length() > 25){
//            throw new NullPointerException("A complete First and Last name must be entered.");
//        }
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error, invalid email.\n");
//            System.out.println("""
//                    Error, invalid email.
//                    Please remember to enter email format: name@domain.com
//                    """);

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
        return "Email: " + email + "\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n";
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
