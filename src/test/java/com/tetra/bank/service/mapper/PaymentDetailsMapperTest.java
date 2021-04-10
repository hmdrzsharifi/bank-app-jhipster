package com.tetra.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentDetailsMapperTest {

    private PaymentDetailsMapper paymentDetailsMapper;

    @BeforeEach
    public void setUp() {
        paymentDetailsMapper = new PaymentDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(paymentDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paymentDetailsMapper.fromId(null)).isNull();
    }
}
