package com.synectiks.transport.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.transport.domain.*;
//import com.synectiks.transport.domain.enumeration.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CmsVehicleVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String vehicleNumber;
    private String vehicleType;
    private Long capacity;
    private String ownerShip;
    private LocalDate dateOfRegistration;
    private String yearOfManufacturing;
    private String manufacturingCompany;
    private String model;
    private String chasisNo;
    private String rcNo;
    private String status;
    private LocalDate onBoardingDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate validTill;
    private LocalDate dateOfInsurance;
    private LocalDate startDate;
    private LocalDate endDate;
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
    private Long vehicleId;
    private Long branchId;
    private Branch branch;
    private String strOnBoardingDate;
    private String strDateOfRegistration;
    private String strStartDate;
    private String strEndDate;
    private String strValidTill;
    private Long vehicleContractLinkId;
    private Long vehicleDriverLinkId;
    private Long transportRouteVehicleLinkId;
    private Long transportRouteStopageLinkId;
    private String strDateOfInsurance;
    private CmsVehicleContractLinkVo vehicleContractLink;
    private CmsVehicleDriverLinkVo vehicleDriverLink;
    private CmsTransportRouteVehicleLinkVo transportRouteVehicleLink;
    private CmsTransportRouteStopageLinkVo transportRouteStopageLink;
    private List<CmsVehicleVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getOwnerShip() {
        return ownerShip;
    }

    public void setOwnerShip(String ownerShip) {
        this.ownerShip = ownerShip;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(String yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public String getManufacturingCompany() {
        return manufacturingCompany;
    }

    public void setManufacturingCompany(String manufacturingCompany) {
        this.manufacturingCompany = manufacturingCompany;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChasisNo() {
        return chasisNo;
    }

    public void setChasisNo(String chasisNo) {
        this.chasisNo = chasisNo;
    }

    public String getRcNo() {
        return rcNo;
    }

    public void setRcNo(String rcNo) {
        this.rcNo = rcNo;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOnBoardingDate() {
        return onBoardingDate;
    }

    public void setOnBoardingDate(LocalDate onBoardingDate) {
        this.onBoardingDate = onBoardingDate;
    }

    public LocalDate getValidTill() {
        return validTill;
    }

    public void setValidTill(LocalDate validTill) {
        this.validTill = validTill;
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

    public String getStrOnBoardingDate() {
        return strOnBoardingDate;
    }

    public void setStrOnBoardingDate(String strOnBoardingDate) {
        this.strOnBoardingDate = strOnBoardingDate;
    }

    public String getStrDateOfRegistration() {
        return strDateOfRegistration;
    }

    public void setStrDateOfRegistration(String strDateOfRegistration) {
        this.strDateOfRegistration = strDateOfRegistration;
    }

    public LocalDate getDateOfInsurance() {
        return dateOfInsurance;
    }

    public void setDateOfInsurance(LocalDate dateOfInsurance) {
        this.dateOfInsurance = dateOfInsurance;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStrStartDate() {
        return strStartDate;
    }

    public void setStrStartDate(String strStartDate) {
        this.strStartDate = strStartDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    public String getStrValidTill() {
        return strValidTill;
    }

    public void setStrValidTill(String strValidTill) {
        this.strValidTill = strValidTill;
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

    public String getStrDateOfInsurance() {
        return strDateOfInsurance;
    }

    public void setStrDateOfInsurance(String strDateOfInsurance) {
        this.strDateOfInsurance = strDateOfInsurance;
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

    public List<CmsVehicleVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsVehicleVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsVehicleVo{" +
            "id=" + id +
            ", vehicleNumber='" + vehicleNumber + '\'' +
            ", vehicleType='" + vehicleType + '\'' +
            ", capacity=" + capacity +
            ", ownerShip='" + ownerShip + '\'' +
            ", dateOfRegistration=" + dateOfRegistration +
            ", yearOfManufacturing='" + yearOfManufacturing + '\'' +
            ", manufacturingCompany='" + manufacturingCompany + '\'' +
            ", model='" + model + '\'' +
            ", chasisNo='" + chasisNo + '\'' +
            ", rcNo='" + rcNo + '\'' +
            ", status='" + status + '\'' +
            ", onBoardingDate=" + onBoardingDate +
            ", validTill=" + validTill +
            ", branchId=" + branchId +
            ", branch=" + branch +
            ", strOnBoardingDate='" + strOnBoardingDate + '\'' +
            ", strDateOfRegistration='" + strDateOfRegistration + '\'' +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
