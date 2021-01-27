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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTestUnit {

    @Autowired
    @InjectMocks
    private ReservationServiceImpl reservationService;

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
    JavaMailSenderImpl javaMailSender;
    @Mock
    MicroserviceUtilisateurProxy microserviceUtilisateurProxy;

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

        Emprunt emprunt= new Emprunt();
        emprunt.getIdEmprunt();
        emprunt.setPseudoEmprunteur(utilisateurBean1.getUsername());
        emprunt.setLivre(livre);
        emprunt.setCloturer(false);
        emprunt.setProlongeable(true);
        empruntList.add(emprunt);

        Reservation reservation= new Reservation();
        reservation.setId(reservation.getId());

        /*Email email= new Email();
        email.setNom("notification");
        email.setObjet();
        email.setContenu();*/


        Mockito.when(reservationDao.findAllByPseudoEmprunteurAndEnCoursIsTrue(utilisateurBean.getUsername())).thenReturn(reservationList);
        Mockito.when(reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrue(reservation.getLivre().getTitre())).thenReturn(reservationList);
        Mockito.when(reservationDao.save(reservation)).thenReturn(reservation);
        Mockito.when(empruntLivreDao.listeDEmpruntActifParLivre(livre.getTitre())).thenReturn(empruntList);
        Mockito.when(reservationDao.findById(reservation.getId())).thenReturn(Optional.of(reservation));
      //  Mockito.when(emailDao.findAllByNom("notification")).thenReturn(email);
        Mockito.when(livreDao.findAll()).thenReturn(livreList);
        Mockito.when(reservationDao.findAllByLivre_TitreAndAndEnCoursIsTrueOrderByDateReservationAsc(livre.getTitre())).thenReturn(reservationList);
      //  Mockito.when(microserviceUtilisateurProxy.recupererUnUtilisateur(reservation.getPseudoEmprunteur())).thenReturn(utilisateurBean1.getUsername());
      //  Mockito.when(emailService.sendSimpleMessage(utilisateurBean1.getEmail(), email.getObjet(), text)).thenReturn(email);
    }

}
