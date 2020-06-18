package com.synectiks.transport.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.transport.domain.Branch;
import com.synectiks.transport.domain.TransportRouteStopageLink;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CmsStopageVo extends CmsCommonVo  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String stopageName;
    private String status;
    private String createdBy;
    private LocalDate createdOn;
    private String strCreatedOn;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private String updatedBy;
    private LocalDate updatedOn;
    private String strUpdatedOn;
    private Long transportRouteId;
    private Long stopageId;
    private CmsTransportRouteVo cmsTransportRouteVo;
    private Long transportRouteStopageLinkId;
    private CmsTransportRouteStopageLinkVo cmsTransportRouteStopageLinkVo;
    private List<TransportRouteStopageLink> routeStopageList;
    private Branch branch;
    private Long branchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStopageName() {
        return stopageName;
    }

    public void setStopageName(String stopageName) {
        this.stopageName = stopageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getStrCreatedOn() {
        return strCreatedOn;
    }

    public void setStrCreatedOn(String strCreatedOn) {
        this.strCreatedOn = strCreatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getStrUpdatedOn() {
        return strUpdatedOn;
    }

    public void setStrUpdatedOn(String strUpdatedOn) {
        this.strUpdatedOn = strUpdatedOn;
    }

    public List<TransportRouteStopageLink> getRouteStopageList() {
        return routeStopageList;
    }

    public void setRouteStopageList(List<TransportRouteStopageLink> routeStopageList) {
        this.routeStopageList = routeStopageList;
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

    public Long getTransportRouteStopageLinkId() {
        return transportRouteStopageLinkId;
    }

    public void setTransportRouteStopageLinkId(Long transportRouteStopageLinkId) {
        this.transportRouteStopageLinkId = transportRouteStopageLinkId;
    }

    public CmsTransportRouteStopageLinkVo getCmsTransportRouteStopageLinkVo() {
        return cmsTransportRouteStopageLinkVo;
    }

    public void setCmsTransportRouteStopageLinkVo(CmsTransportRouteStopageLinkVo cmsTransportRouteStopageLinkVo) {
        this.cmsTransportRouteStopageLinkVo = cmsTransportRouteStopageLinkVo;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    public CmsTransportRouteVo getCmsTransportRouteVo() {
        return cmsTransportRouteVo;
    }

    public void setCmsTransportRouteVo(CmsTransportRouteVo cmsTransportRouteVo) {
        this.cmsTransportRouteVo = cmsTransportRouteVo;
    }

    @Override
    public String toString() {
        return "CmsStopageVo{" +
            "id=" + id +
            ", stopageName='" + stopageName + '\'' +
            ", status='" + status + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdOn=" + createdOn +
            ", strCreatedOn='" + strCreatedOn + '\'' +
            ", updatedBy='" + updatedBy + '\'' +
            ", updatedOn=" + updatedOn +
            ", strUpdatedOn='" + strUpdatedOn + '\'' +
            ", transportRouteId=" + transportRouteId +
            ", cmsTransportRouteVo=" + cmsTransportRouteVo +
            ", transportRouteStopageLinkId=" + transportRouteStopageLinkId +
            ", cmsTransportRouteStopageLinkVo=" + cmsTransportRouteStopageLinkVo +
            ", routeStopageList=" + routeStopageList +
            ", branch=" + branch +
            ", branchId=" + branchId +
            "} " + super.toString();
    }

    public Long getStopageId() {
        return stopageId;
    }

    public void setStopageId(Long stopageId) {
        this.stopageId = stopageId;
    }
}
