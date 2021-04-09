package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType SINGLE, RoomType DOUBLE) {
        super(roomNumber, 0.00, SINGLE, DOUBLE);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
