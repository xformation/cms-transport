package com.synectiks.transport.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransportRouteStopageLinkMapperTest {

    private TransportRouteStopageLinkMapper transportRouteStopageLinkMapper;

    @BeforeEach
    public void setUp() {
        transportRouteStopageLinkMapper = new TransportRouteStopageLinkMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(transportRouteStopageLinkMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(transportRouteStopageLinkMapper.fromId(null)).isNull();
    }
}
