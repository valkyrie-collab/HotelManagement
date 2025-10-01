package com.valkyrie.catalog.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "name")
public class Hotel {
    @Id
    private String id;
    private String name;
    private String brand;
    private String description;
    private String address;
    private long contact;
    private int checkIn;
    private int checkOut;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelImage> images;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Rate> rates;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;

    public String getId() {
        return id;
    }
    public Hotel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public Hotel setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }
    public Hotel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public Hotel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAddress() {
        return address;
    }
    public Hotel setAddress(String address) {
        this.address = address;
        return this;
    }

    public long getContact() {
        return contact;
    }
    public Hotel setContact(long contact) {
        this.contact = contact;
        return this;
    }

    public int getCheckIn() {
        return checkIn;
    }
    public Hotel setCheckIn(int checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public int getCheckOut() {
        return checkOut;
    }
    public Hotel setCheckOut(int checkOut) {
        this.checkOut = checkOut;
        return this;
    }

    public List<HotelImage> getImages() {
        return images;
    }
    public Hotel setImages(List<HotelImage> images) {
        this.images = images;
        return this;
    }

    public List<Rate> getRates() {
        return rates;
    }
    public Hotel setRates(List<Rate> rates) {
        this.rates = rates;
        return this;
    }
    
    public List<Room> getRooms() {
        return rooms;
    }
    public Hotel setRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    @Override
    public String toString() {
        return "Hotel [hotelId=" + id + ", name=" + name + ", brand=" + brand + ", description=" + description
                + ", address=" + address + ", contact=" + contact + ", checkIn=" + checkIn + ", checkOut=" + checkOut
                + "]";
    }

}
