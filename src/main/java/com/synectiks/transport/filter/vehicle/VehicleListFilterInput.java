package com.synectiks.transport.filter.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleListFilterInput {
    private String vehicleDriverLinkId;
    private String vehicleContractLinkId;
    private String transportRouteVehicleLinkId;
    private String transportRouteStopageLinkId;
    private String transportRouteId;
    private String vehicleId;
    private String employeeId;
    private String branchId;
    private String contractId;
    private String insuranceId;
    private String status;

    @JsonProperty("vehicleDriverLinkId")
    public String getVehicleDriverLinkId() {
        return vehicleDriverLinkId;
    }

    public void setVehicleDriverLinkId(String vehicleDriverLinkId) {
        this.vehicleDriverLinkId = vehicleDriverLinkId;
    }

    @JsonProperty("vehicleContractLinkId")

    public String getVehicleContractLinkId() {
        return vehicleContractLinkId;
    }

    public void setVehicleContractLinkId(String vehicleContractLinkId) {
        this.vehicleContractLinkId = vehicleContractLinkId;
    }

    @JsonProperty("transportRouteVehicleLinkId")

    public String getTransportRouteVehicleLinkId() {
        return transportRouteVehicleLinkId;
    }

    public void setTransportRouteVehicleLinkId(String transportRouteVehicleLinkId) {
        this.transportRouteVehicleLinkId = transportRouteVehicleLinkId;
    }

    @JsonProperty("transportRouteStopageLinkId")

    public String getTransportRouteStopageLinkId() {
        return transportRouteStopageLinkId;
    }

    public void setTransportRouteStopageLinkId(String transportRouteStopageLinkId) {
        this.transportRouteStopageLinkId = transportRouteStopageLinkId;
    }

    @JsonProperty("transportRouteId")
    public String getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(String transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    @JsonProperty("vehicleId")
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @JsonProperty("employeeId")
    public String getEmployeeId() { return employeeId; }

    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    @JsonProperty("branchId")
    public String getBranchId() {
        return branchId;
    }
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @JsonProperty("contractId")

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @JsonProperty("insuranceId")

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }
}
