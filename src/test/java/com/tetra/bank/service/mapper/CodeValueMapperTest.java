package com.tetra.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CodeValueMapperTest {

    private CodeValueMapper codeValueMapper;

    @BeforeEach
    public void setUp() {
        codeValueMapper = new CodeValueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(codeValueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(codeValueMapper.fromId(null)).isNull();
    }
}
