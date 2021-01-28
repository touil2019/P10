package com.ocr.livre.service;

import com.ocr.livre.model.Reservation;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> findById (Long IdReservation);

    List<Reservation> findReservationByPseudoEmprunteur(String PseudoEmprunteur);

    List<Reservation> findAll();

    List<Reservation>findReservationByLivre();

    void deleteById(Long id);

    ResponseEntity annulerReservation(Long id, String pseudoEmprunteur);

    ResponseEntity creerUneReservation (Long id, String pseudoEmprunteur);

    Date ajouter2Jours(Date date);

    void purgeFileAttente() throws MessagingException;
}
