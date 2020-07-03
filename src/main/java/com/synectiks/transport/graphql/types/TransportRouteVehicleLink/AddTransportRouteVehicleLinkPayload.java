package com.synectiks.transport.graphql.types.TransportRouteVehicleLink;

import com.synectiks.transport.domain.vo.CmsTransportRouteVehicleLinkVo;

public class AddTransportRouteVehicleLinkPayload {
    private final CmsTransportRouteVehicleLinkVo cmsTransportRouteVehicleLinkVo;
    public AddTransportRouteVehicleLinkPayload(CmsTransportRouteVehicleLinkVo cmsTransportRouteVehicleLinkVo){
        this.cmsTransportRouteVehicleLinkVo = cmsTransportRouteVehicleLinkVo;
    }

    public CmsTransportRouteVehicleLinkVo getCmsTransportRouteVehicleLinkVo() {
        return cmsTransportRouteVehicleLinkVo;
    }
}
