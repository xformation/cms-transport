package com.synectiks.transport.domain.vo;

import com.synectiks.transport.domain.TransportRoute;
import com.synectiks.transport.domain.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsTransportRouteVehicleLinkVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private TransportRoute transportRoute;
    private Vehicle vehicle;
    private Long transportRouteId;
    private Long vehicleId;
    private List<CmsTransportRouteVehicleLinkVo> dataList = new ArrayList<CmsTransportRouteVehicleLinkVo>();

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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
            ", transportRoute=" + transportRoute +
            ", vehicle=" + vehicle +
            ", transportRouteId=" + transportRouteId +
            ", vehicleId=" + vehicleId +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
