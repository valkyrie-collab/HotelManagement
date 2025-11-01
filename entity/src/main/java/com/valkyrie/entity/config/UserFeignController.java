package com.valkyrie.entity.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "AUTHENTICATION", configuration = AuthenticationByPass.class)
public interface UserFeignController {

    @DeleteMapping("/user/remove-user")
    public ResponseEntity<String> removeUser(@RequestParam String token);

}
