package com.synectiks.transport.ems.rest;

import com.synectiks.transport.business.service.CommonService;
import com.synectiks.transport.business.service.ContractService;
import com.synectiks.transport.config.ApplicationProperties;
import com.synectiks.transport.domain.Contract;
import com.synectiks.transport.domain.vo.CmsContractVo;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * REST controller to manage Student.
 */
@RestController
@RequestMapping("/api")
public class ContractRestController {

    private final Logger logger = LoggerFactory.getLogger(ContractRestController.class);

    private static final String ENTITY_NAME = "contract";

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    private ContractService contractService;

    @Autowired
    private CommonService commonService;


    @RequestMapping(method = RequestMethod.GET, value = "/cmscontract-by-filters")
    public List<CmsContractVo> getCmsContractListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Cms Contracts based on filter criteria");
        List<CmsContractVo> list = this.contractService.getCmsContractListOnFilterCriteria(dataMap);
        return list;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/Contract-by-filters")
    public List<Contract> getContractListOnFilterCriteria(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("Rest request to get list of Contracts based on filter criteria");
        List<Contract> list = this.contractService.getContractListOnFilterCriteria(dataMap);
        return list;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsContract")
    public List<CmsContractVo> getAllCmsContract() throws Exception {
        logger.debug("REST request to get all Cms contracts");
        Map<String, String> m = new HashMap<String, String>();
        return this.contractService.getCmsContractListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-contract")
    public List<Contract> getAllContract() throws Exception {
        logger.debug("REST request to get all the Contracts");
        Map<String, String> m = new HashMap<String, String>();
        return this.contractService.getContractListOnFilterCriteria(m);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contract-by-id/{id}")
    public ResponseEntity<Contract> getContract(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Contract : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.contractService.getContract(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmscontract/{id}")
    public ResponseEntity<CmsContractVo> getCmsContract(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a Contract : {}", id);
        return ResponseUtil.wrapOrNotFound(Optional.of(this.contractService.getCmsContract(id)));
    }

}
