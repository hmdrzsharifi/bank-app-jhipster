package com.tetra.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class OfficeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Office.class);
        Office office1 = new Office();
        office1.setId(1L);
        Office office2 = new Office();
        office2.setId(office1.getId());
        assertThat(office1).isEqualTo(office2);
        office2.setId(2L);
        assertThat(office1).isNotEqualTo(office2);
        office1.setId(null);
        assertThat(office1).isNotEqualTo(office2);
    }
}
