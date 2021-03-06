package com.ocr.livre.service;

import com.ocr.livre.model.Emprunt;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmpruntService {

    List<Emprunt> findAll();

    Optional<Emprunt> findById(Long idEmprunt);

    List<Emprunt> findAllByPseudoEmprunteur(String pseudoEmprunteur);

   ResponseEntity ouvrirEmprunt(String titre, String pseudoEmprunteur);


  ResponseEntity cloturerEmprunt(Long idEmprunt) throws MessagingException;

    Date ajouter4Semaines(Date date);

    ResponseEntity<Emprunt> prolongerEmprunt(Long idEmprunt);

    List<Emprunt> listeLivreNonRendueApresDateFin();

    Optional<Emprunt> findByLivre_IdAndCloturerIsFalse(Long id);


}