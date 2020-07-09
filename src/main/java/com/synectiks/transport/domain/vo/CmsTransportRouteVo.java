package com.synectiks.transport.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.transport.domain.Branch;
import com.synectiks.transport.domain.TransportRouteStopageLink;
import com.synectiks.transport.domain.TransportRouteVehicleLink;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CmsTransportRouteVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String routeName;
    private String routeDetails;
    private String routeMapUrl;
    private Integer noOfStops;
    private String routeFrequency;
    private String status;
    private String createdBy;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdOn;
    private String updatedBy;
    private LocalDate updatedOn;
    private String strCreatedOn;
    private String strUpdatedOn;
    private Branch branch;
    private Long branchId;

    private List<CmsTransportRouteVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    public String getRouteMapUrl() {
        return routeMapUrl;
    }

    public void setRouteMapUrl(String routeMapUrl) {
        this.routeMapUrl = routeMapUrl;
    }

    public Integer getNoOfStops() {
        return noOfStops;
    }

    public void setNoOfStops(Integer noOfStops) {
        this.noOfStops = noOfStops;
    }

    public String getRouteFrequency() {
        return routeFrequency;
    }

    public void setRouteFrequency(String routeFrequency) {
        this.routeFrequency = routeFrequency;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public LocalDate getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    @Override
    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String getStrCreatedOn() {
        return strCreatedOn;
    }

    @Override
    public void setStrCreatedOn(String strCreatedOn) {
        this.strCreatedOn = strCreatedOn;
    }

    @Override
    public String getStrUpdatedOn() {
        return strUpdatedOn;
    }

    @Override
    public void setStrUpdatedOn(String strUpdatedOn) {
        this.strUpdatedOn = strUpdatedOn;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public List<CmsTransportRouteVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsTransportRouteVo> dataList) {
        this.dataList = dataList;
    }


    @Override
    public String toString() {
        return "CmsTransportRouteVo{" +
            "id=" + id +
            ", routeName='" + routeName + '\'' +
            ", routeDetails='" + routeDetails + '\'' +
            ", routeMapUrl='" + routeMapUrl + '\'' +
            ", noOfStops=" + noOfStops +
            ", routeFrequency='" + routeFrequency + '\'' +
            ", status='" + status + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdOn=" + createdOn +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedOn=" + updatedOn +
            ", strCreatedOn='" + strCreatedOn + '\'' +
            ", strUpdatedOn='" + strUpdatedOn + '\'' +
            ", branch=" + branch +
            ", branchId=" + branchId +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
