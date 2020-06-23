package com.synectiks.transport.filter.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleListFilterInput {
    private String transportRouteId;
    private String vehicleId;
    private String employeeId;
    private String branchId;
    private String status;

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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
