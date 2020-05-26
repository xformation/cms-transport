package com.synectiks.transport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class TransportRouteStopageLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportRouteStopageLink.class);
        TransportRouteStopageLink transportRouteStopageLink1 = new TransportRouteStopageLink();
        transportRouteStopageLink1.setId(1L);
        TransportRouteStopageLink transportRouteStopageLink2 = new TransportRouteStopageLink();
        transportRouteStopageLink2.setId(transportRouteStopageLink1.getId());
        assertThat(transportRouteStopageLink1).isEqualTo(transportRouteStopageLink2);
        transportRouteStopageLink2.setId(2L);
        assertThat(transportRouteStopageLink1).isNotEqualTo(transportRouteStopageLink2);
        transportRouteStopageLink1.setId(null);
        assertThat(transportRouteStopageLink1).isNotEqualTo(transportRouteStopageLink2);
    }
}
