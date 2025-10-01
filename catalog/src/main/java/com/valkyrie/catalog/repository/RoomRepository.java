package com.valkyrie.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valkyrie.catalog.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
