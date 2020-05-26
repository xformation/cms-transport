package com.synectiks.transport.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VehicleDriverLinkMapperTest {

    private VehicleDriverLinkMapper vehicleDriverLinkMapper;

    @BeforeEach
    public void setUp() {
        vehicleDriverLinkMapper = new VehicleDriverLinkMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(vehicleDriverLinkMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(vehicleDriverLinkMapper.fromId(null)).isNull();
    }
}
