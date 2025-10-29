package com.valkyrie.entity.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.valkyrie.entity.model.BasicDetails;
import com.valkyrie.entity.model.EntityDTO;
import com.valkyrie.entity.service.EntityService;

@RestController
@RequestMapping("/entity")
public class EntityController {
    private EntityService service;
    @Autowired
    private void setService(EntityService service) {
        this.service = service;
    }

    @PostMapping("/add-entity")
    public ResponseEntity<String> addEntity(@RequestParam String username) {
        return service.addEntity(username);
    }

    @PostMapping("/update-entity")
    public ResponseEntity<String> updateEntity(@RequestParam String token, @RequestParam String EntityJsonString, @RequestPart MultipartFile profileImage) throws IOException {
        return service.updateEntity(token, EntityJsonString, profileImage);
    }

    @GetMapping("/fetch-entity")
    public ResponseEntity<EntityDTO> getEntityProfile(@RequestParam String token) {
        return service.fetchEntity(token);
    }

    @GetMapping("/fetch-all-basic-entity-details")
    public ResponseEntity<List<BasicDetails>> getBasicDetails(@RequestParam String token) {
        return service.getEntityBasicDetails(token);
    }

    @DeleteMapping("/remove-account")
    public ResponseEntity<String> removeAccount(@RequestParam String token, @RequestParam(required = false) String userId) {
        return service.removeAccount(token, userId);
    }

}
