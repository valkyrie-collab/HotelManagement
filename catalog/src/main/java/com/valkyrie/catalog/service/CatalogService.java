package com.valkyrie.catalog.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valkyrie.catalog.config.TokenConfig;
import com.valkyrie.catalog.model.Hotel;
import com.valkyrie.catalog.model.HotelDTO;
import com.valkyrie.catalog.model.HotelImage;
import com.valkyrie.catalog.model.ImageDTO;
import com.valkyrie.catalog.model.Rate;
import com.valkyrie.catalog.model.Room;
import com.valkyrie.catalog.model.RoomDTO;
import com.valkyrie.catalog.model.RoomImage;
import com.valkyrie.catalog.repository.CatalogRepository;
// import com.valkyrie.catalog.repository.ImageRepository;
import com.valkyrie.catalog.repository.RateRepository;
import com.valkyrie.catalog.repository.RoomRepository;

@Service
public class CatalogService {
    private CatalogRepository catalogRepo;
    // private ImageRepository imageRepo;
    private RoomRepository roomRepo;
    private RateRepository rateRepo;
    private TokenConfig config;

    @Autowired
    private void setConfig(TokenConfig config) {this.config = config;}

    @Autowired
    private void setCatalogRepo(CatalogRepository catalogRepo) {this.catalogRepo = catalogRepo;}

    // @Autowired
    // private void setImageRepo(ImageRepository imageRepo) {this.imageRepo = imageRepo;}

    @Autowired
    private void setRoomRepo(RoomRepository roomRepo) {this.roomRepo = roomRepo;}

    @Autowired
    private void setRateRepo(RateRepository rateRepo) {this.rateRepo = rateRepo;}

    private String doDecoding(String data) {
        return new String(Base64.getDecoder().decode(data));
    }
    
    @Transactional
    public ResponseEntity<String> saveHotel(String token,
        String hotelJsonString, List<MultipartFile> hotelImageFiles) throws IOException {
        hotelJsonString = doDecoding(hotelJsonString);
        List<String> roles = config.getRoles(token);

        if (!roles.getFirst().equals("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Not have right permission....");
        }

        Hotel hotel = new ObjectMapper().readValue(hotelJsonString, Hotel.class); 
        List<HotelImage> hotelImages = new ArrayList<>();
        hotel.setId(UUID.randomUUID().toString());

        for (MultipartFile hotelImageFile : hotelImageFiles) {
            hotelImages.add(
                new HotelImage().setName(hotelImageFile.getOriginalFilename()).setHotel(hotel)
                    .setType(hotelImageFile.getContentType()).setData(hotelImageFile.getBytes())
            );
        }

        hotel.setImages(hotelImages);
        catalogRepo.save(hotel);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hotel data saved successfully.. with ID: " + hotel.getId());
        
    } 

    @Transactional
    public ResponseEntity<String> saveRoom(String token, String hotelId, 
        String RoomJsonString, List<MultipartFile> imageFiles) throws IOException {
        hotelId = doDecoding(hotelId);
        RoomJsonString = doDecoding(RoomJsonString);
        List<String> roles = config.getRoles(token);

        if (!roles.getFirst().equals("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Not have right permission....");
        }

        Room room = new ObjectMapper().readValue(RoomJsonString, Room.class);
        List<RoomImage> images = new LinkedList<>();
        Hotel hotel = catalogRepo.findById(hotelId).orElse(null);

        if (hotel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no such hotel with Id: " + hotelId);
        }

        for (MultipartFile imageFile : imageFiles) {
            images.add(
                new RoomImage().setRoom(room).setName(imageFile.getOriginalFilename())
                    .setData(imageFile.getBytes()).setType(imageFile.getContentType())
            );
        }

        room.setHotel(hotel).setImages(images);
        roomRepo.save(room);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room add successfully to the hotel with id: " + hotelId);
    }

    @Transactional
    public ResponseEntity<String> saveRating(String token, String hotelId, String ratingJsonString) throws IOException {
        hotelId = doDecoding(hotelId);
        ratingJsonString = doDecoding(ratingJsonString);
        List<String> roles = config.getRoles(token);

        if (roles.getFirst().equals("ROLE_USER")) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Not have right permission....");
        }

        String username = config.getUsername(token);
        Rate rate = new ObjectMapper().readValue(ratingJsonString, Rate.class);
        Hotel hotel = catalogRepo.findById(hotelId).orElse(null);

        if (hotel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no such hotel is there ");
        }

        rate.setId(UUID.randomUUID().toString()).setTypeId(username).setHotel(hotel);
        rateRepo.save(rate);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rating saved successfully....");

    }

    @Transactional
    public ResponseEntity<List<RoomDTO>> getRoomsByHotelId(String hotelId) {
        hotelId = doDecoding(hotelId);
        List<Room> rooms = catalogRepo.getRoomsByHotelId(hotelId);

        if (rooms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<RoomDTO> roomDTOs = new LinkedList<>();

        for (Room room : rooms) {
            List<RoomImage> images = catalogRepo.getRoomsImages(room.getId());
            List<ImageDTO> imageDTOs = new LinkedList<>(); 

            if (!images.isEmpty()) {

                for (RoomImage image : images) {
                    imageDTOs.add(
                        new ImageDTO().setData(image.getData())
                            .setId(image.getId()).setName(image.getName())
                            .setType(image.getType())
                    );
                }

            }

            roomDTOs.add(
                new RoomDTO().setAdultNo(room.getAdultNo()).setBeds(room.getBeds())
                    .setChildrenNo(room.getChildrenNo()).setDescription(room.getDescription())
                    .setHotelId(hotelId).setId(room.getId()).setName(room.getName())
                    .setPrice(room.getPrice()).setImageDTOs(imageDTOs).setRoomNumber(room.getRoomNumber())
            );

        }

        return ResponseEntity.status(HttpStatus.OK).body(roomDTOs);

    }

    @Transactional
    public ResponseEntity<List<HotelDTO>> getAllHotel() {
        List<Hotel> hotels = catalogRepo.getAllHotel();
        List<HotelDTO> hotelDTOs = new LinkedList<>();

        if (hotels.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        for (Hotel hotel : hotels) {
            List<HotelImage> images = catalogRepo.getHotelImages(hotel.getId());
            List<ImageDTO> imageDTOs = new LinkedList<>();

            if (!images.isEmpty()) {

                for (HotelImage image : images) {
                    imageDTOs.add(
                        new ImageDTO().setData(image.getData())
                            .setId(image.getId()).setName(image.getName())
                            .setType(image.getType())
                    );
                }

            }

            hotelDTOs.add(
                new HotelDTO().setAddress(hotel.getAddress()).setBrand(hotel.getBrand())
                    .setCheckIn(hotel.getCheckIn()).setCheckOut(hotel.getCheckOut())
                    .setContact(hotel.getContact()).setDescription(hotel.getDescription())
                    .setId(hotel.getId()).setImageDTOs(imageDTOs).setName(hotel.getName())
                    .setRateDTOs(null).setRoomDTOs(null)
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(hotelDTOs);

    }

    @Transactional
    public ResponseEntity<String> removeHotel(String token, String hotelId) {
        hotelId = doDecoding(hotelId);
        List<String> roles = config.getRoles(token);

        if (!roles.getFirst().equals("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Not have right permission....");
        }

        if (catalogRepo.findById(hotelId).orElse(null) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already deleted...");
        }

        catalogRepo.deleteById(hotelId);
        
        return ResponseEntity.status(HttpStatus.OK).body("Deletion successful...");

    }

    @Transactional
    public ResponseEntity<String> removeRoomByRoomNumberAndHotelId(
        String token, String roomNumber, String hotelId) {
        int roomId = Integer.parseInt(doDecoding(roomNumber));
        // System.out.println("2 + hotelId = " + hotelId);
        hotelId = doDecoding(hotelId);
        // System.out.println("3");
        List<String> roles = config.getRoles(token);

        if (!roles.getFirst().equals("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Not have right permission....");
        }
        
        Integer roomOriginalId = catalogRepo.checkIfRoomPresent(roomId, hotelId);
        // System.out.println(roomOriginalId);

        if (roomOriginalId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already deleted...");
        }

        catalogRepo.deleteRoomImages(roomOriginalId);
        catalogRepo.deleteRoomByHotelIdAndRoomNumber(roomId, hotelId);
        
        return ResponseEntity.status(HttpStatus.OK).body("Deletion successful...");

    }

    @Transactional
    public ResponseEntity<String> removeRoomByHotelIds(String token, String hotelId) {
        // int roomId = Integer.parseInt(doDecoding(roomNumber));
        hotelId = doDecoding(hotelId);
        List<String> roles = config.getRoles(token);

        if (!roles.getFirst().equals("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Not have right permission....");
        }

        List<Integer> roomOriginalIds = catalogRepo.checkIfRoomPresentByHotelIdOnly(hotelId);

        if (roomOriginalIds == null || roomOriginalIds.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already deleted...");
        }

        for (Integer roomId : roomOriginalIds) {
            catalogRepo.deleteRoomImages(roomId);
        }

        catalogRepo.deleteRoomByHotelId(hotelId);
        
        return ResponseEntity.status(HttpStatus.OK).body("Deletion successful...");

    }

}
