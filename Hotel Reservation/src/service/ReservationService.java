package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    //method to add rooms to Collection<IRoom> rooms
    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    /**
     *
     * @return listing of all rooms stored in rooms Collection
     */
    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    //method that should return room

    /**
     *
     * @param roomNumber room number of potential rooms for guests to book
     * @return room after comparing the requested room with roomNumber
     */
    public IRoom getARoom(String roomNumber) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    /**
     *
     * @param customer customer account tied to email
     * @param room room number associated with room object
     * @param checkInDate guest check-in date
     * @param checkOutDate guest check-out date
     * @return reservation of customer tied to their account
     */
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    /**
     *
     * @param checkInDate guest requested check-in date
     * @param checkOutDate guest requested check-out date
     * @return rooms that fall within specified dates
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> roomsOpenForReserve = new HashSet<>();
        if (reservations.isEmpty()) {
            return rooms;
        } else {
            for (Reservation reservation : reservations) {
                if (((!checkInDate.after(reservation.getCheckInDate())) && (!checkInDate.before(reservation.getCheckOutDate())))
                        || (((checkOutDate.after(reservation.getCheckInDate())) && (!checkInDate.before(reservation.getCheckOutDate()))))) {
                    for (IRoom room : rooms) {
                        if (!reservation.getRoom().equals(room)) {
                            roomsOpenForReserve.add(room);
                        }
                    }
                }
            }
        }
        return roomsOpenForReserve;
    }

    /**
     *
     * @param customer customer account tied to email
     * @return customer specific reservation
     */
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservation = new HashSet<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }

    /**
     * gives back listing of each reservation stored in reservations
     */
    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

}
