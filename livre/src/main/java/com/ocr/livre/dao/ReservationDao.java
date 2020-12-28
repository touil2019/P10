package com.ocr.livre.dao;

import com.ocr.livre.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findById (Long IdReservation);

    List<Reservation> findAllByPseudoEmprunteurAndEnCoursIsTrue(String pseudoEmprunteur);



    void deleteById(Long Id);


}
