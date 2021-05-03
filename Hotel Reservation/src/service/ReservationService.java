package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ReservationService {
    private static ReservationService reservationService;
    public static Collection<Reservation> reservations = new HashSet<>();
    public static Collection<IRoom> rooms = new HashSet<>();

    //private constructor to facilitate the Singleton Pattern
    // for "There can be only One!!!!" instance of this class
    private ReservationService() {
    }

    //static reference for Reservation class
    public static ReservationService getInstance() {
        if (reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    //method to add rooms to Collection<IRoom> rooms
    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    //method that should return room
    public IRoom getARoom(String roomNumber) {
        for (IRoom room : rooms) {
            if (roomNumber.equals(room.getRoomNumber())) {
                System.out.println("This room has already been created.  Please enter alternate room number.");
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservedRooms = new Reservation(customer,room,checkInDate, checkOutDate);
        reservations.add(reservedRooms);
        return reservedRooms;
    }


    public Collection <IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Set<IRoom> roomsOpenForReserve = new HashSet<>();
        if (reservations.isEmpty()) {
            return rooms;
        } else {
                for (Reservation reservation : reservations) {
                    /* if requested RM & RSV are already taken in system due to prior RSV of RM,
                    requested RM number will be removed from roomsOpenForReserve list */
                    if (((!checkInDate.after(reservation.getCheckInDate())) && (!checkInDate.before(reservation.getCheckOutDate())))
                    || (((!checkOutDate.after(reservation.getCheckInDate())) && (!checkInDate.before(reservation.getCheckOutDate()))))) {
                        reservations.add(reservation);
                        System.out.println(reservation);
                    }
                }
        }
        return roomsOpenForReserve;
    }


    public Collection<Reservation> getCustomerReservation(Customer customer) {
        CustomerService.getInstance().getCustomer(customer.getEmail());
        return reservations;
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }

}
