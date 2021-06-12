package com.getir.rig.api;

import com.getir.rig.domain.model.security.JwtREQ;
import com.getir.rig.domain.model.security.JwtRES;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {

    @PostMapping("/authenticate")
    ResponseEntity<JwtRES> postAuthenticationToken(@RequestBody JwtREQ jwtREQ) throws Exception;
}
