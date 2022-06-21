package com.general.template.controller;

import com.general.template.domain.dto.UserDTO;
import com.general.template.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserController {
    private final UserService userService;

    @PostMapping("/profile")
    public UserDTO createProfile(@RequestBody UserDTO dto) throws Exception {
        UserDTO res = userService.create(dto);
        res.setPassword(null);
        return res;
    }
}
