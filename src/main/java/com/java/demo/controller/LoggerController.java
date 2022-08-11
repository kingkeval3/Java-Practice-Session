package com.java.demo.controller;

import com.java.demo.AbstractRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/log")
public class LoggerController extends AbstractRestService {

    Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping(value ="/hello")
    public ResponseEntity<?> sampleAPI(){


        logger.info("Intro Service Called");
        logger.warn("Null pointer exception may be thrown while accessing unknown property from properties file");
        logger.error("Sample error thrown: "+new NullPointerException().getMessage());

        return buildSuccess(applicationName,"Application Introduced!");
    }

}
