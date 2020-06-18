package com.synectiks.transport.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.transport.domain.Vehicle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CmsInsuranceVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Vehicle vehicle;
    private Long vehicleId;
    private String insuranceCompany;
    private String typeOfInsurance;
    private LocalDate dateOfInsurance;
    private LocalDate validTill;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private String strDateOfInsurance;
    private String strValidTill;
    private List<CmsInsuranceVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public void setTypeOfInsurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public LocalDate getDateOfInsurance() {
        return dateOfInsurance;
    }

    public void setDateOfInsurance(LocalDate dateOfInsurance) {
        this.dateOfInsurance = dateOfInsurance;
    }

    public LocalDate getValidTill() {
        return validTill;
    }

    public void setValidTill(LocalDate validTill) {
        this.validTill = validTill;
    }

    public String getStrDateOfInsurance() {
        return strDateOfInsurance;
    }

    public void setStrDateOfInsurance(String strDateOfInsurance) {
        this.strDateOfInsurance = strDateOfInsurance;
    }

    public String getStrValidTill() {
        return strValidTill;
    }

    public void setStrValidTill(String strValidTill) {
        this.strValidTill = strValidTill;
    }

    public List<CmsInsuranceVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsInsuranceVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsInsuranceVo{" +
            "id=" + id +
            ", vehicle=" + vehicle +
            ", vehicleId=" + vehicleId +
            ", insuranceCompany='" + insuranceCompany + '\'' +
            ", typeOfInsurance='" + typeOfInsurance + '\'' +
            ", dateOfInsurance=" + dateOfInsurance +
            ", validTill=" + validTill +
            ", strDateOfInsurance='" + strDateOfInsurance + '\'' +
            ", strValidTill='" + strValidTill + '\'' +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
