package com.synectiks.transport.graphql.types.TransportRouteStopageLink;

import com.synectiks.transport.domain.vo.CmsTransportRouteStopageLinkVo;

public class AddTransportRouteStopageLinkPayload {
    private final CmsTransportRouteStopageLinkVo cmsTransportRouteStopageLinkVo;
    public AddTransportRouteStopageLinkPayload(CmsTransportRouteStopageLinkVo cmsTransportRouteStopageLinkVo){
        this.cmsTransportRouteStopageLinkVo = cmsTransportRouteStopageLinkVo;
    }

    public CmsTransportRouteStopageLinkVo getCmsTransportRouteStopageLinkVo() {
        return cmsTransportRouteStopageLinkVo;
    }
}
