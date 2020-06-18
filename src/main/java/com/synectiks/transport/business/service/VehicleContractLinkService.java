package com.synectiks.transport.business.service;

import com.synectiks.transport.domain.Contract;
import com.synectiks.transport.domain.Vehicle;
import com.synectiks.transport.domain.VehicleContractLink;
import com.synectiks.transport.domain.vo.CmsContractVo;
import com.synectiks.transport.domain.vo.CmsVehicleContractLinkVo;
import com.synectiks.transport.domain.vo.CmsVehicleVo;
import com.synectiks.transport.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.transport.repository.VehicleContractLinkRepository;
import com.synectiks.transport.service.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class VehicleContractLinkService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VehicleContractLinkRepository vehicleContractLinkRepository;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ContractService contractService;

    public List<CmsVehicleContractLinkVo> getCmsVehicleContractLinkListOnFilterCriteria(Map<String, String> criteriaMap){
        VehicleContractLink obj = new VehicleContractLink();
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
        if(criteriaMap.get("contractId") != null) {
            Contract contract = contractService.getContract(Long.parseLong(criteriaMap.get("contractId")));
            obj.setContract(contract);
            isFilter = true;
        }
        List<VehicleContractLink> list = null;
        if(isFilter) {
            list = this.vehicleContractLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.vehicleContractLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        List<CmsVehicleContractLinkVo> ls = changeVehicleContractLinkToCmsVehicleContractLinkList(list);
        Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return ls;
    }

    public List<CmsVehicleContractLinkVo> getVehicleContractList(){
        List<VehicleContractLink> list = this.vehicleContractLinkRepository.findAll();
        List<CmsVehicleContractLinkVo> ls = changeVehicleContractLinkToCmsVehicleContractLinkList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public List<VehicleContractLink> getVehicleContractLinkListOnFilterCriteria(Map<String, String> criteriaMap){
        VehicleContractLink obj = new VehicleContractLink();
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
        if(criteriaMap.get("contractId") != null) {
            Contract contract = contractService.getContract(Long.parseLong(criteriaMap.get("contractId")));
            obj.setContract(contract);
            isFilter = true;
        }
        List<VehicleContractLink> list = null;
        if(isFilter) {
            list = this.vehicleContractLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.vehicleContractLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        if(list.size() ==0 ) {
            return Collections.emptyList();
        }
        Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return list;
    }


    public CmsVehicleContractLinkVo getCmsVehicleContractLink(Long id){
        Optional<VehicleContractLink> th = this.vehicleContractLinkRepository.findById(id);
        if(th.isPresent()) {
            CmsVehicleContractLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsVehicleContractLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsVehicleContractLink for given id : "+id+". CmsVehicleContractLink object : "+vo);
            return vo;
        }
        logger.debug("VehicleContractLink object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public VehicleContractLink getVehicleContractLink(Long id){
        Optional<VehicleContractLink> th = this.vehicleContractLinkRepository.findById(id);
        if(th.isPresent()) {
            return th.get();
        }
        logger.debug("VehicleContractLink object not found for the given id. "+id+". Returning null");
        return null;
    }

    public CmsVehicleContractLinkVo getCmsVehicleContractLink(Long vehicleId, Long contractId){
        VehicleContractLink vehicleContractLink = new VehicleContractLink();
        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);
        Contract contract = this.contractService.getContract(contractId);
        vehicleContractLink.setVehicle(vehicle);
        vehicleContractLink.setContract(contract);

        Optional<VehicleContractLink> th = this.vehicleContractLinkRepository.findOne(Example.of(vehicleContractLink));

        if(th.isPresent()) {
            CmsVehicleContractLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsVehicleContractLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsVehicleContractLink for given vehicle and contract id. CmsvehicleContractLink object : "+vo);
            return vo;
        }
        logger.debug("vehicleContractLink object not found for the given vehicle and contract ids. Returning null object");
        return null;
    }

    public VehicleContractLink getVehicleContractLink(Long vehicleId, Long contractId){
        VehicleContractLink vehicleContractLink  = new VehicleContractLink();
        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);
        Contract contract = this.contractService.getContract(contractId);
        vehicleContractLink.setVehicle(vehicle);
        vehicleContractLink.setContract(contract);

        Optional<VehicleContractLink> th = this.vehicleContractLinkRepository.findOne(Example.of(vehicleContractLink));
        if(th.isPresent()) {
            return th.get();
        }
        logger.debug("VehicleContractLink object not found for the vehicle and contract id. Returning null");
        return null;
    }
    private List<CmsVehicleContractLinkVo> changeVehicleContractLinkToCmsVehicleContractLinkList(List<VehicleContractLink> list){
        List<CmsVehicleContractLinkVo> ls = new ArrayList<>();
        for(VehicleContractLink tr: list) {
            CmsVehicleContractLinkVo vo = CommonUtil.createCopyProperties(tr, CmsVehicleContractLinkVo.class);
            convertDatesAndProvideDependencies(tr, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(VehicleContractLink tr, CmsVehicleContractLinkVo vo) {
        if(tr.getVehicle() != null) {
            vo.setVehicleId(tr.getVehicle().getId());
            CmsVehicleVo cmsSvo =CommonUtil.createCopyProperties(tr.getVehicle(), CmsVehicleVo.class);
            vo.setCmsVehicleVo(cmsSvo);
        }
        if(tr.getContract() != null) {
            vo.setContractId(tr.getContract().getId());
            CmsContractVo cmsTvo =CommonUtil.createCopyProperties(tr.getContract(), CmsContractVo.class);
            vo.setCmsContractVo(cmsTvo);
        }
    }

    public VehicleContractLink saveVehicleContractLink(AddVehicleInput input) {
        Vehicle vehicle = this.vehicleService.getVehicle(input.getId());
        Contract contract = this.contractService.getContract(input.getContractId());
        logger.debug("Making entries in vehicleContractLink for the given vehicle id : "+input.getVehicleId()+"and contract id : "+input.getContractId());
        VehicleContractLink vehicleContractLink = new VehicleContractLink();
        vehicleContractLink.setContract(contract);
        vehicleContractLink.setVehicle(vehicle);
        Optional<VehicleContractLink> oth = this.vehicleContractLinkRepository.findOne(Example.of(vehicleContractLink));
        if(!oth.isPresent()) {
//            teach.setDesc("Teacher - "+teacher.getTeacherName()+". Subject - "+ subject.getSubjectDesc()+". Branch - "+teacher.getBranch().getBranchName()+". Department - "+teacher.getDepartment().getName()+". Batch/Year - "+ subject.getBatch().getBatch() );
            VehicleContractLink th = this.vehicleContractLinkRepository.save(vehicleContractLink);
            logger.debug("VehicleContractLink data saved : "+vehicleContractLink);
            return th;
        }else {
            logger.debug("VehicleContractLink mapping already exists. "+oth.get());
        }
        return oth.get();
    }


}
