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
import com.ocr.livre.service.Implement.EmpruntServiceImpl;
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
		livre1.setQuantiteDispo(1);
		livreDao.save(livre1);
		Livre livre2 = new Livre("WRIGHT","Richard","Black Boy","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/41sH5979BrL._SX302_BO1,204,203,200_.jpg");
		livre2.setDisponible(false);
		livre2.setQuantiteDispo(1);
		livreDao.save(livre2);
		Livre livre4 = new Livre("STEINBECK","JOHN","Les Raisins De La Colere","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/418A1zRYhGL._SX302_BO1,204,203,200_.jpg");
		livre4.setDisponible(false);
		livre4.setQuantiteDispo(1);
		livreDao.save(livre4);
		Livre livre5 = new Livre("STEINBECK","JOHN","Les Raisins De La Colere","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/418A1zRYhGL._SX302_BO1,204,203,200_.jpg");
		livre5.setDisponible(false);
		livre5.setQuantiteDispo(1);
		livreDao.save(livre5);
		Livre livre6 = new Livre("STEINBECK","JOHN","Les Raisins De La Colere","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/418A1zRYhGL._SX302_BO1,204,203,200_.jpg");
		livre6.setQuantiteDispo(1);
		livreDao.save(livre6);
		Livre livre7 = new Livre("LEE","Harper","Ne Tirez Pas Sur L'Oiseau Moqueur","LE LIVRE DE POCHE","https://m.media-amazon.com/images/I/41mY4e0kS9L.jpg");
		livre7.setDisponible(false);
		livre7.setQuantiteDispo(1);
		livreDao.save(livre7);
		Livre livre8 = new Livre("LEE","Harper","Ne Tirez Pas Sur L'Oiseau Moqueur","LE LIVRE DE POCHE","https://m.media-amazon.com/images/I/41mY4e0kS9L.jpg");
		livre8.setDisponible(false);
		livre8.setQuantiteDispo(1);
		livreDao.save(livre8);
		/*Livre livre9 = new Livre("LEE","Harper","Ne Tirez Pas Sur L'Oiseau Moqueur","LE LIVRE DE POCHE","https://m.media-amazon.com/images/I/41mY4e0kS9L.jpg");
		livre9.setQuantiteDispo(1);
		livreDao.save(livre9);*/


		/**Emprunts du livre : Black Boy**/

		Emprunt emprunt1 = new Emprunt( "user", new GregorianCalendar(2021,Calendar.JANUARY,11).getTime(), true,false,livre1 );
		emprunt1.setDateFin(empruntService.ajouter4Semaines(emprunt1.getDateDebut()));
		empruntLivreDao.save(emprunt1);

		Emprunt emprunt2 = new Emprunt("admin",new GregorianCalendar(2020,Calendar.DECEMBER,28).getTime(),true,false,livre2);
		emprunt2.setDateFin(empruntService.ajouter4Semaines(emprunt2.getDateDebut()));
		empruntLivreDao.save(emprunt2);


		/**Emprunts du livre : Les Raisins De La Colere**/

		Emprunt emprunt3 = new Emprunt("user",new GregorianCalendar(2020, Calendar.DECEMBER,15).getTime(),true,false,livre4);
		emprunt3.setDateFin(empruntService.ajouter4Semaines(emprunt3.getDateDebut()));
		empruntLivreDao.save(emprunt3);


		Emprunt emprunt4 = new Emprunt("test",new GregorianCalendar(2020,Calendar.DECEMBER,28).getTime(),true,false,livre5);
		emprunt4.setDateFin(empruntService.ajouter4Semaines(emprunt4.getDateDebut()));
		empruntLivreDao.save(emprunt4);

		Emprunt emprunt5 = new Emprunt("admin",new GregorianCalendar(2021,01,12).getTime(),true,false,livre6);
		emprunt5.setDateFin(empruntService.ajouter4Semaines(emprunt5.getDateDebut()));
		empruntLivreDao.save(emprunt5);

		/**Emprunts du livre : Ne Tirez Pas Sur L'Oiseau Moqueur**/

		Emprunt emprunt6 = new Emprunt("user",new GregorianCalendar(2020,Calendar.DECEMBER,12).getTime(),true,false,livre7);
		emprunt6.setDateFin(empruntService.ajouter4Semaines(emprunt6.getDateDebut()));
		empruntLivreDao.save(emprunt6);

		Emprunt emprunt7 = new Emprunt("test",new GregorianCalendar(2020,Calendar.DECEMBER,12).getTime(),true,false,livre8);
		emprunt7.setDateFin(empruntService.ajouter4Semaines(emprunt7.getDateDebut()));
		empruntLivreDao.save(emprunt7);

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

		Reservation reservation1 = new Reservation( livre2,"test1",new GregorianCalendar(2021,01,13).getTime(), new GregorianCalendar(2021,01,13).getTime());
		reservationDao.save(reservation1);
		Reservation reservation2 = new Reservation(livre1,"test2",new GregorianCalendar(2021,01,16).getTime(), new GregorianCalendar(2021,01,14).getTime());
		reservationDao.save(reservation2);
		Reservation reservation3 = new Reservation( livre2,"test3",new GregorianCalendar(2021,01,15).getTime(), new GregorianCalendar(2021,01,13).getTime());
		reservationDao.save(reservation3);
		Reservation reservation4 = new Reservation(livre1,"test4",new GregorianCalendar(2021,01,8).getTime(), new GregorianCalendar(2021,01,06).getTime());
		reservationDao.save(reservation4);


		/**Reservation du livre: Ne tirez pas sur l'oiseau moqueur**/

		Reservation reservation5 = new Reservation(livre7,"test1",new GregorianCalendar(2021,Calendar.JANUARY,24).getTime(), new GregorianCalendar(2021, Calendar.JANUARY,22).getTime());
		reservationDao.save(reservation5);
		Reservation reservation6 = new Reservation(livre8,"test2",new GregorianCalendar(2021,Calendar.JANUARY,17).getTime(), new GregorianCalendar(2021,Calendar.JANUARY,15).getTime());
		reservationDao.save(reservation6);
		Reservation reservation7 = new Reservation(livre7,"test3",new GregorianCalendar(2021,Calendar.JANUARY,18).getTime(), new GregorianCalendar(2021,Calendar.JANUARY,16).getTime());
		reservationDao.save(reservation7);
		Reservation reservation8 = new Reservation(livre8,"test4",new GregorianCalendar(2021,Calendar.JANUARY,18).getTime(), new GregorianCalendar(2021,Calendar.JANUARY,16).getTime());
		reservationDao.save(reservation8);

		/**Les Raisins De La Colere**/

		Reservation reservation9 = new Reservation(livre4,"test1",new GregorianCalendar(2021,01,16).getTime(), new GregorianCalendar(2021,01,14).getTime());
		reservationDao.save(reservation9);
		Reservation reservation10 = new Reservation(livre5,"test2",new GregorianCalendar(2021,01,16).getTime(), new GregorianCalendar(2021,01,14).getTime());
		reservationDao.save(reservation10);
		Reservation reservation11 = new Reservation(livre6,"test3",new GregorianCalendar(2021,01,16).getTime(), new GregorianCalendar(2021,01,14).getTime());
		reservationDao.save(reservation11);
		Reservation reservation12 = new Reservation(livre6,"test4",new GregorianCalendar(2020,Calendar.DECEMBER,16).getTime(), new GregorianCalendar(2020,12,14).getTime());
		reservationDao.save(reservation12);
		Reservation reservation13 = new Reservation(livre5,"test5",new GregorianCalendar(2020,Calendar.DECEMBER,16).getTime(), new GregorianCalendar(2020,12,14).getTime());
		reservationDao.save(reservation13);
		Reservation reservation14 = new Reservation(livre5,"test6",new GregorianCalendar(2020,Calendar.DECEMBER,16).getTime(), new GregorianCalendar(2020,12,14).getTime());
		reservationDao.save(reservation14);


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
