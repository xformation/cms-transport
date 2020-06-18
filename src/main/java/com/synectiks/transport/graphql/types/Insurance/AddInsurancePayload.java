package com.synectiks.transport.graphql.types.Insurance;

import com.synectiks.transport.domain.vo.CmsInsuranceVo;

public class AddInsurancePayload{
    private final CmsInsuranceVo cmsInsuranceVo;

    public AddInsurancePayload(CmsInsuranceVo cmsInsuranceVo){
        this.cmsInsuranceVo = cmsInsuranceVo;
    }

    public CmsInsuranceVo getInsuranceVo() {
        return cmsInsuranceVo;
    }

}
