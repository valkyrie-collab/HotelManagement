package com.valkyrie.reservation.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.valkyrie.reservation.model.RoomDTO;

@FeignClient("CATALOG")
public interface CatalogFeignController {

    @GetMapping("/catalog/get-rooms")
    public ResponseEntity<List<RoomDTO>> getRooms(@RequestParam String hotelId);

    @GetMapping("/catalog/find-room")
    public ResponseEntity<RoomDTO> getRoom(@RequestParam String hotelId, @RequestParam String roomNumber);

    @PostMapping("/catalog/book-room")
    public ResponseEntity<String> bookRoom(@RequestParam String roomNumber, @RequestParam String hotelId);

    @PostMapping("/catalog/unBook-room")
    public ResponseEntity<String> unBookRoom(@RequestParam String roomNumber, @RequestParam String hotelId);
}
