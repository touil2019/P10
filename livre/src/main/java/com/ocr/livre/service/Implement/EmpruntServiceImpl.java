package com.ocr.livre.service.Implement;

import com.ocr.livre.LivreApplication;
import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.service.EmpruntService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service
public class EmpruntServiceImpl implements EmpruntService {

    private static final Logger logger = LogManager.getLogger(LivreApplication.class);

    @Autowired
    EmpruntLivreDao empruntLivreDao;
    @Autowired
    LivreDao livreDao ;

    /**
     * trouver l ensemble des emprunts de livres
     * @return liste d emprunt
     */
    @Override
    public List<Emprunt> findAll() {
        logger.debug("Appel empruntService méthode findAll");
        return empruntLivreDao.findAll();
    }

    /**
     * retrouver un emprunt par son id
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
     * @param pseudoEmprunteur
     * @return liste d emprunt
     */
    @Override
    public List<Emprunt> findAllByPseudoEmprunteur(String pseudoEmprunteur) {
        logger.debug("Appel empruntService méthode findAllByPseudoEmprunteur avec paramètre pseudoEmprunteur : " + pseudoEmprunteur);
        return empruntLivreDao.findAllByPseudoEmprunteurAndCloturerIsFalseOrderByDateDebutAsc(pseudoEmprunteur);
    }

    /**
     * Ajoute 4 semaine à une date
     * @param date date à laquelle les 4 semaines doivent être ajoutée
     * @return la nouvelle date
     */
    @Override
    public Date ajouter4Semaines(Date date){

        logger.debug("Appel empruntService méthode ajouter4Semaines avec paramètre date : " + date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 28);
        return calendar.getTime();
    }

    /**
     * retourne l emprunt prolonger
     * @param idEmprunt
     * @return l'emprunt prolongé
     */
    @Override
    public Emprunt prolongerEmprunt(Long idEmprunt) {

        logger.debug("Appel empruntService méthode prolongerEmprunt avec paramètre idEmprunt : " + idEmprunt);

        Emprunt emprunt = empruntLivreDao.findById(idEmprunt).get();


        if (emprunt.isProlongeable() == true && emprunt.isCloturer()== false) {
            emprunt.setDateFin(ajouter4Semaines( emprunt.getDateFin()));
            emprunt.setProlongeable(false);
            empruntLivreDao.save(emprunt);
            return emprunt;

        } else {
            return null ;
        }

    }
    /**
     * Trouve tous les emprunts non rendues à date
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
     * @param idLivre id du livre emprunté
     * @param pseudoEmprunteur pseudo de l'emprunteur
     * @return le nouvel emprunt
     */

    @Transactional
    @Override
    public Emprunt ouvrirEmprunt(Long idLivre, String pseudoEmprunteur) {

        logger.debug("Appel empruntService méthode ouvrirEmprunt");

        Livre livre = livreDao.findById(idLivre).get();
        Emprunt nouvelEmprunt = new Emprunt("USER", new Date(), true, false,livre);

        Date date = new Date();

        nouvelEmprunt.setDateDebut(date);
        nouvelEmprunt.setDateFin(ajouter4Semaines(date));
        nouvelEmprunt.setPseudoEmprunteur(pseudoEmprunteur);
        nouvelEmprunt.setCloturer(false);
        nouvelEmprunt.setProlongeable(true);
        livre.setQuantiteDispo(livre.getQuantiteDispo() - 1);

        return empruntLivreDao.save(nouvelEmprunt);
    }

    /**
     * cloteur un emprunt
     * @param idEmprunt
     * @return emprunt cloturer
     */
    @Transactional
    @Override
    public Emprunt cloturerEmprunt(Long idEmprunt) {

        logger.debug("Appel empruntService méthode cloturerEmprunt");


        Emprunt emprunt= empruntLivreDao.findById(idEmprunt).get();
        Livre livre = livreDao.findById(emprunt.getLivre().getId()).get();
        emprunt.setCloturer(true);
        emprunt.setDateFin(new Date());
        livre.setQuantiteDispo(livre.getQuantiteDispo() + 1);

        empruntLivreDao.save(emprunt);
        livreDao.save(livre);

        return emprunt;
    }

    }

