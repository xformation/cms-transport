package com.synectiks.transport.business.service;

import com.synectiks.transport.constant.CmsConstants;
import com.synectiks.transport.domain.Contract;
import com.synectiks.transport.domain.Vehicle;
import com.synectiks.transport.domain.VehicleContractLink;
import com.synectiks.transport.domain.vo.CmsContractVo;
import com.synectiks.transport.domain.vo.CmsVehicleContractLinkVo;
import com.synectiks.transport.domain.vo.CmsVehicleVo;
import com.synectiks.transport.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.transport.graphql.types.VehicleContractList.AddVehicleContractListInput;
import com.synectiks.transport.repository.ContractRepository;
import com.synectiks.transport.repository.VehicleContractLinkRepository;
import com.synectiks.transport.repository.VehicleRepository;
import com.synectiks.transport.service.util.CommonUtil;
import com.synectiks.transport.service.util.DateFormatUtil;
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
    VehicleRepository vehicleRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ContractService contractService;

    public List<CmsVehicleContractLinkVo> getCmsVehicleContractLinkListOnFilterCriteria(Map<String, String> criteriaMap) {
        VehicleContractLink obj = new VehicleContractLink();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }

        if (criteriaMap.get("vehicleId") != null) {
            Vehicle vehicle = vehicleService.getVehicle(Long.parseLong(criteriaMap.get("vehicleId")));
            obj.setVehicle(vehicle);
            isFilter = true;
        }
        if (criteriaMap.get("contractId") != null) {
            Contract contract = contractService.getContract(Long.parseLong(criteriaMap.get("contractId")));
            obj.setContract(contract);
            isFilter = true;
        }
        List<VehicleContractLink> list = null;
        if (isFilter) {
            list = this.vehicleContractLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        } else {
            list = this.vehicleContractLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        List<CmsVehicleContractLinkVo> ls = changeVehicleContractLinkToCmsVehicleContractLinkList(list);
        Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return ls;
    }

    public List<CmsVehicleContractLinkVo> getVehicleContractList() {
        List<VehicleContractLink> list = this.vehicleContractLinkRepository.findAll();
        List<CmsVehicleContractLinkVo> ls = changeVehicleContractLinkToCmsVehicleContractLinkList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public List<VehicleContractLink> getVehicleContractLinkListOnFilterCriteria(Map<String, String> criteriaMap) {
        VehicleContractLink obj = new VehicleContractLink();
        boolean isFilter = false;
        if (criteriaMap.get("id") != null) {
            obj.setId(Long.parseLong(criteriaMap.get("id")));
            isFilter = true;
        }
        if (criteriaMap.get("vehicleId") != null) {
            Vehicle vehicle = vehicleService.getVehicle(Long.parseLong(criteriaMap.get("vehicleId")));
            obj.setVehicle(vehicle);
            isFilter = true;
        }
        if (criteriaMap.get("contractId") != null) {
            Contract contract = contractService.getContract(Long.parseLong(criteriaMap.get("contractId")));
            obj.setContract(contract);
            isFilter = true;
        }
        List<VehicleContractLink> list = null;
        if (isFilter) {
            list = this.vehicleContractLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        } else {
            list = this.vehicleContractLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        if (list.size() == 0) {
            return Collections.emptyList();
        }
        Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return list;
    }


    public CmsVehicleContractLinkVo getCmsVehicleContractLink(Long id) {
        Optional<VehicleContractLink> th = this.vehicleContractLinkRepository.findById(id);
        if (th.isPresent()) {
            CmsVehicleContractLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsVehicleContractLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsVehicleContractLink for given id : " + id + ". CmsVehicleContractLink object : " + vo);
            return vo;
        }
        logger.debug("VehicleContractLink object not found for the given id. " + id + ". Returning null object");
        return null;
    }

    public VehicleContractLink getVehicleContractLink(Long id) {
        Optional<VehicleContractLink> th = this.vehicleContractLinkRepository.findById(id);
        if (th.isPresent()) {
            return th.get();
        }
        logger.debug("VehicleContractLink object not found for the given id. " + id + ". Returning null");
        return null;
    }

    public CmsVehicleContractLinkVo getCmsVehicleContractLink(Long vehicleId, Long contractId) {
        VehicleContractLink vehicleContractLink = new VehicleContractLink();
        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);
        Contract contract = this.contractService.getContract(contractId);
        vehicleContractLink.setVehicle(vehicle);
        vehicleContractLink.setContract(contract);

        Optional<VehicleContractLink> th = this.vehicleContractLinkRepository.findOne(Example.of(vehicleContractLink));

        if (th.isPresent()) {
            CmsVehicleContractLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsVehicleContractLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsVehicleContractLink for given vehicle and contract id. CmsvehicleContractLink object : " + vo);
            return vo;
        }
        logger.debug("vehicleContractLink object not found for the given vehicle and contract ids. Returning null object");
        return null;
    }

    public VehicleContractLink getVehicleContractLink(Long vehicleId, Long contractId) {
        VehicleContractLink vehicleContractLink = new VehicleContractLink();
        Vehicle vehicle = this.vehicleService.getVehicle(vehicleId);
        Contract contract = this.contractService.getContract(contractId);
        vehicleContractLink.setVehicle(vehicle);
        vehicleContractLink.setContract(contract);

        Optional<VehicleContractLink> th = this.vehicleContractLinkRepository.findOne(Example.of(vehicleContractLink));
        if (th.isPresent()) {
            return th.get();
        }
        logger.debug("VehicleContractLink object not found for the vehicle and contract id. Returning null");
        return null;
    }

    private List<CmsVehicleContractLinkVo> changeVehicleContractLinkToCmsVehicleContractLinkList(List<VehicleContractLink> list) {
        List<CmsVehicleContractLinkVo> ls = new ArrayList<>();
        for (VehicleContractLink tr : list) {
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
            if(tr.getVehicle().getDateOfRegistration() != null) {
                cmsSvo.setStrDateOfRegistration(DateFormatUtil.changeLocalDateFormat(tr.getVehicle().getDateOfRegistration(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            if(tr.getVehicle().getOnBoardingDate() != null) {
                cmsSvo.setStrOnBoardingDate(DateFormatUtil.changeLocalDateFormat(tr.getVehicle().getOnBoardingDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
            vo.setVehicle(cmsSvo);
        }
        if (tr.getContract() != null) {
            vo.setContractId(tr.getContract().getId());
            CmsContractVo cmsTvo = CommonUtil.createCopyProperties(tr.getContract(), CmsContractVo.class);
            if(tr.getContract().getStartDate() != null) {
                cmsTvo.setStrStartDate(DateFormatUtil.changeLocalDateFormat(tr.getContract().getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//             vo.setStartDate(null);
            }
            if(tr.getContract().getEndDate() != null) {
                cmsTvo.setStrEndDate(DateFormatUtil.changeLocalDateFormat(tr.getContract().getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//             vo.setEndDate(null);
            }
            vo.setContract(cmsTvo);
        }
    }

    public CmsVehicleContractLinkVo saveVehicleContractLink(AddVehicleContractListInput input) {
        logger.info("Saving VehicleContractLink");
        CmsVehicleContractLinkVo vo = null;
        try {
            VehicleContractLink vehicleContractLink = null;
            if (input.getId() == null) {
                logger.debug("Adding new VehicleContractLink");
                vehicleContractLink = CommonUtil.createCopyProperties(input, VehicleContractLink.class);
//                transportRoute.setCreatedOn(LocalDate.now());
            }
            else {
                logger.debug("Updating existing VehicleContractLink");
                vehicleContractLink = this.vehicleContractLinkRepository.findById(input.getId()).get();
            }
            Vehicle v = this.vehicleRepository.findById(input.getVehicleId()).get();
            vehicleContractLink.setVehicle(v);
            Contract ct = this.contractRepository.findById(input.getContractId()).get();
            vehicleContractLink.setContract(ct);
            vehicleContractLink = this.vehicleContractLinkRepository.save(vehicleContractLink);
            vo = CommonUtil.createCopyProperties(vehicleContractLink, CmsVehicleContractLinkVo.class);
            vo.setExitCode(0L);
            if (input.getId() == null) {
                vo.setExitDescription("VehicleContractLink is added successfully");
                logger.debug("VehicleContractLink is added successfully");
            } else {
                vo.setExitDescription("VehicleContractLink is updated successfully");
                logger.debug("VehicleContractLink is updated successfully");
            }
        } catch (Exception e) {
            vo = new CmsVehicleContractLinkVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, vehicleContractLink data could not be saved");
            logger.error("vehicleContractLink save failed. Exception : ", e);
        }
        logger.info("VehicleContractLink saved successfully");
        List<CmsVehicleContractLinkVo> ls = getVehicleContractList();
        vo.setDataList((ls));
        return vo;
    }
}

//    public VehicleContractLink saveVehicleContractLink(AddVehicleInput input) {
//        Vehicle vehicle = this.vehicleService.getVehicle(input.getId());
//        Contract contract = this.contractService.getContract(input.getContractId());
//        logger.debug("Making entries in vehicleContractLink for the given vehicle id : "+input.getVehicleId()+"and contract id : "+input.getContractId());
//        VehicleContractLink vehicleContractLink = new VehicleContractLink();
//        vehicleContractLink.setContract(contract);
//        vehicleContractLink.setVehicle(vehicle);
//        Optional<VehicleContractLink> oth = this.vehicleContractLinkRepository.findOne(Example.of(vehicleContractLink));
//        if(!oth.isPresent()) {
////            teach.setDesc("Teacher - "+teacher.getTeacherName()+". Subject - "+ subject.getSubjectDesc()+". Branch - "+teacher.getBranch().getBranchName()+". Department - "+teacher.getDepartment().getName()+". Batch/Year - "+ subject.getBatch().getBatch() );
//            VehicleContractLink th = this.vehicleContractLinkRepository.save(vehicleContractLink);
//            logger.debug("VehicleContractLink data saved : "+vehicleContractLink);
//            return th;
//        }else {
//            logger.debug("VehicleContractLink mapping already exists. "+oth.get());
//        }
//        return oth.get();
//    }
//}
