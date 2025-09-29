package com.valkyrie.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valkyrie.catalog.model.Hotel;

@Repository
public interface CatalogRepository extends JpaRepository<Hotel, String> {

}
