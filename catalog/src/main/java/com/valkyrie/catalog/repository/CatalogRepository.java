package com.valkyrie.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

import com.valkyrie.catalog.model.Hotel;
import com.valkyrie.catalog.model.HotelImage;
import com.valkyrie.catalog.model.Room;
import com.valkyrie.catalog.model.RoomImage;

@Repository
public interface CatalogRepository extends JpaRepository<Hotel, String> {

    @Query(value = "select 1 from hotel where id = :id", nativeQuery = true)
    Integer existedById(@Param("id") String id);

    // @Transactional
    // @Modifying
    // @Query(value = "update hotel set ")
    // void addRoom(@Param("roomId") String roomId);

    @Query(value = "select * from room where hotel_id like :hotelId ", nativeQuery = true)
    List<Room> getRoomsByHotelId(@Param("hotelId") String hotelId);

    @Query(value = "select * from room_image where room_id = :roomId", nativeQuery = true)
    List<RoomImage> getRoomsImages(@Param("roomId") String roomId);

    @Query(value = "select * from hotel", nativeQuery = true)
    List<Hotel> getAllHotel();

    @Query(value = "select * from hotel_image where hotel_id = :hotelId ", nativeQuery = true)
    List<HotelImage> getHotelImages(@Param("hotelId") String hotelId);
}
