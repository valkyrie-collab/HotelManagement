package com.valkyrie.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valkyrie.entity.model.BasicDetails;
import com.valkyrie.entity.model.Entities;
import com.valkyrie.entity.model.Image;

@Repository
public interface EntityRepository extends JpaRepository<Entities, String> {

    @Query(value = "select case when exists (select 1 from entities where id = :userId) then true else false end", nativeQuery = true)
    boolean checkEntityPresent(@Param("userId") String userId);

    @Query(value = "select id, first_name, last_name, address, email, phone_number from entities", nativeQuery = true)
    List<BasicDetails> getEntityBasicDetails();

    @Query(value = "select * from image where entity_id = :userId", nativeQuery = true)
    Image getProfileImage(@Param("userId") String userId);

}
