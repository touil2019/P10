package com.clientui.beans;

import com.clientui.security.UtiRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean de l utilisateur
 */
public class UtilisateurBean implements UserDetails {
    /**
     * identifiant de l utilisateur
     */
    private Long id;
    /**
     * nom de l utilisateur
     */
    private String username;
    /**
     * adresse email de l utilisateur
     */
    private String email;
    /**
     * mot de passe de l utilisateur
     */
    private String password;
    /**
     * Role de l utilisateur
     */
    private Set<UtiRole> userRoleList;


    public UtilisateurBean(){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public Set<UtiRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(Set<UtiRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void grantAuthority(UtiRole authority) {
        if ( userRoleList == null ) userRoleList = new HashSet<>();
        userRoleList.add(authority);
    }
    @Override
    public String toString(){
        return "UtilisateurLivre{"+
                "id=" + id +
                ", username=" + username +
                ", email=" + email +
                ", password=" + password +"}";
    }
}