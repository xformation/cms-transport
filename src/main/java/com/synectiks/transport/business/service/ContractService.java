package com.synectiks.transport.business.service;

import com.synectiks.transport.constant.CmsConstants;
import com.synectiks.transport.domain.Contract;
import com.synectiks.transport.domain.TransportRoute;
import com.synectiks.transport.domain.Vehicle;
import com.synectiks.transport.domain.VehicleContractLink;
import com.synectiks.transport.domain.vo.CmsContractVo;
import com.synectiks.transport.domain.vo.CmsTransportRouteVo;
import com.synectiks.transport.domain.vo.CmsVehicleVo;
import com.synectiks.transport.graphql.types.Contract.AddContractInput;
import com.synectiks.transport.repository.ContractRepository;
import com.synectiks.transport.repository.VehicleContractLinkRepository;
import com.synectiks.transport.service.VehicleDriverLinkService;
import com.synectiks.transport.service.util.CommonUtil;
import com.synectiks.transport.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Component
public class ContractService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ContractRepository contractRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    VehicleContractLinkService vehicleContractLinkService;

    @Autowired
    VehicleContractLinkRepository vehicleContractLinkRepository;

    @Autowired
    CommonService commonService;

    public List<CmsContractVo> getCmsContractListOnFilterCriteria(Map<String, String> criteriaMap) {
        Contract obj = new Contract();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("vendorName") != null) {
            obj.setVendorName(criteriaMap.get("vendorName"));
            isFilter = true;
        }
        if (criteriaMap.get("typeOfOwnerShip") != null) {
            obj.setTypeOfOwnerShip(criteriaMap.get("typeOfOwnerShip"));
            isFilter = true;
        }
        if (criteriaMap.get("durationOfContract") != null) {
            obj.setDurationOfContract(criteriaMap.get("durationOfContract"));
            isFilter = true;
        }
        if (criteriaMap.get("startDate") != null) {
            obj.setStartDate(DateFormatUtil.convertStringToLocalDate(criteriaMap.get("startDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            isFilter = true;
        }
        if (criteriaMap.get("endDate") != null) {
            obj.setEndDate(DateFormatUtil.convertStringToLocalDate(criteriaMap.get("endDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            isFilter = true;
        }
            List<Contract> list = null;
            if(isFilter) {
                list = this.contractRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
            }else {
                list = this.contractRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            }
            List<CmsContractVo> ls = changeContractToCmsContractList(list);
            if(ls.size() == 0) {
                return Collections.emptyList();
            }
            Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
            return ls;
        }

    public List<Contract> getContractListOnFilterCriteria(Map<String, String> criteriaMap) {
        Contract obj = new Contract();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("vendorName") != null) {
            obj.setVendorName(criteriaMap.get("vendorName"));
            isFilter = true;
        }
        if (criteriaMap.get("typeOfOwnerShip") != null) {
            obj.setTypeOfOwnerShip(criteriaMap.get("typeOfOwnerShip"));
            isFilter = true;
        }
        if (criteriaMap.get("durationOfContract") != null) {
            obj.setDurationOfContract(criteriaMap.get("durationOfContract"));
            isFilter = true;
        }
        if (criteriaMap.get("startDate") != null) {
            obj.setStartDate(DateFormatUtil.convertStringToLocalDate(criteriaMap.get("startDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            isFilter = true;
        }
        if (criteriaMap.get("endDate") != null) {
            obj.setEndDate(DateFormatUtil.convertStringToLocalDate(criteriaMap.get("endDate"), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            isFilter = true;
        }
        List<Contract> list = null;
        if (isFilter) {
            list = this.contractRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        } else {
            list = this.contractRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        List<CmsContractVo> ls = changeContractToCmsContractList(list);
        if (ls.size() == 0) {
            return Collections.emptyList();
        }
        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }
    public CmsContractVo getCmsContract(Long id){
        Optional<Contract> c = this.contractRepository.findById(id);
        if(c.isPresent()) {
            CmsContractVo vo = CommonUtil.createCopyProperties(c.get(), CmsContractVo.class);
            convertDatesAndProvideDependencies(c.get(), vo);
            logger.debug("CmsContract for given id : "+id+". CmsContract object : "+vo);
            return vo;
        }
        logger.debug("Contract object not found for the given id. "+id+". Returning null object");
        return null;
    }

    public Contract getContract(Long id){
        Optional< Contract> c = this.contractRepository.findById(id);
        if(c.isPresent()) {
            return c.get();
        }
        logger.debug("Contract object not found for the given id. "+id+". Returning null");
        return null;
    }

    public List<VehicleContractLink> getVehicleContractList(CmsContractVo vo){
        VehicleContractLink vehicleContractLink = new VehicleContractLink();
        Contract contract = CommonUtil.createCopyProperties(vo, Contract.class);
        vehicleContractLink.setContract(contract);
        List<VehicleContractLink> list = this.vehicleContractLinkRepository.findAll(Example.of(vehicleContractLink));
//    Collections.sort(list, (o1, o2) -> o1.getFacility().getName().compareTo(o2.getFacility().getName()));
        return list;
    }

    private List<CmsContractVo> changeContractToCmsContractList(List<Contract> list){
        List<CmsContractVo> ls = new ArrayList<>();
        for(Contract c: list) {
            CmsContractVo vo = CommonUtil.createCopyProperties(c, CmsContractVo.class);
            convertDatesAndProvideDependencies(c, vo);
            ls.add(vo);
        }
        return ls;
    }
    private void convertDatesAndProvideDependencies(Contract c, CmsContractVo vo) {
        if (c.getStartDate() != null) {
            vo.setStrStartDate(DateFormatUtil.changeLocalDateFormat(c.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setStartDate(null);

        }
        if(c.getEndDate() != null) {
            vo.setStrEndDate(DateFormatUtil.changeLocalDateFormat(c.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setEndDate(null);
        }
    }
//    public List<Contract> getContractList(Long branchId) {
//        List<Contract> list = null;
//        if(branchId != null) {
//            Vehicle vl = new Vehicle();
//            vl.setBranchId(branchId);
//            list = this.vehicleRepository.findAll(Example.of(vl));
//        }else {
//            list = this.vehicleRepository.findAll();
//        }
//        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
//        return list;
//    }
    public List<CmsContractVo> getContractList(){
        List<Contract> list = this.contractRepository.findAll();
        List<CmsContractVo> ls = changeContractToCmsContractList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }


    public CmsContractVo addContract(AddContractInput cmsContractVo) {
        logger.info("Saving Contract");
        CmsContractVo vo = null;
        try {
            Contract contract = null;
            if (cmsContractVo.getId() == null) {
                logger.debug("Adding new contract");
                contract = CommonUtil.createCopyProperties(cmsContractVo, Contract.class);
            } else {
                logger.debug("Updating existing contract");
                contract = this.contractRepository.findById(cmsContractVo.getId()).get();

                if (cmsContractVo.getVendorName() != null) {
                    contract.setVendorName(cmsContractVo.getVendorName());
                }
                if (cmsContractVo.getDurationOfContract() != null) {
                    contract.setDurationOfContract(cmsContractVo.getDurationOfContract());
                }
                if (cmsContractVo.getTypeOfOwnerShip() != null) {
                    contract.setTypeOfOwnerShip(cmsContractVo.getTypeOfOwnerShip());
                }
            }
            contract.setStartDate(cmsContractVo.getStrStartDate() != null ? DateFormatUtil.convertStringToLocalDate(cmsContractVo.getStrStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            contract.setEndDate(cmsContractVo.getStrEndDate() != null ? DateFormatUtil.convertStringToLocalDate(cmsContractVo.getStrEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            contract = this.contractRepository.save(contract);
            vo = CommonUtil.createCopyProperties(contract, CmsContractVo.class);
            vo.setStrStartDate(contract.getStartDate() != null ? DateFormatUtil.changeLocalDateFormat(contract.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrEndDate(contract.getEndDate() != null ? DateFormatUtil.changeLocalDateFormat(contract.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setExitCode(0L);
            if (cmsContractVo.getId() == null) {
                vo.setExitDescription("Contract is added successfully");
                logger.debug("Contract is added successfully");
            } else {
                vo.setExitDescription("Contract is updated successfully");
                logger.debug("Contract is updated successfully");
            }
        } catch (Exception e) {
            vo = new CmsContractVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, contract data cannot be saved");
            logger.error("Contract save failed. Exception : ", e);
        }
        logger.info("Contract saved successfully");
        List<CmsContractVo> ls = getContractList();
        vo.setDataList(ls);
        return vo;
    }
}
