package com.example.smarticity.data.model.repository;

import com.example.smarticity.data.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //TODO UNCOMMENT
    Optional<User> findByUsername(String username);
//    User findByUsername(String username);

    User findUserById(String id);

}
