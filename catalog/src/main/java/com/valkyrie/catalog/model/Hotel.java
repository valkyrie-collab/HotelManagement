package com.valkyrie.catalog.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "name")
public class Hotel {
    @Id
    private String hotelId;
    private String name;
    private String brand;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<StarRating> starRating;
    private String description;
    private String address;
    private long contact;
    private int checkIn;
    private int checkOut;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Image> images;

    public String getHotelId() {
        return hotelId;
    }
    public Hotel setHotelId(String hotelId) {
        this.hotelId = hotelId;
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

    public List<StarRating> getStarRating() {
        return starRating;
    }
    public Hotel setStarRating(List<StarRating> starRating) {
        this.starRating = starRating;
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

    public List<Image> getImages() {
        return images;
    }
    public Hotel setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    @Override
    public String toString() {
        return "Hotel [hotelId=" + hotelId + ", name=" + name + ", brand=" + brand + ", description=" + description
                + ", address=" + address + ", contact=" + contact + ", checkIn=" + checkIn + ", checkOut=" + checkOut
                + "]";
    }

}
