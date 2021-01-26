package com.horriblehades.toxictalks.repos;

import com.horriblehades.toxictalks.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByActivationCode(String code);

    Optional<User> findById(Long id);

}
