package com.ocr.livre.batch;


import com.ocr.livre.service.EmailService;
import com.ocr.livre.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class BatchJob2 {

    private static final Logger logger = LogManager.getLogger(BatchJob2.class);

    @Autowired
    ReservationService reservationService;

    @Scheduled(cron = "* */10 * * * *")
    public void lendingRevival() throws MessagingException {
        logger.info("Execution du batch de notification");

        reservationService.purgeFileAttente();

    }
}