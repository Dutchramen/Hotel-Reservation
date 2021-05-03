package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();
    public Collection<Reservation> reservations = new HashSet<>();
    public Collection<IRoom> rooms = new HashSet<>();
    public Collection<Customer> customers = new HashSet<>();
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    public static void selectMainMenuOptions() {

        int guestSelection = mainMenuOptions();

        switch (guestSelection) {
            case 1 ->
                    //assist customer with finding and reserving a room
                    {
                        try {
                            findAndReserveARoom();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            case 2 ->
                    //allow customer to see the reservation just created
                    seeCustomerReservation();
            case 3 ->
                    //assist customer with creating an account
                    createAnAccount();
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
        System.out.println("Thank you for selection, you will now be taken to the appropriate page.");
        System.out.println();
        return guestSelection;
    }

    //method should use findRooms() and reserveARoom() methods from ReservationService
    public static String findAndReserveARoom() throws ParseException {
        //scanner to capture user input
        Scanner input = new Scanner(System.in);

        System.out.println("Thank you for choosing our lovely establishment!");
        System.out.println("Before we begin your experience.  Do you have an account with us?");
        System.out.println("Y for (yes) / N for (no)");
        String registeredUser = input.next().toUpperCase();
        switch (registeredUser){
            case "Y" -> {
                System.out.println("Already have an account?  Enter your email address to verify your account.");
                System.out.println("Please enter your registered email address: ");
                String registeredUserEmail = input.next();
                Customer customer = hotelResource.getCustomer(registeredUserEmail);

                //guest has already registered an account
                System.out.println("Thank you! You may proceed with placing your reservation.");
                System.out.println();
                //retrieve and display list of rooms
                System.out.println("Below is a listing of our available rooms.");
                Collection<IRoom> rooms = adminResource.getAllRooms();
                rooms.forEach(System.out::println);
                System.out.println();
                System.out.println("Please enter the number of the room you would like to reserve.");
                System.out.println();
                System.out.println("Room number: " + input.next());
                System.out.println();
            }
            case "N" -> {
                //guest has NOT registered an account and will be redirected to the create an Account page
                System.out.println("Please register in order to continue making your reservation.");
                createAnAccount();
                System.out.println("Thank you! You may proceed with placing your reservation.");
                //retrieve and display list of rooms
                System.out.println("Below is a listing of our available rooms.");
                Collection<IRoom> rooms = adminResource.getAllRooms();
                rooms.forEach(System.out::println);
                System.out.println("Please enter the number of the room you would like to reserve.");
                System.out.println("Room number: " + input.next());
            }
            default -> System.out.println("Invalid entry.  \"Y for (yes) / N for (no)\"");

        }




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

        System.out.println("Check-Out Date: ");
        String checkOutDate = input.next();
        Date checkOut = new SimpleDateFormat(DATE_FORMAT).parse(checkOutDate);
        System.out.println(checkOut);
        System.out.println();

        Collection<IRoom> rooms = hotelResource.findARoom(checkIn,checkOut);
        System.out.println(rooms);

        //think through this method!!
//        Set<IRoom> roomsOpenForReserve = new HashSet<>();
//        HotelResource.getInstance().findARoom(reservationService.findRooms(reservationService.reserveARoom(roomsOpenForReserve))).add(roomsOpenForReserve);
//        return HotelResource.reservationService.findRooms(reservationService.reserveARoom(roomsOpenForReserve));
        return null;
    }

    public static void seeCustomerReservation() {
        selectMainMenuOptions();
    }

    public static void createAnAccount() {

//        Collection<Customer> newCustomers = new HashSet<>();
        Scanner userInput = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("Enter email format: name@domain.com");
        String newGuestEmail = userInput.nextLine();
        System.out.println("First Name: ");
        String firstName = userInput.nextLine();
        System.out.println("Last Name: ");
        String lastName = userInput.nextLine();
        System.out.println("--------------------------------------------------");
        System.out.println("Please make your selection.");
        System.out.println("--------------------------------------------------");
        System.out.println("1: Find and Reserve a Room");
        System.out.println("2: See my reservations");
        System.out.println("3: Create an account");
        System.out.println("4: Admin");
        System.out.println("5: Exit");
        System.out.println("--------------------------------------------------");

        hotelResource.createACustomer(newGuestEmail,firstName,lastName);
        selectMainMenuOptions();

    }

    public static void adminiViewOptions() {
        AdminMenu.selectAdminiViewOptions();
    }
}



