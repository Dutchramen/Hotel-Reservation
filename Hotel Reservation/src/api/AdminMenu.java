package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public class AdminMenu {
    private static HotelResource hotelResource;
    private static AdminResource adminResource;
    public static CustomerService customerService = CustomerService.getInstance();
    public static ReservationService reservationService = ReservationService.getInstance();
    public Collection<Reservation> reservations = new HashSet<>();
    public Collection<IRoom> rooms = new HashSet<>();
    public Collection<Customer> customers = new HashSet<>();

    public static void selectAdminiViewOptions() {

        int adminSelection = adminiViewOptions();

        switch (adminSelection) {
            case 1:
                //method to view all customers created in system
                seeAllCustomers();
                break;
            case 2:
                //method to view all rooms created in system
                seeAllRooms();
                break;
            case 3:
                //method to view all reservations created in system
                seeAllReservations();
                break;
            case 4:
                //method to retrieve/open admin menu
                addARoom();
                break;
            //exits application
            case 5:
                //method to return back to main menu
                returnToMainMenu();
            case 6:
                System.exit(0);
        }
    }

    public static int adminiViewOptions() {
        System.out.println("Welcome to AdminiView");
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("1: See all customers");
        System.out.println("2: See all rooms");
        System.out.println("3: See all reservations");
        System.out.println("4: Add a room");
        System.out.println("5: Back to Main Menu");
        System.out.println("--------------------------------------------------");
        System.out.println("Please enter a number for the menu option");
        System.out.println();

        Scanner option = new Scanner(System.in);
        int adminSelection = option.nextInt();
        System.out.println("Thank you for selection, you will now be taken to the appropriate page.");
        System.out.println();
        return adminSelection;
    }

    public static void seeAllCustomers() {
        customerService.getAllCustomers();
        adminiViewOptions();
    }

    public static void seeAllRooms() {
        adminResource.getAllRooms();
        adminiViewOptions();
    }

    public static void seeAllReservations() {
        reservationService.printAllReseverations();
    }

    public static void addARoom() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter room number: ");
        System.out.println(input.nextInt());
        System.out.println("Enter price per night(PPN)");
        System.out.println(input.nextInt());
        System.out.println("Enter room type: 1 for Single bed room, 2 for Double bed room");
        System.out.println(input.nextInt());

        System.out.println("Would you like to add another room: Y/N?");
        System.out.println(input.next());


//        if (input.equals("Y")){
//            adminResource.addRoom(room);
//        } else {
//            adminiViewOptions();
        }




    public static void returnToMainMenu(){
        MainMenu.mainMenuOptions();
    }
}
