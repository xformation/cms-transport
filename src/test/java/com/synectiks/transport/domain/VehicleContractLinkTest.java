package com.synectiks.transport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class VehicleContractLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleContractLink.class);
        VehicleContractLink vehicleContractLink1 = new VehicleContractLink();
        vehicleContractLink1.setId(1L);
        VehicleContractLink vehicleContractLink2 = new VehicleContractLink();
        vehicleContractLink2.setId(vehicleContractLink1.getId());
        assertThat(vehicleContractLink1).isEqualTo(vehicleContractLink2);
        vehicleContractLink2.setId(2L);
        assertThat(vehicleContractLink1).isNotEqualTo(vehicleContractLink2);
        vehicleContractLink1.setId(null);
        assertThat(vehicleContractLink1).isNotEqualTo(vehicleContractLink2);
    }
}
