package com.synectiks.transport.business.service;

import com.synectiks.transport.domain.*;
import com.synectiks.transport.domain.vo.CmsContractVo;
import com.synectiks.transport.domain.vo.CmsVehicleVo;
import com.synectiks.transport.filter.vehicle.VehicleListFilterInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleFilterProcessor {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private TransportRouteService transportRouteService;
    @Autowired
    private  ContractService contractService;


//    public List<CmsVehicleVo> searchVehicle(Long transportRouteId,Long vehicleId,Long employeeId, String vehicleNumber) throws Exception {
//        return vehicleService.searchVehicle(vehicleId,transportRouteId,employeeId,vehicleNumber);
//    }
//    public List<TransportRoute> searchTransportRoute(VehicleListFilterInput filter) throws Exception {
//        return transportRouteService.searchTransportRoute(filter);
//    }
//    public List<VehicleDriverLink> getDriverList(CmsVehicleVo vo){
//        return vehicleService.getDriverList(vo);
//    }
//    public List<VehicleContractLink> getVehicleContractList(CmsVehicleVo vo){
//        return vehicleService.getVehicleContractList(vo);
//    }
//    public List<TransportRouteVehicleLink> getRouteVehicleList(CmsVehicleVo vo){
//        return vehicleService.getRouteVehicleList(vo);
//    }
//    public List<VehicleContractLink> getVehicleContractList(CmsContractVo vo){
//        return contractService.getVehicleContractList(vo);
//    }

//    public List<CmsVehicleVo> searchVehicle(Long transportRouteId,Long vehicleId,Long employeeId, String vehicleNumber) throws Exception {
//        return vehicleService.searchVehicle(vehicleId,transportRouteId,employeeId,vehicleNumber);
//    }

    public List<Vehicle> searchVehicle(VehicleListFilterInput filter) {
        return vehicleService.searchVehicle(filter);
    }

}
