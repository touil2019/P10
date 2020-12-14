package com.ocr.livre.web.controller;


import com.ocr.livre.LivreApplication;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Reservation;
import com.ocr.livre.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
