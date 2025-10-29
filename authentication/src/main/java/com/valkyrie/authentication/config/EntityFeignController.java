package com.valkyrie.authentication.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ENTITY")
public interface EntityFeignController {

    @PostMapping("/entity/add-entity")
    public ResponseEntity<String> addEntity(@RequestParam String username);

}
