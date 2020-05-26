package com.synectiks.transport.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class StopageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StopageDTO.class);
        StopageDTO stopageDTO1 = new StopageDTO();
        stopageDTO1.setId(1L);
        StopageDTO stopageDTO2 = new StopageDTO();
        assertThat(stopageDTO1).isNotEqualTo(stopageDTO2);
        stopageDTO2.setId(stopageDTO1.getId());
        assertThat(stopageDTO1).isEqualTo(stopageDTO2);
        stopageDTO2.setId(2L);
        assertThat(stopageDTO1).isNotEqualTo(stopageDTO2);
        stopageDTO1.setId(null);
        assertThat(stopageDTO1).isNotEqualTo(stopageDTO2);
    }
}
