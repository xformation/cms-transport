package com.synectiks.transport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class TransportRouteVehicleLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportRouteVehicleLink.class);
        TransportRouteVehicleLink transportRouteVehicleLink1 = new TransportRouteVehicleLink();
        transportRouteVehicleLink1.setId(1L);
        TransportRouteVehicleLink transportRouteVehicleLink2 = new TransportRouteVehicleLink();
        transportRouteVehicleLink2.setId(transportRouteVehicleLink1.getId());
        assertThat(transportRouteVehicleLink1).isEqualTo(transportRouteVehicleLink2);
        transportRouteVehicleLink2.setId(2L);
        assertThat(transportRouteVehicleLink1).isNotEqualTo(transportRouteVehicleLink2);
        transportRouteVehicleLink1.setId(null);
        assertThat(transportRouteVehicleLink1).isNotEqualTo(transportRouteVehicleLink2);
    }
}
