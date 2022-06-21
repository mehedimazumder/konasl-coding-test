package com.general.template.service;

import com.general.template.common.exception.UserNotFoundException;
import com.general.template.domain.User;
import com.general.template.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create(UserDTO dto) throws Exception;

    User readByUsername(String username) throws UserNotFoundException;

    boolean isPasswordMatches(User user, String password) throws Exception;
}