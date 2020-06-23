package com.synectiks.transport.business.service;

import com.synectiks.transport.config.ApplicationProperties;
import com.synectiks.transport.constant.CmsConstants;
import com.synectiks.transport.domain.*;
import com.synectiks.transport.domain.vo.CmsTransportRouteVo;
import com.synectiks.transport.graphql.types.TransportRoute.AddTransportRouteInput;
import com.synectiks.transport.repository.TransportRouteRepository;
import com.synectiks.transport.repository.TransportRouteStopageLinkRepository;
import com.synectiks.transport.repository.TransportRouteVehicleLinkRepository;
import com.synectiks.transport.service.util.CommonUtil;
import com.synectiks.transport.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.*;

@Component
public class TransportRouteService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    TransportRouteRepository transportRouteRepository;

    @Autowired
    TransportRouteStopageLinkRepository transportRouteStopageLinkRepository;

    @Autowired
    TransportRouteVehicleLinkRepository transportRouteVehicleLinkRepository;

    @Autowired
    private CommonService commonService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    TransportRouteStopageLinkService transportRouteStopageLinkService;

    @Autowired
    TransportRouteVehicleLinkService transportRouteVehicleLinkService;

    public List<CmsTransportRouteVo> getCmsTransportRouteListOnFilterCriteria(Map<String, String> criteriaMap) {
        TransportRoute obj = new TransportRoute();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("routeName") != null) {
            obj.setRouteName(criteriaMap.get("routeName"));
            isFilter = true;
        }
        if (criteriaMap.get("routeDetails") != null) {
            obj.setRouteDetails(criteriaMap.get("routeDetails"));
            isFilter = true;
        }
        if (criteriaMap.get("routeMapUrl") != null) {
            obj.setRouteMapUrl(criteriaMap.get("routeMapUrl"));
            isFilter = true;

        }
//        if (criteriaMap.get("noOfStops") != null) {
//            obj.setCapacity(Long.parseLong(criteriaMap.get("capacity")));
//            isFilter = true;
//        }
        if (criteriaMap.get("routeFrequency") != null) {
            obj.setRouteFrequency(criteriaMap.get("routeFrequency"));
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
        List<TransportRoute> list = null;
        if(isFilter) {
            list = this.transportRouteRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
        }else {
            list = this.transportRouteRepository.findAll(Sort.by(Direction.DESC, "id"));
        }
        List<CmsTransportRouteVo> ls = changeTransportRouteToCmsTransportRouteList(list);
        if(ls.size() == 0) {
            return Collections.emptyList();
        }
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    public List<TransportRoute> getTransportRouteListOnFilterCriteria(Map<String, String> criteriaMap) {
        TransportRoute obj = new TransportRoute();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("routeName") != null) {
            obj.setRouteName(criteriaMap.get("routeName"));
            isFilter = true;
        }
        if (criteriaMap.get("routeDetails") != null) {
            obj.setRouteDetails(criteriaMap.get("routeDetails"));
            isFilter = true;
        }
        if (criteriaMap.get("routeMapUrl") != null) {
            obj.setRouteMapUrl(criteriaMap.get("routeMapUrl"));
            isFilter = true;

        }
//        if (criteriaMap.get("noOfStops") != null) {
//            obj.setCapacity(Long.parseLong(criteriaMap.get("capacity")));
//            isFilter = true;
//        }
        if (criteriaMap.get("routeFrequency") != null) {
            obj.setRouteFrequency(criteriaMap.get("routeFrequency"));
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
        List<TransportRoute> list = null;
        if(isFilter) {
            list = this.transportRouteRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
        }else {
            list = this.transportRouteRepository.findAll(Sort.by(Direction.DESC, "id"));
        }
        List<CmsTransportRouteVo> ls = changeTransportRouteToCmsTransportRouteList(list);
        if(ls.size() == 0) {
            return Collections.emptyList();
        }
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }

//    public List<TransportRoute> getTransportRouteList(RouteFrequency routeFrequency) {
//        TransportRoute transportRoute = new TransportRoute();
//        transportRoute.setRouteFrequency(routeFrequency);
//        List<TransportRoute> list = this.transportRouteRepository.findAll(Example.of(transportRoute), Sort.by(Direction.DESC, "id"));
//        logger.debug("TransportRoute list for the given routeFrequency : "+routeFrequency+". List : "+list);
//        return list;
//    }

//    public List<CmsTransportRouteVo> getTransportRouteList(){
//        List<TransportRoute> list = this.transportRouteRepository.findAll();
//        List<CmsTransportRouteVo> ls = changeTransportRouteToCmsTransportRouteList(list);
////        logger.debug("Transport Route list : "+list);
//        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
//        return ls;
//    }
//        public List<CmsTransportRouteVo> getTransportRouteList(){
//            Map<String, String> m = new HashMap<String, String>();
//            List<TransportRoute> list = this.transportRouteRepository.findAll();
//        List<CmsTransportRouteVo> ls = changeTransportRouteToCmsTransportRouteList(list);
//        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
//        logger.debug("CmsTransport Route list : "+list);
//        return ls;
//    }

    public CmsTransportRouteVo getCmsTransportRoute(Long id){
        Optional<TransportRoute> tr = this.transportRouteRepository.findById(id);
        if(tr.isPresent()) {
            CmsTransportRouteVo vo = CommonUtil.createCopyProperties(tr.get(), CmsTransportRouteVo.class);
            convertDatesAndProvideDependencies(tr.get(), vo);
            logger.debug("CmsTransportRoute for given id : "+id+". CmsTransportRoute object : "+vo);
            return vo;
        }
        logger.debug("Transport Route object not found for the given id. "+id+". Returning null ");
        return null;
    }

//    public CmsTransportRouteVo getCmsTransportRoute(Long id){
//        Optional<TransportRoute> tr = this.transportRouteRepository.findById(id);
//        if(tr.isPresent()) {
//            CmsTransportRouteVo vo = CommonUtil.createCopyProperties(tr.get(), CmsTransportRouteVo.class);
//            logger.debug("CmsTransport Route for given id : "+id+". CmsTransport Route object : "+vo);
//            return vo;
//        }
//        logger.debug("TransportRoute object not found for the given id. "+id+". Returning null object");
//        return null;
//    }
    public TransportRoute getTransportRoute(Long id){
        Optional<TransportRoute> tr = this.transportRouteRepository.findById(id);
        if(tr.isPresent()) {
//            logger.debug("Transport Route object found for given id : "+id+". Transport Route object : "+tr.get());
            return tr.get();
        }
        logger.debug("Transport Route object not found for the given id. "+id+". Returning null");
        return null;
    }

    private List<CmsTransportRouteVo> changeTransportRouteToCmsTransportRouteList(List<TransportRoute> list) {
        List<CmsTransportRouteVo> ls = new ArrayList<>();
        for (TransportRoute tr: list) {
            CmsTransportRouteVo vo = CommonUtil.createCopyProperties(tr, CmsTransportRouteVo.class);
            convertDatesAndProvideDependencies(tr, vo);
            ls.add(vo);
        }
        return ls;
    }
        private void convertDatesAndProvideDependencies(TransportRoute tr, CmsTransportRouteVo vo) {
            if(tr.getCreatedOn() != null) {
                vo.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(tr.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                vo.setCreatedOn(null);
            }
            if(tr.getUpdatedOn() != null) {
                vo.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(tr.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                vo.setUpdatedOn(null);
            }
        }

    public List<TransportRouteVehicleLink> getRouteVehicleList(CmsTransportRouteVo vo){
        TransportRouteVehicleLink transportRouteVehicleLink = new TransportRouteVehicleLink();
        TransportRoute transportRoute = CommonUtil.createCopyProperties(vo, TransportRoute.class);
        transportRouteVehicleLink.setTransportRoute(transportRoute);
        List<TransportRouteVehicleLink> list = this.transportRouteVehicleLinkRepository.findAll(Example.of(transportRouteVehicleLink));
//    Collections.sort(list, (o1, o2) -> o1.getFacility().getName().compareTo(o2.getFacility().getName()));
        return list;
    }
    public List<TransportRouteStopageLink> getRouteStopageList(CmsTransportRouteVo vo){
        TransportRouteStopageLink transportRouteStopageLink = new TransportRouteStopageLink();
        TransportRoute transportRoute = CommonUtil.createCopyProperties(vo, TransportRoute.class);
        transportRouteStopageLink.setTransportRoute(transportRoute);
        List<TransportRouteStopageLink> list = this.transportRouteStopageLinkRepository.findAll(Example.of(transportRouteStopageLink));
//    Collections.sort(list, (o1, o2) -> o1.getFacility().getName().compareTo(o2.getFacility().getName()));
        return list;
    }

    public List<CmsTransportRouteVo> searchTransportRoute(Long transportRouteId, String vehicleNumber) throws Exception {
        TransportRoute transportRoute = new TransportRoute();
//        if (vehicleId != null) {
//            vehicle.setId(vehicleId);
//        }
//        if (employeeId !=null){
//            vehicle.setEmployeeId(employeeId);
//        }
        if (transportRouteId != null) {
//            TransportRoute transportRoute = new TransportRoute();
            transportRoute.setId(transportRouteId);
//            vehicle.setTransportRoute(transportRoute);
        }
//        if (vehicleNumber != null) {
//            vehicle.setVehicleNumber(vehicleNumber);
//        }
        Example<TransportRoute> example = Example.of(transportRoute);
        List<TransportRoute> list = this.transportRouteRepository.findAll(example);
        List<CmsTransportRouteVo> ls = new ArrayList<>();
        for(TransportRoute transportRoute1: list) {
            CmsTransportRouteVo vo = CommonUtil.createCopyProperties(transportRoute1, CmsTransportRouteVo.class);
            ls.add(vo);
        }
        return ls;
    }
    public List<CmsTransportRouteVo> getTransportRouteList(){
        List<TransportRoute> list = this.transportRouteRepository.findAll();
        List<CmsTransportRouteVo> ls = changeTransportRouteToCmsTransportRouteList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public CmsTransportRouteVo addTransportRoute(AddTransportRouteInput cmsTransportRouteVo) {
        logger.info("Saving TransportRoute");
        CmsTransportRouteVo vo = null;
        try {
            TransportRoute transportRoute = null;
            if (cmsTransportRouteVo.getId() == null) {
                logger.debug("Adding new transportRoute");
                transportRoute = CommonUtil.createCopyProperties(cmsTransportRouteVo, TransportRoute.class);
                transportRoute.setCreatedOn(LocalDate.now());
            }
            else {
                logger.debug("Updating existing transportRoute");
                transportRoute = this.transportRouteRepository.findById(cmsTransportRouteVo.getId()).get();
            }

                    transportRoute.setRouteName(cmsTransportRouteVo.getRouteName());
                    transportRoute.setRouteDetails(cmsTransportRouteVo.getRouteDetails());
                    transportRoute.setRouteMapUrl(cmsTransportRouteVo.getRouteMapUrl());
                    transportRoute.setNoOfStops(cmsTransportRouteVo.getNoOfStops());
                    transportRoute.setRouteFrequency(cmsTransportRouteVo.getRouteFrequency());
                    transportRoute.setStatus(cmsTransportRouteVo.getStatus());
                    transportRoute.setCreatedBy(cmsTransportRouteVo.getCreatedBy());
                    transportRoute.setCreatedOn(cmsTransportRouteVo.getCreatedOn());
//                    transportRoute.setStrCreatedOn(cmsTransportRouteVo.getStrCreatedOn() != null ? DateFormatUtil.convertStringToLocalDate(cmsTransportRouteVo.getStrCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
                    transportRoute.setUpdatedBy(cmsTransportRouteVo.getUpdatedBy());
                    transportRoute.setUpdatedOn(cmsTransportRouteVo.getUpdatedOn());
                    transportRoute.setBranchId(cmsTransportRouteVo.getBranchId());

            String prefUrl = applicationProperties.getPrefSrvUrl();
            if(cmsTransportRouteVo.getBranchId() != null) {
                String url = prefUrl+"/api/branch-by-id/"+cmsTransportRouteVo.getBranchId();
                Branch branch = this.commonService.getObject(url, Branch.class);
                if(branch != null) {
                    transportRoute.setBranchName(branch.getBranchName());
                }
            }

            transportRoute = this.transportRouteRepository.save(transportRoute);
            vo = CommonUtil.createCopyProperties(transportRoute, CmsTransportRouteVo.class);
            vo.setStrCreatedOn(transportRoute.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(transportRoute.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrUpdatedOn(transportRoute.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(transportRoute.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setCreatedOn(null);
            vo.setUpdatedOn(null);
            vo.setExitCode(0L);
            if (cmsTransportRouteVo.getId() == null) {
                vo.setExitDescription("TransportRoute is added successfully");
                logger.debug("TransportRoute is added successfully");
            } else {
                vo.setExitDescription("TransportRoute is updated successfully");
                logger.debug("TransportRoute is updated successfully");
            }
        } catch (Exception e) {
            vo = new CmsTransportRouteVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, transportRoute data could not be saved");
            logger.error("TransportRoute save failed. Exception : ", e);
        }
        logger.info("TransportRoute saved successfully");
        List<CmsTransportRouteVo> ls = getTransportRouteList();
        vo.setDataList((ls));
        return vo;
    }
}




