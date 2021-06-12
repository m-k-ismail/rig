package com.getir.rig.security.controller;

import com.getir.rig.api.AuthenticationApi;
import com.getir.rig.domain.model.security.JwtREQ;
import com.getir.rig.domain.model.security.JwtRES;
import com.getir.rig.security.service.ApplicationUserDetailsService;
import com.getir.rig.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationApiImpl implements AuthenticationApi {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtTokenUtil;
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    AuthenticationApiImpl(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, ApplicationUserDetailsService userDetailsService){
        this.authenticationManager= authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public ResponseEntity<JwtRES> postAuthenticationToken(JwtREQ jwtREQ) throws Exception {
        authenticate(jwtREQ.getUsername(), jwtREQ.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(jwtREQ.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtRES(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
