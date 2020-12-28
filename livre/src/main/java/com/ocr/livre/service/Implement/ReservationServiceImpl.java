package com.ocr.livre.service.Implement;

import com.ocr.livre.LivreApplication;
import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.model.Reservation;
import com.ocr.livre.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service

public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LogManager.getLogger(LivreApplication.class);

    @Autowired
    ReservationDao reservationDao;
    @Autowired
    LivreDao livreDao;
    @Autowired
    EmpruntLivreDao empruntLivreDao;

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

        reservationDao.deleteById(Id);

    }

    @Override
    public void creerUneReservation(Long id, String pseudoEmprunteur) {

      Optional<Livre> l= livreDao.findById(id);
      Livre livre= null;
      if(l.isPresent()){
          livre=l.get();

          List<Emprunt> listEmpruntEnCours= empruntLivreDao.listeDEmpruntActifParLivre(livre.getTitre());

          Date dateProchainRetour= listEmpruntEnCours.get(0).getDateFin();

          Reservation reservation = new Reservation(livre,pseudoEmprunteur, new Date(), dateProchainRetour);

          reservationDao.save(reservation);

      }


    }


    @Override
    public void annulerReservation(Long id, String pseudoEmprunteur){

        Optional<Reservation> r=reservationDao.findById(id);
        Reservation reservation = null;
        if (r.isPresent()){
            reservation= r.get();
            if ( reservation.getPseudoEmprunteur().equals(pseudoEmprunteur)){
                reservation.setEnCours(false);
                reservationDao.save(reservation);
            }
        }
   }

    @Override
    public Date ajouter2Jours(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 2);
        return cal.getTime();
    }
}

