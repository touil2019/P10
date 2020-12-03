package com.ocr.livre.service;

import com.ocr.livre.model.Livre;

import java.util.List;

public interface LivreService {

    List<Livre> findAll();

    List<Livre> findByTitreContainingIgnoreCase(String mc);

    Livre enregistrerNouveauLivre(Livre livre);

    void supprimerLivre(Long livre);

    Livre findLivreById(Long idLidvre);
}
