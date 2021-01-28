package com.ocr.livre.service.Implement;


import com.ocr.livre.beans.UtilisateurBean;
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
import org.springframework.http.ResponseEntity;

import java.util.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTestUnit {

    @Autowired
    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    EmpruntLivreDao empruntLivreDao;
    @Mock
    ReservationDao reservationDao;
    @Mock
    LivreDao livreDao;
    @Mock
    MicroserviceUtilisateurProxy microserviceUtilisateurProxy;
    private List<Reservation> reservations;

    @Before
    public void initialisation(){

        List<Livre> livreList=new ArrayList<>();
        List<Emprunt> empruntList= new ArrayList<>();
        List<Reservation> reservationList= new ArrayList<>();
        List<String> listTitres= new ArrayList<>();


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

        Livre livre1= new Livre();
        livre1.setId(8L);
        livre1.setTitre("def");
        livre1.setDisponible(true);
        livre1.setReservable(true);
        livreList.add(livre1);

        Emprunt emprunt= new Emprunt();
        emprunt.setIdEmprunt(1L);
        emprunt.setPseudoEmprunteur(utilisateurBean1.getUsername());
        emprunt.setLivre(livre);
        emprunt.setDateDebut(new GregorianCalendar(2021,Calendar.JANUARY,9).getTime());
        emprunt.setDateFin(new GregorianCalendar(2021,Calendar.FEBRUARY,6).getTime());
        emprunt.setCloturer(false);
        emprunt.setProlongeable(true);
        empruntList.add(emprunt);

        Reservation reservation= new Reservation();
        reservation.setId(1L);
        reservation.setPseudoEmprunteur("userX");
        reservation.setDateReservation(new GregorianCalendar(2021,Calendar.FEBRUARY,1).getTime());
        reservation.setEnCours(true);
        reservation.setLivre(livre);
        reservationList.add(reservation);


        Reservation reservation1= new Reservation();
        reservation1.setId(2L);
        reservation1.setPseudoEmprunteur("userX");
        reservation1.setDateReservation(new GregorianCalendar(2021,Calendar.FEBRUARY,1).getTime());
        reservation1.setEnCours(true);
        reservation1.setLivre(livre1);
        reservationList.add(reservation1);


        Reservation reservation2= new Reservation();
        reservation2.setId(3L);
        reservation2.setPseudoEmprunteur("userY");
        reservation2.setDateReservation(new GregorianCalendar(2021,Calendar.FEBRUARY,2).getTime());
        reservation2.setEnCours(true);
        reservation2.setLivre(livre1);
        reservationList.add(reservation2);



        Mockito.when(reservationDao.findAllByPseudoEmprunteurAndEnCoursIsTrue(utilisateurBean1.getUsername())).thenReturn(reservationList.subList(0,2));
        Mockito.when(reservationDao.findAllByPseudoEmprunteurAndEnCoursIsTrue(utilisateurBean2.getUsername())).thenReturn(reservationList.subList(2,3));
        Mockito.when(reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrue("def")).thenReturn(reservationList.subList(1,3));
        Mockito.when(reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrue("abc")).thenReturn(reservationList.subList(0,1));
        Mockito.when(reservationDao.save(reservation)).thenReturn(reservation);
        Mockito.when(empruntLivreDao.listeDEmpruntActifParLivre(livre.getTitre())).thenReturn(empruntList);
        Mockito.when(reservationDao.findById(reservation.getId())).thenReturn(Optional.of(reservation));
        Mockito.when(livreDao.findById(livre.getId())).thenReturn(Optional.of(livre));

    }

    /**
     *Vérifier qu'on ajoute 2jours
     * entrant: une date
     * sortant: une date
     * attendu: date sortante est 2jours après la date d'entrée
     */
    @Test
    public void test_ajouter2Jours(){

        Assertions.assertEquals(reservationService.ajouter2Jours(new GregorianCalendar(2021, Calendar.JANUARY,1).getTime()),new GregorianCalendar(2021,Calendar.JANUARY,3).getTime());
    }

    /**
     *Récupérer les reservations d'un utilisateur et mettre à jour l'attribut position
     * entrant: le pseudo du réservant
     * sortant: une liste de reservation
     * attendu: une liste de deux reservations pour "userX" et une pour "userY"
     * userY a réservé après userX
     */
    @Test
    public void test_findReservationByPseudoEmprunteur(){

       List<Reservation> reservations = reservationService.findReservationByPseudoEmprunteur("userX");

        Assertions.assertEquals(reservations.size(),2);
        Assertions.assertEquals(reservations.get(0).getPosition(),1);
        Assertions.assertEquals(reservations.get(1).getPosition(),1);

        reservations = reservationService.findReservationByPseudoEmprunteur("userY");

        Assertions.assertEquals(reservations.size(),1);
        Assertions.assertEquals(reservations.get(0).getPosition(),2);


    }
    /**
     * une nouvelle reservation reservations d'un utilisateur
     * entrant: le pseudo du réservant
     * sortant: une liste de reservation
     * attendu: une reservation
     */
    @Test
    public void test_creerUneReservation(){

        ResponseEntity reponse= reservationService.creerUneReservation(7L,"userY");

        Assertions.assertEquals(reponse.getStatusCodeValue(),200);
        Assertions.assertEquals(reponse.getBody(),"reservation réalisée");


    }
    /**
     * une nouvelle reservation reservations d'un utilisateur
     * entrant: le pseudo du réservant
     * sortant: une liste de reservation
     * attendu: une reservation
     */
    @Test
    public void test_annulerReservation(){

        ResponseEntity reponse= reservationService.annulerReservation(1L,"userX");
        Assertions.assertEquals(reponse.getStatusCodeValue(),200);
        Assertions.assertEquals(reponse.getBody(),"reservation annulée");

        reponse= reservationService.annulerReservation(1L,"userY");
        Assertions.assertEquals(reponse.getStatusCodeValue(),400);
        Assertions.assertEquals(reponse.getBody(),"pseudo ne corresponds pas");

        reponse= reservationService.annulerReservation(6L,"userY");
        Assertions.assertEquals(reponse.getStatusCodeValue(),400);
        Assertions.assertEquals(reponse.getBody(),"reservation introuvable");

    }

}
