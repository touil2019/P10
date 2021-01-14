package com.ocr.livre.batch;


import com.ocr.livre.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;


@Component
public class BatchJob {

    private static final Logger logger = LogManager.getLogger(BatchJob.class);

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "0 */60 * * * *")
    public void lendingRevival() throws MessagingException {
      logger.info("Execution du batch");


        emailService.envoyerEmailRelance();
    }
}