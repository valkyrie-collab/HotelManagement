package com.valkyrie.catalog.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valkyrie.catalog.model.Hotel;
import com.valkyrie.catalog.model.HotelImage;
import com.valkyrie.catalog.model.Rate;
import com.valkyrie.catalog.model.Room;
import com.valkyrie.catalog.model.RoomImage;
import com.valkyrie.catalog.model.StarRating;
import com.valkyrie.catalog.repository.CatalogRepository;
import com.valkyrie.catalog.repository.ImageRepository;
import com.valkyrie.catalog.repository.RateRepository;
import com.valkyrie.catalog.repository.RoomRepository;
import com.valkyrie.catalog.repository.StarRatingRepository;

@Service
public class CatalogService {
    private CatalogRepository catalogRepo;
    private ImageRepository imageRepo;
    private StarRatingRepository starRepo;
    private RoomRepository roomRepo;
    private RateRepository rateRepo;

    @Autowired
    private void setCatalogRepo(CatalogRepository catalogRepo) {this.catalogRepo = catalogRepo;}

    @Autowired
    private void setImageRepo(ImageRepository imageRepo) {this.imageRepo = imageRepo;}

    @Autowired
    private void setStarRepo(StarRatingRepository starRepo) {this.starRepo = starRepo;}

    @Autowired
    private void setRoomRepo(RoomRepository roomRepo) {this.roomRepo = roomRepo;}

    @Autowired
    private void setRateRepo(RateRepository rateRepo) {this.rateRepo = rateRepo;}

    public ResponseEntity<String> saveHotel(
        String hotelJsonString, List<MultipartFile> hotelImageFiles) throws IOException {
        Hotel hotel = new ObjectMapper().readValue(hotelJsonString, Hotel.class);
        
        List<HotelImage> hotelImages = new ArrayList<>();

        hotel.setId(UUID.randomUUID().toString());

        for (MultipartFile hotelImageFile : hotelImageFiles) {
            hotelImages.add(
                new HotelImage().setName(hotelImageFile.getOriginalFilename()).setHotel(hotel)
                    .setType(hotelImageFile.getContentType()).setByte(hotelImageFile.getBytes())
            );
        }

        hotel.setImages(hotelImages);
        catalogRepo.save(hotel);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hotel data saved successfully..");
        
    } 

    public ResponseEntity<String> saveRoom(
        String hotelId, String RoomJsonString, List<MultipartFile> imageFiles) throws IOException {
        Room room = new ObjectMapper().readValue(RoomJsonString, Room.class);


        return null;
    }
}
