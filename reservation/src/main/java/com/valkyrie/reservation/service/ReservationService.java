package com.valkyrie.reservation.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valkyrie.reservation.config.CatalogFeignController;
import com.valkyrie.reservation.config.TokenConfig;
import com.valkyrie.reservation.model.BookedRooms;
import com.valkyrie.reservation.model.Reservation;
import com.valkyrie.reservation.model.ReservationDTO;
import com.valkyrie.reservation.model.RoomDTO;
import com.valkyrie.reservation.model.Status;
import com.valkyrie.reservation.repository.ReservationRepository;

@Service
public class ReservationService {
    private TokenConfig config;
    private ReservationRepository reservationRepo;
    private CatalogFeignController feign;

    @Autowired
    private void setTokenConfig(TokenConfig config) {
        this.config = config;
    }

    @Autowired
    private void setReservationRepo(ReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Autowired
    private void setFeign(CatalogFeignController feign) {this.feign = feign;}

    private String doDecoding(String word) {
        return new String(Base64.getDecoder().decode(word));
    }

    public ResponseEntity<String> saveReservation(String token, String reservationJsonString) throws IOException {
        Reservation reservation = new ObjectMapper().readValue(doDecoding(reservationJsonString), Reservation.class);
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
        reservation.setCanceledAt(new Date(System.currentTimeMillis()));
        reservationRepo.save(reservation);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("canceled reservation....");

    }

    public ResponseEntity<List<BookedRooms>> getRooms(String hotelId) {
        List<BookedRooms> bookedRooms = reservationRepo.findBookedRooms(doDecoding(hotelId));
        ResponseEntity<List<RoomDTO>> rooms = feign.getRooms(hotelId);

        if (rooms == null || !rooms.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (bookedRooms.isEmpty()) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);}

        List<RoomDTO> roomDTOs = rooms.getBody();

        if (roomDTOs == null || roomDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Collections.sort(
            bookedRooms, new Comparator<BookedRooms>() {
                @Override
                public int compare(BookedRooms roomOne, BookedRooms roomTwo) {
                    Integer a = roomOne.getRoomNumber();
                    Integer b = roomTwo.getRoomNumber();
                    return a.compareTo(b);
                }
            }
        );

        Collections.sort(
            roomDTOs, new Comparator<RoomDTO>() {
                @Override
                public int compare(RoomDTO roomDTOOne, RoomDTO roomDTOTwo) {
                    Integer a = roomDTOOne.getRoomNumber();
                    Integer b = roomDTOTwo.getRoomNumber();
                    return a.compareTo(b);
                }
            }
        );
        
        int i = 0; int j = 0;
        while (i < roomDTOs.size() && j < bookedRooms.size()) {
            RoomDTO roomDTO = roomDTOs.get(i++);
            BookedRooms bookedRoom = bookedRooms.get(j);

            if (roomDTO.getRoomNumber() == bookedRoom.getRoomNumber()){j++;}
            else {
                bookedRooms.add(new BookedRooms().setRoomNumber(
                    roomDTO.getRoomNumber()).setHotelId(hotelId));
            }

        } 
        
        return ResponseEntity.status(HttpStatus.OK).body(bookedRooms);

    }

    public ResponseEntity<RoomDTO> getRoomData(String hotelId, String roomNumber) {
        ResponseEntity<RoomDTO> response = feign.getRoom(hotelId, roomNumber);

        if (response == null || !response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
    }

    public ResponseEntity<List<ReservationDTO>> findAllReservationWithUserId(String token) {
        String username = config.getUsername(token);
        List<Reservation> reservations = reservationRepo.findAllReservationWithUserId(username);
        List<ReservationDTO> reservationDTOs = new LinkedList<>();

        if (reservations.isEmpty()) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);}
        
        for (Reservation reservation : reservations) {
            reservationDTOs.add(new ReservationDTO().setBookedAt(reservation.getBookedAt())
                .setBookingId(reservation.getReservationId()).setCanceledAt(reservation.getCanceledAt())
                .setCheckIn(reservation.getCheckIn()).setCheckOut(reservation.getCheckOut())
                .setHotelId(reservation.getHotelId()).setModifiedAt(reservation.getModifiedAt())
                .setNight(reservation.getNight()).setOccupancy(reservation.getOccupancy()[0], reservation.getOccupancy()[1])
                .setPerNightCost(reservation.getPerNightCost()).setRoomId(reservation.getRoomNumber())
                .setSpecialRequest(reservation.getSpecialRequest()).setStatus(reservation.getStatus())
                .setTotalAmount(reservation.getTotalAmount()).setTypeId(reservation.getTypeId())
                .setUserId(reservation.getUserId()));
        } 

        return ResponseEntity.status(HttpStatus.OK).body(reservationDTOs);
    }

    @Transactional
    public ResponseEntity<String> setCheckIn(String reservationId, String username) {
        reservationId = doDecoding(reservationId);
        username = doDecoding(username);
        Integer presentReservation = reservationRepo.checkReservation(reservationId);

        if (presentReservation != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No reservation found");
        }

        int affectedRows = reservationRepo.updateCheckIn(new Date(System.currentTimeMillis()), reservationId);

        return (affectedRows > 0)? ResponseEntity.status(HttpStatus.ACCEPTED).body("Check In success " + username) : 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check In not updated...");
        
    }

    @Transactional
    public ResponseEntity<String> setCheckOut(String reservationId, String username) {
        reservationId = doDecoding(reservationId);
        username = doDecoding(username);
        reservationId = doDecoding(reservationId);
        username = doDecoding(username);
        Integer presentReservation = reservationRepo.checkReservation(reservationId);

        if (presentReservation != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No reservation found");
        }

        int affectedRows = reservationRepo.updateCheckOut(new Date(System.currentTimeMillis()), reservationId);

        return (affectedRows > 0)? ResponseEntity.status(HttpStatus.ACCEPTED).body("Check In success " + username) : 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check In not updated...");
        
    }

    @Transactional
    public ResponseEntity<String> deleteReservation(String reservationId) {
        reservationId = doDecoding(reservationId);

        if (reservationRepo.checkReservation(reservationId) != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already been deleted....");
        }

        reservationRepo.deleteById(reservationId);
        
        return reservationRepo.checkReservation(reservationId) != 1? 
            ResponseEntity.status(HttpStatus.OK).body("removed reservation") : 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("deletion is unsuccessful....");
    }

}
