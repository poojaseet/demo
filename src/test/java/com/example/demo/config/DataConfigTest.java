package com.example.demo.config;

import com.example.demo.persistence.FauxDataSvc;
import com.example.demo.service.FauxCompanyEventsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataConfigTest {
    @Autowired
    private FauxDataSvc dataSvc;
    @Autowired
    private FauxCompanyEventsImpl companySvc;
  //  @Autowired
 //   private FauxPeoplesSvcImpl peopleSvc;


    @BeforeEach
    public void setUp() {
    }

}