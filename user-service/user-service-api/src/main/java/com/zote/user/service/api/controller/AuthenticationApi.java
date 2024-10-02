package com.zote.user.service.api.controller;

import com.zote.user.service.api.request.AuthRequest;
import com.zote.user.service.api.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API")
@RestController
@RequestMapping("/")
public interface AuthenticationApi {

    @Operation(summary = "Authenticate a user")
    @PostMapping("authenticate")
    AuthResponse login(@RequestBody AuthRequest request);
}
