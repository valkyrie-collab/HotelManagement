package com.valkyrie.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "star")
public class StarRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String raterId;
    private String hotelId;
    private double rating;
    private int starts;
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    public Hotel getHotel() {return hotel;}

    public StarRating setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }
    
    public int getId() {
        return id;
    }
    public StarRating setId(int id) {
        this.id = id;
        return this;
    }
    
    public String getRaterId() {
        return raterId;
    }
    public StarRating setRaterId(String raterId) {
        this.raterId = raterId;
        return this;
    }
    
    public String getHotelId() {
        return hotelId;
    }
    public StarRating setHotelId(String hotelId) {
        this.hotelId = hotelId;
        return this;
    }
    
    public double getRating() {
        return rating;
    }
    public StarRating setRating(double rating) {
        this.rating = rating;
        return this;
    }
    
    public int getStarts() {
        return starts;
    }
    public StarRating setStarts(int starts) {
        this.starts = starts;
        return this;
    }

}
