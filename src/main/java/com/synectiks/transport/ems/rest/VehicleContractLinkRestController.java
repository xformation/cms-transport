package com.synectiks.transport.ems.rest;

import com.synectiks.transport.business.service.VehicleContractLinkService;
import com.synectiks.transport.domain.VehicleContractLink;
import com.synectiks.transport.domain.vo.CmsVehicleContractLinkVo;
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
public class VehicleContractLinkRestController {

    private final Logger logger = LoggerFactory.getLogger(VehicleContractLinkRestController.class);

@Autowired
    private VehicleContractLinkService vehicleContractLinkService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsvehicleContractLink-by-filters")
    public List<CmsVehicleContractLinkVo> getCmsVehicleContractLinkListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Vehicle based on filter criteria");
        List<CmsVehicleContractLinkVo> list = this.vehicleContractLinkService.getCmsVehicleContractLinkListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/VehicleContractLink-by-filters")
    public List<VehicleContractLink> getVehicleContractLinkListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of VehicleContractLink based on filter criteria");
        List<VehicleContractLink> list = this.vehicleContractLinkService.getVehicleContractLinkListOnFilterCriteria(dataMap);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsVehicleContractLink")
    public List<CmsVehicleContractLinkVo> getAllCmsVehicleContractLink() throws Exception {
        logger.debug("REST request to get all Cms VehicleContractLink");
        Map<String, String> m = new HashMap<String, String>();
        return this.vehicleContractLinkService.getCmsVehicleContractLinkListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-vehicleContractLink")
    public List<VehicleContractLink> getAllVehicleContractLink() throws Exception {
        logger.debug("REST request to get all the VehicleContractLink");
        Map<String, String> m = new HashMap<String, String>();
        return this.vehicleContractLinkService.getVehicleContractLinkListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vehicleContractLink-by-id/{id}")
    public ResponseEntity<VehicleContractLink> getVehicleContractLink(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a VehicleContractLink : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.vehicleContractLinkService.getVehicleContractLink(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsVehicleContractLink/{id}")
    public ResponseEntity<CmsVehicleContractLinkVo> getCmsVehicleContractLink(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a VehicleContractLink : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.vehicleContractLinkService.getCmsVehicleContractLink(id)));
    }

}
