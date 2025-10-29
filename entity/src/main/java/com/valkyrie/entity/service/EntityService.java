package com.valkyrie.entity.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valkyrie.entity.config.TokenConfig;
import com.valkyrie.entity.model.BasicDetails;
import com.valkyrie.entity.model.Entities;
import com.valkyrie.entity.model.EntityDTO;
import com.valkyrie.entity.model.Image;
import com.valkyrie.entity.model.ImageDTO;
import com.valkyrie.entity.repository.EntityRepository;
// import com.valkyrie.entity.repository.ImageRepository;

import jakarta.transaction.Transactional;

@Service
public class EntityService {
    private EntityRepository entityRepo;
    @Autowired
    private void setEntityRepo(EntityRepository entityRepo) {
        this.entityRepo = entityRepo;
    }

    private TokenConfig config;
    @Autowired
    private void setConfig(TokenConfig config) {
        this.config = config;
    }

    // private ImageRepository imageRepo;
    // @Autowired
    // private void setImageRepo(ImageRepository imageRepo) {
    //     this.imageRepo = imageRepo;
    // }

    private String doDecoding(String word) {return new String(Base64.getDecoder().decode(word));}

    @Transactional
    public ResponseEntity<String> addEntity(String username) {
        username = doDecoding(username);
        boolean exist = entityRepo.checkEntityPresent(username);

        if (exist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already exist plz try other username....");
        }

        entityRepo.save(new Entities().setId(username));
        exist = entityRepo.checkEntityPresent(username);

        return exist? ResponseEntity.status(HttpStatus.ACCEPTED).body("Entity saved...") : 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entity not saved check carefully......");

    }

    @Transactional
    public ResponseEntity<String> updateEntity(String token, String EntityJsonString, MultipartFile profileImage) throws IOException {
        String username = config.getUsername(token);
        boolean exist = entityRepo.checkEntityPresent(username);

        if (!exist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First add user.....");
        }

        Entities entity = new ObjectMapper().readValue(doDecoding(EntityJsonString), Entities.class);
        entity.setId(username);
        Image image = new Image().setData(profileImage.getBytes()).setType(profileImage.getContentType())
            .setName(profileImage.getOriginalFilename()).setEntity(entity);
        entity.setProfileImage(image);
        entityRepo.save(entity);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("profile updated successfully.....");

    }

    @Transactional
    public ResponseEntity<EntityDTO> fetchEntity(String token) {
        String username = config.getUsername(token);
        Entities entity = entityRepo.findById(username).orElse(null);
        boolean exist = entity == null;

        if (exist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Image profileImage = entity.getProfileImage();
        ImageDTO profileImageDTO = null;

        if (profileImage != null) {
            profileImageDTO = new ImageDTO().setData(profileImage.getData()).setId(profileImage.getId())
            .setName(profileImage.getName()).setType(profileImage.getType());
        }

        EntityDTO entityDTO = new EntityDTO().setAddress(entity.getAddress()).setBio(entity.getBio())
            .setEmail(entity.getEmail()).setFirstName(entity.getFirstName()).setId(entity.getId())
            .setLastName(entity.getLastName()).setPhoneNumber(entity.getPhoneNumber()).setProfileImage(profileImageDTO);
        
        return ResponseEntity.status(HttpStatus.OK).body(entityDTO);

    }

    @Transactional
    public ResponseEntity<List<BasicDetails>> getEntityBasicDetails(String token) {
        boolean isAdmin = config.isAdmin(token);

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<BasicDetails> basicDetails = entityRepo.getEntityBasicDetails();

        for (BasicDetails basicDetail : basicDetails) {
            Image profileImage = entityRepo.getProfileImage(basicDetail.getId());
            ImageDTO profile = null;

            if (profileImage != null) {
                profile = new ImageDTO().setData(profileImage.getData()).setId(profileImage.getId())
                    .setName(profileImage.getName()).setType(profileImage.getType());
            }

            basicDetail.setProfileImage(profile);

        }

        return basicDetails.isEmpty()? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : 
            ResponseEntity.status(HttpStatus.OK).body(basicDetails);

    }

    @Transactional
    public ResponseEntity<String> removeAccount(String token, String userId) {
        String username = null;

        if (userId == null) {
            username = config.getUsername(token);
        } else {
            boolean isAdmin = config.isAdmin(token);

            if (!isAdmin) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your not admin.....");
            }

            username = doDecoding(userId);
        }

        if (!entityRepo.checkEntityPresent(username)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already been deleted......");
        }

        entityRepo.deleteById(username);

        return entityRepo.checkEntityPresent(username)? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deletion is not successful....") : 
            ResponseEntity.status(HttpStatus.OK).body("Deletion successful.........");

    }

}
