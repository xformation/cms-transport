package com.synectiks.transport.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransportRouteVehicleLinkMapperTest {

    private TransportRouteVehicleLinkMapper transportRouteVehicleLinkMapper;

    @BeforeEach
    public void setUp() {
        transportRouteVehicleLinkMapper = new TransportRouteVehicleLinkMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(transportRouteVehicleLinkMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(transportRouteVehicleLinkMapper.fromId(null)).isNull();
    }
}
