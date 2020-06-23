package com.synectiks.transport.domain;

import com.synectiks.transport.domain.vo.CmsVehicleVo;

import java.util.List;

public class InsuranceDataCache {
    private List<CmsVehicleVo> vehicle;
    public List<CmsVehicleVo> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<CmsVehicleVo> vehicle) {
        this.vehicle = vehicle;
    }

}
