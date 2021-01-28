package com.ocr.livre.service.Implement;

import com.ocr.livre.beans.UtilisateurBean;
import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.model.Reservation;
import com.ocr.livre.service.EmpruntService;
import com.ocr.livre.service.ReservationService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LivreServiceImplTestUnit {

    @Mock
    private EmpruntService empruntService;
    @Mock
    private ReservationService reservationService;
    @Mock
    private LivreDao livreDao;
    @Mock
    private UtilisateurBean utilisateurBean;
    @Autowired
    @InjectMocks
    private LivreServiceImpl livreService;
    @Mock
    private EmpruntLivreDao empruntLivreDao;
    @Mock
    private ReservationDao reservationDao;

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
        livre.setReservable(true);

        livreList.add(livre);



        Emprunt emprunt= new Emprunt();
        emprunt.setPseudoEmprunteur(utilisateurBean1.getUsername());
        emprunt.setLivre(livre);
        emprunt.setCloturer(false);
        empruntList.add(emprunt);


        Mockito.when(livreDao.findAllByTitre(livre.getTitre())).thenReturn(livreList);
        Mockito.when(reservationDao.findAllByPseudoEmprunteurAndLivre_TitreAndEnCoursIsTrue(utilisateurBean1.getUsername(), livre.getTitre())).thenReturn(reservationList);
        Mockito.when(reservationDao.findAllByPseudoEmprunteurAndLivre_TitreAndEnCoursIsTrue(utilisateurBean2.getUsername(), livre.getTitre())).thenReturn(reservationList);
        Mockito.when(empruntLivreDao.findAllByPseudoEmprunteurAndLivre_TitreAndCloturerIsFalse(utilisateurBean1.getUsername(), livre.getTitre())).thenReturn(empruntList);
        Mockito.when(empruntLivreDao.findAllByPseudoEmprunteurAndLivre_TitreAndCloturerIsFalse(utilisateurBean2.getUsername(), livre.getTitre())).thenReturn(new ArrayList<>());
        Mockito.when(reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrue(livre.getTitre())).thenReturn(reservationList);
        Mockito.when(livreDao.findById(livre.getId())).thenReturn(Optional.of(livre));
        Mockito.when(empruntLivreDao.listeDEmpruntActifParLivre(livre.getTitre())).thenReturn(empruntList);
        Mockito.when(livreDao.save(livre)).thenReturn(livre);
    }

    /**
     * vérifier qu'un utilisateur peut réserver un livre
     * entrant: id d' un livre et pseudo utilisateur
     * sortant: livre
     * attendu: utilisateur1 ne peut pas reserver le livre
     */
    @Test
    public void test_recupererUnLivreParUtilisateur_utilisateurBean1(){

        Livre livreTest= livreService.recupererUnLivreParUtilisateur(7L,"userX");

        Assertions.assertEquals(livreTest.getTitre(),"abc");
        Assertions.assertEquals(livreTest.getQuantiteDispo(),0);
        Assertions.assertFalse(livreTest.isReservable());



    }

    /**
     * vérifier qu'un utilisateur peut réserver un livre
     * entrant: id d' un livre et pseudo utilisateur
     * sortant: livre
     * attendu: utilisateur2 peut reserver le livre
     */
    @Test
    public void test_recupererUnLivreParUtilisateur_utilisateurBean2(){

        Livre livreTest= livreService.recupererUnLivreParUtilisateur(7L,"userY");

        Assertions.assertEquals(livreTest.getTitre(),"abc");
        Assertions.assertEquals(livreTest.getQuantiteDispo(),0);
        Assertions.assertTrue(livreTest.isReservable());



    }

}
