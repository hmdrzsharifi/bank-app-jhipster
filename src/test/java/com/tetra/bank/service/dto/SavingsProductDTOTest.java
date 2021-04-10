package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class SavingsProductDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SavingsProductDTO.class);
        SavingsProductDTO savingsProductDTO1 = new SavingsProductDTO();
        savingsProductDTO1.setId(1L);
        SavingsProductDTO savingsProductDTO2 = new SavingsProductDTO();
        assertThat(savingsProductDTO1).isNotEqualTo(savingsProductDTO2);
        savingsProductDTO2.setId(savingsProductDTO1.getId());
        assertThat(savingsProductDTO1).isEqualTo(savingsProductDTO2);
        savingsProductDTO2.setId(2L);
        assertThat(savingsProductDTO1).isNotEqualTo(savingsProductDTO2);
        savingsProductDTO1.setId(null);
        assertThat(savingsProductDTO1).isNotEqualTo(savingsProductDTO2);
    }
}
