package com.synectiks.transport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TransportRouteVehicleLink.
 */
@Entity
@Table(name = "transport_route_vehicle_link")
public class TransportRouteVehicleLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("transportRouteVehicleLinks")
    private TransportRoute transportRoute;

    @ManyToOne
    @JsonIgnoreProperties("transportRouteVehicleLinks")
    private Vehicle vehicle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransportRoute getTransportRoute() {
        return transportRoute;
    }

    public TransportRouteVehicleLink transportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
        return this;
    }

    public void setTransportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public TransportRouteVehicleLink vehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportRouteVehicleLink)) {
            return false;
        }
        return id != null && id.equals(((TransportRouteVehicleLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TransportRouteVehicleLink{" +
            "id=" + getId() +
            "}";
    }
}
