package com.tetra.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientNonPersonMapperTest {

    private ClientNonPersonMapper clientNonPersonMapper;

    @BeforeEach
    public void setUp() {
        clientNonPersonMapper = new ClientNonPersonMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(clientNonPersonMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(clientNonPersonMapper.fromId(null)).isNull();
    }
}
