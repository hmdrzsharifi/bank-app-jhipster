package com.tetra.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class SavingsAccountTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SavingsAccountTransaction.class);
        SavingsAccountTransaction savingsAccountTransaction1 = new SavingsAccountTransaction();
        savingsAccountTransaction1.setId(1L);
        SavingsAccountTransaction savingsAccountTransaction2 = new SavingsAccountTransaction();
        savingsAccountTransaction2.setId(savingsAccountTransaction1.getId());
        assertThat(savingsAccountTransaction1).isEqualTo(savingsAccountTransaction2);
        savingsAccountTransaction2.setId(2L);
        assertThat(savingsAccountTransaction1).isNotEqualTo(savingsAccountTransaction2);
        savingsAccountTransaction1.setId(null);
        assertThat(savingsAccountTransaction1).isNotEqualTo(savingsAccountTransaction2);
    }
}
