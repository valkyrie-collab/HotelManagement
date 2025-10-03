package com.valkyrie.catalog.model;

import java.util.Date;
import java.util.List;

public class HotelDTO {
    private String id;
    private String name;
    private String brand;
    private String description;
    private String address;
    private long contact;
    private Date checkIn;
    private Date checkOut;
    private List<ImageDTO> imageDTOs;
    private List<RoomDTO> roomDTOs;
    private List<RateDTO> rateDTOs;

    public String getId() {
        return id;
    }
    public HotelDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public HotelDTO setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getBrand() {
        return brand;
    }
    public HotelDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    public HotelDTO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public String getAddress() {
        return address;
    }
    public HotelDTO setAddress(String address) {
        this.address = address;
        return this;
    }
    
    public long getContact() {
        return contact;
    }
    public HotelDTO setContact(long contact) {
        this.contact = contact;
        return this;
    }
    
    public Date getCheckIn() {
        return checkIn;
    }
    public HotelDTO setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
        return this;
    }
    
    public Date getCheckOut() {
        return checkOut;
    }
    public HotelDTO setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
        return this;
    }
    
    public List<ImageDTO> getImageDTOs() {
        return imageDTOs;
    }
    public HotelDTO setImageDTOs(List<ImageDTO> imageDTOs) {
        this.imageDTOs = imageDTOs;
        return this;
    }
    
    public List<RoomDTO> getRoomDTOs() {
        return roomDTOs;
    }
    public HotelDTO setRoomDTOs(List<RoomDTO> roomDTOs) {
        this.roomDTOs = roomDTOs;
        return this;
    }
    
    public List<RateDTO> getRateDTOs() {
        return rateDTOs;
    }
    public HotelDTO setRateDTOs(List<RateDTO> rateDTOs) {
        this.rateDTOs = rateDTOs;
        return this;
    }

}
