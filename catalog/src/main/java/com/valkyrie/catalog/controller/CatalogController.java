package com.valkyrie.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valkyrie.catalog.service.CatalogService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private CatalogService service;
    
    @Autowired
    private void setService(CatalogService service) {this.service = service;}

    

}
