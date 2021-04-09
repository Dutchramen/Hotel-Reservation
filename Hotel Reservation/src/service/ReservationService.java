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

    private ReservationService() {
    }

    //static reference for Reservation class
    public static ReservationService getInstance(){
        if (reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            System.out.println(roomId);
        }
        return rooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservedRoom = new Reservation(customer,room, checkInDate, checkOutDate);
        reservations.add(reservedRoom);
        return reservedRoom;
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        Iterator<IRoom> iRoomIterator = rooms.iterator();
        while (iRoomIterator.hasNext()){
            System.out.println(iRoomIterator.next());
        }
        return rooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){

    }

    public void printAllReseverations(){
        for (Reservation reservation : reservations){
            System.out.println(reservations);
        }
    }
}
