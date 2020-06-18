package com.synectiks.transport.business.service;

import com.synectiks.transport.domain.*;
import com.synectiks.transport.domain.vo.*;
import com.synectiks.transport.graphql.types.TransportRoute.AddTransportRouteInput;
import com.synectiks.transport.repository.TransportRouteStopageLinkRepository;
import com.synectiks.transport.service.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TransportRouteStopageLinkService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TransportRouteStopageLinkRepository transportRouteStopageLinkRepository;

    @Autowired
    TransportRouteService transportRouteService;

    @Autowired
    StopageService stopageService;

    public List<CmsTransportRouteStopageLinkVo> getCmsTransportRouteStopageLinkListOnFilterCriteria(Map<String, String> criteriaMap){
        TransportRouteStopageLink obj = new TransportRouteStopageLink();
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
        if(criteriaMap.get("stopageId") != null) {
            Stopage stopage = stopageService.getStopage(Long.parseLong(criteriaMap.get("stopageId")));
            obj.setStopage(stopage);
            isFilter = true;
        }
        List<TransportRouteStopageLink> list = null;
        if(isFilter) {
            list = this.transportRouteStopageLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.transportRouteStopageLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        List<CmsTransportRouteStopageLinkVo> ls = changeTransportRouteStopageLinkToCmsTransportRouteStopageLinkList(list);
        Collections.sort(ls, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return ls;
    }

    public List<TransportRouteStopageLink> getTransportRouteStopageLinkListOnFilterCriteria(Map<String, String> criteriaMap){
        TransportRouteStopageLink obj = new TransportRouteStopageLink();
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
        if(criteriaMap.get("stopageId") != null) {
            Stopage stopage = stopageService.getStopage(Long.parseLong(criteriaMap.get("stopageId")));
            obj.setStopage(stopage);
            isFilter = true;
        }
        List<TransportRouteStopageLink> list = null;
        if(isFilter) {
            list = this.transportRouteStopageLinkRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.transportRouteStopageLinkRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        if(list.size() ==0 ) {
            return Collections.emptyList();
        }
        Collections.sort(list, (o1, o2) -> o1.getId().compareTo(o2.getId()));
        return list;
    }

    public List<CmsTransportRouteStopageLinkVo> getTransportRouteStopageList(){
        List<TransportRouteStopageLink> list = this.transportRouteStopageLinkRepository.findAll();
        List<CmsTransportRouteStopageLinkVo> ls = changeTransportRouteStopageLinkToCmsTransportRouteStopageLinkList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public CmsTransportRouteStopageLinkVo getCmsTransportRouteStopageLink(Long id){
        Optional<TransportRouteStopageLink> th = this.transportRouteStopageLinkRepository.findById(id);
        if(th.isPresent()) {
            CmsTransportRouteStopageLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsTransportRouteStopageLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsTransportRouteStopageLink for given id : "+id+". CmsTransportRouteStopageLink object : "+vo);
            return vo;
        }
        logger.debug("TransportRouteStopageLink object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public TransportRouteStopageLink getTransportRouteStopageLink(Long id){
        Optional<TransportRouteStopageLink> th = this.transportRouteStopageLinkRepository.findById(id);
        if(th.isPresent()) {
            return th.get();
        }
        logger.debug("TransportRouteStopageLink object not found for the given id. "+id+". Returning null");
        return null;
    }

    public CmsTransportRouteStopageLinkVo getCmsTransportRouteStopageLink(Long transportRouteId, Long stopageId){
        TransportRouteStopageLink transportRouteStopageLink = new TransportRouteStopageLink();
        TransportRoute transportRoute = this.transportRouteService.getTransportRoute(transportRouteId);
        Stopage stopage = this.stopageService.getStopage(stopageId);
        transportRouteStopageLink.setTransportRoute(transportRoute);
        transportRouteStopageLink.setStopage(stopage);

        Optional<TransportRouteStopageLink> th = this.transportRouteStopageLinkRepository.findOne(Example.of(transportRouteStopageLink));

        if(th.isPresent()) {
            CmsTransportRouteStopageLinkVo vo = CommonUtil.createCopyProperties(th.get(), CmsTransportRouteStopageLinkVo.class);
            convertDatesAndProvideDependencies(th.get(), vo);
            logger.debug("CmsTransportRouteStopageLink for given transportRoute and stopage id. CmstransportRouteStopageLink object : "+vo);
            return vo;
        }
        logger.debug("transportRouteStopageLink object not found for the given transportRoute and stopage ids. Returning null object");
        return null;
    }

    public TransportRouteStopageLink getTransportRouteStopageLink(Long transportRouteId, Long stopageId){
        TransportRouteStopageLink transportRouteStopageLink  = new TransportRouteStopageLink();
        TransportRoute transportRoute = this.transportRouteService.getTransportRoute(transportRouteId);
        Stopage stopage = this.stopageService.getStopage(stopageId);
        transportRouteStopageLink.setTransportRoute(transportRoute);
        transportRouteStopageLink.setStopage(stopage);

        Optional<TransportRouteStopageLink> th = this.transportRouteStopageLinkRepository.findOne(Example.of(transportRouteStopageLink));
        if(th.isPresent()) {
            return th.get();
        }
        logger.debug("TransportRouteStopageLink object not found for the transportRoute and stopage id. Returning null");
        return null;
    }

    private List<CmsTransportRouteStopageLinkVo> changeTransportRouteStopageLinkToCmsTransportRouteStopageLinkList(List<TransportRouteStopageLink> list){
        List<CmsTransportRouteStopageLinkVo> ls = new ArrayList<>();
        for(TransportRouteStopageLink tr: list) {
            CmsTransportRouteStopageLinkVo vo = CommonUtil.createCopyProperties(tr, CmsTransportRouteStopageLinkVo.class);
            convertDatesAndProvideDependencies(tr, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(TransportRouteStopageLink tr, CmsTransportRouteStopageLinkVo vo) {
        if(tr.getTransportRoute() != null) {
            vo.setTransportRouteId(tr.getTransportRoute().getId());
            CmsTransportRouteVo cmsSvo =CommonUtil.createCopyProperties(tr.getTransportRoute(), CmsTransportRouteVo.class);
            vo.setCmsTransportRouteVo(cmsSvo);
        }
        if(tr.getStopage() != null) {
            vo.setStopageId(tr.getStopage().getId());
            CmsStopageVo cmsTvo =CommonUtil.createCopyProperties(tr.getStopage(), CmsStopageVo.class);
            vo.setCmsStopageVo(cmsTvo);
        }
    }

    public TransportRouteStopageLink saveTransportRouteStopageLink(AddTransportRouteInput input) {
        TransportRoute transportRoute = this.transportRouteService.getTransportRoute(input.getId());
        Stopage stopage = this.stopageService.getStopage(input.getStopageId());
        logger.debug("Making entries in transportRouteStopageLink for the given transportRoute id : "+input.getTransportRouteId()+"and stopage id : "+input.getStopageId());
        TransportRouteStopageLink transportRouteStopageLink = new TransportRouteStopageLink();
        transportRouteStopageLink.setTransportRoute(transportRoute);
        transportRouteStopageLink.setStopage(stopage);
        Optional<TransportRouteStopageLink> oth = this.transportRouteStopageLinkRepository.findOne(Example.of(transportRouteStopageLink));
        if(!oth.isPresent()) {
//            teach.setDesc("Teacher - "+teacher.getTeacherName()+". Subject - "+ subject.getSubjectDesc()+". Branch - "+teacher.getBranch().getBranchName()+". Department - "+teacher.getDepartment().getName()+". Batch/Year - "+ subject.getBatch().getBatch() );
            TransportRouteStopageLink th = this.transportRouteStopageLinkRepository.save(transportRouteStopageLink);
            logger.debug("TransportRouteStopageLink data saved : "+transportRouteStopageLink);
            return th;
        }else {
            logger.debug("TransportRouteStopageLink mapping already exists. "+oth.get());
        }
        return oth.get();
    }



}
