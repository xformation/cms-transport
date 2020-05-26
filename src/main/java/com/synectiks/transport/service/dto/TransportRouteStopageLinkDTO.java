package com.synectiks.transport.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.transport.domain.TransportRouteStopageLink} entity.
 */
public class TransportRouteStopageLinkDTO implements Serializable {

    private Long id;


    private Long transportRouteId;

    private Long stopageId;

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

    public Long getStopageId() {
        return stopageId;
    }

    public void setStopageId(Long stopageId) {
        this.stopageId = stopageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransportRouteStopageLinkDTO transportRouteStopageLinkDTO = (TransportRouteStopageLinkDTO) o;
        if (transportRouteStopageLinkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transportRouteStopageLinkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransportRouteStopageLinkDTO{" +
            "id=" + getId() +
            ", transportRouteId=" + getTransportRouteId() +
            ", stopageId=" + getStopageId() +
            "}";
    }
}
