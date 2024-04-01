package com.unla.Beneficiarios.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.unla.Beneficiarios.entities.User;
import com.unla.Beneficiarios.security.entities.UserSecurity;
import com.unla.Beneficiarios.services.IUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserService.findByUsername(username).get();
        return UserSecurity.build(user);
    }
}