package com.example.smarticity.data.model.repository;

import com.example.smarticity.data.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    @Query(value = "SELECT name FROM Permission ")
    Set<String> findAllAsSet();

//    void deleteWhereNotIn(List<String> permissions);

}
