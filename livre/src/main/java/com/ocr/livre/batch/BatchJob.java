package com.ocr.livre.batch;

import com.ocr.livre.LivreApplication;
import com.ocr.livre.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.logging.Logger;

@Component
public class BatchJob {

    private static final Logger logger = (Logger) LogManager.getLogger(LivreApplication.class);

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "0 0 4 * * *")
    public void lendingRevival() throws MessagingException {
      logger.info("Execution du batch");


        emailService.envoyerEmailRelance();
    }
}