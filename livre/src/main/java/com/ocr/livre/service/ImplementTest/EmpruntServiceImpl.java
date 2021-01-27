package com.ocr.livre.service.ImplementTest;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.text.DateFormat;
import java.util.*;

@EnableScheduling
@Service
public class EmpruntServiceImpl implements EmpruntService {

    private static final Logger logger = LogManager.getLogger(EmpruntServiceImpl.class);

    @Autowired
    EmpruntLivreDao empruntLivreDao;
    @Autowired
    LivreDao livreDao;
    @Autowired
    EmailDao emailDao;
    @Autowired
    ReservationDao reservationDao;
    @Autowired
    MicroserviceUtilisateurProxy microserviceUtilisateurProxy;
    @Autowired
    EmailService emailService;


    /**
     * trouver l ensemble des emprunts de livres
     *
     * @return liste d emprunt
     */
    @Override
    public List<Emprunt> findAll() {
        logger.debug("Appel empruntService méthode findAll");
        return empruntLivreDao.findAll();
    }

    /**
     * retrouver un emprunt par son id
     *
     * @param idEmprunt
     * @return un emprunt
     */
    @Override
    public Optional<Emprunt> findById(Long idEmprunt) {
        logger.debug("Appel empruntService méthode findById avec paramètre id : " + idEmprunt);
        return empruntLivreDao.findById(idEmprunt);
    }

    /**
     * trouver tous les emprunts pour un pseudo emprunteur
     *
     * @param pseudoEmprunteur
     * @return liste d emprunt
     */
    @Override
    public List<Emprunt> findAllByPseudoEmprunteur(String pseudoEmprunteur) {

        logger.debug("Appel empruntService méthode findAllByPseudoEmprunteur avec paramètre pseudoEmprunteur : " + pseudoEmprunteur);
        List<Emprunt> emprunts = empruntLivreDao.findAllByPseudoEmprunteurAndCloturerIsFalseOrderByDateDebutAsc(pseudoEmprunteur);

        List<Emprunt> empruntsAjour= new ArrayList<>();
        for (Emprunt e : emprunts) {
            if (e.getDateFin().before(new Date()) && e.isProlongeable() == true) {

                e.setProlongeable(false);
            }
            empruntLivreDao.save(e);
            empruntsAjour.add(e);
        }

        return empruntsAjour;
    }

    /**
     * Ajoute 4 semaine à une date
     *
     * @param date date à laquelle les 4 semaines doivent être ajoutée
     * @return la nouvelle date
     */
    @Override
    public Date ajouter4Semaines(Date date) {

        logger.debug("Appel empruntService méthode ajouter4Semaines avec paramètre date : " + date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 28);
        return calendar.getTime();
    }

    /**
     * retourne l emprunt prolonger
     *
     * @param idEmprunt
     * @return l'emprunt prolongé
     */
    @Override
    public ResponseEntity<Emprunt> prolongerEmprunt(Long idEmprunt) {

        logger.debug("Appel empruntService méthode prolongerEmprunt avec paramètre idEmprunt : " + idEmprunt);

        Emprunt emprunt = empruntLivreDao.findById(idEmprunt).get();


        if (emprunt.isProlongeable() == true && emprunt.isCloturer() == false && emprunt.getDateFin().after(new Date())) {
            emprunt.setDateFin(ajouter4Semaines(emprunt.getDateFin()));
            emprunt.setProlongeable(false);
            empruntLivreDao.save(emprunt);
            return ResponseEntity.ok(emprunt);

        } else {
            return new ResponseEntity(
                    "Ne peut pas être prolongé",
                    HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Trouve tous les emprunts non rendues à date
     *
     * @return liste d'emprunts
     */
    @Override
    public List<Emprunt> listeLivreNonRendueApresDateFin() {

        logger.debug("Appel empruntService méthode listeLivreNonRendueApresDateFin");

        Date dateDuJour = new Date();
        List<Emprunt> listeEmprunt = empruntLivreDao.findAllByCloturerIsFalseAndDateFinBefore(dateDuJour);
        System.out.println(listeEmprunt.toString());
        return listeEmprunt;
    }



    /**
     * Enregistre un nouvel emprunt
     *
     * @param titre            titre du livre emprunté
     * @param pseudoEmprunteur pseudo de l'emprunteur
     * @return le nouvel emprunt
     */

  @Override
    public ResponseEntity ouvrirEmprunt(String titre, String pseudoEmprunteur) {

        logger.debug("Appel empruntService méthode ouvrirEmprunt");

        List<Livre> livresDispo = livreDao.findAllByTitreAndDisponibleIsTrue(titre);

        if (livresDispo.size() > 0) {
            Livre livre = livresDispo.get(0);
            livre.setDisponible(false);
            livreDao.save(livre);

            Emprunt nouvelEmprunt = new Emprunt(pseudoEmprunteur, new Date(), true, false, livre);

            Date date = new Date();

            nouvelEmprunt.setDateDebut(date);
            nouvelEmprunt.setDateFin(ajouter4Semaines(date));
            nouvelEmprunt.setPseudoEmprunteur(pseudoEmprunteur);
            nouvelEmprunt.setCloturer(false);
            nouvelEmprunt.setProlongeable(true);
            empruntLivreDao.save(nouvelEmprunt);

            List<Livre> livres = livreDao.findAllByTitre(titre);
            for (Livre l : livres) {
                l.setQuantiteDispo(livre.getQuantiteDispo() - 1);
                livreDao.save(l);
            }


            return new ResponseEntity("L'emprunt a été ouvert", HttpStatus.OK);
        } else return new ResponseEntity("L'emprunt impossible car pas de livre disponible", HttpStatus.BAD_REQUEST);


    }

    /**
     * cloteur un emprunt
     *
     * @param
     * @return emprunt cloturer
     */
    @Transactional
    @Override
    public ResponseEntity cloturerEmprunt(Long idEmprunt) throws MessagingException {

        logger.debug("Appel empruntService méthode cloturerEmprunt");

        Email email = emailDao.findAllByNom("notification");
        Emprunt emprunt = empruntLivreDao.findById(idEmprunt).get();
        Livre livre = livreDao.findById(emprunt.getLivre().getId()).get();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT
        );
        String dateDuJour = shortDateFormat.format(new Date());
        if (emprunt != null) {

            if (!emprunt.isCloturer()) {
                List<Livre> livresIndisponible= livreDao.findAllByTitreAndDisponibleIsFalse(livre.getTitre());

                livre.setDisponible(true);
                livre.setQuantiteDispo(livre.getQuantiteDispo()+1);
                livreDao.save(livre);
                emprunt.setCloturer(true);
                empruntLivreDao.save(emprunt);
                livresIndisponible= livreDao.findAllByTitreAndDisponibleIsFalse(livre.getTitre());
                for (Livre l: livresIndisponible ) {
                    l.setQuantiteDispo(l.getQuantiteDispo()+1);
                    livreDao.save(l);
                }

                List<Reservation> fileAttente = reservationDao.findAllByLivre_TitreAndEnCoursIsTrueAndNotifiedIsFalseOrderByDateReservationAsc(livre.getTitre());

                if (!fileAttente.isEmpty()) {
                    Reservation reservation = fileAttente.get(0);
                    UtilisateurBean reservant = microserviceUtilisateurProxy.recupererUnUtilisateur(reservation.getPseudoEmprunteur());
                    String text = email.getContenu()
                            .replace("[NOMUTILISATEUR]", reservant.getUsername())
                            .replace("[TITRELIVRE]", reservation.getLivre().getTitre())
                            .replace("[DATE_RENDU]", dateDuJour);
                    emailService.sendSimpleMessage(reservant.getEmail(), email.getObjet(), text);
                    reservation.setNotified(true);
                    reservation.setDateNotification(new Date());
                    reservationDao.save(reservation);
                }
                return new ResponseEntity<>("emprunt cloturé", HttpStatus.OK);
            } return new ResponseEntity<>("emprunt déjà clôturé", HttpStatus.BAD_REQUEST);
        } return new ResponseEntity<>("emprunt introuvable", HttpStatus.BAD_REQUEST);
    }

    }
