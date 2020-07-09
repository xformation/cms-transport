package com.synectiks.transport.domain.vo;

import com.synectiks.transport.domain.Contract;
import com.synectiks.transport.domain.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsVehicleContractLinkVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private CmsVehicleVo vehicle;
    private CmsContractVo contract;
    private Long vehicleId;
    private Long contractId;
    private List<CmsVehicleContractLinkVo> dataList = new ArrayList<CmsVehicleContractLinkVo>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CmsVehicleVo getVehicle() {
        return vehicle;
    }

    public void setVehicle(CmsVehicleVo vehicle) {
        this.vehicle = vehicle;
    }

    public CmsContractVo getContract() {
        return contract;
    }

    public void setContract(CmsContractVo contract) {
        this.contract = contract;
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
            ", vehicle=" + vehicle +
            ", contract=" + contract +
            ", vehicleId=" + vehicleId +
            ", contractId=" + contractId +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
