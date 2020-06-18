package com.synectiks.transport.graphql.types.Contract;

import com.synectiks.transport.domain.vo.CmsContractVo;


public class AddContractPayload {
    private final CmsContractVo cmsContractVo;

    public AddContractPayload(CmsContractVo cmsContractVo) {
        this.cmsContractVo = cmsContractVo;
    }

    public CmsContractVo getContractVo() {
        return cmsContractVo;
    }
}
