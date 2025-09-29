package com.valkyrie.catalog.model;

public class StarRatingDTO {
    private String id;
    private String raterId;
    private String hotelId;
    private double rating;
    private int starts;

    public String getId() {
        return id;
    }
    public StarRatingDTO setId(String id) {
        this.id = id;
        return this;
    }
    
    public String getRaterId() {
        return raterId;
    }
    public StarRatingDTO setRaterId(String raterId) {
        this.raterId = raterId;
        return this;
    }
    
    public String getHotelId() {
        return hotelId;
    }
    public StarRatingDTO setHotelId(String hotelId) {
        this.hotelId = hotelId;
        return this;
    }
    
    public double getRating() {
        return rating;
    }
    public StarRatingDTO setRating(double rating) {
        this.rating = rating;
        return this;
    }
    
    public int getStarts() {
        return starts;
    }
    public StarRatingDTO setStarts(int starts) {
        this.starts = starts;
        return this;
    }
    
}
