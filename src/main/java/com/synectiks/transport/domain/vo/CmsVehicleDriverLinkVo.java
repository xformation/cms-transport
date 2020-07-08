package com.synectiks.transport.domain.vo;

import com.synectiks.transport.domain.Employee;
import com.synectiks.transport.domain.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CmsVehicleDriverLinkVo extends CmsCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Vehicle vehicle;
    private Employee employee;
    private Long vehicleId;
    private Long employeeId;
    private List<CmsVehicleDriverLinkVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<CmsVehicleDriverLinkVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsVehicleDriverLinkVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsVehicleDriverLinkVo{" +
            "id=" + id +
            ", vehicle=" + vehicle +
            ", vehicleId=" + vehicleId +
            ", employeeId=" + employeeId +
            ", employee=" + employee +
            ", dataList=" + dataList +
            "} " + super.toString();
    }
}
