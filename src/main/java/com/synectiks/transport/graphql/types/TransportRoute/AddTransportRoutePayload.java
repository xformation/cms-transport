package com.synectiks.transport.graphql.types.TransportRoute;

import com.synectiks.transport.domain.vo.CmsTransportRouteVo;

public class AddTransportRoutePayload {
    private final CmsTransportRouteVo cmsTransportRouteVo;

    public AddTransportRoutePayload(CmsTransportRouteVo cmsTransportRouteVo){
        this.cmsTransportRouteVo = cmsTransportRouteVo;
    }

    public CmsTransportRouteVo getTransportRouteVo() {
        return cmsTransportRouteVo;
    }

}
