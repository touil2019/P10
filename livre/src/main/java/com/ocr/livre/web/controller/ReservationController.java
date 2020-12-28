package com.ocr.livre.web.controller;


import com.ocr.livre.model.Reservation;
import com.ocr.livre.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private static final Logger logger = LogManager.getLogger(ReservationController.class);

    @Autowired
    ReservationService reservationService;

    @GetMapping(value = "/reservation/pseudo/{pseudoEmprunteur}")
    public List<Reservation> listeReservationUtilisateur(@PathVariable("pseudoEmprunteur") String pseudoEmprunteur){

        logger.debug("Appel controlleur ListeReservationByPseudoEmprunteur");
        return reservationService.findReservationByPseudoEmprunteur(pseudoEmprunteur);
    }

    @GetMapping(value="/reservation/pseudo/{pseudoEmprunteur}/delete")
    public void deleteById(@PathVariable("id") Long id){
        reservationService.deleteById(id);
    }

    @GetMapping(value="/reservation/{id}/pseudo/{pseudoEmprunteur}/annuler")
    public void annulerReservation(@PathVariable("id") Long id,@PathVariable("pseudoEmprunteur") String pseudoEmprunteur){

        reservationService.annulerReservation( id,pseudoEmprunteur);
    }


    @PostMapping("/reservation/livre/{id}/utilisateur/{pseudoEmprunteur}")
    void creerUneReservation(@PathVariable("id") long id,@PathVariable("pseudoEmprunteur") String pseudoEmprunteur){

        reservationService.creerUneReservation(id,pseudoEmprunteur);
    }

}
