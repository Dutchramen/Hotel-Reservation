package model;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType SINGLE, DOUBLE;

    public Room(String roomNumber, Double price, RoomType SINGLE, RoomType DOUBLE) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.SINGLE = SINGLE;
        this.DOUBLE = DOUBLE;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomType getSINGLE() {
        return SINGLE;
    }

    public void setSINGLE(RoomType SINGLE) {
        this.SINGLE = SINGLE;
    }

    public RoomType getDOUBLE() {
        return DOUBLE;
    }

    public void setDOUBLE(RoomType DOUBLE) {
        this.DOUBLE = DOUBLE;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room details: " + "roomNumber " + roomNumber + ", price " + price +
                ", SINGLE=" + SINGLE + ", DOUBLE=" + DOUBLE;
    }
}
