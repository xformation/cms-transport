package com.synectiks.transport.graphql.types.Vehicle;

import com.synectiks.transport.domain.vo.CmsVehicleVo;

public class AddVehiclePayload {
    private final CmsVehicleVo cmsVehicleVo;

    public AddVehiclePayload(CmsVehicleVo cmsVehicleVo){
        this.cmsVehicleVo = cmsVehicleVo;
    }

    public CmsVehicleVo getVehicleVo() {
        return cmsVehicleVo;
    }

}
