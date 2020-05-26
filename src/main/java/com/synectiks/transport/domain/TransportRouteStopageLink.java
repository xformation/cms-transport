package com.synectiks.transport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TransportRouteStopageLink.
 */
@Entity
@Table(name = "transport_route_stopage_link")
public class TransportRouteStopageLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("transportRouteStopageLinks")
    private TransportRoute transportRoute;

    @ManyToOne
    @JsonIgnoreProperties("transportRouteStopageLinks")
    private Stopage stopage;

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

    public TransportRouteStopageLink transportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
        return this;
    }

    public void setTransportRoute(TransportRoute transportRoute) {
        this.transportRoute = transportRoute;
    }

    public Stopage getStopage() {
        return stopage;
    }

    public TransportRouteStopageLink stopage(Stopage stopage) {
        this.stopage = stopage;
        return this;
    }

    public void setStopage(Stopage stopage) {
        this.stopage = stopage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportRouteStopageLink)) {
            return false;
        }
        return id != null && id.equals(((TransportRouteStopageLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TransportRouteStopageLink{" +
            "id=" + getId() +
            "}";
    }
}
