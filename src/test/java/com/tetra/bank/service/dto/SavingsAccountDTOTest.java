package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class SavingsAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SavingsAccountDTO.class);
        SavingsAccountDTO savingsAccountDTO1 = new SavingsAccountDTO();
        savingsAccountDTO1.setId(1L);
        SavingsAccountDTO savingsAccountDTO2 = new SavingsAccountDTO();
        assertThat(savingsAccountDTO1).isNotEqualTo(savingsAccountDTO2);
        savingsAccountDTO2.setId(savingsAccountDTO1.getId());
        assertThat(savingsAccountDTO1).isEqualTo(savingsAccountDTO2);
        savingsAccountDTO2.setId(2L);
        assertThat(savingsAccountDTO1).isNotEqualTo(savingsAccountDTO2);
        savingsAccountDTO1.setId(null);
        assertThat(savingsAccountDTO1).isNotEqualTo(savingsAccountDTO2);
    }
}
