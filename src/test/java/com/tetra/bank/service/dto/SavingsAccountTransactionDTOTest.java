package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class SavingsAccountTransactionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SavingsAccountTransactionDTO.class);
        SavingsAccountTransactionDTO savingsAccountTransactionDTO1 = new SavingsAccountTransactionDTO();
        savingsAccountTransactionDTO1.setId(1L);
        SavingsAccountTransactionDTO savingsAccountTransactionDTO2 = new SavingsAccountTransactionDTO();
        assertThat(savingsAccountTransactionDTO1).isNotEqualTo(savingsAccountTransactionDTO2);
        savingsAccountTransactionDTO2.setId(savingsAccountTransactionDTO1.getId());
        assertThat(savingsAccountTransactionDTO1).isEqualTo(savingsAccountTransactionDTO2);
        savingsAccountTransactionDTO2.setId(2L);
        assertThat(savingsAccountTransactionDTO1).isNotEqualTo(savingsAccountTransactionDTO2);
        savingsAccountTransactionDTO1.setId(null);
        assertThat(savingsAccountTransactionDTO1).isNotEqualTo(savingsAccountTransactionDTO2);
    }
}
