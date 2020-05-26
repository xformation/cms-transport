package com.synectiks.transport.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class TransportRouteStopageLinkDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportRouteStopageLinkDTO.class);
        TransportRouteStopageLinkDTO transportRouteStopageLinkDTO1 = new TransportRouteStopageLinkDTO();
        transportRouteStopageLinkDTO1.setId(1L);
        TransportRouteStopageLinkDTO transportRouteStopageLinkDTO2 = new TransportRouteStopageLinkDTO();
        assertThat(transportRouteStopageLinkDTO1).isNotEqualTo(transportRouteStopageLinkDTO2);
        transportRouteStopageLinkDTO2.setId(transportRouteStopageLinkDTO1.getId());
        assertThat(transportRouteStopageLinkDTO1).isEqualTo(transportRouteStopageLinkDTO2);
        transportRouteStopageLinkDTO2.setId(2L);
        assertThat(transportRouteStopageLinkDTO1).isNotEqualTo(transportRouteStopageLinkDTO2);
        transportRouteStopageLinkDTO1.setId(null);
        assertThat(transportRouteStopageLinkDTO1).isNotEqualTo(transportRouteStopageLinkDTO2);
    }
}
