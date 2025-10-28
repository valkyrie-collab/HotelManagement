package com.valkyrie.entity.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "entities")
public class Entities {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String bio;
    private long phoneNumber;
    @OneToOne(mappedBy = "entity", cascade = CascadeType.ALL)
    private Image profileImage;

    public String getId() {return id;}

    public Entities setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {return firstName;}

    public Entities setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {return lastName;}

    public Entities setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {return email;}

    public Entities setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {return address;}

    public Entities setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBio() {return bio;}

    public Entities setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public long getPhoneNumber() {return phoneNumber;}

    public Entities setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Image getProfileImage() {return profileImage;}

    public Entities setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
        return this;
    }

}
