package com.synectiks.transport.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.transport.domain.TransportRouteVehicleLink} entity.
 */
public class TransportRouteVehicleLinkDTO implements Serializable {

    private Long id;


    private Long transportRouteId;

    private Long vehicleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
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

        TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO = (TransportRouteVehicleLinkDTO) o;
        if (transportRouteVehicleLinkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transportRouteVehicleLinkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransportRouteVehicleLinkDTO{" +
            "id=" + getId() +
            ", transportRouteId=" + getTransportRouteId() +
            ", vehicleId=" + getVehicleId() +
            "}";
    }
}
