package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.Annotations.DefinePermissions;
import com.example.smarticity.data.model.entity.Permission;
import com.example.smarticity.data.model.entity.Role;
import com.example.smarticity.data.model.entity.User;
import com.example.smarticity.data.model.repository.PermissionRepository;
import com.example.smarticity.data.model.repository.UserRepository;
import com.example.smarticity.data.service.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.lang.NonNull;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements ApplicationListener<WebServerInitializedEvent>, PermissionEvaluator {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionServiceImpl.class);
    private static final String API_CONTROLLERS_PACKAGE = "com.example.smarticity.web.controller";
    private final RoleService roleService;

    @Autowired
    public PermissionServiceImpl(UserRepository userRepository, PermissionRepository permissionRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.roleService = roleService;
    }


    /**
     * This is done after the server is started successfully
     * Take all controllers with the @DefinePermissions annotation and get their permissions
     * Look for any permission in db and if nothing is found, create a new permission
     */
    @Override
    public void onApplicationEvent(@NonNull WebServerInitializedEvent event) {
        List<String> permissions = new ArrayList<>();
        Set<String> permissionsFromDb = permissionRepository.findAllAsSet();

        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true, new StandardServletEnvironment());

        for (BeanDefinition beanDefinition : provider.findCandidateComponents(API_CONTROLLERS_PACKAGE)) { // For each all Controllers
            try {
                Class<?> aClass = Class.forName(beanDefinition.getBeanClassName()); // Get Controller
                Annotation annotation = aClass.getAnnotation(DefinePermissions.class); // Get Controller DefinePermissions Annotation

                if (annotation == null) {
                    continue;
                }

                // Get All permissions for Controller
                String[] permissionsFromControllers = Class.forName(beanDefinition.getBeanClassName()).getAnnotation(DefinePermissions.class).permissions();
                for (String permission : permissionsFromControllers) {
                    String name = aClass.getSimpleName() + "." + permission;
                    permissions.add(name);

                    // Insert only if permission is not in db
                    if (!permissionsFromDb.contains(name)) { // todo
                        permissionRepository.save(new Permission(name));
                    }
                }
            } catch (ClassNotFoundException e) {
                LOGGER.warn("Could not resolve class object for bean definition", e);
            }


        }

//        permissionRepository.deleteWhereNotIn(permissions); // todo Clear old Permissions
        permissionRepository.flush();

    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return hasPermission(targetDomainObject + "." + permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        LOGGER.warn("Not implemented");
        return false;
    }

    /**
     * Checks the permissions of the current logged  user
     * If it doesn't find an admin, return false
     */
    private boolean hasPermission(String permission) {
        User currentLoggedUser = getCurrentLoggedUser();
        if (currentLoggedUser == null) {
            return false;
        }

        Set<Role> roles = currentLoggedUser.getRoles();

        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            for (Permission p : permissions) {
                if (p.getName().equals(permission)) {
                    return true;
                }
            }
        }


        return false;

//        Permissions permToCheck = new Permissions(permission);
//
//        return roles.stream().anyMatch(r -> r.getPermissions().contains(permToCheck)); // Check the roles of the current logged user
    }

    /**
     * It can be used in other places in the future
     */
    public User getCurrentLoggedUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }

}
