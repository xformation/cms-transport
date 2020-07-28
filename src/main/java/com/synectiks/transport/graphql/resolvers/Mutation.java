package com.synectiks.transport.graphql.resolvers;

import com.synectiks.transport.business.service.*;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.transport.config.ApplicationProperties;
import com.synectiks.transport.constant.CmsConstants;
import com.synectiks.transport.domain.Branch;
import com.synectiks.transport.domain.TransportRoute;
import com.synectiks.transport.domain.TransportRouteVehicleLink;
import com.synectiks.transport.domain.Vehicle;
import com.synectiks.transport.domain.vo.*;
import com.synectiks.transport.filter.vehicle.VehicleFilterProcessor;
import com.synectiks.transport.filter.vehicle.VehicleListFilterInput;
import com.synectiks.transport.graphql.types.Contract.AddContractInput;
import com.synectiks.transport.graphql.types.Contract.AddContractPayload;
import com.synectiks.transport.graphql.types.Insurance.AddInsuranceInput;
import com.synectiks.transport.graphql.types.Insurance.AddInsurancePayload;
import com.synectiks.transport.graphql.types.Stopage.AddStopageInput;
import com.synectiks.transport.graphql.types.Stopage.AddStopagePayload;
import com.synectiks.transport.graphql.types.TransportRoute.AddTransportRouteInput;
import com.synectiks.transport.graphql.types.TransportRoute.AddTransportRoutePayload;
import com.synectiks.transport.graphql.types.TransportRouteStopageLink.AddTransportRouteStopageLinkInput;
import com.synectiks.transport.graphql.types.TransportRouteStopageLink.AddTransportRouteStopageLinkPayload;
import com.synectiks.transport.graphql.types.TransportRouteVehicleLink.AddTransportRouteVehicleLinkInput;
import com.synectiks.transport.graphql.types.TransportRouteVehicleLink.AddTransportRouteVehicleLinkPayload;
import com.synectiks.transport.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.transport.graphql.types.Vehicle.AddVehiclePayload;
import com.synectiks.transport.graphql.types.VehicleContractList.AddVehicleContractListInput;
import com.synectiks.transport.graphql.types.VehicleContractList.AddVehicleContractListPayload;
import com.synectiks.transport.graphql.types.VehicleDriverList.AddVehicleDriverListInput;
import com.synectiks.transport.graphql.types.VehicleDriverList.AddVehicleDriverListPayload;
import com.synectiks.transport.repository.*;
import com.synectiks.transport.service.util.CommonUtil;
import com.synectiks.transport.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    @Autowired
    CommonService commonService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    TransportRouteService transportRouteService;

    @Autowired
    ContractService contractService;

    @Autowired
    InsuranceService insuranceService;

    @Autowired
    StopageService stopageService;

    @Autowired
    VehicleContractLinkService vehicleContractLinkService;

    @Autowired
    VehicleDriverLinkService vehicleDriverLinkService;

    @Autowired
    TransportRouteVehicleLinkService transportRouteVehicleLinkService;

    @Autowired
    TransportRouteStopageLinkService transportRouteStopageLinkService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TransportRouteRepository transportRouteRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private StopageRepository stopageRepository;

    @Autowired
    private TransportRouteVehicleLinkRepository transportRouteVehicleLinkRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private VehicleFilterProcessor vehicleFilterProcessor;


    public AddVehiclePayload addVehicle(AddVehicleInput cmsVehicleVo) {
        CmsVehicleVo vo = this.vehicleService.addVehicle(cmsVehicleVo);
        return new AddVehiclePayload(vo);
    }

    public AddTransportRoutePayload addTransportRoute(AddTransportRouteInput cmsTransportRouteVo) {
        CmsTransportRouteVo vo = this.transportRouteService.addTransportRoute(cmsTransportRouteVo);
        return new AddTransportRoutePayload(vo);
    }
    public AddContractPayload addContract(AddContractInput cmsContractVo) {
        CmsContractVo vo = this.contractService.addContract(cmsContractVo);
        return new AddContractPayload(vo);
    }
    public AddInsurancePayload addInsurance(AddInsuranceInput cmsInsuranceVo) {
        CmsInsuranceVo vo = this.insuranceService.addInsurance(cmsInsuranceVo);
        return new AddInsurancePayload(vo);
    }
    public AddStopagePayload addStopage(AddStopageInput cmsStopageVo) {
        CmsStopageVo vo = this.stopageService.addStopage(cmsStopageVo);
        return new AddStopagePayload(vo);
    }

    public AddVehicleContractListPayload saveVehicleContractLink(AddVehicleContractListInput cmsVehicleContractLinkVo) {
        CmsVehicleContractLinkVo vo = this.vehicleContractLinkService.saveVehicleContractLink(cmsVehicleContractLinkVo);
        return new AddVehicleContractListPayload(vo);
    }
    public AddVehicleDriverListPayload saveVehicleDriverLink(AddVehicleDriverListInput cmsVehicleDriverLinkVo) {
        CmsVehicleDriverLinkVo vo = this.vehicleDriverLinkService.saveVehicleDriverLink(cmsVehicleDriverLinkVo);
        return new AddVehicleDriverListPayload(vo);
    }
    public AddTransportRouteVehicleLinkPayload saveTransportRouteVehicleLink(AddTransportRouteVehicleLinkInput cmsTransportRouteVehicleLinkVo) {
        CmsTransportRouteVehicleLinkVo vo = this.transportRouteVehicleLinkService.saveTransportRouteVehicleLink(cmsTransportRouteVehicleLinkVo);
        return new AddTransportRouteVehicleLinkPayload(vo);
    }
    public AddTransportRouteStopageLinkPayload saveTransportRouteStopageLink(AddTransportRouteStopageLinkInput cmsTransportRouteStopageLinkVo) {
        CmsTransportRouteStopageLinkVo vo = this.transportRouteStopageLinkService.saveTransportRouteStopageLink(cmsTransportRouteStopageLinkVo);
        return new AddTransportRouteStopageLinkPayload(vo);
    }
    public List<CmsVehicleListVo> getVehicleList(VehicleListFilterInput filter) throws Exception {
        List<CmsVehicleListVo> list = this.vehicleFilterProcessor.searchVehicle(filter);
        List<CmsVehicleListVo> ls = new ArrayList<>();

        String transportSrvUrl = applicationProperties.getTransportSrvUrl();
        for(CmsVehicleListVo vehicle: list) {
            CmsVehicleListVo vo = CommonUtil.createCopyProperties(vehicle, CmsVehicleListVo.class);
//            TransportRouteVehicleLink tvl =  this.transportRouteVehicleLinkRepository.findById(filter.getTransportRouteVehicleLinkId()).get();
//            String url = transportSrvUrl + "/api/transportRoute-by-id/" + vo.getTransportRouteId();
//            TransportRoute tr = this.commonService.getObject(url, TransportRoute.class);

//            vo.setStrDateOfRegistration(DateFormatUtil.changeLocalDateFormat(vehicle.getDateOfRegistration(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//            vo.setStrOnBoardingDate(DateFormatUtil.changeLocalDateFormat(vehicle.getOnBoardingDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//            vo.setDateOfRegistration(null);
//            vo.setOnBoardingDate(null);
//            vo.setTransportRoute(tr);
            ls.add(vo);
        }
        logger.debug("Total vehicles retrieved. "+list.size());
        return ls;
    }
}
