package com.valkyrie.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valkyrie.catalog.model.Rate;

public interface RateRepository extends JpaRepository<Rate, String> {

}
