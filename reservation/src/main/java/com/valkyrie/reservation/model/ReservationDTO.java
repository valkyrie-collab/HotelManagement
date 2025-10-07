package com.valkyrie.reservation.model;

import java.util.Date;

public class ReservationDTO {
    private String bookingId;
    private String userId;
    private String hotelId;
    private String roomId;
    private String typeId;
    private Date checkIn;
    private Date checkOut;
    private int night;
    private final String[] occupancy = new String[2];
    private int perNightCost;
    private int totalAmount;
    private Status status;
    private Date bookedAt;
    private Date modifiedAt;
    private Date canceledAt;
    private String specialRequest;
    
    public String getBookingId() {
        return bookingId;
    }
    public ReservationDTO setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }
    
    public String getUserId() {
        return userId;
    }
    public ReservationDTO setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    
    public String getHotelId() {
        return hotelId;
    }
    public ReservationDTO setHotelId(String hotelId) {
        this.hotelId = hotelId;
        return this;
    }
    
    public String getTypeId() {
        return typeId;
    }
    public ReservationDTO setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }
    
    public Date getCheckIn() {
        return checkIn;
    }
    public ReservationDTO setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
        return this;
    }
    
    public Date getCheckOut() {
        return checkOut;
    }
    public ReservationDTO setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
        return this;
    }
    
    public int getNight() {
        return night;
    }
    public ReservationDTO setNight(int night) {
        this.night = night;
        return this;
    }
    
    public String[] getOccupancy() {
        return occupancy;
    }
    public ReservationDTO setOccupancy(String value1, String value2) {
        occupancy[0] = value1; occupancy[1] = value2;
        return this;
    }
    
    public int getPerNightCost() {
        return perNightCost;
    }
    public ReservationDTO setPerNightCost(int perNightCost) {
        this.perNightCost = perNightCost;
        return this;
    }
    
    public int getTotalAmount() {
        return totalAmount;
    }
    public ReservationDTO setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }
    
    public Status getStatus() {
        return status;
    }
    public ReservationDTO setStatus(Status status) {
        this.status = status;
        return this;
    }
    
    public Date getBookedAt() {
        return bookedAt;
    }
    public ReservationDTO setBookedAt(Date bookedAt) {
        this.bookedAt = bookedAt;
        return this;
    }
    
    public Date getModifiedAt() {
        return modifiedAt;
    }
    public ReservationDTO setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }
    
    public Date getCanceledAt() {
        return canceledAt;
    }
    public ReservationDTO setCanceledAt(Date canceledAt) {
        this.canceledAt = canceledAt;
        return this;
    }
    
    public String getSpecialRequest() {
        return specialRequest;
    }
    public ReservationDTO setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
        return this;
    }

    public String getRoomId() {
        return roomId;
    }
    public ReservationDTO setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

}
