package com.login_page.security;


import com.login_page.entity.Roles;
import com.login_page.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login_page.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("first printing username="+userName);

        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return null;
        }
        System.out.println("print User: "+  user.toString());
        System.out.println("printing username="+userName+"|user.pass="+user.getPassword());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();


        for (Roles role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }


        //grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));//TODO переделать
        System.out.println("return="+new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordFromPass(), grantedAuthorities);
    }



}
