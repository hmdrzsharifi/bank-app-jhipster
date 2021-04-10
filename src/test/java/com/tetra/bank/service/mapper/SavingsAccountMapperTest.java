package com.tetra.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SavingsAccountMapperTest {

    private SavingsAccountMapper savingsAccountMapper;

    @BeforeEach
    public void setUp() {
        savingsAccountMapper = new SavingsAccountMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(savingsAccountMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(savingsAccountMapper.fromId(null)).isNull();
    }
}
