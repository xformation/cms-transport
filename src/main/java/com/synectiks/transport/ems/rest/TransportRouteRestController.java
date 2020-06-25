package com.synectiks.transport.ems.rest;

import com.synectiks.transport.business.service.CommonService;
import com.synectiks.transport.business.service.TransportRouteService;
import com.synectiks.transport.config.ApplicationProperties;
import com.synectiks.transport.domain.*;
import com.synectiks.transport.domain.vo.CmsContractVo;
import com.synectiks.transport.domain.vo.CmsTransportRouteVo;
import com.synectiks.transport.repository.TransportRouteRepository;
import com.synectiks.transport.service.util.CommonUtil;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
public class TransportRouteRestController {

    private final Logger logger = LoggerFactory.getLogger(TransportRouteRestController.class);

    private static final String ENTITY_NAME = "transportRoute";

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    TransportRouteService transportRouteService;
//    @Autowired
//    private TransportRouteRepository transportRouteRepository;

    @Autowired
    private CommonService commonService;

    @PersistenceContext
    private EntityManager entityManager;


    @RequestMapping(method = RequestMethod.GET, value = "/cmstransportRoute-by-filters")
    public List<CmsTransportRouteVo> getCmsTransportRouteListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms TransportRoutes based on filter criteria");
        List<CmsTransportRouteVo> list = this.transportRouteService.getCmsTransportRouteListOnFilterCriteria(dataMap);
        return list;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/TransportRoute-by-filters")
    public List<TransportRoute> getTransportRouteListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of TransportRoutes based on filter criteria");
        List<TransportRoute> list = this.transportRouteService.getTransportRouteListOnFilterCriteria(dataMap);
        return list;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsTransportRoute")
    public List<CmsTransportRouteVo> getAllCmsTransportRoute() throws Exception {
        logger.debug("REST request to get all Cms transportRoutes");
        Map<String, String> m = new HashMap<String, String>();
        return this.transportRouteService.getCmsTransportRouteListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-transportRoute")
    public List<TransportRoute> getAllTransportRoute() throws Exception {
        logger.debug("REST request to get all the TransportRoutes");
        Map<String, String> m = new HashMap<String, String>();
        return this.transportRouteService.getTransportRouteListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transportRoute-by-id/{id}")
    public ResponseEntity<TransportRoute> getTransportRoute(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a TransportRoute : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.transportRouteService.getTransportRoute(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmstransportRoute/{id}")
    public ResponseEntity<CmsTransportRouteVo> getCmsTransportRoute(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a TransportRoute : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.transportRouteService.getCmsTransportRoute(id)));
    }


//    @PostMapping("/cmstransport-bulk-load")
//    public List<CmsTransportRouteVo> bulkLoad(@RequestBody List<CmsTransportRouteVo> list) throws Exception {
//        List<CmsTransportRouteVo> failedRecords = new ArrayList<>();
//        for(CmsTransportRouteVo cmsTransportRouteVo: list) {
//            try {
//                createTransportRoute(cmsTransportRouteVo);
//            }catch(Exception e) {
//                failedRecords.add(cmsTransportRouteVo);
//                log.error("Exception. Saving of transportRoute failed. ", e);
//            }
//        }
//
//        return failedRecords;
//    }
//
//
//    @PostMapping("/cmstransport-routes")
//    public ResponseEntity<CmsTransportRouteVo> createTransportRoute(@Valid @RequestBody CmsTransportRouteVo CmsTransportRouteVo) throws Exception {
//        log.debug("REST request to save a transportRoute : {}", CmsTransportRouteVo);
//        if (CmsTransportRouteVo.getId() != null) {
//            throw new BadRequestAlertException("A new transportRoute cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        TransportRoute transportRoute = CommonUtil.createCopyProperties(CmsTransportRouteVo, TransportRoute.class);
//        TransportRoute result = transportRouteRepository.save(transportRoute);
//        CmsTransportRouteVo vo = CommonUtil.createCopyProperties(transportRoute, CmsTransportRouteVo.class);
//        return ResponseEntity.created(new URI("/api/cmstransport-routes/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(vo);
//    }
//
//
//    @PutMapping("/cmstransport-routes")
//    public ResponseEntity<CmsTransportRouteVo> updateTransportRoute(@Valid @RequestBody CmsTransportRouteVo CmsTransportRouteVo) throws URISyntaxException {
//        log.debug("REST request to update a transportRoute : {}", CmsTransportRouteVo);
//        if (CmsTransportRouteVo.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        TransportRoute transportRoute = CommonUtil.createCopyProperties(CmsTransportRouteVo, TransportRoute.class);
//        TransportRoute result = transportRouteRepository.save(transportRoute);
//        CmsTransportRouteVo vo = CommonUtil.createCopyProperties(transportRoute, CmsTransportRouteVo.class);
//        return ResponseEntity.created(new URI("/api/cmstransport-routes/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(vo);
//    }
//
//
//    @GetMapping("/cmstransport-routes")
//    public List<CmsTransportRouteVo> getAllTransportRoutes(@RequestParam Map<String, String> dataMap) {
//        List<TransportRoute> list = null;
//        List<CmsTransportRouteVo> ls = null;
//
//        TransportRoute obj = new TransportRoute();
//        boolean isFilter = false;
//        if(!CommonUtil.isNullOrEmpty(dataMap.get("id"))) {
//            obj.setId(Long.parseLong(dataMap.get("id")));
//            isFilter = true;
//        }
//
//        if(!CommonUtil.isNullOrEmpty(dataMap.get("routeName"))) {
//            isFilter = true;
//            String name = dataMap.get("routeName");
//            StringTokenizer token = new StringTokenizer(name, " ");
//            int cnt = 0;
//            while(token.hasMoreTokens()) {
//                if(cnt == 0) {
//                    obj.setRouteName(token.nextToken());
//                }
//                cnt++;
//            }
////        	ls = getStudentListByName(name) ;
//        }
////        List<Teacher> list = null;
//        if(isFilter) {
//            list = this.transportRouteRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
//        }else {
//            list = this.transportRouteRepository.findAll(Sort.by(Direction.DESC, "id"));
//        }
//        ls = new ArrayList<>();
//        for(TransportRoute tr: list) {
//            CmsTransportRouteVo vo = CommonUtil.createCopyProperties(tr, CmsTransportRouteVo.class);
//            ls.add(vo);
//        }
//
//        return ls;
//    }
//
//
//    @GetMapping("/cmstransport-routes/{id}")
//    public ResponseEntity<CmsTransportRouteVo> getTransportRoute(@PathVariable Long id) {
//        log.debug("REST request to get transportRoute : {}", id);
//        Optional<TransportRoute> transportRouteDTO = transportRouteRepository.findById(id);
//        CmsTransportRouteVo vo = null;
//        if(transportRouteDTO.isPresent()) {
//            TransportRoute tr = transportRouteDTO.get();
//            vo = CommonUtil.createCopyProperties(tr,  CmsTransportRouteVo.class);
//        }else {
//            vo = new CmsTransportRouteVo();
//        }
//        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
//    }
//
//    public List<CmsTransportRouteVo> getTransportRouteListByName(String name) {
//        TransportRoute transportRoute = null;
//        if(!CommonUtil.isNullOrEmpty(name)) {
//            transportRoute = new TransportRoute();
//            StringTokenizer token = new StringTokenizer(name, " ");
//            int cnt = 0;
//            while(token.hasMoreTokens()) {
//                if(cnt == 0) {
//                    transportRoute.setRouteName(token.nextToken());
//                }
//                cnt++;
//            }
//        }
//        log.debug("REST request to get TransportRoute by name : {}", name);
//        List<TransportRoute> list = null;
//        if(transportRoute != null) {
//            list = transportRouteRepository.findAll(Example.of(transportRoute));
//        }else {
//            list = Collections.emptyList();
//        }
//
//        List<CmsTransportRouteVo> ls = new ArrayList<>();
//        for(TransportRoute st: list) {
//            CmsTransportRouteVo vo = CommonUtil.createCopyProperties(st, CmsTransportRouteVo.class);
//            ls.add(vo);
//        }
//        return ls;
//    }
//
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/cmstransport-routes/{id}")
//    public Integer deleteTransportRoute(@PathVariable Long id) {
//        try {
//            log.debug("REST request to delete a section : {}", id);
//            this.transportRouteRepository.deleteById(id);
//        }catch(Exception e) {
//            return HttpStatus.FAILED_DEPENDENCY.value();
//        }
//        return HttpStatus.OK.value();
//    }


}
