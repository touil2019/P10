package com.ocr.livre.service.Implement;

import com.ocr.livre.LivreApplication;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Reservation;
import com.ocr.livre.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service

public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LogManager.getLogger(LivreApplication.class);

    @Autowired
    ReservationDao reservationDao;

    @Override
    public Optional<Reservation> findById(Long IdReservation) {
        return Optional.empty();
    }

    @Override
    public List<Reservation> findReservationByPseudoEmprunteur(String pseudoEmprunteur) {
        return reservationDao.findAllByPseudoEmprunteurAndEnCoursIsTrue( pseudoEmprunteur);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationDao.findAll();
    }


    @Override
    public List<Reservation> findReservationByLivre() {
        return null;
    }

    @Override
    public void deleteById(Long Id) {

    }
}
