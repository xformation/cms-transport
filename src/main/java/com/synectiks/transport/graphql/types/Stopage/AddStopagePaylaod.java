package com.synectiks.transport.graphql.types.Stopage;

import com.synectiks.transport.domain.vo.CmsStopageVo;

public class AddStopagePaylaod {
    private final CmsStopageVo cmsStopageVo;

    public AddStopagePaylaod(CmsStopageVo cmsStopageVo) {
        this.cmsStopageVo = cmsStopageVo;
    }

    public CmsStopageVo getCmsStopageVo() {
        return cmsStopageVo;
    }
}
