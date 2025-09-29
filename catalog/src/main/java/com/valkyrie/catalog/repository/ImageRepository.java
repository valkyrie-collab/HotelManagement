package com.valkyrie.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valkyrie.catalog.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
