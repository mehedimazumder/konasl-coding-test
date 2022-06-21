package com.general.template.service;

import com.general.template.domain.Role;
import com.general.template.domain.User;
import com.general.template.domain.dto.UserDTO;
import com.general.template.repository.RoleRepository;
import com.general.template.repository.UserRepository;
import com.general.template.service.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Load User Roles
        if (roleRepository.findById(1) == null)
             roleRepository.save(new Role(1,"ROLE_ADMIN"));
        else if (!roleRepository.findById(1).getName().equals("ROLE_ADMIN")) {
            Role oldAdminDate = roleRepository.findById(1);
            Role role = new Role();
            role.setId(oldAdminDate.getId());
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

        if (roleRepository.findById(2) == null)
            roleRepository.save(new Role(2,"ROLE_CLIENT"));
        else if (!roleRepository.findById(2).getName().equals("ROLE_CLIENT")) {
            Role oldAdminDate = roleRepository.findById(2);
            Role role = new Role();
            role.setId(oldAdminDate.getId());
            role.setName("ROLE_CLIENT");
            roleRepository.save(role);
        }

        if(userRepository.findByUsername("test") == null){
            UserDTO user = UserDTO.builder()
             .name("test user")
             .phone("01700000000")
            .username("test")
            .roleId(2L)
            .password("000000").build();
            userRepository.save(userMapper.map(user));
        }

    }
}