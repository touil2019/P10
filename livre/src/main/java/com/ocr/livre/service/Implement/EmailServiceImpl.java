package com.ocr.livre.service.Implement;

import com.ocr.livre.LivreApplication;
import com.ocr.livre.beans.UtilisateurBean;
import com.ocr.livre.dao.EmailDao;
import com.ocr.livre.model.Email;
import com.ocr.livre.model.Emprunt;
import com.ocr.livre.proxies.MicroserviceUtilisateurProxy;
import com.ocr.livre.service.EmailService;
import com.ocr.livre.service.EmpruntService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = (Logger) LogManager.getLogger(LivreApplication.class);

    @Autowired
    private JavaMailSenderImpl sender;

    @Autowired
    private EmpruntService empruntService ;

    @Autowired
    MicroserviceUtilisateurProxy microserviceUtilisateurProxy ;

    @Autowired
    EmailDao emailDao ;

    /**
     * envoi d un email
     * @param email adresse email
     * @param objet objet de l email
     * @param contenu contenu de l email
     * @throws MessagingException
     */
  @Override
    public void sendSimpleMessage(String email, String objet, String contenu) throws MessagingException {

        logger.info("Appel EmailServiceImpl méthode sendSimpleMessage à l'adresse : " + email);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject(objet);
        helper.setText(contenu);

        sender.send(message);
    }

    /**
     * methode pour l envoye d une relance par email
     * @throws MessagingException
     */
   @Override
    public void envoyerEmailRelance() throws MessagingException {

        Email email = emailDao.findByNom("relance");
        List<Emprunt> listeEmpruntNonRendue = empruntService.listeLivreNonRendueApresDateFin();

        for (Emprunt e: listeEmpruntNonRendue) {

            Date dateFin = e.getDateFin();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sdf.format(dateFin);

            UtilisateurBean utilisateur = microserviceUtilisateurProxy.recupererUnUtilisateur(e.getPseudoEmprunteur());

            logger.info("Appel EmailServiceImpl méthode envoyerEmailRelance à l'adresse : " + utilisateur.getEmail() + " pour le livre : " +e.getLivre().getTitre() + " pour l'emprunt id : " + e.getIdEmprunt());

            String text = email.getContenu()
                    .replace("[NOMUTILISATEUR]", e.getPseudoEmprunteur())
                    .replace("[TITRELIVRE]", e.getLivre().getTitre())
                    .replace("[DATEFIN]", strDate);

            sendSimpleMessage(utilisateur.getEmail(), email.getObjet(),text);
        }
    }
}