package com.synectiks.transport.ems.rest;

import com.synectiks.transport.business.service.CommonService;
import com.synectiks.transport.business.service.StopageService;
import com.synectiks.transport.config.ApplicationProperties;
import com.synectiks.transport.domain.Contract;
import com.synectiks.transport.domain.Stopage;
import com.synectiks.transport.domain.vo.CmsContractVo;
import com.synectiks.transport.domain.vo.CmsStopageVo;
import com.synectiks.transport.repository.StopageRepository;
import com.synectiks.transport.service.util.CommonUtil;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller to manage Student.
 */
@RestController
@RequestMapping("/api")
public class StopageRestController {

    private final Logger logger = LoggerFactory.getLogger(StopageRestController.class);

    private static final String ENTITY_NAME = "stopage";

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    private StopageRepository stopageRepository;

    @Autowired
    private StopageService stopageService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsstopage-by-filters")
    public List<CmsStopageVo> getCmsStopageListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Stopages based on filter criteria");
        List<CmsStopageVo> list = this.stopageService.getCmsStopageListOnFilterCriteria(dataMap);
        return list;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/Stopage-by-filters")
    public List<Stopage> getStopageListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Stopages based on filter criteria");
        List<Stopage> list = this.stopageService.getStopageListOnFilterCriteria(dataMap);
        return list;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsStopage")
    public List<CmsStopageVo> getAllCmsStopage() throws Exception {
        logger.debug("REST request to get all Cms stopages");
        Map<String, String> m = new HashMap<String, String>();
        return this.stopageService.getCmsStopageListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-stopage")
    public List<Stopage> getAllStopage() throws Exception {
        logger.debug("REST request to get all the Stopages");
        Map<String, String> m = new HashMap<String, String>();
        return this.stopageService.getStopageListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stopage-by-id/{id}")
    public ResponseEntity<Stopage> getStopage(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Stopage : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.stopageService.getStopage(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsstopage/{id}")
    public ResponseEntity<CmsStopageVo> getCmsStopage(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Stopage : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.stopageService.getCmsStopage(id)));
    }

}



