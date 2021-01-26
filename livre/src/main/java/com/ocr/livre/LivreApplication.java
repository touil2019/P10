package com.ocr.livre;

import com.ocr.livre.configuration.CustomErrorDecoder;
import com.ocr.livre.dao.EmailDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.dao.ReservationDao;
import com.ocr.livre.model.Email;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.model.Reservation;
import com.ocr.livre.service.ImplementTest.EmpruntServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.GregorianCalendar;

@EnableFeignClients("com.ocr.livre")
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class LivreApplication {

@Autowired
private LivreDao livreDao;
@Autowired
private EmpruntLivreDao empruntLivreDao;
@Autowired
private EmailDao emailDao;
@Autowired
private EmpruntServiceImpl empruntService;
@Autowired
private ReservationDao reservationDao;

	public static void main(String[] args) {
		SpringApplication.run(LivreApplication.class, args);
	}

	@Bean
	public CustomErrorDecoder CustomErrorDecoder() {
		return new CustomErrorDecoder();
	}

	@PostConstruct
	public void postConstruct(){

		/**Livres**/


		Livre livre1 = new Livre("WRIGHT","Richard","Black Boy","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/41sH5979BrL._SX302_BO1,204,203,200_.jpg");
		livre1.setDisponible(false);
		livre1.setQuantiteDispo(0);
		livreDao.save(livre1);
		Livre livre2 = new Livre("STEINBECK","JOHN","Les Raisins De La Colere","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/418A1zRYhGL._SX302_BO1,204,203,200_.jpg");
		livre2.setDisponible(false);
		livre2.setQuantiteDispo(0);
		livreDao.save(livre2);

		Livre livre3= new Livre("LEE","Harper","Ne Tirez Pas Sur L'Oiseau Moqueur","LE LIVRE DE POCHE","https://m.media-amazon.com/images/I/41mY4e0kS9L.jpg");
		livre3.setDisponible(false);
		livre3.setQuantiteDispo(1);
		livreDao.save(livre3);
		Livre livre4 = new Livre("LEE","Harper","Ne Tirez Pas Sur L'Oiseau Moqueur","LE LIVRE DE POCHE","https://m.media-amazon.com/images/I/41mY4e0kS9L.jpg");
		livre4.setDisponible(true);
		livre4.setQuantiteDispo(1);
		livreDao.save(livre4);



		/**Emprunts du livre : Black Boy**/

		Emprunt emprunt1 = new Emprunt( "user", new GregorianCalendar(2020,Calendar.DECEMBER,15).getTime(), true,false,livre1 );
		emprunt1.setDateFin(empruntService.ajouter4Semaines(emprunt1.getDateDebut()));
		empruntLivreDao.save(emprunt1);


		/**Emprunts du livre : Les Raisins De La Colere**/

		Emprunt emprunt2 = new Emprunt("admin",new GregorianCalendar(2020, Calendar.DECEMBER,15).getTime(),true,false,livre2);
		emprunt2.setDateFin(empruntService.ajouter4Semaines(emprunt2.getDateDebut()));
		empruntLivreDao.save(emprunt2);


		/**Emprunts du livre : Ne Tirez Pas Sur L'Oiseau Moqueur**/

		Emprunt emprunt3 = new Emprunt("admin",new GregorianCalendar(2020,Calendar.OCTOBER,12).getTime(),true,false,livre4);
		emprunt3.setDateFin(empruntService.ajouter4Semaines(emprunt3.getDateDebut()));
		empruntLivreDao.save(emprunt3);



		/**Email de relance pour livre non rendu**/

		Email email = new Email();
		email.setNom("relance");
		email.setObjet("relance pour livre non rendu");
		email.setContenu("Bonjour [NOMUTILISATEUR], \n "+
				"\n"+
				"\tVous deviez rendre le livre [TITRELIVRE] à la blibliothèque au plus tard à la date : [DATEFIN].\n" +
				"à ce jour nous n'avons toujours pas enregistré le retour de ce livre.\n" +
				"Nous vous invitons à régulariser la situation dès à présent.\n" +
				"\n"+
				"Cordialement.");

		emailDao.save(email);

		/**Reservation du livre: Black Boy**/

		Reservation reservation1 = new Reservation( livre1,"admin",new GregorianCalendar(2021,Calendar.JANUARY,9).getTime(), new GregorianCalendar(2021,Calendar.JANUARY,10).getTime());
		reservation1.setNotified(true);
		reservation1.setDateNotification(new GregorianCalendar(2021,Calendar.JANUARY,11).getTime());
		reservationDao.save(reservation1);
		Reservation reservation2 = new Reservation(livre1,"test",new GregorianCalendar(2021,Calendar.JANUARY,16).getTime(), new GregorianCalendar(2021,Calendar.JANUARY,14).getTime());
		reservationDao.save(reservation2);


		/**Reservation du livre: Ne tirez pas sur l'oiseau moqueur**/

		Reservation reservation3 = new Reservation(livre4,"user",new GregorianCalendar(2021,Calendar.JANUARY,8).getTime(), new GregorianCalendar(2021,01,06).getTime());
		reservationDao.save(reservation3);

		/**Les Raisins De La Colere**/



		/**Email de notification de disponibilité d'un livre**/

		Email email2 = new Email();
		email2.setNom("notification");
		email2.setObjet("notification de disponiblité");
		email2.setContenu("Bonjour [NOMUTILISATEUR], \n "+
				"\n"+
				"\tBonjour, le livre [TITRELIVRE] que vous avez réservé est de nouveau disponible à la blibliothèque .\n" +
				"Vous disposez de 48h à partir du [DATE_RENDU] pour venir retirer votre exemplaire, passé ce délai vous sortirez de la liste d'attente.\n" +
				"Dans l'attente de votre visite.\n" +
				"\n"+
				"Cordialement.");

		emailDao.save(email2);

	}
}
