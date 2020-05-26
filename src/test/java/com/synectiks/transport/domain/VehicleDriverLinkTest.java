package com.synectiks.transport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class VehicleDriverLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleDriverLink.class);
        VehicleDriverLink vehicleDriverLink1 = new VehicleDriverLink();
        vehicleDriverLink1.setId(1L);
        VehicleDriverLink vehicleDriverLink2 = new VehicleDriverLink();
        vehicleDriverLink2.setId(vehicleDriverLink1.getId());
        assertThat(vehicleDriverLink1).isEqualTo(vehicleDriverLink2);
        vehicleDriverLink2.setId(2L);
        assertThat(vehicleDriverLink1).isNotEqualTo(vehicleDriverLink2);
        vehicleDriverLink1.setId(null);
        assertThat(vehicleDriverLink1).isNotEqualTo(vehicleDriverLink2);
    }
}
