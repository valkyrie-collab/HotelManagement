package com.valkyrie.catalog.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int roomNumber;
    private String name;
    private String description;
    private int adultNo;
    private int childrenNo;
    private int price;
    private int beds;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomImage> images;
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    public int getId() {
        return id;
    }
    public Room setId(int id) {
        this.id = id;
        return this;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public Room setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }
    
    public Hotel getHotel() {return hotel;}

    public Room setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public List<RoomImage> getImages() {return images;}

    public Room setImages(List<RoomImage> images) {
        this.images = images;
        return this;
    }
    
    public String getName() {
        return name;
    }
    public Room setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    public Room setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public int getAdultNo() {
        return adultNo;
    }
    public Room setAdultNo(int adultNo) {
        this.adultNo = adultNo;
        return this;
    }
    
    public int getChildrenNo() {
        return childrenNo;
    }
    public Room setChildrenNo(int childrenNo) {
        this.childrenNo = childrenNo;
        return this;
    }
    
    public int getPrice() {
        return price;
    }
    public Room setPrice(int price) {
        this.price = price;
        return this;
    }
    
    public int getBeds() {
        return beds;
    }
    public Room setBeds(int beds) {
        this.beds = beds;
        return this;
    }

}
