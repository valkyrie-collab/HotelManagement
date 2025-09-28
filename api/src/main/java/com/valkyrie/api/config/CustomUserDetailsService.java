package com.valkyrie.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.valkyrie.api.model.User;
// import com.valkyrie.authentication.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    // private UserRepository userRepository;
    // @Autowired
    // private void setUserRepository(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }
    
    private AuthenticationFeignController feign;

    @Autowired
    private void setFeign(AuthenticationFeignController feign) {
        this.feign = feign;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<User> response = feign.getUser(username);

        if (response == null || 
            !response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
                throw new UsernameNotFoundException("USER-404");
        }

        return CustomUserDetails.initialize(response.getBody());
    }

}
