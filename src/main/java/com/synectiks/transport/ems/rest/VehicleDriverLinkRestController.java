package com.synectiks.transport.ems.rest;

import com.synectiks.transport.business.service.VehicleDriverLinkService;
import com.synectiks.transport.domain.VehicleDriverLink;
import com.synectiks.transport.domain.vo.CmsVehicleDriverLinkVo;
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
public class VehicleDriverLinkRestController {

    private final Logger logger = LoggerFactory.getLogger(VehicleDriverLinkRestController.class);

    @Autowired
    private VehicleDriverLinkService vehicleDriverLinkService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsVehicleDriverLink-by-filters")
    public List<CmsVehicleDriverLinkVo> getCmsVehicleDriverLinkListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms VehicleDriverLink based on filter criteria");
        List<CmsVehicleDriverLinkVo> list = this.vehicleDriverLinkService.getCmsVehicleDriverLinkListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/VehicleDriverLink-by-filters")
    public List<VehicleDriverLink> getVehicleDriverLinkListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of VehicleDriverLink based on filter criteria");
        List<VehicleDriverLink> list = this.vehicleDriverLinkService.getVehicleDriverLinkListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsVehicleDriverLink")
    public List<CmsVehicleDriverLinkVo> getAllCmsVehicleDriverLink() throws Exception {
        logger.debug("REST request to get all Cms VehicleDriverLink");
        Map<String, String> m = new HashMap<String, String>();
        return this.vehicleDriverLinkService.getCmsVehicleDriverLinkListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-vehicleDriverLink")
    public List<VehicleDriverLink> getAllVehicleDriverLink() throws Exception {
        logger.debug("REST request to get all the VehicleDriverLink");
        Map<String, String> m = new HashMap<String, String>();
        return this.vehicleDriverLinkService.getVehicleDriverLinkListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vehicleDriverLink-by-id/{id}")
    public ResponseEntity<VehicleDriverLink> getVehicleDriverLink(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a VehicleDriverLink : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.vehicleDriverLinkService.getVehicleDriverLink(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsVehicleDriverLink/{id}")
    public ResponseEntity<CmsVehicleDriverLinkVo> getCmsVehicleDriverLink(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a VehicleDriverLink : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.vehicleDriverLinkService.getCmsVehicleDriverLink(id)));
    }
}
