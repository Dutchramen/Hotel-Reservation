package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType roomType) {
        super(roomNumber, 0.00, roomType);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getRoomNumber() {
        return super.getRoomNumber();
    }

    @Override
    public Double getRoomPrice() {
        return super.getRoomPrice();
    }

    @Override
    public void setRoomNumber(String roomNumber) {
        super.setRoomNumber(roomNumber);
    }

    @Override
    public Double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(Double price) {
        super.setPrice(0.00);
    }

    @Override
    public RoomType getRoomType() {
        return super.getRoomType();
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public void setRoomType(RoomType roomType) {
        super.setRoomType(roomType);
    }
}
