package com.valkyrie.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_image")
public class RoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    public int getId() {return id;}

    public RoomImage setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {return name;}

    public RoomImage setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {return type;}

    public RoomImage setType(String type) {
        this.type = type;
        return this;
    }

    public byte[] getData() {return data;}

    public RoomImage setData(byte[] data) {
        this.data = data;
        return this;
    }

    public Room getRoom() {return room;}

    public RoomImage setRoom(Room room) {
        this.room = room;
        return this;
    }
}
