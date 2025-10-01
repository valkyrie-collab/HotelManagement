package com.valkyrie.catalog.model;

// import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_image")
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    public int getId() {return id;}

    public HotelImage setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {return name;}

    public HotelImage setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {return type;}

    public HotelImage setType(String type) {
        this.type = type;
        return this;
    }

    public byte[] getData() {return data;}

    public HotelImage setData(byte[] data) {
        this.data = data;
        return this;
    }

    public Hotel getHotel() {return hotel;}

    public HotelImage setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }
    
}
