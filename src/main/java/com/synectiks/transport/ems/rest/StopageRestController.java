package com.synectiks.transport.ems.rest;

import com.synectiks.transport.business.service.CommonService;
import com.synectiks.transport.config.ApplicationProperties;
import com.synectiks.transport.repository.StopageRepository;
import com.synectiks.transport.repository.TransportRouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * REST controller to manage Student.
 */
@RestController
@RequestMapping("/api")
public class StopageRestController {

    private final Logger logger = LoggerFactory.getLogger(ContractRestController.class);

    private static final String ENTITY_NAME = "transportRoute";

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    private StopageRepository stopageRepository;

    @Autowired
    private CommonService commonService;

    @PersistenceContext
    private EntityManager entityManager;


}



