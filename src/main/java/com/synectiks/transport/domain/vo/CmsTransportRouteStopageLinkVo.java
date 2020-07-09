package com.synectiks.transport.domain.vo;

import com.synectiks.transport.domain.Stopage;
import com.synectiks.transport.domain.TransportRoute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsTransportRouteStopageLinkVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private CmsTransportRouteVo transportRoute;
    private CmsStopageVo stopage;
    private Long transportRouteId;
    private Long stopageId;
    private List<CmsTransportRouteStopageLinkVo> dataList = new ArrayList<CmsTransportRouteStopageLinkVo>();

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

    public CmsStopageVo getStopage() {
        return stopage;
    }

    public void setStopage(CmsStopageVo stopage) {
        this.stopage = stopage;
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

    public List<CmsTransportRouteStopageLinkVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsTransportRouteStopageLinkVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsTransportRouteStopageLinkVo{" +
            "id=" + id +
            ", transportRoute=" + transportRoute +
            ", stopage=" + stopage +
            ", transportRouteId=" + transportRouteId +
            ", stopageId=" + stopageId +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
