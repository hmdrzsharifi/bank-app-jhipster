package com.tetra.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class SavingsProductTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SavingsProduct.class);
        SavingsProduct savingsProduct1 = new SavingsProduct();
        savingsProduct1.setId(1L);
        SavingsProduct savingsProduct2 = new SavingsProduct();
        savingsProduct2.setId(savingsProduct1.getId());
        assertThat(savingsProduct1).isEqualTo(savingsProduct2);
        savingsProduct2.setId(2L);
        assertThat(savingsProduct1).isNotEqualTo(savingsProduct2);
        savingsProduct1.setId(null);
        assertThat(savingsProduct1).isNotEqualTo(savingsProduct2);
    }
}
