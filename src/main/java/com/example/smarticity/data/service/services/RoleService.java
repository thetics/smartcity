package com.example.smarticity.data.service.services;


import com.example.smarticity.data.service.models.RoleServiceModel;

public interface RoleService  {
    void  seedRolesInDb();

    void setPermissionsInDb();

    RoleServiceModel findByName(String name);

}
