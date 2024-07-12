package com.example.Security1.repository;

//import com.helloIftekhar.springJwt.model.User;
import com.example.Security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
