package com.ocr.utilisateur.dao;


import com.ocr.utilisateur.model.UtilisateurLivre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurDao extends JpaRepository<UtilisateurLivre, Long> {

   /**
    * trouver tous les utilsateurs
    * @return une liste d utilisateurs
    */
   List<UtilisateurLivre> findAll();

   /**
    * trouver un utilisateurs par son username
    * @param username
    * @return un utilisateur par son username
    */
   Optional<UtilisateurLivre> findByUsername(String username);
}
