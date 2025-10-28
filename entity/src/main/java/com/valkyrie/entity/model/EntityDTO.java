package com.valkyrie.entity.model;

public class EntityDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String bio;
    private long phoneNumber;
    private ImageDTO profileImage;

    public String getId() {return id;}

    public EntityDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {return firstName;}

    public EntityDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {return lastName;}

    public EntityDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {return address;}

    public EntityDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBio() {return bio;}

    public EntityDTO setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public String getEmail() {return email;}

    public EntityDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getPhoneNumber() {return phoneNumber;}

    public EntityDTO setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ImageDTO getProfileImage() {return profileImage;}

    public EntityDTO setProfileImage(ImageDTO profileImage) {
        this.profileImage = profileImage;
        return this;
    }

}
