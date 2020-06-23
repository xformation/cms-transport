package com.synectiks.transport.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsVehicleContractLinkVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private CmsVehicleVo cmsVehicleVo;
    private CmsContractVo cmsContractVo;
    private Long vehicleId;
    private Long contractId;
    private List<CmsVehicleContractLinkVo> dataList = new ArrayList<CmsVehicleContractLinkVo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CmsVehicleVo getCmsVehicleVo() {
        return cmsVehicleVo;
    }

    public void setCmsVehicleVo(CmsVehicleVo cmsVehicleVo) {
        this.cmsVehicleVo = cmsVehicleVo;
    }

    public CmsContractVo getCmsContractVo() {
        return cmsContractVo;
    }

    public void setCmsContractVo(CmsContractVo cmsContractVo) {
        this.cmsContractVo = cmsContractVo;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public List<CmsVehicleContractLinkVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsVehicleContractLinkVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsVehicleContractLinkVo{" +
            "id=" + id +
            ", cmsVehicleVo=" + cmsVehicleVo +
            ", cmsContractVo=" + cmsContractVo +
            ", vehicleId=" + vehicleId +
            ", contractId=" + contractId +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
