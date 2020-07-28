package com.synectiks.transport.domain.vo;

import com.synectiks.transport.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsVehicleListVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private TransportRoute transportRoute;
    private Contract contract;
    private Long transportRouteId;
    private Long contractId;
    private Long insuranceId;
    private Long stopageId;
    private Long employeeId;
    private Employee employee;
    private Stopage stopage;
    private Insurance insurance;
    private Vehicle vehicle;
    private Long vehicleId;
    private Long branchId;
    private Branch branch;
    private Long vehicleContractLinkId;
    private Long vehicleDriverLinkId;
    private Long transportRouteVehicleLinkId;
    private Long transportRouteStopageLinkId;
    private VehicleContractLink vehicleContractLink;
    private VehicleDriverLink vehicleDriverLink;
    private TransportRouteVehicleLink transportRouteVehicleLink;
    private TransportRouteStopageLink transportRouteStopageLink;
    private List<CmsVehicleListVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportRoute getTransportRoute() {
        return transportRoute;
    }

    public void setTransportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Long getStopageId() {
        return stopageId;
    }

    public void setStopageId(Long stopageId) {
        this.stopageId = stopageId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Stopage getStopage() {
        return stopage;
    }

    public void setStopage(Stopage stopage) {
        this.stopage = stopage;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getVehicleContractLinkId() {
        return vehicleContractLinkId;
    }

    public void setVehicleContractLinkId(Long vehicleContractLinkId) {
        this.vehicleContractLinkId = vehicleContractLinkId;
    }

    public Long getVehicleDriverLinkId() {
        return vehicleDriverLinkId;
    }

    public void setVehicleDriverLinkId(Long vehicleDriverLinkId) {
        this.vehicleDriverLinkId = vehicleDriverLinkId;
    }

    public Long getTransportRouteVehicleLinkId() {
        return transportRouteVehicleLinkId;
    }

    public void setTransportRouteVehicleLinkId(Long transportRouteVehicleLinkId) {
        this.transportRouteVehicleLinkId = transportRouteVehicleLinkId;
    }

    public Long getTransportRouteStopageLinkId() {
        return transportRouteStopageLinkId;
    }

    public void setTransportRouteStopageLinkId(Long transportRouteStopageLinkId) {
        this.transportRouteStopageLinkId = transportRouteStopageLinkId;
    }

    public VehicleContractLink getVehicleContractLink() {
        return vehicleContractLink;
    }

    public void setVehicleContractLink(VehicleContractLink vehicleContractLink) {
        this.vehicleContractLink = vehicleContractLink;
    }

    public VehicleDriverLink getVehicleDriverLink() {
        return vehicleDriverLink;
    }

    public void setVehicleDriverLink(VehicleDriverLink vehicleDriverLink) {
        this.vehicleDriverLink = vehicleDriverLink;
    }

    public TransportRouteVehicleLink getTransportRouteVehicleLink() {
        return transportRouteVehicleLink;
    }

    public void setTransportRouteVehicleLink(TransportRouteVehicleLink transportRouteVehicleLink) {
        this.transportRouteVehicleLink = transportRouteVehicleLink;
    }

    public TransportRouteStopageLink getTransportRouteStopageLink() {
        return transportRouteStopageLink;
    }

    public void setTransportRouteStopageLink(TransportRouteStopageLink transportRouteStopageLink) {
        this.transportRouteStopageLink = transportRouteStopageLink;
    }

    public List<CmsVehicleListVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsVehicleListVo> dataList) {
        this.dataList = dataList;
    }
}
