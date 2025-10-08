package com.valkyrie.reservation.model;

public class BookedRooms {
    private int roomNumber;
    private String hotelId;
    private boolean isBooked = false;

    public BookedRooms(int roomNumber, String hotelId) {
        this.roomNumber = roomNumber; this.hotelId = hotelId; this.isBooked = true;
    }

    public BookedRooms() {}

    public int getRoomNumber() {return roomNumber;}

    public String getHotelId() {return hotelId;}

    public boolean getIsBooked() {return isBooked;}

    public BookedRooms setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public BookedRooms setHotelId(String hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public BookedRooms setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
        return this;
    }

}
