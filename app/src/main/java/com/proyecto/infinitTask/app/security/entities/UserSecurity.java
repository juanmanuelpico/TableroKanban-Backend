package com.unla.Beneficiarios.security.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.unla.Beneficiarios.entities.User;

import java.util.Collection;
import java.util.Collections;

public class UserSecurity implements UserDetails {

    private String username;
    private String password;

    public UserSecurity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserSecurity build(User user) {
        return new UserSecurity(user.getUsername(), user.getPassword());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}