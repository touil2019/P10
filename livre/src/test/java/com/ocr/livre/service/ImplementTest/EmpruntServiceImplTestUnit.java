package com.ocr.livre.service.ImplementTest;


import com.ocr.livre.beans.UtilisateurBean;
import com.ocr.livre.dao.EmailDao;
import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.model.Reservation;
import com.ocr.livre.proxies.MicroserviceUtilisateurProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmpruntServiceImplTestUnit {

    @Autowired
    @InjectMocks
    private EmpruntServiceImpl empruntService;

    @Mock
    EmpruntLivreDao empruntLivreDao;
    @Mock
    EmailServiceImpl emailService;
    @Mock
    EmailDao emailDao;
    @Mock
    ReservationDao reservationDao;
    @Mock
    UtilisateurBean utilisateurBean;
    @Mock
    LivreDao livreDao;
    @Mock
    MicroserviceUtilisateurProxy microserviceUtilisateurProxy;


    @Before
    public void initialisation(){

        List<Livre> livreList=new ArrayList<>();
        List<Emprunt> empruntList= new ArrayList<>();
        List<Reservation> reservationList= new ArrayList<>();



        UtilisateurBean utilisateurBean1= new UtilisateurBean();

        utilisateurBean1.setUsername("userX");

        UtilisateurBean utilisateurBean2= new UtilisateurBean();

        utilisateurBean2.setUsername("userY");

        Livre livre= new Livre();
        livre.setId(7L);
        livre.setTitre("abc");
        livre.setDisponible(true);
        livre.setReservable(true);
        livreList.add(livre);

        Livre livre1 = new Livre();
        livre1.setId(9L);
        livre1.setTitre("def");
        livre1.setDisponible(false);
        livre1.setReservable(true);
        livreList.add(livre1);



        Emprunt emprunt= new Emprunt();
        emprunt.getIdEmprunt();
        emprunt.setPseudoEmprunteur(utilisateurBean1.getUsername());
        emprunt.setLivre(livre1);
        emprunt.setCloturer(false);
        emprunt.setProlongeable(true);
        empruntList.add(emprunt);

        Reservation reservation= new Reservation();


      //  Email email=new Email();


        Date dateDuJour = new Date();

        Mockito.when(empruntLivreDao.findAllByPseudoEmprunteurAndCloturerIsFalseOrderByDateDebutAsc(utilisateurBean1.getUsername())).thenReturn(empruntList);
        Mockito.when(empruntLivreDao.save(emprunt)).thenReturn(emprunt);
        Mockito.when(empruntLivreDao.findAllByPseudoEmprunteurAndCloturerIsFalseOrderByDateDebutAsc(utilisateurBean1.getUsername())).thenReturn(empruntList);
        Mockito.when(empruntLivreDao.findById(emprunt.getIdEmprunt())).thenReturn(Optional.of(emprunt));
        Mockito.when(empruntLivreDao.findAllByCloturerIsFalseAndDateFinBefore(new Date())).thenReturn(empruntList);
        Mockito.when(livreDao.findAllByTitreAndDisponibleIsTrue(livre.getTitre())).thenReturn(livreList);
        Mockito.when(livreDao.findAllByTitreAndDisponibleIsFalse(livre1.getTitre())).thenReturn(livreList);
        Mockito.when(livreDao.save(livre1)).thenReturn(livre1);
        Mockito.when(livreDao.save(livre)).thenReturn(livre);
        Mockito.when(reservationDao.findAllByLivre_TitreAndEnCoursIsTrueAndNotifiedIsFalseOrderByDateReservationAsc(livre.getTitre())).thenReturn(reservationList);
        //Mockito.when(microserviceUtilisateurProxy.recupererUnUtilisateur(reservation.getPseudoEmprunteur())).thenReturn(utilisateurBean2.setUsername());
       // Mockito.when(emailDao.findAllByNom("notification")).thenReturn(email);
       // Mockito.when(emailService.sendSimpleMessage(reservant.getEmail(), email.getObjet(), text);)

        /**
         *
         * entrant:
         * sortant:
         * attendu:
         */


    }
}
