package com.synectiks.transport.ems.rest;

import com.synectiks.transport.domain.TransportRouteVehicleLink;
import com.synectiks.transport.domain.vo.CmsTransportRouteVehicleLinkVo;
import com.synectiks.transport.business.service.TransportRouteVehicleLinkService;
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
public class TransportRouteVehicleLinkRestController {

    private final Logger logger = LoggerFactory.getLogger(TransportRouteVehicleLinkRestController.class);

    @Autowired
    private TransportRouteVehicleLinkService transportRouteVehicleLinkService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsTransportRouteVehicleLink-by-filters")
    public List<CmsTransportRouteVehicleLinkVo> getCmsTransportRouteVehicleLinkListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms TransportRouteVehicleLink based on filter criteria");
        List<CmsTransportRouteVehicleLinkVo> list = this.transportRouteVehicleLinkService.getCmsTransportRouteVehicleLinkListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/TransportRouteVehicleLink-by-filters")
    public List<TransportRouteVehicleLink> getTransportRouteVehicleLinkListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of TransportRouteVehicleLink based on filter criteria");
        List<TransportRouteVehicleLink> list = this.transportRouteVehicleLinkService.getTransportRouteVehicleLinkListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsTransportRouteVehicleLink")
    public List<CmsTransportRouteVehicleLinkVo> getAllCmsTransportRouteVehicleLink() throws Exception {
        logger.debug("REST request to get all Cms TransportRouteVehicleLink");
        Map<String, String> m = new HashMap<String, String>();
        return this.transportRouteVehicleLinkService.getCmsTransportRouteVehicleLinkListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-transportRouteVehicleLink")
    public List<TransportRouteVehicleLink> getAllTransportRouteVehicleLink() throws Exception {
        logger.debug("REST request to get all the TransportRouteVehicleLink");
        Map<String, String> m = new HashMap<String, String>();
        return this.transportRouteVehicleLinkService.getTransportRouteVehicleLinkListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transportRouteVehicleLink-by-id/{id}")
    public ResponseEntity<TransportRouteVehicleLink> getTransportRouteVehicleLink(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a TransportRouteVehicleLink : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.transportRouteVehicleLinkService.getTransportRouteVehicleLink(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsTransportRouteVehicleLink/{id}")
    public ResponseEntity<CmsTransportRouteVehicleLinkVo> getCmsTransportRouteVehicleLink(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a TransportRouteVehicleLink : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.transportRouteVehicleLinkService.getCmsTransportRouteVehicleLink(id)));
    }
}
