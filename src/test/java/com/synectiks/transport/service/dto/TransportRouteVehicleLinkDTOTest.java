package com.synectiks.transport.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class TransportRouteVehicleLinkDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportRouteVehicleLinkDTO.class);
        TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO1 = new TransportRouteVehicleLinkDTO();
        transportRouteVehicleLinkDTO1.setId(1L);
        TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO2 = new TransportRouteVehicleLinkDTO();
        assertThat(transportRouteVehicleLinkDTO1).isNotEqualTo(transportRouteVehicleLinkDTO2);
        transportRouteVehicleLinkDTO2.setId(transportRouteVehicleLinkDTO1.getId());
        assertThat(transportRouteVehicleLinkDTO1).isEqualTo(transportRouteVehicleLinkDTO2);
        transportRouteVehicleLinkDTO2.setId(2L);
        assertThat(transportRouteVehicleLinkDTO1).isNotEqualTo(transportRouteVehicleLinkDTO2);
        transportRouteVehicleLinkDTO1.setId(null);
        assertThat(transportRouteVehicleLinkDTO1).isNotEqualTo(transportRouteVehicleLinkDTO2);
    }
}
