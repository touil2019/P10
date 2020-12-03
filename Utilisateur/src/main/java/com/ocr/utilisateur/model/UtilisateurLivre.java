package com.ocr.utilisateur.model;


import com.ocr.utilisateur.security.BCryptEncoderConfig;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UtilisateurLivre implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Size(max=50)
    @NotBlank
    @NotEmpty( message = "Merci de saisir un pseudo" )
    private String username;
    @Size(max=100)
    @NotBlank
    @Email( message = "Merci de saisir une adresse mail valide." )
    @NotEmpty( message = "Merci de saisir une adresse email" )
    private String email;
    @Size(max=100)
    @NotBlank
    private String password;

    @ElementCollection(targetClass = UtiRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UtiRole> userRoleList;

    public UtilisateurLivre() {
    }

    public UtilisateurLivre(@Size(max=50)
                    @NotBlank
                    @NotEmpty( message = "Merci de saisir un pseudo" )String username,
                            @Size(max=100)
                    @NotBlank
                    @Email( message = "Merci de saisir une adresse mail valide." )
                    @NotEmpty( message = "Merci de saisir une adresse email" )String email,
                            @Size(max=100)
                    @NotBlank
                            String password){

        this.username = username;
        this.email = email;
        this.password = BCryptEncoderConfig.passwordencoder().encode(password);

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void grantAuthority() {
        if ( userRoleList == null ) userRoleList = new HashSet<>();
        userRoleList.add(UtiRole.USER);
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