package com.ocr.livre.service;

import com.ocr.livre.model.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> findById (Long IdReservation);

    List<Reservation> findReservationByPseudoEmprunteur(String PseudoEmprunteur);

    List<Reservation> findAll();

    List<Reservation>findReservationByLivre();

    void deleteById(Long id);

    void annulerReservation(Long id, String pseudoEmprunteur);

    void creerUneReservation (Long id, String pseudoEmprunteur);

    Date ajouter2Jours(Date date);

}
