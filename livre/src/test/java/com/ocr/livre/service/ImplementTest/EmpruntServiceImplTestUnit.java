package com.ocr.livre.service.ImplementTest;


import com.ocr.livre.beans.UtilisateurBean;

import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.model.Reservation;

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
public class EmpruntServiceImplTestUnit {

    @Autowired
    @InjectMocks
    private EmpruntServiceImpl empruntService;

    @Mock
    EmpruntLivreDao empruntLivreDao;
    @Mock
    ReservationDao reservationDao;
    @Mock
    LivreDao livreDao;



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
        emprunt.setIdEmprunt(1L);
        emprunt.setDateDebut(new GregorianCalendar(2020,Calendar.OCTOBER,1).getTime());
        emprunt.setDateFin(new GregorianCalendar(2020,Calendar.OCTOBER,29).getTime());
        emprunt.setPseudoEmprunteur(utilisateurBean1.getUsername());
        emprunt.setLivre(livre);
        emprunt.setCloturer(false);
        emprunt.setProlongeable(true);
        empruntList.add(emprunt);

        Emprunt emprunt1= new Emprunt();
        emprunt1.setIdEmprunt(2L);
        emprunt1.setDateDebut(new GregorianCalendar(2021,Calendar.JANUARY,9).getTime());
        emprunt1.setDateFin(new GregorianCalendar(2021,Calendar.FEBRUARY,6).getTime());
        emprunt1.setPseudoEmprunteur(utilisateurBean1.getUsername());
        emprunt1.setLivre(livre1);
        emprunt1.setCloturer(false);
        emprunt1.setProlongeable(true);
        empruntList.add(emprunt1);





        Mockito.when(empruntLivreDao.findAllByPseudoEmprunteurAndCloturerIsFalseOrderByDateDebutAsc(utilisateurBean1.getUsername())).thenReturn(empruntList);
        Mockito.when(empruntLivreDao.save(emprunt)).thenReturn(emprunt);
        Mockito.when(empruntLivreDao.findAllByPseudoEmprunteurAndCloturerIsFalseOrderByDateDebutAsc(utilisateurBean1.getUsername())).thenReturn(empruntList);
        Mockito.when(empruntLivreDao.findById(1L)).thenReturn(Optional.of(emprunt));
        Mockito.when(empruntLivreDao.findById(2L)).thenReturn(Optional.of(emprunt1));
        Mockito.when(empruntLivreDao.findAllByCloturerIsFalseAndDateFinBefore(new Date())).thenReturn(empruntList);
        Mockito.when(livreDao.findAllByTitreAndDisponibleIsTrue(livre.getTitre())).thenReturn(livreList.subList(0,1));
        Mockito.when(livreDao.findAllByTitreAndDisponibleIsFalse(livre1.getTitre())).thenReturn(livreList.subList(1,2));
        Mockito.when(livreDao.save(livre1)).thenReturn(livre1);
        Mockito.when(livreDao.save(livre)).thenReturn(livre);
        Mockito.when(reservationDao.findAllByLivre_TitreAndEnCoursIsTrueAndNotifiedIsFalseOrderByDateReservationAsc(livre.getTitre())).thenReturn(reservationList);


    }
    /**
     *Récupérer les emprunts d'un utilisateur et mettre à jour l'attribut prolongeable
     * entrant: le pseudo de l'emprunteur
     * sortant: une liste d'emprunt
     * attendu: une liste de deux emprunts (1er emprunt non prolongeable-Octobre)
     */
    @Test
    public void findAllByPseudoEmprunteur_Test(){

        List<Emprunt> emprunts= empruntService.findAllByPseudoEmprunteur("userX");

        Assertions.assertEquals(emprunts.size(),2);
        Assertions.assertFalse(emprunts.get(0).isProlongeable());
        Assertions.assertTrue(emprunts.get(1).isProlongeable());

    }
    /**
     *Vérifier qu'on ajoute 28jours
     * entrant: une date
     * sortant: une date
     * attendu: date sortante est 28jours après la date d'entrée
     */
    @Test
    public void test_ajouter4semaines(){

        Assertions.assertEquals(empruntService.ajouter4Semaines(new GregorianCalendar(2021,Calendar.JANUARY,1).getTime()),new GregorianCalendar(2021,Calendar.JANUARY,29).getTime());
    }


    /**
     *Vérifier qu'un emprunt est prolongeable
     * entrant: idEmprunt
     * sortant: ResponseEntity
     * attendu: emprunt n°1 non prolongeable et emprunt n°2 prolongeable
     */
    @Test
    public void test_prolongEmprunt(){

        ResponseEntity<Emprunt> reponse= empruntService.prolongerEmprunt(1L);
        Assertions.assertEquals(reponse.getStatusCodeValue(),400);

        reponse= empruntService.prolongerEmprunt(2L);
        Assertions.assertEquals(reponse.getStatusCodeValue(),200);
    }

}
