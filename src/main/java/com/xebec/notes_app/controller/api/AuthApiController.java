package com.xebec.notes_app.controller.api;

import com.xebec.notes_app.dto.RegisterDto;
import com.xebec.notes_app.service.UserService;
import com.xebec.notes_app.service.impl.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthApiController {

    private UserService userService;
    private JwtService jwtService;

    @PostMapping("register")
    public String registerUser(@RequestBody RegisterDto registerDto) {
        userService.register(registerDto);
        return jwtService.generateToken(registerDto.getUsername());
    }
}
