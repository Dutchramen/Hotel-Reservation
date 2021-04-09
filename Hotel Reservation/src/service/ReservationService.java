package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    public Collection<Reservation> reservations = new HashSet<>();
    public Collection<IRoom> rooms = new HashSet<>();

    //private constructor to facilitate the Singleton Pattern
    // for the "There can be only One!!!!" instance of this class
    private ReservationService() {
    }

    //static reference for Reservation class
    public static ReservationService getInstance(){
        if (reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    //method to add rooms to Collection<IRoom> rooms
    public void addRoom(IRoom room){
        rooms.add(room);
    }

    //method that should return roomId if roomId is available and
    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (rooms.contains(roomId)){
                System.out.println(roomId);
            }
            return room;
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservedRooms = new Reservation(customer,room, checkInDate, checkOutDate);
        reservations.add(reservedRooms);
        return reservedRooms;
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        for (IRoom room : rooms) {
            if (checkInDate.after(checkInDate) && checkOutDate.before(checkOutDate)) {
                Iterator<IRoom> iRoomIterator = rooms.iterator();
                while (iRoomIterator.hasNext()) {
                    System.out.println(iRoomIterator.next());
                }
                return rooms;
            }
        }
        return null;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        return reservations;
    }

    public void printAllReseverations(){
        for (Reservation reservation : reservations){
            System.out.println(reservations);
        }
    }
}
