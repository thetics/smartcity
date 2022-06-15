package com.example.smarticity.data.service.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel{

    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private Set<RoleServiceModel> roles;

    public UserServiceModel() {
    }

    @NotEmpty(message = "Username name cannot be empty")
    @Size(min = 2, max = 30, message = "Username should be between 2 and 30")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 1, max = 10, message = "Password should be between 1 and 10")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "Password cannot be empty")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Set<RoleServiceModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleServiceModel> roles) {
        this.roles = roles;
    }
}
