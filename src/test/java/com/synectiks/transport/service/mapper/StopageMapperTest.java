package com.synectiks.transport.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StopageMapperTest {

    private StopageMapper stopageMapper;

    @BeforeEach
    public void setUp() {
        stopageMapper = new StopageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(stopageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(stopageMapper.fromId(null)).isNull();
    }
}
