package com.ocr.livre.service;

import com.ocr.livre.model.Livre;

import java.util.List;

public interface LivreService {

    List<Livre> findAll();

    List<Livre> findByTitreContainingIgnoreCase(String mc);

    Livre enregistrerNouveauLivre(Livre livre);

    Livre findLivreById(Long idLidvre);

    Livre save(Livre livre);


    Livre recupererUnLivreParUtilisateur(Long id, String pseudo);

    List<Livre> findAllByIdAndDisponibleIsTrue(Long id);

    List<Livre> findAllByIdAndDisponibleIsFalse(Long id);
}
