package com.valkyrie.reservation.model;

import jakarta.persistence.Lob;

public class RoomImage {
    private int id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    // @ManyToOne
    // @JoinColumn(name = "room_id", referencedColumnName = "id")
    // private Room room;

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

    // public Room getRoom() {return room;}

    // public RoomImage setRoom(Room room) {
    //     this.room = room;
    //     return this;
    // }
}
