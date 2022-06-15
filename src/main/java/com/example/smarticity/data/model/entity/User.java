package com.example.smarticity.data.model.entity;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity  {

    private String username;
    private String password;
    private Set<Role> roles;


    public User() {
    }

    @NonNull
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> authorities) {
        this.roles = authorities;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
