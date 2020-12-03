package com.ocr.livre;

import com.ocr.livre.configuration.CustomErrorDecoder;
import com.ocr.livre.dao.EmailDao;
import com.ocr.livre.dao.LivreDao;
import com.ocr.livre.dao.EmpruntLivreDao;
import com.ocr.livre.model.Email;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.model.Livre;
import com.ocr.livre.service.Implement.EmpruntServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.Date;
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

	public static void main(String[] args) {
		SpringApplication.run(LivreApplication.class, args);
	}

	@Bean
	public CustomErrorDecoder CustomErrorDecoder() {
		return new CustomErrorDecoder();
	}

	@PostConstruct
	public void postConstruct(){
		Livre livre1 = new Livre("WRIGHT","Richard","Black Boy","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/41sH5979BrL._SX302_BO1,204,203,200_.jpg");
		livreDao.save(livre1);
		Livre livre2 = new Livre("WRIGHT","Richard","Black Boy","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/41sH5979BrL._SX302_BO1,204,203,200_.jpg");
		livreDao.save(livre2);
		Livre livre3 = new Livre("WRIGHT","Richard","Black Boy","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/41sH5979BrL._SX302_BO1,204,203,200_.jpg");
		livreDao.save(livre3);
		Livre livre4 = new Livre("STEINBECK","JOHN","Les Raisins De La Colere","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/418A1zRYhGL._SX302_BO1,204,203,200_.jpg");
		livreDao.save(livre4);
		Livre livre5 = new Livre("STEINBECK","JOHN","Les Raisins De La Colere","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/418A1zRYhGL._SX302_BO1,204,203,200_.jpg");
		livreDao.save(livre5);
		Livre livre6 = new Livre("STEINBECK","JOHN","Les Raisins De La Colere","GALLIMARD","https://images-na.ssl-images-amazon.com/images/I/418A1zRYhGL._SX302_BO1,204,203,200_.jpg");
		livreDao.save(livre6);
		Livre livre7 = new Livre("LEE","Harper","Ne Tirez Pas Sur L'Oiseau Moqueur","LE LIVRE DE POCHE","https://m.media-amazon.com/images/I/41mY4e0kS9L.jpg");
		livreDao.save(livre7);
		Livre livre8 = new Livre("LEE","Harper","Ne Tirez Pas Sur L'Oiseau Moqueur","LE LIVRE DE POCHE","https://m.media-amazon.com/images/I/41mY4e0kS9L.jpg");
		livreDao.save(livre8);
		Livre livre9 = new Livre("LEE","Harper","Ne Tirez Pas Sur L'Oiseau Moqueur","LE LIVRE DE POCHE","https://m.media-amazon.com/images/I/41mY4e0kS9L.jpg");
		livreDao.save(livre9);

		Emprunt emprunt1 = new Emprunt( "user", new GregorianCalendar(2020,07,12).getTime(), true,false,livre1 );
		emprunt1.setDateFin(empruntService.ajouter4Semaines(emprunt1.getDateDebut()));
		empruntLivreDao.save(emprunt1);

		Emprunt emprunt2 = new Emprunt("user",new GregorianCalendar(2020,07,28).getTime(),true,false,livre4);
		emprunt2.setDateFin(empruntService.ajouter4Semaines(emprunt2.getDateDebut()));
		empruntLivreDao.save(emprunt2);

		Emprunt emprunt3 = new Emprunt("user",new GregorianCalendar(2020,8,01).getTime(),true,false,livre7);
		emprunt3.setDateFin(empruntService.ajouter4Semaines(emprunt3.getDateDebut()));
		empruntLivreDao.save(emprunt3);

		Emprunt emprunt4 = new Emprunt("admin",new GregorianCalendar(2020,7,22).getTime(),true,false,livre2);
		emprunt4.setDateFin(empruntService.ajouter4Semaines(emprunt4.getDateDebut()));
		empruntLivreDao.save(emprunt4);

		Emprunt emprunt5 = new Emprunt("admin",new GregorianCalendar(2020,7,12).getTime(),true,false,livre5);
		emprunt5.setDateFin(empruntService.ajouter4Semaines(emprunt5.getDateDebut()));
		empruntLivreDao.save(emprunt5);

		Emprunt emprunt6 = new Emprunt("admin",new GregorianCalendar(2020,06,06).getTime(),true,false,livre8);
		emprunt6.setDateFin(empruntService.ajouter4Semaines(emprunt6.getDateDebut()));
		empruntLivreDao.save(emprunt6);


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


	}
}
