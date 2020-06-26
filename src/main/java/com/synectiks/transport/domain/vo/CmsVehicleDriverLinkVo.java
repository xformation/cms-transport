package com.synectiks.transport.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsVehicleDriverLinkVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private CmsVehicleVo cmsVehicleVo;
    private Long vehicleId;
    private Long employeeId;
    private List<CmsVehicleDriverLinkVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CmsVehicleVo getCmsVehicleVo() {
        return cmsVehicleVo;
    }

    public void setCmsVehicleVo(CmsVehicleVo cmsVehicleVo) {
        this.cmsVehicleVo = cmsVehicleVo;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<CmsVehicleDriverLinkVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsVehicleDriverLinkVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsVehicleDriverLinkVo{" +
            "id=" + id +
            ", cmsVehicleVo=" + cmsVehicleVo +
            ", vehicleId=" + vehicleId +
            ", employeeId=" + employeeId +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
