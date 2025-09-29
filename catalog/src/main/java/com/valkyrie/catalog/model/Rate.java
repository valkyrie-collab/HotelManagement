package com.valkyrie.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    private String id;
    private String typeId;
    private int pricePerNight;
    private int mealPlan;
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    public String getId() {
        return id;
    }
    public Rate setId(String id) {
        this.id = id;
        return this;
    }
    
    public Hotel getHotel() {return hotel;}

    public Rate setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }
    
    public String getTypeId() {
        return typeId;
    }
    public Rate setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }
    
    public int getPricePerNight() {
        return pricePerNight;
    }
    public Rate setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }
    
    public int getMealPlan() {
        return mealPlan;
    }
    public Rate setMealPlan(int mealPlan) {
        this.mealPlan = mealPlan;
        return this;
    }
    
}
