package com.synectiks.transport.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransportRouteMapperTest {

    private TransportRouteMapper transportRouteMapper;

    @BeforeEach
    public void setUp() {
        transportRouteMapper = new TransportRouteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(transportRouteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(transportRouteMapper.fromId(null)).isNull();
    }
}
