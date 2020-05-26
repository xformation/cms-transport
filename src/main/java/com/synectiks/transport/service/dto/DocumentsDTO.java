package com.synectiks.transport.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.transport.domain.Documents} entity.
 */
public class DocumentsDTO implements Serializable {

    private Long id;

    private String fileName;

    private String fileType;

    private String filePath;

    @Size(max = 5000)
    private String msOneDriveFilePath;

    @Size(max = 5000)
    private String jsrOakRepoFilePath;

    @Size(max = 5000)
    private String awsFilePath;


    private Long vehicleId;

    private Long contractId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMsOneDriveFilePath() {
        return msOneDriveFilePath;
    }

    public void setMsOneDriveFilePath(String msOneDriveFilePath) {
        this.msOneDriveFilePath = msOneDriveFilePath;
    }

    public String getJsrOakRepoFilePath() {
        return jsrOakRepoFilePath;
    }

    public void setJsrOakRepoFilePath(String jsrOakRepoFilePath) {
        this.jsrOakRepoFilePath = jsrOakRepoFilePath;
    }

    public String getAwsFilePath() {
        return awsFilePath;
    }

    public void setAwsFilePath(String awsFilePath) {
        this.awsFilePath = awsFilePath;
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

        DocumentsDTO documentsDTO = (DocumentsDTO) o;
        if (documentsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentsDTO{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", fileType='" + getFileType() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", msOneDriveFilePath='" + getMsOneDriveFilePath() + "'" +
            ", jsrOakRepoFilePath='" + getJsrOakRepoFilePath() + "'" +
            ", awsFilePath='" + getAwsFilePath() + "'" +
            ", vehicleId=" + getVehicleId() +
            ", contractId=" + getContractId() +
            "}";
    }
}
