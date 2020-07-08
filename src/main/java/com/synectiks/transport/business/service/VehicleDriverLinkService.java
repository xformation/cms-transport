package com.synectiks.transport.business.service;

import com.synectiks.transport.domain.Contract;
import com.synectiks.transport.domain.Employee;
import com.synectiks.transport.domain.Vehicle;
import com.synectiks.transport.domain.VehicleDriverLink;
import com.synectiks.transport.domain.vo.CmsContractVo;
import com.synectiks.transport.domain.vo.CmsVehicleDriverLinkVo;
import com.synectiks.transport.domain.vo.CmsVehicleVo;
import com.synectiks.transport.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.transport.graphql.types.VehicleDriverList.AddVehicleDriverListInput;
import com.synectiks.transport.repository.VehicleDriverLinkRepository;
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
public class VehicleDriverLinkService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VehicleDriverLinkRepository vehicleDriverLinkRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleService vehicleService;

    public List<CmsVehicleDriverLinkVo> getCmsVehicleDriverLinkListOnFilterCriteria(Map<String, String> criteriaMap){
        VehicleDriverLink obj = new VehicleDriverLink();
        boolean isFilter = false;
        if(criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if(criteriaMap.get("vehicleId") != null) {
            Vehicle vehicle = vehicleService.getVehicle(Long.parseLong(criteriaMap.get("vehicleId")));
            obj.setVehicle(vehicle);
            isFilter = true;
        }
        List<VehicleDriverLink> list = null;
        if(isFilter) {
            list = this.vehicleDriverLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.vehicleDriverLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        List<CmsVehicleDriverLinkVo> ls = changeVehicleDriverLinkToCmsVehicleDriverLinkList(list);
        Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return ls;
    }

    public List<VehicleDriverLink> getVehicleDriverLinkListOnFilterCriteria(Map<String, String> criteriaMap){
        VehicleDriverLink obj = new VehicleDriverLink();
        boolean isFilter = false;
        if(criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if(criteriaMap.get("vehicleId") != null) {
            Vehicle vehicle = vehicleService.getVehicle(Long.parseLong(criteriaMap.get("vehicleId")));
            obj.setVehicle(vehicle);
            isFilter = true;
        }
        List<VehicleDriverLink> list = null;
        if(isFilter) {
            list = this.vehicleDriverLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.vehicleDriverLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        if(list.size() ==0 ) {
            return Collections.emptyList();
        }
        Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return list;
    }

    public CmsVehicleDriverLinkVo getCmsVehicleDriverLink(Long id){
        Optional<VehicleDriverLink> th = this.vehicleDriverLinkRepository.findById(id);
        if(th.isPresent()) {
            CmsVehicleDriverLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsVehicleDriverLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsVehicleDriverLink for given id : "+id+". CmsVehicleDriverLink object : "+vo);
            return vo;
        }
        logger.debug("VehicleDriverLink object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public VehicleDriverLink getVehicleDriverLink(Long id){
        Optional<VehicleDriverLink> th = this.vehicleDriverLinkRepository.findById(id);
        if(th.isPresent()) {
            return th.get();
        }
        logger.debug("VehicleDriverLink object not found for the given id. "+id+". Returning null");
        return null;
    }
    public List<CmsVehicleDriverLinkVo> getVehicleDriverList(){
        List<VehicleDriverLink> list = this.vehicleDriverLinkRepository.findAll();
        List<CmsVehicleDriverLinkVo> ls = changeVehicleDriverLinkToCmsVehicleDriverLinkList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

//    public CmsVehicleDriverLinkVo getCmsVehicleDriverLink(Long vehicleId){
//        VehicleDriverLink vehicleDriverLink = new VehicleDriverLink();
//        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);
//        vehicleDriverLink.setVehicle(vehicle);
//
//        Optional<VehicleDriverLink> th = this.vehicleDriverLinkRepository.findOne(Example.of(vehicleDriverLink));
//
//        if(th.isPresent()) {
//            CmsVehicleDriverLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsVehicleDriverLinkVo.class);
//            convertDatesAndProvideDependencies(th.get(), vo);
//            logger.debug("CmsVehicleDriverLink for given vehicle id. CmsVehicleDriverLink object : "+vo);
//            return vo;
//        }
//        logger.debug("VehicleDriverLink object not found for the given vehicle id. Returning null object");
//        return null;
//    }

//    public VehicleDriverLink getVehicleDriverLink(Long vehicleId){
//        VehicleDriverLink vehicleDriverLink = new VehicleDriverLink();
//        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);
//        vehicleDriverLink.setVehicle(vehicle);
//
//        Optional<VehicleDriverLink> th = this.vehicleDriverLinkRepository.findOne(Example.of(vehicleDriverLink));
//        if(th.isPresent()) {
//            return th.get();
//        }
//        logger.debug("VehicleDriverLink object not found for the vehicle id. Returning null");
//        return null;
//    }

    private List<CmsVehicleDriverLinkVo> changeVehicleDriverLinkToCmsVehicleDriverLinkList(List<VehicleDriverLink> list){
        List<CmsVehicleDriverLinkVo> ls = new ArrayList<>();
        for(VehicleDriverLink tr: list) {
            CmsVehicleDriverLinkVo vo = CommonUtil.createCopyProperties(tr, CmsVehicleDriverLinkVo.class);
            convertDatesAndProvideDependencies(tr, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(VehicleDriverLink tr, CmsVehicleDriverLinkVo vo) {
//        if(tr.getVehicle() != null) {
//            vo.setVehicleId(tr.getVehicle().getId());
//            CmsVehicleVo cmsSvo =CommonUtil.createCopyProperties(tr.getVehicle(), CmsVehicleVo.class);
//            vo.setCmsVehicleVo(cmsSvo);
//        }
    }

    public CmsVehicleDriverLinkVo saveVehicleDriverLink(AddVehicleDriverListInput input) {
        logger.info("Saving VehicleDriverLink");
        CmsVehicleDriverLinkVo vo = null;
        try {
            VehicleDriverLink vehicleDriverLink = null;
            if (input.getId() == null) {
                logger.debug("Adding new VehicleDriverLink");
                vehicleDriverLink = CommonUtil.createCopyProperties(input, VehicleDriverLink.class);
//                transportRoute.setCreatedOn(LocalDate.now());
            }
            else {
                logger.debug("Updating existing VehicleDriverLink");
                vehicleDriverLink = this.vehicleDriverLinkRepository.findById(input.getId()).get();
            }
            Vehicle v = this.vehicleRepository.findById(input.getVehicleId()).get();
            vehicleDriverLink.setVehicle(v);
            vehicleDriverLink.setEmployeeId(input.getEmployeeId());
            vehicleDriverLink = this.vehicleDriverLinkRepository.save(vehicleDriverLink);
            vo = CommonUtil.createCopyProperties(vehicleDriverLink, CmsVehicleDriverLinkVo.class);
            vo.setExitCode(0L);
            if (input.getId() == null) {
                vo.setExitDescription("VehicleDriverLink is added successfully");
                logger.debug("VehicleDriverLink is added successfully");
            } else {
                vo.setExitDescription("VehicleDriverLink is updated successfully");
                logger.debug("VehicleDriverLink is updated successfully");
            }
        } catch (Exception e) {
            vo = new CmsVehicleDriverLinkVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, VehicleDriverLink data could not be saved");
            logger.error("VehicleDriverLink save failed. Exception : ", e);
        }
        logger.info("VehicleDriverLink saved successfully");
        List<CmsVehicleDriverLinkVo> ls = getVehicleDriverList();
        vo.setDataList((ls));
        return vo;
    }
//    public VehicleDriverLink saveVehicleDriverLink(AddVehicleDriverListInput input) {
//        CmsVehicleDriverLinkVo vo = null;
//        Vehicle vehicle = this.vehicleService.getVehicle(input.getId());
//        VehicleDriverLink vdl=null;
//        logger.debug("Making entries in vehicleDriverLink for the given vehicle id : "+input.getVehicleId());
//        VehicleDriverLink vehicleDriverLink = new VehicleDriverLink();
//        vehicleDriverLink.setVehicle(vehicle);
//        vdl.setEmployeeId(input.getEmployeeId());
//        Optional<VehicleDriverLink> oth = this.vehicleDriverLinkRepository.findOne(Example.of(vehicleDriverLink));
//        if(!oth.isPresent()) {
////            vehicleDriverLink.setDesc("Vehicle - "+vehicle.getvehicleNumber()+". Subject - "+ subject.getSubjectDesc()+". Branch - "+teacher.getBranch().getBranchName()+". Department - "+teacher.getDepartment().getName()+". Batch/Year - "+ subject.getBatch().getBatch() );
////            vehicleDriverLink.setId("Vehicle - "+vehicle.getVehicleNumber()+". Branch - "+teacher.getBranch().getBranchName()+". Department - "+teacher.getDepartment().getName()+". Batch/Year - "+ subject.getBatch().getBatch() );
//
//            VehicleDriverLink th = this.vehicleDriverLinkRepository.save(vehicleDriverLink);
//            logger.debug("VehicleDriverLink data saved : "+vehicleDriverLink);
//            return th;
//        }else {
//            logger.debug("VehicleDriverLink mapping already exists. "+oth.get());
//        }
//        return oth.get();
//    }


}
