package com.valkyrie.reservation.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valkyrie.reservation.config.TokenConfig;
import com.valkyrie.reservation.model.BookedRooms;
import com.valkyrie.reservation.model.Reservation;
import com.valkyrie.reservation.model.Status;
import com.valkyrie.reservation.repository.ReservationRepository;

@Service
public class ReservationService {
    private TokenConfig config;
    private ReservationRepository reservationRepo;

    @Autowired
    private void setTokenConfig(TokenConfig config) {
        this.config = config;
    }

    @Autowired
    private void setReservationRepo(ReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    private String doDecoding(String word) {
        return new String(Base64.getDecoder().decode(word));
    }

    public ResponseEntity<String> saveReservation(String token, String reservationJsonString, String status) throws IOException {
        Reservation reservation = new ObjectMapper().readValue(doDecoding(reservationJsonString), Reservation.class);
        status = doDecoding(status);
        String username = config.getUsername(token);
        // boolean isAdmin = config.isAdmin(token);
        reservation.setReservationId(UUID.randomUUID().toString()).setUserId(username);
        reservation.setStatus(Status.CONFIRMED).setBookedAt(new Date());
        reservationRepo.save(reservation);

        return reservationRepo.checkReservation(reservation.getReservationId()) == 1? 
            ResponseEntity.status(HttpStatus.ACCEPTED).body(
                "The reservation is successfully of user " + username + " with reservation ID: " + reservation.getUserId()) :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The reservation is unsuccessful");
    }

    public ResponseEntity<String> cancelReservation(String reservationId) {
        reservationId = doDecoding(reservationId);
        Reservation reservation = reservationRepo.findById(reservationId).orElse(null);

        if (reservation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no such reservation");
        }

        reservation.setStatus(Status.CANCELLED);
        reservation.setCanceledAt(new Date());
        reservationRepo.save(reservation);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("canceled reservation....");

    }

    public ResponseEntity<List<BookedRooms>> getRooms() {
        List<BookedRooms> bookedRooms = reservationRepo.findBookedRooms();
        
        return bookedRooms.isEmpty()? 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : 
            ResponseEntity.status(HttpStatus.OK).body(bookedRooms);

    }

}
