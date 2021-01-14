package com.ocr.livre.service.Implement;

import com.ocr.livre.LivreApplication;
import com.ocr.livre.beans.UtilisateurBean;
import com.ocr.livre.dao.EmailDao;
import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Email;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.model.Reservation;
import com.ocr.livre.proxies.MicroserviceUtilisateurProxy;
import com.ocr.livre.service.EmailService;
import com.ocr.livre.service.EmpruntService;
import com.ocr.livre.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.*;

@EnableScheduling
@Service

public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LogManager.getLogger(LivreApplication.class);

    @Autowired
    EmailService emailService;

    @Autowired
    private JavaMailSenderImpl sender;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    MicroserviceUtilisateurProxy microserviceUtilisateurProxy;

    @Autowired
    EmailDao emailDao;

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

        Date dateDuJour= new Date();

        List<Reservation> reservations= new ArrayList<>();

        reservations =  reservationDao.findAllByPseudoEmprunteurAndEnCoursIsTrue( pseudoEmprunteur);

        for (Reservation r :reservations ) {

            List<Reservation> reservationsParLivre= reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrue(r.getLivre().getTitre());

            for (int i=0;i< reservationsParLivre.size();i++) {

                Reservation rl= reservationsParLivre.get(i);
                if( r==rl){
                    r.setPosition(i+1);
                    r.setDateNotification(ajouter2Jours(r.getDateReservation()));
                    r.setNotified(true);
                    reservationDao.save(r);
                }
            }
        }


        return  reservationDao.findAllByPseudoEmprunteurAndEnCoursIsTrue( pseudoEmprunteur);
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

    /**
     *
     *
     */
    @Override
    public void purgeFileAttente() throws MessagingException {

        Date dateDuJour= new Date();

        Email email = emailDao.findAllByNom("notification");

        List<Livre> listLivres= livreDao.findAll();

        List<String> listTitres= new ArrayList<>();

        for (Livre l: listLivres) {

            if(!listTitres.contains(l.getTitre())){

                listTitres.add(l.getTitre());
            }

        }

        for (String titre:listTitres) {

            List<Reservation> listReservationActive = reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrueOrderByDateReservationAsc(titre);

            if(listReservationActive.size()>0){
                for ( Reservation reservation : listReservationActive) {
                    if(reservation.isNotified()){
                        if(dateDuJour.after(ajouter2Jours(reservation.getDateNotification()))){
                            reservation.setEnCours(false);
                            reservationDao.save(reservation);
                            listReservationActive = reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrueOrderByDateReservationAsc(titre);
                            for (Reservation r : listReservationActive) {

                                if(!r.isNotified()){

                                    r.setDateNotification(dateDuJour);
                                    r.setNotified(true);
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    String strDate = sdf.format(r.getDateNotification());

                                    UtilisateurBean utilisateur = microserviceUtilisateurProxy.recupererUnUtilisateur(r.getPseudoEmprunteur());

                                    logger.info("Appel ReservationServiceImpl méthode envoyerEmailNotification à l'adresse : " + utilisateur.getEmail() + " pour le livre : " + r.getLivre().getTitre() + " pour la réservation id : " + r.getId());

                                    String text = email.getContenu()
                                            .replace("[NOMUTILISATEUR]", r.getPseudoEmprunteur())
                                            .replace("[TITRELIVRE]", r.getLivre().getTitre())
                                            .replace("[DATE_RENDU]", strDate);

                                    emailService.sendSimpleMessage(utilisateur.getEmail(), email.getObjet(), text);

                                    reservationDao.save(r);
                                    break;
                                }
                            }

                            for ( int i=0;i<listReservationActive.size();i++) {

                                Reservation resa= listReservationActive.get(i);
                                resa.setPosition(i + 1);
                                reservationDao.save(resa);
                            }
                        }
                    }

                }
            }

        }


    }
}

