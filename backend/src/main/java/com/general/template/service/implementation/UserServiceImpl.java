package com.general.template.service.implementation;

import com.general.template.common.exception.UserNotFoundException;
import com.general.template.common.util.PasswordUtil;
import com.general.template.domain.User;
import com.general.template.domain.dto.UserDTO;
import com.general.template.repository.UserRepository;
import com.general.template.service.UserService;
import com.general.template.service.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    @Override
    public User readByUsername(String username) throws UserNotFoundException {
        System.out.println("From username in impl:" + username);
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isPasswordMatches(User user, String password) throws Exception {
        /**
         * Check if password matches with shaencoder, if matches encode it with bicrypt and save to the database.
         */
        if (PasswordUtil.encryptPassword(password).equals(user.getPassword())) {
            user.setPassword(PasswordUtil.encryptPassword(password));
            user = userRepository.save(user);
        }

        return PasswordUtil.getPasswordEncoder().matches(password, user.getPassword());
    }

    @Override
    public UserDTO create(UserDTO dto) throws Exception {
        User user = userRepository.findByUsername(dto.getUsername());

        if(user != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists");

        user = userRepository.save(userMapper.map(dto));
        return modelMapper.map(user, UserDTO.class);
    }
}