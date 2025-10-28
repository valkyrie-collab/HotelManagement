package com.valkyrie.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valkyrie.entity.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
