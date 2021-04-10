package com.tetra.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SavingsAccountTransactionMapperTest {

    private SavingsAccountTransactionMapper savingsAccountTransactionMapper;

    @BeforeEach
    public void setUp() {
        savingsAccountTransactionMapper = new SavingsAccountTransactionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(savingsAccountTransactionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(savingsAccountTransactionMapper.fromId(null)).isNull();
    }
}
