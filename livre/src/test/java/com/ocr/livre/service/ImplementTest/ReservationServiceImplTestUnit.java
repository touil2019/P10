package com.ocr.livre.service.ImplementTest;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTestUnit {

    @Autowired
    @InjectMocks
    private ReservationServiceImpl reservationService;



}
