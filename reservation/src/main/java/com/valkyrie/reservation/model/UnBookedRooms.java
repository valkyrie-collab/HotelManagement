package com.valkyrie.reservation.model;

public class UnBookedRooms {
    private int roomNumber;
    private String hotelId;

    // public BookedRooms(int roomNumber, String hotelId) {
    //     this.roomNumber = roomNumber; this.hotelId = hotelId; this.isBooked = true;
    // }

    // public BookedRooms() {}

    public int getRoomNumber() {return roomNumber;}

    public String getHotelId() {return hotelId;}

    // public boolean getIsBooked() {return isBooked;}

    public UnBookedRooms setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public UnBookedRooms setHotelId(String hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    // public BookedRooms setIsBooked(boolean isBooked) {
    //     this.isBooked = isBooked;
    //     return this;
    // }

}
