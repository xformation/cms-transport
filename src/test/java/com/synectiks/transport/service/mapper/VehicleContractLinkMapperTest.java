package com.synectiks.transport.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VehicleContractLinkMapperTest {

    private VehicleContractLinkMapper vehicleContractLinkMapper;

    @BeforeEach
    public void setUp() {
        vehicleContractLinkMapper = new VehicleContractLinkMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(vehicleContractLinkMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(vehicleContractLinkMapper.fromId(null)).isNull();
    }
}
