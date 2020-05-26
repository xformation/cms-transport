package com.synectiks.transport.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class VehicleDriverLinkDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleDriverLinkDTO.class);
        VehicleDriverLinkDTO vehicleDriverLinkDTO1 = new VehicleDriverLinkDTO();
        vehicleDriverLinkDTO1.setId(1L);
        VehicleDriverLinkDTO vehicleDriverLinkDTO2 = new VehicleDriverLinkDTO();
        assertThat(vehicleDriverLinkDTO1).isNotEqualTo(vehicleDriverLinkDTO2);
        vehicleDriverLinkDTO2.setId(vehicleDriverLinkDTO1.getId());
        assertThat(vehicleDriverLinkDTO1).isEqualTo(vehicleDriverLinkDTO2);
        vehicleDriverLinkDTO2.setId(2L);
        assertThat(vehicleDriverLinkDTO1).isNotEqualTo(vehicleDriverLinkDTO2);
        vehicleDriverLinkDTO1.setId(null);
        assertThat(vehicleDriverLinkDTO1).isNotEqualTo(vehicleDriverLinkDTO2);
    }
}
