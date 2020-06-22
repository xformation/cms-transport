package com.synectiks.transport.domain.vo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.transport.domain.VehicleContractLink;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CmsContractVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String vendorName;
    private String typeOfOwnerShip;
    private String durationOfContract;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    private LocalDate endDate;
    private String strStartDate;
    private String strEndDate;
    private Long vehicleContractLinkId;
    private Long vehicleId;
    private Long contractId;
    private CmsVehicleVo cmsVehicleVo;
    private CmsVehicleContractLinkVo cmsVehicleContractLinkVo;
    private List<VehicleContractLink> vehicleContractList;
    private List<CmsContractVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getTypeOfOwnerShip() {
        return typeOfOwnerShip;
    }

    public void setTypeOfOwnerShip(String typeOfOwnerShip) {
        this.typeOfOwnerShip = typeOfOwnerShip;
    }

    public String getDurationOfContract() {
        return durationOfContract;
    }

    public void setDurationOfContract(String durationOfContract) {
        this.durationOfContract = durationOfContract;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStrStartDate() {
        return strStartDate;
    }

    public void setStrStartDate(String strStartDate) {
        this.strStartDate = strStartDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    public List<VehicleContractLink> getVehicleContractList() {
        return vehicleContractList;
    }

    public void setVehicleContractList(List<VehicleContractLink> vehicleContractList) {
        this.vehicleContractList = vehicleContractList;
    }

    public List<CmsContractVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsContractVo> dataList) {
        this.dataList = dataList;
    }

    public Long getVehicleContractLinkId() {
        return vehicleContractLinkId;
    }

    public void setVehicleContractLinkId(Long vehicleContractLinkId) {
        this.vehicleContractLinkId = vehicleContractLinkId;
    }

    public CmsVehicleContractLinkVo getCmsVehicleContractLinkVo() {
        return cmsVehicleContractLinkVo;
    }

    public void setCmsVehicleContractLinkVo(CmsVehicleContractLinkVo cmsVehicleContractLinkVo) {
        this.cmsVehicleContractLinkVo = cmsVehicleContractLinkVo;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public CmsVehicleVo getCmsVehicleVo() {
        return cmsVehicleVo;
    }

    public void setCmsVehicleVo(CmsVehicleVo cmsVehicleVo) {
        this.cmsVehicleVo = cmsVehicleVo;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "CmsContractVo{" +
            "id=" + id +
            ", vendorName='" + vendorName + '\'' +
            ", typeOfOwnerShip='" + typeOfOwnerShip + '\'' +
            ", durationOfContract='" + durationOfContract + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", strStartDate='" + strStartDate + '\'' +
            ", strEndDate='" + strEndDate + '\'' +
            ", vehicleContractLinkId=" + vehicleContractLinkId +
            ", vehicleId=" + vehicleId +
            ", contractId=" + contractId +
            ", cmsVehicleVo=" + cmsVehicleVo +
            ", cmsVehicleContractLinkVo=" + cmsVehicleContractLinkVo +
            ", vehicleContractList=" + vehicleContractList +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
