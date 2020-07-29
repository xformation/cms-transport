package com.synectiks.transport.domain;

import com.synectiks.transport.domain.vo.*;

import java.util.List;

public class VehicleDataCache {

    private List<CmsTransportRouteVo> transportRoute;
    private List<CmsContractVo> contract;
    private List<CmsVehicleVo> vehicle;
    private List<Employee> employee;
    private List<CmsStopageVo> stopage;
    private List<CmsInsuranceVo> insurance;
    private List<CmsVehicleContractLinkVo> vehicleContractLink;
    private List<CmsVehicleDriverLinkVo> vehicleDriverLink;
    private List<CmsTransportRouteVehicleLinkVo> transportRouteVehicleLink;
    private List<CmsTransportRouteStopageLinkVo> transportRouteStopageLink;
    private List<Branch> branches;

    public List<CmsTransportRouteVo> getTransportRoute() {
        return transportRoute;
    }
    public void setTransportRoute(List<CmsTransportRouteVo> transportRoute) {
        this.transportRoute = transportRoute;
    }
    public List<CmsContractVo> getContract() {
        return contract;
    }
    public void setContract(List<CmsContractVo> contract) {
        this.contract = contract;
    }
    public List<CmsVehicleVo> getVehicle() {
        return vehicle;
    }
    public void setVehicle(List<CmsVehicleVo> vehicle) {
        this.vehicle = vehicle;
    }
    public List<Employee> getEmployee() {
        return employee;
    }

    public List<CmsStopageVo> getStopage() {
        return stopage;
    }

    public void setStopage(List<CmsStopageVo> stopage) {
        this.stopage = stopage;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
    public List<Branch> getBranches() {
        return branches;
    }
    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<CmsVehicleContractLinkVo> getVehicleContractLink() {
        return vehicleContractLink;
    }

    public void setVehicleContractLink(List<CmsVehicleContractLinkVo> vehicleContractLink) {
        this.vehicleContractLink = vehicleContractLink;
    }

    public List<CmsVehicleDriverLinkVo> getVehicleDriverLink() {
        return vehicleDriverLink;
    }

    public void setVehicleDriverLink(List<CmsVehicleDriverLinkVo> vehicleDriverLink) {
        this.vehicleDriverLink = vehicleDriverLink;
    }

    public List<CmsTransportRouteVehicleLinkVo> getTransportRouteVehicleLink() {
        return transportRouteVehicleLink;
    }

    public void setTransportRouteVehicleLink(List<CmsTransportRouteVehicleLinkVo> transportRouteVehicleLink) {
        this.transportRouteVehicleLink = transportRouteVehicleLink;
    }

    public List<CmsTransportRouteStopageLinkVo> getTransportRouteStopageLink() {
        return transportRouteStopageLink;
    }

    public void setTransportRouteStopageLink(List<CmsTransportRouteStopageLinkVo> transportRouteStopageLink) {
        this.transportRouteStopageLink = transportRouteStopageLink;
    }

    public List<CmsInsuranceVo> getInsurance() {
        return insurance;
    }

    public void setInsurance(List<CmsInsuranceVo> insurance) {
        this.insurance = insurance;
    }
}
