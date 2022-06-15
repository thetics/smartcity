package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.model.entity.Hotel;
import com.example.smarticity.data.model.entity.Role;
import com.example.smarticity.data.model.entity.User;
import com.example.smarticity.data.model.repository.RoleRepository;
import com.example.smarticity.data.model.repository.UserRepository;
import com.example.smarticity.data.service.models.HotelServiceModel;
import com.example.smarticity.data.service.models.UserServiceModel;
import com.example.smarticity.data.service.services.RoleService;
import com.example.smarticity.data.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void setUserRole(String id, String role) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect id!"));

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);

        //TODO DELETE FROM ROLES PERMISSIONS
        userServiceModel.getRoles().clear();

        if (role.equals("ADMIN")) {
            userServiceModel.getRoles().add(this.roleService.findByName("ADMIN"));
        }else if (role.equals("MODERATOR")){
            userServiceModel.getRoles().add(this.roleService.findByName("MODERATOR"));
        }else if (role.equals("USER")){
            userServiceModel.getRoles().add(this.roleService.findByName("USER"));
        }



        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public User register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        if (this.userRepository.count() == 1) {
            roleService.setPermissionsInDb();
            user.setRoles(new HashSet<>(Set.of(this.roleRepository.findByName("USER"))));

        } else if (this.userRepository.count() == 0) {
            user.setRoles(new HashSet<>());
            user.setRoles(Set.of(this.roleRepository.findByName("ADMIN")));
        } else {
            user.setRoles(new HashSet<>(Set.of(this.roleRepository.findByName("USER"))));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));

    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findUserById(id);

user.getRoles().clear();
        this.userRepository.delete(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
    }


}
