package com.synectiks.transport.graphql.types.Stopage;

import com.synectiks.transport.domain.vo.CmsStopageVo;

public class AddStopagePayload {
    private final CmsStopageVo cmsStopageVo;

    public AddStopagePayload(CmsStopageVo cmsStopageVo) {
        this.cmsStopageVo = cmsStopageVo;
    }

    public CmsStopageVo getCmsStopageVo() {
        return cmsStopageVo;
    }
}
