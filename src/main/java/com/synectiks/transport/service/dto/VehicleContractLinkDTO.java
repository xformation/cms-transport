package com.synectiks.transport.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.transport.domain.VehicleContractLink} entity.
 */
public class VehicleContractLinkDTO implements Serializable {

    private Long id;


    private Long vehicleId;

    private Long contractId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VehicleContractLinkDTO vehicleContractLinkDTO = (VehicleContractLinkDTO) o;
        if (vehicleContractLinkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleContractLinkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleContractLinkDTO{" +
            "id=" + getId() +
            ", vehicleId=" + getVehicleId() +
            ", contractId=" + getContractId() +
            "}";
    }
}
