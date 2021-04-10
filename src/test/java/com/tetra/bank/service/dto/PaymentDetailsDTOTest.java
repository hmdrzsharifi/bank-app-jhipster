package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class PaymentDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentDetailsDTO.class);
        PaymentDetailsDTO paymentDetailsDTO1 = new PaymentDetailsDTO();
        paymentDetailsDTO1.setId(1L);
        PaymentDetailsDTO paymentDetailsDTO2 = new PaymentDetailsDTO();
        assertThat(paymentDetailsDTO1).isNotEqualTo(paymentDetailsDTO2);
        paymentDetailsDTO2.setId(paymentDetailsDTO1.getId());
        assertThat(paymentDetailsDTO1).isEqualTo(paymentDetailsDTO2);
        paymentDetailsDTO2.setId(2L);
        assertThat(paymentDetailsDTO1).isNotEqualTo(paymentDetailsDTO2);
        paymentDetailsDTO1.setId(null);
        assertThat(paymentDetailsDTO1).isNotEqualTo(paymentDetailsDTO2);
    }
}
