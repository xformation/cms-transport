package com.synectiks.transport.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class VehicleContractLinkDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleContractLinkDTO.class);
        VehicleContractLinkDTO vehicleContractLinkDTO1 = new VehicleContractLinkDTO();
        vehicleContractLinkDTO1.setId(1L);
        VehicleContractLinkDTO vehicleContractLinkDTO2 = new VehicleContractLinkDTO();
        assertThat(vehicleContractLinkDTO1).isNotEqualTo(vehicleContractLinkDTO2);
        vehicleContractLinkDTO2.setId(vehicleContractLinkDTO1.getId());
        assertThat(vehicleContractLinkDTO1).isEqualTo(vehicleContractLinkDTO2);
        vehicleContractLinkDTO2.setId(2L);
        assertThat(vehicleContractLinkDTO1).isNotEqualTo(vehicleContractLinkDTO2);
        vehicleContractLinkDTO1.setId(null);
        assertThat(vehicleContractLinkDTO1).isNotEqualTo(vehicleContractLinkDTO2);
    }
}
