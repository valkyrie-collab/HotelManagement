package com.valkyrie.catalog.model;

public class RateDTO {
    private String id;
    private String hotelId;
    private String typeId;
    private int pricePerNight;
    private int mealPlan;

    public String getId() {
        return id;
    }
    public RateDTO setId(String id) {
        this.id = id;
        return this;
    }
    
    public String getHotelId() {
        return hotelId;
    }
    public RateDTO setHotelId(String hotelId) {
        this.hotelId = hotelId;
        return this;
    }
    
    public String getTypeId() {
        return typeId;
    }
    public RateDTO setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }
    
    public int getPricePerNight() {
        return pricePerNight;
    }
    public RateDTO setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
        return this;
    }
    
    public int getMealPlan() {
        return mealPlan;
    }
    public RateDTO setMealPlan(int mealPlan) {
        this.mealPlan = mealPlan;
        return this;
    }

}
