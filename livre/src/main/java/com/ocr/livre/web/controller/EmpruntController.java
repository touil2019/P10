package com.ocr.livre.web.controller;

import com.ocr.livre.LivreApplication;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.service.EmpruntService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * controleur emprunt du microservice
 */

@RestController
public class EmpruntController {

    private static final Logger logger = LogManager.getLogger(LivreApplication.class);

    @Autowired
    EmpruntService empruntService ;

    /**
     * retrouver un emprunt par son identifiant
     * @param idEmprunt
     * @return l'emprunt par l idEmprunt
     */

    @GetMapping(value = "/emprunt/id/{idEmprunt}")
    public Optional<Emprunt> findById(@RequestParam Long idEmprunt){
        logger.debug("Appel controlleur findById emprunt");
        return empruntService.findById(idEmprunt);
    }

    /**
     *
     * @param pseudoEmprunteur
     * @return une liste d emprunt par pseudo emprunteur
     */
    @GetMapping(value = "/emprunt/pseudo/{pseudoEmprunteur}")
    public List<Emprunt> listeEmpruntUtilisateur(@PathVariable("pseudoEmprunteur") String pseudoEmprunteur){
        logger.debug("Appel controlleur listeEmpruntUtilisateur");
        return empruntService.findAllByPseudoEmprunteur(pseudoEmprunteur) ;
    }

    /**
     * controleur pour prolonger un emprunt
     * @param idEmprunt
     * @return
     */
    @PutMapping(value = "/emprunt/{id}/prolonger")
    public ResponseEntity<Emprunt> prolongerEmprunt(@PathVariable("id")Long idEmprunt){

        logger.debug("Appel controlleur prolongerEmprunt");

        Emprunt emprunt = empruntService.prolongerEmprunt(idEmprunt);

        if (emprunt != null){
            return ResponseEntity.ok(emprunt);
        }
        return new ResponseEntity(
                "Ne peut pas être prolongé",
                HttpStatus.BAD_REQUEST);
    }

    /**
     * controleur pour l emprunt d un livre
     * @param idLivre
     * @param pseudoEmprunteur
     * @return
     */
    @PostMapping(value = "/emprunt/{id}")
    public Emprunt ouvrirEmprunt(@PathVariable("id") Long idLivre, @RequestParam String pseudoEmprunteur){

        logger.debug("Appel controlleur ouvrirEmprunt");

        return empruntService.ouvrirEmprunt(idLivre, pseudoEmprunteur);
    }

    /**
     * cloture d un emprunt
     * @param idEmprunt
     * @return
     */
    @PutMapping(value = "emprunt/{id}/cloturer")
    public Emprunt cloturerEmprunt(@PathVariable("id") Long idEmprunt){
        logger.debug("Appel controlleur cloturerEmprunt");
        return empruntService.cloturerEmprunt(idEmprunt);
    }

    /**
     * liste des livres non rendu apres la date de fin
     * @return
     */
    @GetMapping(value = "/emprunt")
    public List<Emprunt> listeLivreNonRendueApresDateFin(){
        logger.debug("Appel controlleur listeLivreNonRendueApresDateFin");

        return empruntService.listeLivreNonRendueApresDateFin();
    }
}

