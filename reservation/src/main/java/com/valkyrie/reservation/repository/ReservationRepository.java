package com.valkyrie.reservation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valkyrie.reservation.model.BookedRooms;
import com.valkyrie.reservation.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query(value = "select 1 from reservation where reservation_id = :reservationId", nativeQuery = true)
    Integer checkReservation(@Param("reservationId") String reservationId);

    @Query(value = "select room_number, hotel_id from reservation where hotel_id = :hotelId", nativeQuery = true)
    List<BookedRooms> findBookedRooms(@Param("hotelId") String hotelId);

    @Query(value = "select * from reservation where user_id = :userId", nativeQuery = true)
    List<Reservation> findAllReservationWithUserId(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query(value = "update reservation set check_in = :checkIn where reservation_id = :reservationId", nativeQuery = true)
    int updateCheckIn(@Param("checkIn") Date checkIn, @Param("reservationId") String reservationId);

    @Modifying
    @Transactional
    @Query(value = "update reservation set check_out = :checkOut where reservation_id = :reservationId", nativeQuery = true)
    int updateCheckOut(@Param("checkOut") Date checkOut, @Param("reservationId") String reservationId);
}
