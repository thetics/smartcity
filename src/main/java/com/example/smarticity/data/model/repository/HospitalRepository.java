package com.example.smarticity.data.model.repository;

import com.example.smarticity.data.model.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, String> {

    Optional<Hospital> findByName(String name);

    List<Hospital> findAll();

}
