package com.example.smarticity.data.model.repository;

import com.example.smarticity.data.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

    Optional<Hotel> findByName(String name);

}
