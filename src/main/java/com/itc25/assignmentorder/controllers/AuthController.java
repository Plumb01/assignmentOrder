package com.itc25.assignmentorder.controllers;

import com.itc25.assignmentorder.dtos.RegistrationDto;
import com.itc25.assignmentorder.dtos.RestResponse;
import com.itc25.assignmentorder.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService service;

    // {{base_url}}/auth
    @GetMapping
    public Authentication index(Authentication authentication){
        return authentication;
    }



    @PostMapping("register")
    public ResponseEntity<RestResponse<Boolean>> registration(String memberId,@RequestBody RegistrationDto registrant){
        return ResponseEntity.ok().body(new RestResponse<>(service.registration(memberId,registrant)
                ,"user account berhasil dibuat",
                "201"));
    }

}
