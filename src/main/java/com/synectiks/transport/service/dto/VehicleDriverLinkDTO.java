package com.synectiks.transport.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.transport.domain.VehicleDriverLink} entity.
 */
public class VehicleDriverLinkDTO implements Serializable {

    private Long id;

    private Long employeeId;


    private Long vehicleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

        VehicleDriverLinkDTO vehicleDriverLinkDTO = (VehicleDriverLinkDTO) o;
        if (vehicleDriverLinkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleDriverLinkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleDriverLinkDTO{" +
            "id=" + getId() +
            ", employeeId=" + getEmployeeId() +
            ", vehicleId=" + getVehicleId() +
            "}";
    }
}
