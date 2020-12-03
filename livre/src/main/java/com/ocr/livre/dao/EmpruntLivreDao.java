package com.ocr.livre.dao;


import com.ocr.livre.model.Emprunt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * DAO pour l'emprunt d un livre
 */

public interface EmpruntLivreDao extends JpaRepository<Emprunt, Long> {


    /**
     * retrouver un emprunt par son id
     * @param idEmprunt
     * @return
     */
    Optional<Emprunt> findById(Long idEmprunt);

    /**
     * liste des emprunts par pseudoEmprunteur et ordonner
     * @param pseudoEmprunteur
     * @return
     */
    List<Emprunt> findAllByPseudoEmprunteurAndCloturerIsFalseOrderByDateDebutAsc(String pseudoEmprunteur);

    /**
     * emprunts qui sont en cours et la date de fin prévu est avant la date du jour
     * @param dateDuJour
     * @return
     */
    List<Emprunt> findAllByCloturerIsFalseAndDateFinBefore(Date dateDuJour);

    /**
     * liste des livres en cours d emprunt permet de déterminer la quantité de livre disponible
     * @param titre
     * @return
     */
    @Query(value = "select e from Emprunt e inner join fetch e.livre l where l.titre=:titre and e.cloturer=false",
            countQuery="select count (e) from Emprunt e inner join e.livre l where l.titre=:titre and e.cloturer=false")
    List<Emprunt> listeDEmpruntActifParLivre(String titre);
}
