package com.synectiks.transport.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsTransportRouteStopageLinkVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private CmsTransportRouteVo cmsTransportRouteVo;
    private CmsStopageVo cmsStopageVo;
    private Long transportRouteId;
    private Long stopageId;
    private List<CmsTransportRouteStopageLinkVo> dataList = new ArrayList<CmsTransportRouteStopageLinkVo>();

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

    public CmsStopageVo getCmsStopageVo() {
        return cmsStopageVo;
    }

    public void setCmsStopageVo(CmsStopageVo cmsStopageVo) {
        this.cmsStopageVo = cmsStopageVo;
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
            ", cmsTransportRouteVo=" + cmsTransportRouteVo +
            ", cmsStopageVo=" + cmsStopageVo +
            ", transportRouteId=" + transportRouteId +
            ", stopageId=" + stopageId +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
