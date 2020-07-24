package com.synectiks.transport.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.synectiks.transport.business.service.*;
import com.synectiks.transport.config.ApplicationProperties;
import com.synectiks.transport.domain.*;
import com.synectiks.transport.domain.vo.*;
import com.synectiks.transport.filter.vehicle.VehicleListFilterInput;
import com.synectiks.transport.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final static Logger logger = LoggerFactory.getLogger(Query.class);

    private final VehicleRepository vehicleRepository;
    private final ContractRepository contractRepository;
    private final InsuranceRepository insuranceRepository;
    private final TransportRouteRepository transportRouteRepository;
    private final StopageRepository stopageRepository;
    private final TransportRouteVehicleLinkRepository transportRouteVehicleLinkRepository;
    private final TransportRouteStopageLinkRepository transportRouteStopageLinkRepository;
    private final VehicleDriverLinkRepository vehicleDriverLinkRepository;
    private final VehicleContractLinkRepository vehicleContractLinkRepository;

    @Autowired
    private VehicleFilterProcessor vehicleFilterProcessor;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private TransportRouteService transportRouteService;
    @Autowired
    private StopageService stopageService;
    @Autowired
    private TransportRouteVehicleLinkService transportRouteVehicleLinkService;
    @Autowired
    private TransportRouteStopageLinkService transportRouteStopageLinkService;
    @Autowired
    private VehicleContractLinkService vehicleContractLinkService;
    @Autowired
    private VehicleDriverLinkService vehicleDriverLinkService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ApplicationProperties applicationProperties;

    public List<CmsVehicleVo> getVehicleList() throws Exception {
        logger.debug("Query - getVehicleList :");
        return this.vehicleService.getVehicleList();
    }
    public List<CmsInsuranceVo> getInsuranceList() throws Exception {
        logger.debug("Query - getInsuranceList :");
        return this.insuranceService.getInsuranceList();
    }
    public List<CmsContractVo> getContractList() throws Exception {
        logger.debug("Query - getContractList :");
        return this.contractService.getContractList();
    }
    public List<CmsTransportRouteVo> getTransportRouteList() throws Exception {
        logger.debug("Query - getTransportRouteList :");
        return this.transportRouteService.getTransportRouteList();
    }
    public List<CmsTransportRouteStopageLinkVo> getTransportRouteStopageList() throws Exception {
        logger.debug("Query - geTransportRouteStopageList :");
        return this.transportRouteStopageLinkService.getTransportRouteStopageList();
    }
    public List<CmsTransportRouteVehicleLinkVo> getTransportRouteVehicleList() throws Exception {
        logger.debug("Query - getTransportRouteVehicleList :");
        return this.transportRouteVehicleLinkService.getTransportRouteVehicleList();
    }
    public List<CmsVehicleContractLinkVo> getVehicleContractList() throws Exception {
        logger.debug("Query - getVehicleContractList :");
        return this.vehicleContractLinkService.getVehicleContractList();
    }
    public List<CmsStopageVo> getStopageList() throws Exception {
        logger.debug("Query - getStopageList :");
        return this.stopageService.getStopageList();
    }
    public List<CmsVehicleDriverLinkVo> getVehicleDriverList() throws Exception {
        logger.debug("Query - getVehicleDriverList :");
        return this.vehicleDriverLinkService.getVehicleDriverList();
    }

    public Query(VehicleRepository vehicleRepository,
                 InsuranceRepository insuranceRepository,
                 ContractRepository contractRepository,
                 TransportRouteRepository transportRouteRepository,
                 StopageRepository stopageRepository,
                 VehicleContractLinkRepository vehicleContractLinkRepository,
                 VehicleDriverLinkRepository vehicleDriverLinkRepository,
                 TransportRouteStopageLinkRepository transportRouteStopageLinkRepository,
                 TransportRouteVehicleLinkRepository transportRouteVehicleLinkRepository)
    {
        this.contractRepository = contractRepository;
        this.insuranceRepository = insuranceRepository;
        this.vehicleRepository = vehicleRepository;
        this.transportRouteRepository = transportRouteRepository;
        this.stopageRepository = stopageRepository;
        this.vehicleContractLinkRepository = vehicleContractLinkRepository;
        this.vehicleDriverLinkRepository = vehicleDriverLinkRepository;
        this.transportRouteStopageLinkRepository = transportRouteStopageLinkRepository;
        this.transportRouteVehicleLinkRepository = transportRouteVehicleLinkRepository;
    }

    public Contract contract(long id){
        return contractRepository.findById(id).get();
    }
    public List<Contract> contracts (){
        return Lists.newArrayList(contractRepository.findAll());
    }

    public Insurance insurance(long id){
        return insuranceRepository.findById(id).get();
    }
    public List<Insurance>  insurances(){
        return  Lists.newArrayList(insuranceRepository.findAll());
    }
    public TransportRoute transportRoute(long id){
        return transportRouteRepository.findById(id).get();
    }
    public List<TransportRoute>  transportRoutes(){
        return  Lists.newArrayList(transportRouteRepository.findAll());
    }
    public Vehicle vehicle(long id){
        return vehicleRepository.findById(id).get();
    }
    public List<Vehicle>  vehicles(){
        return  Lists.newArrayList(vehicleRepository.findAll());
    }

    public Stopage stopage(long id){
        return stopageRepository.findById(id).get();
    }
    public List<Stopage>  stopages(){
        return  Lists.newArrayList(stopageRepository.findAll());
    }

    public TransportRouteVehicleLink transportRouteVehicleLink(long id){
        return transportRouteVehicleLinkRepository.findById(id).get();
    }

    public List<TransportRouteVehicleLink> transportRouteVehicleLinks(){
        return  Lists.newArrayList(transportRouteVehicleLinkRepository.findAll());
    }

    public TransportRouteStopageLink transportRouteStopageLink(long id){
        return transportRouteStopageLinkRepository.findById(id).get();
    }

    public List<TransportRouteStopageLink> transportRouteStopageLinks(){
        return  Lists.newArrayList(transportRouteStopageLinkRepository.findAll());
    }

    public VehicleContractLink vehicleContractLink(long id){
        return vehicleContractLinkRepository.findById(id).get();
    }

    public List<VehicleContractLink> vehicleContractLinks(){
        return  Lists.newArrayList(vehicleContractLinkRepository.findAll());
    }

    public VehicleDriverLink vehicleDriverLink(long id){
        return vehicleDriverLinkRepository.findById(id).get();
    }

    public List<VehicleDriverLink> vehicleDriverLinks(){
        return  Lists.newArrayList(vehicleDriverLinkRepository.findAll());
    }
//    public List<CmsVehicleVo> searchVehicle(Long vehicleId, Long transportRouteId ,Long employeeId,String vehicleNumber) {
//        return Lists.newArrayList(vehicleFilterProcessor.searchVehicle(vehicleId, transportRouteId, employeeId, vehicleNumber));
//    }
//
//    public List<CmsVehicleVo> searchVehicle(VehicleListFilterInput filter) throws Exception {
//        return vehicleService.searchVehicle(filter);
//    }

    public VehicleDataCache createVehicleDataCache() throws Exception{
        List<CmsTransportRouteVo> transportRouteList = this.transportRouteService.getTransportRouteList();
        List<CmsContractVo> contractList = this.contractService.getContractList();
        List<CmsVehicleVo> vehicleList = this.vehicleService.getVehicleList();
        List<CmsStopageVo> stopageList = this.stopageService.getStopageList();
        List<CmsVehicleContractLinkVo> vehicleContractLinkList = this.vehicleContractLinkService.getVehicleContractList();
        List<CmsVehicleDriverLinkVo> vehicleDriverLinkList = this.vehicleDriverLinkService.getVehicleDriverList();
        List<CmsTransportRouteVehicleLinkVo> transportRouteVehicleLinkList = this.transportRouteVehicleLinkService.getTransportRouteVehicleList();
        List<CmsTransportRouteStopageLinkVo> transportRouteStopageLinkList = this.transportRouteStopageLinkService.getTransportRouteStopageList();
        String preUrl = this.applicationProperties.getPrefSrvUrl();
        String url = preUrl+"/api/employee-by-filters/";
        Employee[] employeeList = this.commonService.getObject(url,Employee[].class);
        url = preUrl+"/api/branch-by-filters";
        Branch[] branchList = this.commonService.getObject(url,Branch[].class);
        VehicleDataCache cache = new VehicleDataCache();
        cache.setTransportRoute(transportRouteList);
        cache.setContract(contractList);
        cache.setVehicle(vehicleList);
        cache.setStopage(stopageList);
        cache.setVehicleContractLink(vehicleContractLinkList);
        cache.setVehicleDriverLink(vehicleDriverLinkList);
        cache.setTransportRouteVehicleLink(transportRouteVehicleLinkList);
        cache.setTransportRouteStopageLink(transportRouteStopageLinkList);
        cache.setEmployee(Arrays.asList(employeeList));
        cache.setBranches(Arrays.asList(branchList));
        return cache;
    }
    public InsuranceDataCache createInsuranceDataCache() throws Exception {
        List<CmsVehicleVo> vehicleList = this.vehicleService.getVehicleList();
        InsuranceDataCache cache = new InsuranceDataCache();
        cache.setVehicle(vehicleList);
        return cache;
    }
}
