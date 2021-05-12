package api;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final AdminResource adminResource = AdminResource.getInstance();

    public static void selectAdminiViewOptions() {

        int adminSelection = 0;
        try {
            adminSelection = adminiViewOptions();
        } catch (InputMismatchException e) {
            System.out.println("Please make sure you only enter numbers that " +
                    "are available to select on your menu.  Please re-enter your selection.\n");
        }

        switch (adminSelection) {
            case 1 -> //method to view all customers created in system
                    seeAllCustomers();
            case 2 -> //method to view all rooms created in system
                    seeAllRooms();
            case 3 -> //method to view all reservations created in system
                    seeAllReservations();
            case 4 -> //method to retrieve/open admin menu
                    addARoom();
            case 5 ->  //method to return back to main menu
                    returnToMainMenu();
            case 6 -> //exits application
                    System.exit(0);
            default -> {
                selectAdminiViewOptions();
            }
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
        System.out.println("Please make your selection.");
        System.out.println();

        Scanner option = new Scanner(System.in);
        int adminSelection = option.nextInt();
        System.out.println("Thank you for selection, you will now be taken to the appropriate page.");
        System.out.println();
        return adminSelection;
    }

    public static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        System.out.println();
        selectAdminiViewOptions();
    }

    public static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        rooms.forEach(System.out::println);
        System.out.println();
        selectAdminiViewOptions();
    }

    public static void seeAllReservations() {
        AdminResource.displayAllReservations();
        selectAdminiViewOptions();
    }

    public static void addARoom() {
        //scanner to capture user input
        Scanner input = new Scanner(System.in);

        //capture room number to add to list of rooms
        System.out.println("Enter room number: ");
        String roomNumber = input.nextLine();

        //capture the price of the room
        System.out.println("Enter room price: ");
        double price = input.nextDouble();

        //capture room type
        System.out.println("Enter room type: SINGLE(1) / DOUBLE(2): ");
        RoomType roomType = input.nextInt() == 1 ? RoomType.SINGLE : RoomType.DOUBLE;

        IRoom room = new Room(roomNumber, price, roomType);
        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room);

        System.out.println("Would you like to add another room: Y/N?");
        String roomOption = input.next().toUpperCase();
        switch (roomOption) {
            case "Y" -> {
                adminResource.addRoom(rooms);
                addARoom();
            }
            case "N" -> {
                adminResource.addRoom(rooms);
                selectAdminiViewOptions();
                System.out.println();
            }
            default -> {
                System.out.println("Would you like to add another room: Y/N?");
                selectAdminiViewOptions();
            }
        }
    }



    public static void returnToMainMenu() {
        MainMenu.selectMainMenuOptions();
    }
}
