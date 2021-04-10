package com.tetra.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StaffMapperTest {

    private StaffMapper staffMapper;

    @BeforeEach
    public void setUp() {
        staffMapper = new StaffMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(staffMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(staffMapper.fromId(null)).isNull();
    }
}
