package com.valkyrie.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valkyrie.reservation.model.BookedRooms;
import com.valkyrie.reservation.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query(value = "select 1 from reservation where reservation_id = :reservationId", nativeQuery = true)
    Integer checkReservation(@Param("reservationId") String reservationId);

    @Query(value = "select room_number, hotel_id from reservation", nativeQuery = true)
    List<BookedRooms> findBookedRooms();
}
