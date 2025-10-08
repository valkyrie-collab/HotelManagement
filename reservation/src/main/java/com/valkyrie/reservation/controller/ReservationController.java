package com.valkyrie.reservation.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valkyrie.reservation.model.BookedRooms;
import com.valkyrie.reservation.model.ReservationDTO;
import com.valkyrie.reservation.model.RoomDTO;
import com.valkyrie.reservation.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService service;
    @Autowired
    private void setService(ReservationService service) {
        this.service = service;
    }

    @PostMapping("/add-reservation")
    public ResponseEntity<String> add(String token, String reservationJsonString) throws IOException {
        return service.saveReservation(token, reservationJsonString);
    }

    @PostMapping("/cancel-reservation")
    public ResponseEntity<String> cancel(String reservationId) {
        return service.cancelReservation(reservationId);
    }

    @PostMapping("/update-check-in")
    public ResponseEntity<String> checkIn(String reservationId, String username) {
        return service.setCheckIn(reservationId, username);
    }

    @PostMapping("/update-check-out")
    public ResponseEntity<String> checkOut(String reservationId, String username) {
        return service.setCheckOut(reservationId, username);
    }

    @GetMapping("/all-room")
    public ResponseEntity<List<BookedRooms>> rooms(String hotelId) {
        return service.getRooms(hotelId);
    }

    @GetMapping("/get-all-room")
    public ResponseEntity<RoomDTO> roomData(String hotelId, String roomNumber) {
        return service.getRoomData(hotelId, roomNumber);
    }

    @GetMapping("/get-all-user-reservation")
    public ResponseEntity<List<ReservationDTO>> userReservation(String token) {
        return service.findAllReservationWithUserId(token);
    }

    @GetMapping("delete-reservation")
    public ResponseEntity<String> remove(String reservationId) {
        return service.deleteReservation(reservationId);
    }
}
