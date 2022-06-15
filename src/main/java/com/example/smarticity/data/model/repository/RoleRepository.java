package com.example.smarticity.data.model.repository;

import com.example.smarticity.data.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(String name);

    List<Role> findAll();

}
