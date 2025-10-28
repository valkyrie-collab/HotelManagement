package com.valkyrie.entity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valkyrie.entity.repository.EntityRepository;

@Service
public class EntityService {
    private EntityRepository entityRepo;
    @Autowired
    private void setEntityRepo(EntityRepository entityRepo) {
        this.entityRepo = entityRepo;
    }

}
