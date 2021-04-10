package com.tetra.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SavingsProductMapperTest {

    private SavingsProductMapper savingsProductMapper;

    @BeforeEach
    public void setUp() {
        savingsProductMapper = new SavingsProductMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(savingsProductMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(savingsProductMapper.fromId(null)).isNull();
    }
}
