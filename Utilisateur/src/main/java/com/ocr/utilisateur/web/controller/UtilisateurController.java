package com.ocr.utilisateur.web.controller;


import com.ocr.utilisateur.dao.UtilisateurDao;
import com.ocr.utilisateur.model.UtilisateurLivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    /**
     * liste d'utilisateurs
     * @return tous les utilisateurs
     */

    @GetMapping("/listeUtilisateur")
    public List<UtilisateurLivre> utilLivreList (){
        List<UtilisateurLivre> utilLivres = utilisateurDao.findAll();

        return utilLivres;
    }

    /**
     * trouver un utilisateur par son username
     * @param username
     * @return
     */
    @GetMapping("/utilisateur/{username}")
    public UtilisateurLivre connexion (@PathVariable String username){
        Optional<UtilisateurLivre> u = utilisateurDao.findByUsername(username.toLowerCase());
        UtilisateurLivre utilisateurLivre=null;
        if (u.isPresent()) {
            utilisateurLivre = u.get();
        }
        return utilisateurLivre;
    }



}

