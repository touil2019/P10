package com.ocr.livre.service.Implement;

import com.ocr.livre.LivreApplication;
import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.model.Reservation;
import com.ocr.livre.service.LivreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;

    @EnableScheduling
    @Service
    public class LivreServiceImpl implements LivreService {

        private static final Logger logger = (Logger) LogManager.getLogger(LivreApplication.class);

        @Autowired
        LivreDao livreDao;
        @Autowired
        EmpruntLivreDao empruntLivreDao;
        @Autowired
        ReservationDao reservationDao;

        /**
         * trouver tous les livres
         * @return liste des livres
         */

        @Override
        public List<Livre> findAll() {

            logger.info("Appel LivreServiceImpl méthode findAll");

            return livreDao.findAll();
        }

        /**
         * trouver un livre par mc mot cle
         * @param mc mot cle
         * @return une liste de livres
         */
        @Override
        public List<Livre> findByTitreContainingIgnoreCase(String mc) {

            logger.info("Appel LivreServiceImpl méthode findByTitreContainingIgnoreCase avec paramètre mc : " +mc );

            return livreDao.chercher(mc);
        }

        /**
         * trouver un livre
         * @param idLivre id du livre
         * @return le livre
         */
        @Override
        public Livre findLivreById(Long idLivre) {

            logger.info("Appel LivreServiceImpl méthode findLivreById avec paramètre idLidvre : " +idLivre );

            Livre livre= livreDao.findById(idLivre).get();


            List<Livre> livres= livreDao.findAllByTitre(livre.getTitre());

            List<Emprunt> emprunts= empruntLivreDao.listeDEmpruntActifParLivre(livre.getTitre());

            livre.setQuantiteDispo(livres.size() - emprunts.size());

            livreDao.save(livre);


            return livre;
        }

        @Override
        public Livre recupererUnLivreParUtilisateur(Long id, String pseudo) {

            Livre livre= findLivreById(id);

            List<Livre> livres= livreDao.findAllByTitre(livre.getTitre());

            List<Reservation> reservationsDeUtilisateur= reservationDao.findAllByPseudoEmprunteurAndLivre_TitreAndEnCoursIsTrue(pseudo, livre.getTitre());

            List<Emprunt> empruntsDeUtilisateur= empruntLivreDao.findAllByPseudoEmprunteurAndLivre_TitreAndCloturerIsFalse(pseudo, livre.getTitre());

            List<Reservation> reservationsDunLivre= reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrue(livre.getTitre());

            if( livre.getQuantiteDispo()==0 && reservationsDeUtilisateur.size()==0
                    && empruntsDeUtilisateur.size()==0 && reservationsDunLivre.size()<= livres.size()*2){

                livre.setReservable(true);

            } else{
                livre.setReservable(false);
            }

            return livre;
        }

        /**
         * enregister un nouveau livre
         * @param livre
         * @return
         */
        @Override
        public Livre enregistrerNouveauLivre(Livre livre){

            logger.info("Appel LivreServiceImpl méthode enregistrerNouveauLivre");

            Livre nouveauLivre = new Livre() ;

            nouveauLivre.setAuteurName(livre.getAuteurName());
            nouveauLivre.setAuteurPrenom(livre.getAuteurPrenom());
            nouveauLivre.setEdition(livre.getEdition());
            nouveauLivre.setQuantiteDispo(livre.getQuantiteDispo());
            nouveauLivre.setTitre(livre.getTitre());
            nouveauLivre.setImage(livre.getImage());

            return livreDao.save(nouveauLivre);
        }

        /**
         * supprimer un livre par son id
         * @param idLivre
         */
        @Override
        public void supprimerLivre(Long idLivre) {

            logger.info("Appel LivreServiceImpl méthode supprimerLivre avec paramètre idLidvre : " + idLivre );

            livreDao.deleteById(idLivre);
        }



    }
