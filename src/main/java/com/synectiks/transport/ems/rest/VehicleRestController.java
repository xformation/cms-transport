package com.synectiks.transport.ems.rest;

import com.synectiks.transport.business.service.VehicleService;
import com.synectiks.transport.domain.Vehicle;
import com.synectiks.transport.domain.vo.CmsVehicleVo;
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
public class VehicleRestController {

    private final Logger logger = LoggerFactory.getLogger(VehicleRestController.class);

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET, value = "/cmsvehicle-by-filters")
    public List<CmsVehicleVo> getCmsVehicleListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Vehicles based on filter criteria");
        List<CmsVehicleVo> list = this.vehicleService.getCmsVehicleListOnFilterCriteria(dataMap);
        return list;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/vehicle-by-filters")
    public List<Vehicle> getVehicleListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Vehicles based on filter criteria");
        List<Vehicle> list = this.vehicleService.getVehicleListOnFilterCriteria(dataMap);
        return list;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsVehicle")
    public List<CmsVehicleVo> getAllCmsVehicle() throws Exception {
        logger.debug("REST request to get all Cms vehicles");
        Map<String, String> m = new HashMap<String, String>();
        return this.vehicleService.getCmsVehicleListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-vehicle")
    public List<Vehicle> getAllVehicle() throws Exception {
        logger.debug("REST request to get all the Vehicles");
        Map<String, String> m = new HashMap<String, String>();
        return this.vehicleService.getVehicleListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vehicle-by-id/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Vehicle : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.vehicleService.getVehicle(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsvehicle/{id}")
    public ResponseEntity<CmsVehicleVo> getCmsVehicle(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Vehicle : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.vehicleService.getCmsVehicle(id)));
    }
//    @RequestMapping(method = RequestMethod.GET, value = "/cmsvehicles-bydepartmentid")
//    public List<Vehicle> getSubjectsByDepartmentId(@RequestParam Map<String, String> dataMap) {
//        List<Vehicle> list = this.vehicleService.getVehicleListOnFilterCriteria(dataMap);
//        return list;
//    }

}
