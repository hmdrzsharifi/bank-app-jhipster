package com.tetra.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class PaymentDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentDetails.class);
        PaymentDetails paymentDetails1 = new PaymentDetails();
        paymentDetails1.setId(1L);
        PaymentDetails paymentDetails2 = new PaymentDetails();
        paymentDetails2.setId(paymentDetails1.getId());
        assertThat(paymentDetails1).isEqualTo(paymentDetails2);
        paymentDetails2.setId(2L);
        assertThat(paymentDetails1).isNotEqualTo(paymentDetails2);
        paymentDetails1.setId(null);
        assertThat(paymentDetails1).isNotEqualTo(paymentDetails2);
    }
}
