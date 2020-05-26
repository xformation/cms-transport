package com.synectiks.transport.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "capacity")
    private Long capacity;

    @Column(name = "owner_ship")
    private String ownerShip;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Column(name = "year_of_manufacturing")
    private String yearOfManufacturing;

    @Column(name = "manufacturing_company")
    private String manufacturingCompany;

    @Column(name = "model")
    private String model;

    @Column(name = "chasis_no")
    private String chasisNo;

    @Column(name = "rc_no")
    private String rcNo;

    @Column(name = "status")
    private String status;

    @Column(name = "on_boarding_date")
    private LocalDate onBoardingDate;

    @Column(name = "branch_id")
    private Long branchId;

    @OneToOne(mappedBy = "vehicle")
    @JsonIgnore
    private Insurance insurance;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Vehicle vehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Vehicle vehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Vehicle capacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getOwnerShip() {
        return ownerShip;
    }

    public Vehicle ownerShip(String ownerShip) {
        this.ownerShip = ownerShip;
        return this;
    }

    public void setOwnerShip(String ownerShip) {
        this.ownerShip = ownerShip;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public Vehicle dateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
        return this;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public Vehicle yearOfManufacturing(String yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
        return this;
    }

    public void setYearOfManufacturing(String yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public String getManufacturingCompany() {
        return manufacturingCompany;
    }

    public Vehicle manufacturingCompany(String manufacturingCompany) {
        this.manufacturingCompany = manufacturingCompany;
        return this;
    }

    public void setManufacturingCompany(String manufacturingCompany) {
        this.manufacturingCompany = manufacturingCompany;
    }

    public String getModel() {
        return model;
    }

    public Vehicle model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChasisNo() {
        return chasisNo;
    }

    public Vehicle chasisNo(String chasisNo) {
        this.chasisNo = chasisNo;
        return this;
    }

    public void setChasisNo(String chasisNo) {
        this.chasisNo = chasisNo;
    }

    public String getRcNo() {
        return rcNo;
    }

    public Vehicle rcNo(String rcNo) {
        this.rcNo = rcNo;
        return this;
    }

    public void setRcNo(String rcNo) {
        this.rcNo = rcNo;
    }

    public String getStatus() {
        return status;
    }

    public Vehicle status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOnBoardingDate() {
        return onBoardingDate;
    }

    public Vehicle onBoardingDate(LocalDate onBoardingDate) {
        this.onBoardingDate = onBoardingDate;
        return this;
    }

    public void setOnBoardingDate(LocalDate onBoardingDate) {
        this.onBoardingDate = onBoardingDate;
    }

    public Long getBranchId() {
        return branchId;
    }

    public Vehicle branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public Vehicle insurance(Insurance insurance) {
        this.insurance = insurance;
        return this;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        return id != null && id.equals(((Vehicle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + getId() +
            ", vehicleNumber='" + getVehicleNumber() + "'" +
            ", vehicleType='" + getVehicleType() + "'" +
            ", capacity=" + getCapacity() +
            ", ownerShip='" + getOwnerShip() + "'" +
            ", dateOfRegistration='" + getDateOfRegistration() + "'" +
            ", yearOfManufacturing='" + getYearOfManufacturing() + "'" +
            ", manufacturingCompany='" + getManufacturingCompany() + "'" +
            ", model='" + getModel() + "'" +
            ", chasisNo='" + getChasisNo() + "'" +
            ", rcNo='" + getRcNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", onBoardingDate='" + getOnBoardingDate() + "'" +
            ", branchId=" + getBranchId() +
            "}";
    }
}
