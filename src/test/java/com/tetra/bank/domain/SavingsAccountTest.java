package com.tetra.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class SavingsAccountTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SavingsAccount.class);
        SavingsAccount savingsAccount1 = new SavingsAccount();
        savingsAccount1.setId(1L);
        SavingsAccount savingsAccount2 = new SavingsAccount();
        savingsAccount2.setId(savingsAccount1.getId());
        assertThat(savingsAccount1).isEqualTo(savingsAccount2);
        savingsAccount2.setId(2L);
        assertThat(savingsAccount1).isNotEqualTo(savingsAccount2);
        savingsAccount1.setId(null);
        assertThat(savingsAccount1).isNotEqualTo(savingsAccount2);
    }
}
