package com.valkyrie.entity.model;

public class BasicDetails {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private long phoneNumber;
    private ImageDTO profileImage;

    public BasicDetails(String id, String firstName, String lastName, String address, String email, long phoneNumber) {
        this.firstName = firstName; this.lastName = lastName; this.address = address;
        this.email = email; this.phoneNumber = phoneNumber; this.id = id;
    }

    public BasicDetails() {}
    
    public String getId() {
        return id;
    }

    public BasicDetails setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BasicDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BasicDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BasicDetails setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BasicDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public BasicDetails setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ImageDTO getProfileImage() {
        return profileImage;
    }

    public BasicDetails setProfileImage(ImageDTO profileImage) {
        this.profileImage = profileImage;
        return this;
    }

}
