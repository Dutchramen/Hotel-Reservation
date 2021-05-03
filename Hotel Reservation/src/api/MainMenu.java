package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class MainMenu {
    public static AdminMenu adminMenu;
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();
    public Collection<Reservation> reservations = new HashSet<>();
    public Collection<IRoom> rooms = new HashSet<>();
    public Collection<Customer> customers = new HashSet<>();

    public static void selectMainMenuOptions() {

        int guestSelection = mainMenuOptions();

        switch (guestSelection) {
            case 1 ->
                    //assist customer with finding and reserving a room
                    findAndReserveARoom();
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
    public static String findAndReserveARoom() {
        boolean roomAvailable = true;
        System.out.println("Please enter CheckIn & CheckOut dates below.");
        System.out.println("Date format:  mm/dd/yyyy");
        System.out.println();



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



