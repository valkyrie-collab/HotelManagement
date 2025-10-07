package com.valkyrie.reservation.model;

import java.util.List;

public class RoomDTO {
    private int id;
    private int roomNumber;
    private String hotelId;
    private String name;
    private String description;
    private int adultNo;
    private int childrenNo;
    private int price;
    private int Beds;
    private List<ImageDTO> imageDTOs;

    public List<ImageDTO> getImageDTOs() {return imageDTOs;}

    public RoomDTO setImageDTOs(List<ImageDTO> imageDTOs) {
        this.imageDTOs = imageDTOs;
        return this;
    }

    public int getId() {
        return id;
    }
    public RoomDTO setId(int id) {
        this.id = id;
        return this;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public RoomDTO setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }
    
    public String getHotelId() {
        return hotelId;
    }
    public RoomDTO setHotelId(String hotelId) {
        this.hotelId = hotelId;
        return this;
    }
    
    public String getName() {
        return name;
    }
    public RoomDTO setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    public RoomDTO setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public int getAdultNo() {
        return adultNo;
    }
    public RoomDTO setAdultNo(int adultNo) {
        this.adultNo = adultNo;
        return this;
    }
    
    public int getChildrenNo() {
        return childrenNo;
    }
    public RoomDTO setChildrenNo(int childrenNo) {
        this.childrenNo = childrenNo;
        return this;
    }
    
    public int getPrice() {
        return price;
    }
    public RoomDTO setPrice(int price) {
        this.price = price;
        return this;
    }
    
    public int getBeds() {
        return Beds;
    }
    public RoomDTO setBeds(int beds) {
        Beds = beds;
        return this;
    }
    
}
