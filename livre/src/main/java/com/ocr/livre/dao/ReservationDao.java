package com.ocr.livre.dao;

import com.ocr.livre.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findById (Long IdReservation);

    List<Reservation> findAllByPseudoEmprunteurAndEnCoursIsTrue(String pseudoEmprunteur);

    List<Reservation> findAllByPseudoEmprunteurAndLivre_TitreAndEnCoursIsTrue(String pseudoEmprunteur, String titre);

    void deleteById(Long Id);

    List<Reservation> findAllByLivre_TitreAndAndEnCoursIsTrue(String titre);

    List<Reservation> findAllByLivre_TitreAndEnCoursIsTrueAndNotifiedIsFalseOrderByDateReservationAsc(String titre);

    List<Reservation> findAllByLivre_TitreAndEnCoursIsTrueAndNotifiedIsTrueOrderByDateReservationAsc(String titre);

}
