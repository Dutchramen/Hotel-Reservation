package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType roomType) {
        super(roomNumber, 0.00, roomType);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
