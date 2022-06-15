package com.example.smarticity.data.service.services;

import com.example.smarticity.data.model.entity.User;
import com.example.smarticity.data.service.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends  UserDetailsService  {

    User register(UserServiceModel userServiceModel);

    UserServiceModel findUserByUserName(String username);

    List<UserServiceModel> findAllUsers();

    void setUserRole(String id, String role);


    void deleteUser(String id);
}
