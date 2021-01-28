package com.ocr.livre.web.controller;


import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.model.Livre;
import com.ocr.livre.service.LivreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlleur Livre du microservice
 */
@RestController
public class LivreController {

    private static final Logger logger = LogManager.getLogger(LivreController.class);

    @Autowired
    private LivreDao livreDao;


    @Autowired
    LivreService livreService ;




    /**
     * recuperer une liste de livres
     * @return
     */
    @GetMapping(value = "/Livres")
    public List<Livre> listeLivre(){
        return livreService.findAll();
    }

    /**
     * recherche multicritere d un livre
     * @param mc
     * @return
     */
    @GetMapping(value = "/listeRecherche")
    public List<Livre> listeLivreRecherche(@RequestParam(name = "mc",defaultValue = "")String mc){

        return livreService.findByTitreContainingIgnoreCase(mc);
    }

    /**
     * rechercher un livre par son id
     * @param idLidvre
     * @return
     */
    @GetMapping(value = "/Livre/{id}")
    public Livre findById(@PathVariable("id")Long idLidvre){
        return livreService.findLivreById(idLidvre);
    }

    /**
     * enregistrer un nouveau livre
     * @param livre
     * @return
     */
    @PostMapping(value ="/livre/enregistrer")
    public Livre enregistrerNouveauLivre(@RequestBody Livre livre){
         return livreService.enregistrerNouveauLivre(livre);
    }

    /**
     * supprimer un livre
     * @param idLivre
     */
    @GetMapping(value = "/livre/supprimer/{id}")
    public void supprimerLivre(@PathVariable("id") Long idLivre){
       livreService.supprimerLivre(idLivre);
    }


    @GetMapping(value = "/Livre/{id}/utilisateur/{pseudo}")
    public Livre recupererUnLivreParUtilisateur(@PathVariable("id") Long id,@PathVariable("pseudo") String pseudo){
        return livreService.recupererUnLivreParUtilisateur(id,pseudo);
    }

    @RequestMapping(value = "/addLivre",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String ajouterUnLivre(@RequestBody Livre livre){
        livreService.save(livre);
        return "Livre ajouté";
    }
}

