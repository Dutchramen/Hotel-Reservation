package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    public static void selectMainMenuOptions() {

        int guestSelection = 0;
        try {
            guestSelection = mainMenuOptions();
        } catch (InputMismatchException e) {
            System.out.println("Please make sure you only enter numbers that " +
                    "are available to select on your menu.  Please re-enter your selection.\n");
        }

        switch (guestSelection) {
            case 1 ->
                    //assist customer with finding and reserving a room
                    {
                        try {
                            findAndReserveARoom();
                        } catch (ParseException e) {
                            System.out.println("Please make a valid entry.\n");
                        }
                    }
            case 2 ->
                    //allow customer to see the reservation(s) just created
                    seeCustomerReservations();
            case 3 ->
                    //assist customer with creating an account
                    {
                        try {
                            createAnAccount();
                        } catch (Exception e) {
                            System.out.println(e.getLocalizedMessage());
                        } finally {
                            selectMainMenuOptions();
                        }
                    }
            case 4 ->
                    //retrieve/open admin menu
                    adminiViewOptions();
            //exits application
            case 5 -> System.exit(0);
            default -> selectMainMenuOptions();
        }
    }

    public static int mainMenuOptions() {
        System.out.println("Welcome to Dutch's Hotel Reservation Application!");
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("1: Find and Reserve a Room");
        System.out.println("2: See my reservations");
        System.out.println("3: Create an account");
        System.out.println("4: Admin");
        System.out.println("5: Exit");
        System.out.println("--------------------------------------------------");
        System.out.println("Please make your selection.");

        Scanner option = new Scanner(System.in);
        int guestSelection = option.nextInt();
        System.out.println("Thank you for selection, you will now be taken to the appropriate page.\n");
        return guestSelection;
    }

    public static void createAnAccount() {

        Scanner userInput = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("Enter email format: name@domain.com");
        String guestEmail = userInput.next();
        System.out.println("First Name: ");
        String firstName = userInput.next();
        System.out.println("Last Name: ");
        String lastName = userInput.next();
        System.out.println("--------------------------------------------------");
        System.out.println("Please make your selection.");
        System.out.println("--------------------------------------------------");

        hotelResource.createACustomer(guestEmail, firstName, lastName);
    }

    //method should use findARoom() and bookARoom() methods from HotelResource
    public static void findAndReserveARoom() throws ParseException, NullPointerException {
        //scanner to capture user input
        Scanner input = new Scanner(System.in);

        System.out.println("Thank you for choosing our lovely establishment!");
        System.out.println("Before we begin your experience.  Do you have an account with us?\n");
        System.out.println("Y for (yes) / N for (no)");
        String registeredUser = input.next().toUpperCase();

        switch (registeredUser) {
            case "Y" -> {
                System.out.println("Already have an account?  Verify your account by entering your email address below.\n");
                System.out.println("Please enter your registered email address: \n");

                String email = input.next();
                Customer customer = hotelResource.getCustomer(email);
                System.out.println(customer);

                //guest has already registered an account
                System.out.println("Thank you! You may proceed with placing your reservation.\n");

                //give guest date format
                System.out.println("Please enter CheckIn & CheckOut dates below.");
                System.out.println("Date format:  MM/DD/YYYY");
                System.out.println();

                //check-in date
                System.out.println("Check-In Date: ");
                String checkInDate = input.next();
                Date checkIn = new SimpleDateFormat(DATE_FORMAT).parse(checkInDate);
                System.out.println(checkIn);
                System.out.println();

                //check-out date
                System.out.println("Check-Out Date: ");
                String checkOutDate = input.next();
                Date checkOut = new SimpleDateFormat(DATE_FORMAT).parse(checkOutDate);
                System.out.println(checkOut);
                System.out.println();

                //search rooms using user input from above
                Collection<IRoom> rooms = hotelResource.findARoom(checkIn, checkOut);
                rooms.forEach(System.out::println);

                //ask guest what room number they would possibly like to reserve
                System.out.println("Please enter the room number for the room you would like to reserve.\n");
                System.out.println("Room number: ");
                String roomNumber = input.next();
                IRoom requestedRoom = hotelResource.getRoom(roomNumber);
                System.out.println("Room number: " + requestedRoom + "\n");
                if (rooms.contains(requestedRoom)) {
                    hotelResource.bookARoom(customer.getEmail(), requestedRoom, checkIn, checkOut);
                    System.out.println("Room " + requestedRoom + "\nReserved for Username: " + customer + "\n");
                } else {
                    System.out.println("We're sorry but " + requestedRoom + " is already reserved.  " +
                            "Please select another room to reserve.\n");
                }
            }

            case "N" -> {
                //guest has NOT registered an account and will be redirected to the create an Account page
                System.out.println("Please register in order to continue making your reservation.");
                createAnAccount();
                System.out.println("Thank you! You may proceed with placing your reservation.\n");
                System.out.println("Please enter the number of the room you would like to reserve.");
                String roomNumber = input.next();
                System.out.println("Room number: " + roomNumber);
            }
            default -> System.out.println("Invalid entry.  \"Y for (yes) / N for (no)\"\n");
        }
        selectMainMenuOptions();
    }

    public static void seeCustomerReservations() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to see your current reservation(s)?");
        System.out.println("Please enter your registered email address.");

        String email = input.next();
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
        selectMainMenuOptions();
    }



    public static void adminiViewOptions() {
        AdminMenu.selectAdminiViewOptions();
    }
}