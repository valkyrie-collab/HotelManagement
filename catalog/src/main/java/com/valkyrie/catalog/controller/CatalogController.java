package com.valkyrie.catalog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.valkyrie.catalog.model.HotelDTO;
import com.valkyrie.catalog.model.RoomDTO;
import com.valkyrie.catalog.service.CatalogService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private CatalogService service;
    
    @Autowired
    private void setService(CatalogService service) {this.service = service;}

    @PostMapping("/add-hotel")
    public ResponseEntity<String> saveHotel(@RequestParam String token, @RequestParam String hotelJsonDataString,
        @RequestPart List<MultipartFile> imageFiles) throws IOException {
        return service.saveHotel(token, hotelJsonDataString, imageFiles);
    }

    @PostMapping("/add-room")
    public ResponseEntity<String> saveRoom(@RequestParam String token, @RequestParam String roomJsonDataString,
        @RequestParam String hotelId, @RequestPart List<MultipartFile> imageFiles) throws IOException {
        return service.saveRoom(token, hotelId, roomJsonDataString, imageFiles);
    }

    @PostMapping("/add-rating")
    public ResponseEntity<String> saveRating(@RequestParam String token, @RequestParam String hotelId, 
        @RequestParam String ratingJsonString) throws IOException {
            return service.saveRating(token, hotelId, ratingJsonString);
        }
    @GetMapping("/search-hotels")
    public ResponseEntity<List<HotelDTO> findHotels(@RequestParam String name) {
        
    }

    @GetMapping("/get-hotels")
    public ResponseEntity<List<HotelDTO>> getHotels() {return service.getAllHotel();}

    @GetMapping("/get-rooms")
    public ResponseEntity<List<RoomDTO>> getRooms(@RequestParam String hotelId) {
        return service.getRoomsByHotelId(hotelId);
    }

    @DeleteMapping("/remove-hotel")
    public ResponseEntity<String> removeHotel(@RequestParam String token, @RequestParam String hotelId) {
        return service.removeHotel(token, hotelId);
    }

    @DeleteMapping("/remove-rooms")
    public ResponseEntity<String> removeRoom(
        @RequestParam String token, @RequestParam String roomNumber, @RequestParam String hotelId) {
        
            return service.removeRoomByRoomNumberAndHotelId(token, roomNumber, hotelId);
    }

    @DeleteMapping("/remove-all-rooms")
    public ResponseEntity<String> removeAllRooms(
        @RequestParam String token, @RequestParam String hotelId
    ) {
        return service.removeRoomByHotelIds(token, hotelId);
    }

}
