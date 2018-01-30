package com.softeng.athleticsticket.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class MyUserDetails implements UserDetails {

    private User user;
    private HashSet<GrantedAuthority> authorities;

    public MyUserDetails(final User user){
        this.user = user;
        authorities = new HashSet<GrantedAuthority>(user.getRoles().size());
        user.getRoles().forEach((role)-> {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    public String getEmail(){
        return this.user.getEmail();
    }

    public String getLastName(){
        return this.user.getLastName();
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId(){
        return user.getId();
    }

}
