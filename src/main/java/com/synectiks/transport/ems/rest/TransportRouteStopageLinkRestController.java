package com.synectiks.transport.ems.rest;

import com.synectiks.transport.business.service.TransportRouteStopageLinkService;
import com.synectiks.transport.domain.TransportRouteStopageLink;

import com.synectiks.transport.domain.vo.CmsTransportRouteStopageLinkVo;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * REST controller to manage Student.
 */
@RestController
@RequestMapping("/api")
public class TransportRouteStopageLinkRestController {

    private final Logger logger = LoggerFactory.getLogger(TransportRouteStopageLinkRestController.class);

    @Autowired
    private TransportRouteStopageLinkService transportRouteStopageLinkService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsTransportRouteStopageLink-by-filters")
    public List<CmsTransportRouteStopageLinkVo> getCmsTransportRouteStopageLinkListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms TransportRouteStopageLink based on filter criteria");
        List<CmsTransportRouteStopageLinkVo> list = this.transportRouteStopageLinkService.getCmsTransportRouteStopageLinkListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/TransportRouteStopageLink-by-filters")
    public List<TransportRouteStopageLink> getTransportRouteStopageLinkListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of TransportRouteStopageLink based on filter criteria");
        List<TransportRouteStopageLink> list = this.transportRouteStopageLinkService.getTransportRouteStopageLinkListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsTransportRouteStopageLink")
    public List<CmsTransportRouteStopageLinkVo> getAllCmsTransportRouteStopageLink() throws Exception {
        logger.debug("REST request to get all Cms TransportRouteStopageLink");
        Map<String, String> m = new HashMap<String, String>();
        return this.transportRouteStopageLinkService.getCmsTransportRouteStopageLinkListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-transportRouteStopageLink")
    public List<TransportRouteStopageLink> getAllTransportRouteStopageLink() throws Exception {
        logger.debug("REST request to get all the TransportRouteStopageLink");
        Map<String, String> m = new HashMap<String, String>();
        return this.transportRouteStopageLinkService.getTransportRouteStopageLinkListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transportRouteStopageLink-by-id/{id}")
    public ResponseEntity<TransportRouteStopageLink> getTransportRouteStopageLink(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a TransportRouteStopageLink : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.transportRouteStopageLinkService.getTransportRouteStopageLink(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsTransportRouteStopageLink/{id}")
    public ResponseEntity<CmsTransportRouteStopageLinkVo> getCmsTransportRouteStopageLink(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a TransportRouteStopageLink : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.transportRouteStopageLinkService.getCmsTransportRouteStopageLink(id)));
    }
}
