package com.synectiks.transport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.synectiks.transport.web.rest.TestUtil;

public class StopageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Stopage.class);
        Stopage stopage1 = new Stopage();
        stopage1.setId(1L);
        Stopage stopage2 = new Stopage();
        stopage2.setId(stopage1.getId());
        assertThat(stopage1).isEqualTo(stopage2);
        stopage2.setId(2L);
        assertThat(stopage1).isNotEqualTo(stopage2);
        stopage1.setId(null);
        assertThat(stopage1).isNotEqualTo(stopage2);
    }
}
