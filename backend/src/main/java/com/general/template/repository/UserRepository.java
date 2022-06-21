package com.general.template.repository;

import com.general.template.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User getFirstById(Long id);
}