package com.valkyrie.catalog.model;

public class RoomDTO {
    private String id;
    private String hotelId;
    private String name;
    private String description;
    private int adultNo;
    private int childrenNo;
    private int price;
    private int Beds;

    public String getId() {
        return id;
    }
    public RoomDTO setId(String id) {
        this.id = id;
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
