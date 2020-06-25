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
    private String updatedBy;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private String strCreatedOn;
    private String strUpdatedOn;
    private Long transportRouteId;
    private Long stopageId;
    private Branch branch;
    private Long branchId;
    private CmsTransportRouteVo cmsTransportRouteVo;
    private Long transportRouteStopageLinkId;
    private CmsTransportRouteStopageLinkVo cmsTransportRouteStopageLinkVo;
    private List<TransportRouteStopageLink> routeStopageList;
    private List<CmsStopageVo> dataList = new ArrayList<>();


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
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    public Long getStopageId() {
        return stopageId;
    }

    public void setStopageId(Long stopageId) {
        this.stopageId = stopageId;
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

    public CmsTransportRouteVo getCmsTransportRouteVo() {
        return cmsTransportRouteVo;
    }

    public void setCmsTransportRouteVo(CmsTransportRouteVo cmsTransportRouteVo) {
        this.cmsTransportRouteVo = cmsTransportRouteVo;
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

    public List<TransportRouteStopageLink> getRouteStopageList() {
        return routeStopageList;
    }

    public void setRouteStopageList(List<TransportRouteStopageLink> routeStopageList) {
        this.routeStopageList = routeStopageList;
    }

    public List<CmsStopageVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsStopageVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsStopageVo{" +
            "id=" + id +
            ", stopageName='" + stopageName + '\'' +
            ", status='" + status + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", updatedBy='" + updatedBy + '\'' +
            ", createdOn=" + createdOn +
            ", updatedOn=" + updatedOn +
            ", strCreatedOn='" + strCreatedOn + '\'' +
            ", strUpdatedOn='" + strUpdatedOn + '\'' +
            ", transportRouteId=" + transportRouteId +
            ", stopageId=" + stopageId +
            ", branch=" + branch +
            ", branchId=" + branchId +
            ", cmsTransportRouteVo=" + cmsTransportRouteVo +
            ", transportRouteStopageLinkId=" + transportRouteStopageLinkId +
            ", cmsTransportRouteStopageLinkVo=" + cmsTransportRouteStopageLinkVo +
            ", routeStopageList=" + routeStopageList +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
