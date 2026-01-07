package com.xebec.notes_app.controller.api;

import com.xebec.notes_app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthApiController {

    private UserService userService;


}
