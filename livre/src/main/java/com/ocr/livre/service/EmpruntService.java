package com.ocr.livre.service;

import com.ocr.livre.model.Emprunt;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmpruntService {

    List<Emprunt> findAll();

    Optional<Emprunt> findById(Long idEmprunt);

    List<Emprunt> findAllByPseudoEmprunteur(String pseudoEmprunteur);

    Emprunt ouvrirEmprunt(Long idLivre, String pseudoEmprunteur);

    Emprunt cloturerEmprunt(Long idEmprunt);

    Date ajouter4Semaines(Date date);

    ResponseEntity<Emprunt> prolongerEmprunt(Long idEmprunt);

    List<Emprunt> listeLivreNonRendueApresDateFin();
}