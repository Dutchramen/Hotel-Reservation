package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource;
    public static CustomerService customerService = CustomerService.getInstance();
    public static ReservationService reservationService = ReservationService.getInstance();
    public static Collection<Reservation> reservations = new HashSet<>();
    public static Collection<IRoom> rooms = new HashSet<>();
    public static Collection<Customer> customers = new HashSet<>();


    //static reference
    private AdminResource() {
    }

    public static AdminResource getInstance() {
        if (null == adminResource) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }


    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    public static void displayAllReservations() {
        reservationService.printAllReservations();
    }

}




