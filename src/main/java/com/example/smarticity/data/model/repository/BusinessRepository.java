package com.example.smarticity.data.model.repository;

import com.example.smarticity.data.model.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {

    Optional<Business> findByName(String name);

}
