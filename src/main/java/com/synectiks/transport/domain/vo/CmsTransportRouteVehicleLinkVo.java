package com.synectiks.transport.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsTransportRouteVehicleLinkVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private CmsTransportRouteVo cmsTransportRouteVo;
    private CmsVehicleVo cmsVehicleVo;
    private Long transportRouteId;
    private Long vehicleId;
    private List<CmsTransportRouteVehicleLinkVo> dataList = new ArrayList<CmsTransportRouteVehicleLinkVo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CmsTransportRouteVo getCmsTransportRouteVo() {
        return cmsTransportRouteVo;
    }

    public void setCmsTransportRouteVo(CmsTransportRouteVo cmsTransportRouteVo) {
        this.cmsTransportRouteVo = cmsTransportRouteVo;
    }

    public CmsVehicleVo getCmsVehicleVo() {
        return cmsVehicleVo;
    }

    public void setCmsVehicleVo(CmsVehicleVo cmsVehicleVo) {
        this.cmsVehicleVo = cmsVehicleVo;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<CmsTransportRouteVehicleLinkVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsTransportRouteVehicleLinkVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsTransportRouteVehicleLinkVo{" +
            "id=" + id +
            ", cmsTransportRouteVo=" + cmsTransportRouteVo +
            ", cmsVehicleVo=" + cmsVehicleVo +
            ", transportRouteId=" + transportRouteId +
            ", vehicleId=" + vehicleId +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
