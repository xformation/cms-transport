package com.synectiks.transport.business.service;

import com.synectiks.transport.config.ApplicationProperties;
import com.synectiks.transport.constant.CmsConstants;
import com.synectiks.transport.domain.*;
import com.synectiks.transport.domain.vo.CmsContractVo;
import com.synectiks.transport.domain.vo.CmsStopageVo;
import com.synectiks.transport.domain.vo.CmsTransportRouteVo;
import com.synectiks.transport.graphql.types.Stopage.AddStopageInput;
import com.synectiks.transport.repository.StopageRepository;
import com.synectiks.transport.repository.TransportRouteStopageLinkRepository;
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
import java.time.LocalDate;
import java.util.*;

@Component
public class StopageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    StopageRepository stopageRepository;

    @Autowired
    TransportRouteStopageLinkRepository transportRouteStopageLinkRepository;

    @Autowired
    TransportRouteStopageLinkService transportRouteStopageLinkService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ApplicationProperties applicationProperties;

    public List<CmsStopageVo> getCmsStopageListOnFilterCriteria(Map<String, String> criteriaMap) {
        Stopage obj = new Stopage();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("stopageName") != null) {
            obj.setStopageName(criteriaMap.get("stopageName"));
            isFilter = true;
        }
        if (criteriaMap.get("status") != null) {
            obj.setStatus(criteriaMap.get("status"));
            isFilter = true;
        }
        if (criteriaMap.get("createdBy") != null) {
            obj.setCreatedBy(criteriaMap.get("createdBy"));
            isFilter = true;
        }
        if (criteriaMap.get("createdOn") != null) {
            obj.setCreatedOn(DateFormatUtil.convertStringToLocalDate(criteriaMap.get("createdOn"), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            isFilter = true;
        }
        if (criteriaMap.get("updatedBy") != null) {
            obj.setUpdatedBy(criteriaMap.get("updatedBy"));
            isFilter = true;
        }
        if (criteriaMap.get("updatedOn") != null) {
            obj.setUpdatedOn(DateFormatUtil.convertStringToLocalDate(criteriaMap.get("updatedOn"), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            isFilter = true;
        }
        if (criteriaMap.get("branchId") != null) {
            obj.setBranchId(Long.parseLong(criteriaMap.get("branchId")));
            isFilter = true;
        }
        List<Stopage> list = null;
        if(isFilter) {
            list = this.stopageRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.stopageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        List<CmsStopageVo> ls = changeStopageToCmsStopageList(list);
        if(ls.size() == 0) {
            return Collections.emptyList();
        }
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public List<Stopage> getStopageListOnFilterCriteria(Map<String, String> criteriaMap) {
        Stopage obj = new Stopage();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("stopageName") != null) {
            obj.setStopageName(criteriaMap.get("stopageName"));
            isFilter = true;
        }
        if (criteriaMap.get("status") != null) {
            obj.setStatus(criteriaMap.get("status"));
            isFilter = true;
        }
        if (criteriaMap.get("createdBy") != null) {
            obj.setCreatedBy(criteriaMap.get("createdBy"));
            isFilter = true;
        }
        if (criteriaMap.get("createdOn") != null) {
            obj.setCreatedOn(DateFormatUtil.convertStringToLocalDate(criteriaMap.get("createdOn"), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            isFilter = true;
        }
        if (criteriaMap.get("updatedBy") != null) {
            obj.setUpdatedBy(criteriaMap.get("updatedBy"));
            isFilter = true;
        }
        if (criteriaMap.get("updatedOn") != null) {
            obj.setUpdatedOn(DateFormatUtil.convertStringToLocalDate(criteriaMap.get("updatedOn"), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            isFilter = true;
        }
        if (criteriaMap.get("branchId") != null) {
            obj.setBranchId(Long.parseLong(criteriaMap.get("branchId")));
            isFilter = true;
        }
        List<Stopage> list = null;
        if (isFilter) {
            list = this.stopageRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        } else {
            list = this.stopageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        List<CmsStopageVo> ls = changeStopageToCmsStopageList(list);
        if (ls.size() == 0) {
            return Collections.emptyList();
        }
        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }
    public CmsStopageVo getCmsStopage(Long id){
        Optional<Stopage> s = this.stopageRepository.findById(id);
        if(s.isPresent()) {
            CmsStopageVo vo = CommonUtil.createCopyProperties(s.get(), CmsStopageVo.class);
            logger.debug("CmsStopage for given id : "+id+". CmsStopage object : "+vo);
            return vo;
        }
        logger.debug("Stopage object not found for the given id. "+id+". Returning null object");
        return null;
    }

    public Stopage getStopage(Long id){
        Optional<Stopage> s = this.stopageRepository.findById(id);
        if(s.isPresent()) {
            return s.get();
        }
        logger.debug("Stopage object not found for the given id. "+id+". Returning null");
        return null;
    }

//    public List<CmsStopageVo> getStopageList(){
//        List<Stopage> list = this.stopageRepository.findAll();
//        List<CmsStopageVo> ls = changeStopageToCmsStopageList(list);
//        logger.debug("Stopage list : "+list);
//        return ls;
//    }

    public List<TransportRouteStopageLink> getRouteStopageList(CmsStopageVo vo){
        TransportRouteStopageLink transportRouteStopageLink = new TransportRouteStopageLink();
        Stopage stopage = CommonUtil.createCopyProperties(vo, Stopage.class);
        transportRouteStopageLink.setStopage(stopage);
        List<TransportRouteStopageLink> list = this.transportRouteStopageLinkRepository.findAll(Example.of(transportRouteStopageLink));
//    Collections.sort(list, (o1, o2) -> o1.getFacility().getName().compareTo(o2.getFacility().getName()));
        return list;
    }

    private List<CmsStopageVo> changeStopageToCmsStopageList(List<Stopage> list){
        List<CmsStopageVo> ls = new ArrayList<>();
        for(Stopage c: list) {
            CmsStopageVo vo = CommonUtil.createCopyProperties(c, CmsStopageVo.class);
            convertDatesAndProvideDependencies(c, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(Stopage c, CmsStopageVo vo) {
        if (c.getCreatedOn() != null) {
            vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(c.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setCreatedOn(null);

        }
        if(c.getUpdatedOn() != null) {
            vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(c.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setUpdatedOn(null);
        }
    }
    public List<CmsStopageVo> getStopageList(){
        List<Stopage> list = this.stopageRepository.findAll();
        List<CmsStopageVo> ls = changeStopageToCmsStopageList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        logger.debug("CmsStopage list : "+list);
        return ls;
    }


//    public List<CmsStopageVo> searchStopage(VehicleListFilterInput filter) throws Exception{
//        Stopage stopage = new Stopage();
//
////        if(!CommonUtil.isNullOrEmpty(filter.getEmployeeId())) {
////            transportRoute.setEmployeeId(Long.parseLong(filter.getEmployeeId()));
////        }
//        if(!CommonUtil.isNullOrEmpty(filter.getTransportRouteId())) {
//            if(transportRoute != null) {
//                transportRoute.setId(Long.valueOf(filter.getTransportRouteId()));
//            }
//        }
//        if(!CommonUtil.isNullOrEmpty(filter.getBranchId())) {
//            transportRoute.setBranchId(Long.parseLong(filter.getBranchId()));
//        }
//        Example<TransportRoute> example = Example.of(transportRoute);
//        List<TransportRoute> list = this.transportRouteRepository.findAll(example);
//        List<CmsTransportRouteVo> ls = new ArrayList<>();
//        for(TransportRoute transportRoute1: list){
//            CmsTransportRouteVo vo = CommonUtil.createCopyProperties(transportRoute1, CmsTransportRouteVo.class);
//            ls.add(vo);
//        }
//        return ls;
//    }
    public CmsStopageVo addStopage(AddStopageInput cmsStopageVo) {
        logger.info("Saving Stopage");
        CmsStopageVo vo = null;
        try {
            Stopage stopage = null;
            if (cmsStopageVo.getId() == null) {
                logger.debug("Adding new stopage");
                stopage = CommonUtil.createCopyProperties(cmsStopageVo, Stopage.class);
//                stopage.setCreatedOn(LocalDate.now());
            } else {
                logger.debug("Updating existing stopage");
                stopage = this.stopageRepository.findById(cmsStopageVo.getId()).get();
            }
            //            Stopage st = this.stopageRepository.findById(CmsStopageVo.getStopageId()).get();
            if (cmsStopageVo.getStopageName() != null) {
                stopage.setStopageName(cmsStopageVo.getStopageName());
            }
            if (cmsStopageVo.getStatus() != null) {
                stopage.setStatus(cmsStopageVo.getStatus());
            }
            if (cmsStopageVo.getUpdatedBy() != null) {
                stopage.setUpdatedBy(cmsStopageVo.getUpdatedBy());
            }
            if (cmsStopageVo.getCreatedBy() != null) {
                stopage.setCreatedBy(cmsStopageVo.getCreatedBy());
            }
            stopage.setCreatedOn(cmsStopageVo.getCreatedOn() != null ? DateFormatUtil.convertStringToLocalDate(cmsStopageVo.getStrCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            stopage.setUpdatedOn(cmsStopageVo.getUpdatedOn() != null ? DateFormatUtil.convertStringToLocalDate(cmsStopageVo.getStrUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
//            stopage.setCreatedOn(cmsStopageVo.getStrCreatedOn());
//            stopage.setUpdatedOn(cmsStopageVo.getUpdatedOn());
            stopage.setBranchId(cmsStopageVo.getBranchId());

            String prefUrl = applicationProperties.getPrefSrvUrl();
            if (cmsStopageVo.getBranchId() != null) {
                String url = prefUrl + "/api/batch-by-id/" + cmsStopageVo.getBranchId();
                Branch branch = this.commonService.getObject(url, Branch.class);
                if (branch != null) {
                    stopage.setBranchName(branch.getBranchName());
                }
            }
            stopage = this.stopageRepository.save(stopage);
            vo = CommonUtil.createCopyProperties(stopage, CmsStopageVo.class);
            vo.setStrCreatedOn(stopage.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(stopage.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrUpdatedOn(stopage.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(stopage.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setCreatedOn(null);
            vo.setUpdatedOn(null);
            vo.setExitCode(0L);
            if (cmsStopageVo.getId() == null) {
                vo.setExitDescription("stopage is added successfully");
                logger.debug("stopage is added successfully");
            } else {
                vo.setExitDescription("stopage is updated successfully");
                logger.debug("stopage is updated successfully");
            }
        } catch (Exception e) {
            vo = new CmsStopageVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, stopage data not be saved");
            logger.error("Stopage save failed. Exception : ", e);
        }
        logger.info("Stopage saved successfully");
        List ls = getStopageList();
        vo.setDataList(ls);
        return vo;
        }
    }
