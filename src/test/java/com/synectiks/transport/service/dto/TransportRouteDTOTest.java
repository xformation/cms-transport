package com.synectiks.transport.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class TransportRouteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransportRouteDTO.class);
        TransportRouteDTO transportRouteDTO1 = new TransportRouteDTO();
        transportRouteDTO1.setId(1L);
        TransportRouteDTO transportRouteDTO2 = new TransportRouteDTO();
        assertThat(transportRouteDTO1).isNotEqualTo(transportRouteDTO2);
        transportRouteDTO2.setId(transportRouteDTO1.getId());
        assertThat(transportRouteDTO1).isEqualTo(transportRouteDTO2);
        transportRouteDTO2.setId(2L);
        assertThat(transportRouteDTO1).isNotEqualTo(transportRouteDTO2);
        transportRouteDTO1.setId(null);
        assertThat(transportRouteDTO1).isNotEqualTo(transportRouteDTO2);
    }
}
