package com.synectiks.transport.business.service;

import com.synectiks.transport.domain.*;
import com.synectiks.transport.domain.vo.*;
import com.synectiks.transport.graphql.types.TransportRoute.AddTransportRouteInput;
import com.synectiks.transport.graphql.types.TransportRouteVehicleLink.AddTransportRouteVehicleLinkInput;
import com.synectiks.transport.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.transport.repository.TransportRouteRepository;
import com.synectiks.transport.repository.TransportRouteVehicleLinkRepository;
import com.synectiks.transport.repository.VehicleRepository;
import com.synectiks.transport.service.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class TransportRouteVehicleLinkService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TransportRouteVehicleLinkRepository transportRouteVehicleLinkRepository;

    @Autowired
    TransportRouteRepository transportRouteRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    TransportRouteService transportRouteService;

    @Autowired
    VehicleService vehicleService;

    public List<CmsTransportRouteVehicleLinkVo> getCmsTransportRouteVehicleLinkListOnFilterCriteria(Map<String, String> criteriaMap){
        TransportRouteVehicleLink obj = new TransportRouteVehicleLink();
        boolean isFilter = false;
        if(criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }

        if(criteriaMap.get("transportRouteId") != null) {
            TransportRoute transportRoute = transportRouteService.getTransportRoute(Long.parseLong(criteriaMap.get("transportRouteId")));
            obj.setTransportRoute(transportRoute);
            isFilter = true;
        }
        if(criteriaMap.get("vehicleId") != null) {
            Vehicle vehicle = vehicleService.getVehicle(Long.parseLong(criteriaMap.get("vehicleId")));
            obj.setVehicle(vehicle);
            isFilter = true;
        }
        List<TransportRouteVehicleLink> list = null;
        if(isFilter) {
            list = this.transportRouteVehicleLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.transportRouteVehicleLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        List<CmsTransportRouteVehicleLinkVo> ls = changeTransportRouteVehicleLinkToCmsTransportRouteVehicleLinkList(list);
        Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return ls;
    }

    public List<TransportRouteVehicleLink> getTransportRouteVehicleLinkListOnFilterCriteria(Map<String, String> criteriaMap){
        TransportRouteVehicleLink obj = new TransportRouteVehicleLink();
        boolean isFilter = false;
        if(criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }

        if(criteriaMap.get("transportRouteId") != null) {
            TransportRoute transportRoute = transportRouteService.getTransportRoute(Long.parseLong(criteriaMap.get("transportRouteId")));
            obj.setTransportRoute(transportRoute);
            isFilter = true;
        }
        if(criteriaMap.get("vehicleId") != null) {
            Vehicle vehicle = vehicleService.getVehicle(Long.parseLong(criteriaMap.get("vehicleId")));
            obj.setVehicle(vehicle);
            isFilter = true;
        }
        List<TransportRouteVehicleLink> list = null;
        if(isFilter) {
            list = this.transportRouteVehicleLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.transportRouteVehicleLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        if(list.size() ==0 ) {
            return Collections.emptyList();
        }
        Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return list;
    }
    public List<CmsTransportRouteVehicleLinkVo> getTransportRouteVehicleList(){
        List<TransportRouteVehicleLink> list = this.transportRouteVehicleLinkRepository.findAll();
        List<CmsTransportRouteVehicleLinkVo> ls = changeTransportRouteVehicleLinkToCmsTransportRouteVehicleLinkList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }
    public CmsTransportRouteVehicleLinkVo getCmsTransportRouteVehicleLink(Long id){
        Optional<TransportRouteVehicleLink> th = this.transportRouteVehicleLinkRepository.findById(id);
        if(th.isPresent()) {
            CmsTransportRouteVehicleLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsTransportRouteVehicleLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsTransportRouteVehicleLink for given id : "+id+". CmsTransportRouteVehicleLink object : "+vo);
            return vo;
        }
        logger.debug("TransportRouteVehicleLink object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public TransportRouteVehicleLink getTransportRouteVehicleLink(Long id){
        Optional<TransportRouteVehicleLink> th = this.transportRouteVehicleLinkRepository.findById(id);
        if(th.isPresent()) {
            return th.get();
        }
        logger.debug("TransportRouteVehicleLink object not found for the given id. "+id+". Returning null");
        return null;
    }

    public CmsTransportRouteVehicleLinkVo getCmsTransportRouteVehicleLink(Long transportRouteId, Long vehicleId){
        TransportRouteVehicleLink transportRouteVehicleLink = new TransportRouteVehicleLink();
        TransportRoute transportRoute = this.transportRouteService.getTransportRoute(transportRouteId);
        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);
        transportRouteVehicleLink.setTransportRoute(transportRoute);
        transportRouteVehicleLink.setVehicle(vehicle);

        Optional<TransportRouteVehicleLink> th = this.transportRouteVehicleLinkRepository.findOne(Example.of(transportRouteVehicleLink));

        if(th.isPresent()) {
            CmsTransportRouteVehicleLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsTransportRouteVehicleLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsTransportRouteVehicleLink for given transportRoute and vehicle id. CmstransportRouteVehicleLink object : "+vo);
            return vo;
        }
        logger.debug("transportRouteVehicleLink object not found for the given transportRoute and vehicle ids. Returning null object");
        return null;
    }

    public TransportRouteVehicleLink getTransportRouteVehicleLink(Long transportRouteId, Long vehicleId){
        TransportRouteVehicleLink transportRouteVehicleLink  = new TransportRouteVehicleLink();
        TransportRoute transportRoute = this.transportRouteService.getTransportRoute(transportRouteId);
        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);
        transportRouteVehicleLink.setTransportRoute(transportRoute);
        transportRouteVehicleLink.setVehicle(vehicle);

        Optional<TransportRouteVehicleLink> th = this.transportRouteVehicleLinkRepository.findOne(Example.of(transportRouteVehicleLink));
        if(th.isPresent()) {
            return th.get();
        }
        logger.debug("TransportRouteVehicleLink object not found for the transportRoute and vehicle id. Returning null");
        return null;
    }

    private List<CmsTransportRouteVehicleLinkVo> changeTransportRouteVehicleLinkToCmsTransportRouteVehicleLinkList(List<TransportRouteVehicleLink> list){
        List<CmsTransportRouteVehicleLinkVo> ls = new ArrayList<>();
        for(TransportRouteVehicleLink tr: list) {
            CmsTransportRouteVehicleLinkVo vo = CommonUtil.createCopyProperties(tr, CmsTransportRouteVehicleLinkVo.class);
            convertDatesAndProvideDependencies(tr, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(TransportRouteVehicleLink tr, CmsTransportRouteVehicleLinkVo vo) {
//        if(tr.getTransportRoute() != null) {
//            vo.setTransportRouteId(tr.getTransportRoute().getId());
//            CmsTransportRouteVo cmsSvo =CommonUtil.createCopyProperties(tr.getTransportRoute(), CmsTransportRouteVo.class);
//            vo.setCmsTransportRouteVo(cmsSvo);
//        }
//        if(tr.getVehicle() != null) {
//            vo.setVehicleId(tr.getVehicle().getId());
//            CmsVehicleVo cmsSvo =CommonUtil.createCopyProperties(tr.getVehicle(), CmsVehicleVo.class);
//            vo.setCmsVehicleVo(cmsSvo);
//        }
    }

    public CmsTransportRouteVehicleLinkVo saveTransportRouteVehicleLink(AddTransportRouteVehicleLinkInput input) {
        logger.info("Saving TransportRouteVehicleLink");
        CmsTransportRouteVehicleLinkVo vo = null;
        try {
            TransportRouteVehicleLink transportRouteVehicleLink = null;
            if (input.getId() == null) {
                logger.debug("Adding new TransportRouteVehicleLink");
                transportRouteVehicleLink = CommonUtil.createCopyProperties(input, TransportRouteVehicleLink.class);
//                transportRoute.setCreatedOn(LocalDate.now());
            }
            else {
                logger.debug("Updating existing TransportRouteVehicleLink");
                transportRouteVehicleLink = this.transportRouteVehicleLinkRepository.findById(input.getId()).get();
            }
            Vehicle v = this.vehicleRepository.findById(input.getVehicleId()).get();
            transportRouteVehicleLink.setVehicle(v);
            TransportRoute t = this.transportRouteRepository.findById(input.getTransportRouteId()).get();
            transportRouteVehicleLink.setTransportRoute(t);
            transportRouteVehicleLink = this.transportRouteVehicleLinkRepository.save(transportRouteVehicleLink);
            vo = CommonUtil.createCopyProperties(transportRouteVehicleLink, CmsTransportRouteVehicleLinkVo.class);
            vo.setExitCode(0L);
            if (input.getId() == null) {
                vo.setExitDescription("TransportRouteVehicleLink is added successfully");
                logger.debug("TransportRouteVehicleLink is added successfully");
            } else {
                vo.setExitDescription("TransportRouteVehicleLink is updated successfully");
                logger.debug("TransportRouteVehicleLink is updated successfully");
            }
        } catch (Exception e) {
            vo = new CmsTransportRouteVehicleLinkVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, TransportRouteVehicleLink data could not be saved");
            logger.error("TransportRouteVehicleLink save failed. Exception : ", e);
        }
        logger.info("TransportRouteVehicleLink saved successfully");
        List<CmsTransportRouteVehicleLinkVo> ls = getTransportRouteVehicleList();
        vo.setDataList((ls));
        return vo;
    }

//    public TransportRouteVehicleLink saveTransportRouteVehicleLink(AddTransportRouteVehicleLinkInput input) {
//        TransportRoute transportRoute = this.transportRouteService.getTransportRoute(input.getId());
//        Vehicle vehicle = this.vehicleService.getVehicle(input.getId());
//        logger.debug("Making entries in transportRouteVehicleLink for the given transportRoute id : "+input.getTransportRouteId()+"and vehicle id : "+input.getVehicleId());
//        TransportRouteVehicleLink transportRouteVehicleLink = new TransportRouteVehicleLink();
//        transportRouteVehicleLink.setTransportRoute(transportRoute);
//        transportRouteVehicleLink.setVehicle(vehicle);
//        Optional<TransportRouteVehicleLink> oth = this.transportRouteVehicleLinkRepository.findOne(Example.of(transportRouteVehicleLink));
//        if(!oth.isPresent()) {
////            teach.setDesc("Teacher - "+teacher.getTeacherName()+". Subject - "+ subject.getSubjectDesc()+". Branch - "+teacher.getBranch().getBranchName()+". Department - "+teacher.getDepartment().getName()+". Batch/Year - "+ subject.getBatch().getBatch() );
//            TransportRouteVehicleLink th = this.transportRouteVehicleLinkRepository.save(transportRouteVehicleLink);
//            logger.debug("TransportRouteVehicleLink data saved : "+transportRouteVehicleLink);
//            return th;
//        }else {
//            logger.debug("TransportRouteVehicleLink mapping already exists. "+oth.get());
//        }
//        return oth.get();
//    }
}
