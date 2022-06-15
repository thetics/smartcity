package com.example.smarticity.data.model.entity;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity {

    private String name; // ControllerName.Perm_Get
    private Set<Role> roles = new HashSet<>();


    public Permission() {
    }

    public Permission(String name) {
        this.name = name;
    }


    @NonNull
    @NotNull
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @NotNull
    @Column(name = "name",unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
