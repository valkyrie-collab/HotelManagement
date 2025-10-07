package com.valkyrie.reservation.model;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    private String reservationId;
    private String userId;
    private String hotelId;
    private String roomNumber;
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
    
    public String getReservationId() {
        return reservationId;
    }
    public Reservation setReservationId(String reservationId) {
        this.reservationId = reservationId;
        return this;
    }
    
    public String getUserId() {
        return userId;
    }
    public Reservation setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    
    public String getHotelId() {
        return hotelId;
    }
    public Reservation setHotelId(String hotelId) {
        this.hotelId = hotelId;
        return this;
    }
    
    public String getTypeId() {
        return typeId;
    }
    public Reservation setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }
    
    public Date getCheckIn() {
        return checkIn;
    }
    public Reservation setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
        return this;
    }
    
    public Date getCheckOut() {
        return checkOut;
    }
    public Reservation setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
        return this;
    }
    
    public int getNight() {
        return night;
    }
    public Reservation setNight(int night) {
        this.night = night;
        return this;
    }
    
    public String[] getOccupancy() {
        return occupancy;
    }
    public Reservation setOccupancy(String value1, String value2) {
        occupancy[0] = value1; occupancy[1] = value2;
        return this;
    }
    
    public int getPerNightCost() {
        return perNightCost;
    }
    public Reservation setPerNightCost(int perNightCost) {
        this.perNightCost = perNightCost;
        return this;
    }
    
    public int getTotalAmount() {
        return totalAmount;
    }
    public Reservation setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }
    
    public Status getStatus() {
        return status;
    }
    public Reservation setStatus(Status status) {
        this.status = status;
        return this;
    }
    
    public Date getBookedAt() {
        return bookedAt;
    }
    public Reservation setBookedAt(Date bookedAt) {
        this.bookedAt = bookedAt;
        return this;
    }
    
    public Date getModifiedAt() {
        return modifiedAt;
    }
    public Reservation setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }
    
    public Date getCanceledAt() {
        return canceledAt;
    }
    public Reservation setCanceledAt(Date canceledAt) {
        this.canceledAt = canceledAt;
        return this;
    }
    
    public String getSpecialRequest() {
        return specialRequest;
    }
    public Reservation setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
        return this;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    public Reservation setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    @Override
    public String toString() {
        return "Reservation [bookingId=" + reservationId + ", userId=" + userId + ", hotelId=" + hotelId + ", typeId="
                + typeId + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", night=" + night + ", occupancy="
                + Arrays.toString(occupancy) + ", perNightCost=" + perNightCost + ", totalAmount=" + totalAmount
                + ", status=" + status + ", bookedAt=" + bookedAt + ", modifiedAt=" + modifiedAt + ", canceledAt="
                + canceledAt + ", specialRequest=" + specialRequest + "]";
    }

}
