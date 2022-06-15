package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.data.model.entity.Permission;
import com.example.smarticity.data.model.entity.Role;
import com.example.smarticity.data.model.repository.PermissionRepository;
import com.example.smarticity.data.model.repository.RoleRepository;
import com.example.smarticity.data.service.models.RoleServiceModel;
import com.example.smarticity.data.service.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void seedRolesInDb() {
        this.roleRepository.saveAndFlush(new Role("ADMIN"));
        this.roleRepository.saveAndFlush(new Role("USER"));
    }

    @Override
    public void setPermissionsInDb() {

        if (this.permissionRepository.count() > 0) {

            //SETTING PERMISSIONS FOR ADMIN ROLE
            Role admin = this.roleRepository.findByName("ADMIN");
            Set<Permission> perms = new HashSet<>(permissionRepository.findAll());
            admin.setPermissions(perms);


            //SETTING PERMISSIONS FOR USER ROLE
            List<Permission> newList = new ArrayList<>();
            Role user = this.roleRepository.findByName("USER");
            for (Permission p : permissionRepository.findAll()) {
                if (p.getName().equals("ReservationController.create") ||
                        p.getName().equals("ReviewController.create")) {
                    newList.add(p);
                } else if (p.getName().contains("get")) {
                    newList.add(p);
                }
            }
            Set<Permission> userPerms = new HashSet<>(newList);
            user.setPermissions(userPerms);


            //SETTING PERMISSIONS FOR MODERATOR ROLE
            List<Permission> modList = new ArrayList<>();
            Role moderator = this.roleRepository.findByName("MODERATOR");
            for (Permission p : permissionRepository.findAll()) {
                if (p.getName().contains("create") ||
                        p.getName().contains("get") ||
                        p.getName().contains("edit")) {
                    modList.add(p);
                }
            }
            Set<Permission> modPerms = new HashSet<>(modList);
            moderator.setPermissions(modPerms);

        }
    }

    @Override
    public RoleServiceModel findByName(String name) {
        return this.modelMapper.map(this.roleRepository.findByName(name), RoleServiceModel.class);
    }
}
