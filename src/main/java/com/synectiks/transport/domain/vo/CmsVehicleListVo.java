package com.synectiks.transport.domain.vo;

import com.synectiks.transport.domain.Branch;
import com.synectiks.transport.domain.Employee;
import com.synectiks.transport.domain.Stopage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsVehicleListVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private CmsTransportRouteVo transportRoute;
    private CmsContractVo contract;
    private Long transportRouteId;
    private Long contractId;
    private Long insuranceId;
    private Long stopageId;
    private Long employeeId;
    private Employee employee;
    private Stopage stopage;
    private CmsInsuranceVo insurance;
    private CmsVehicleVo vehicle;
    private Long vehicleId;
    private Long branchId;
    private Branch branch;
    private Long vehicleContractLinkId;
    private Long vehicleDriverLinkId;
    private Long transportRouteVehicleLinkId;
    private Long transportRouteStopageLinkId;
    private CmsVehicleContractLinkVo vehicleContractLink;
    private CmsVehicleDriverLinkVo vehicleDriverLink;
    private CmsTransportRouteVehicleLinkVo transportRouteVehicleLink;
    private CmsTransportRouteStopageLinkVo transportRouteStopageLink;
    private List<CmsVehicleListVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CmsTransportRouteVo getTransportRoute() {
        return transportRoute;
    }

    public void setTransportRoute(CmsTransportRouteVo transportRoute) {
        this.transportRoute = transportRoute;
    }

    public CmsContractVo getContract() {
        return contract;
    }

    public void setContract(CmsContractVo contract) {
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

    public CmsInsuranceVo getInsurance() {
        return insurance;
    }

    public void setInsurance(CmsInsuranceVo insurance) {
        this.insurance = insurance;
    }

    public CmsVehicleVo getVehicle() {
        return vehicle;
    }

    public void setVehicle(CmsVehicleVo vehicle) {
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

    public CmsVehicleContractLinkVo getVehicleContractLink() {
        return vehicleContractLink;
    }

    public void setVehicleContractLink(CmsVehicleContractLinkVo vehicleContractLink) {
        this.vehicleContractLink = vehicleContractLink;
    }

    public CmsVehicleDriverLinkVo getVehicleDriverLink() {
        return vehicleDriverLink;
    }

    public void setVehicleDriverLink(CmsVehicleDriverLinkVo vehicleDriverLink) {
        this.vehicleDriverLink = vehicleDriverLink;
    }

    public CmsTransportRouteVehicleLinkVo getTransportRouteVehicleLink() {
        return transportRouteVehicleLink;
    }

    public void setTransportRouteVehicleLink(CmsTransportRouteVehicleLinkVo transportRouteVehicleLink) {
        this.transportRouteVehicleLink = transportRouteVehicleLink;
    }

    public CmsTransportRouteStopageLinkVo getTransportRouteStopageLink() {
        return transportRouteStopageLink;
    }

    public void setTransportRouteStopageLink(CmsTransportRouteStopageLinkVo transportRouteStopageLink) {
        this.transportRouteStopageLink = transportRouteStopageLink;
    }

    public List<CmsVehicleListVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsVehicleListVo> dataList) {
        this.dataList = dataList;
    }
}
