package com.synectiks.transport.graphql.types.VehicleContractList;

import com.synectiks.transport.domain.vo.CmsVehicleContractLinkVo;

public class AddVehicleContractListPayload  {
    private final CmsVehicleContractLinkVo cmsVehicleContractLinkVo;

    public AddVehicleContractListPayload(CmsVehicleContractLinkVo cmsVehicleContractLinkVo){
        this.cmsVehicleContractLinkVo = cmsVehicleContractLinkVo;
    }

    public CmsVehicleContractLinkVo getCmsVehicleContractLinkVo() {
        return cmsVehicleContractLinkVo;
    }
}
