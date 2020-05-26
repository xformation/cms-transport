package com.synectiks.transport.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.transport.domain.Insurance} entity.
 */
public class InsuranceDTO implements Serializable {

    private Long id;

    private String insuranceCompany;

    private String typeOfInsurance;

    private LocalDate dateOfInsurance;

    private LocalDate validTill;


    private Long vehicleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuranceDTO insuranceDTO = (InsuranceDTO) o;
        if (insuranceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuranceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuranceDTO{" +
            "id=" + getId() +
            ", insuranceCompany='" + getInsuranceCompany() + "'" +
            ", typeOfInsurance='" + getTypeOfInsurance() + "'" +
            ", dateOfInsurance='" + getDateOfInsurance() + "'" +
            ", validTill='" + getValidTill() + "'" +
            ", vehicleId=" + getVehicleId() +
            "}";
    }
}
