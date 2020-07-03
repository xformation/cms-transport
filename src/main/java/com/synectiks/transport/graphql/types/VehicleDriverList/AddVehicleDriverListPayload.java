package com.synectiks.transport.graphql.types.VehicleDriverList;

import com.synectiks.transport.domain.vo.CmsVehicleDriverLinkVo;

public class AddVehicleDriverListPayload {
    private final CmsVehicleDriverLinkVo cmsVehicleDriverLinkVo;
    public AddVehicleDriverListPayload(CmsVehicleDriverLinkVo cmsVehicleDriverLinkVo){
        this.cmsVehicleDriverLinkVo = cmsVehicleDriverLinkVo;
    }

    public CmsVehicleDriverLinkVo getCmsVehicleDriverLinkVo() {
        return cmsVehicleDriverLinkVo;
    }
}
