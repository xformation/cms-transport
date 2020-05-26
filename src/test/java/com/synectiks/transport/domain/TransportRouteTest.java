package com.synectiks.transport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class TransportRouteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportRoute.class);
        TransportRoute transportRoute1 = new TransportRoute();
        transportRoute1.setId(1L);
        TransportRoute transportRoute2 = new TransportRoute();
        transportRoute2.setId(transportRoute1.getId());
        assertThat(transportRoute1).isEqualTo(transportRoute2);
        transportRoute2.setId(2L);
        assertThat(transportRoute1).isNotEqualTo(transportRoute2);
        transportRoute1.setId(null);
        assertThat(transportRoute1).isNotEqualTo(transportRoute2);
    }
}
