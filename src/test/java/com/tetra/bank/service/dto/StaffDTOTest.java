package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class StaffDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StaffDTO.class);
        StaffDTO staffDTO1 = new StaffDTO();
        staffDTO1.setId(1L);
        StaffDTO staffDTO2 = new StaffDTO();
        assertThat(staffDTO1).isNotEqualTo(staffDTO2);
        staffDTO2.setId(staffDTO1.getId());
        assertThat(staffDTO1).isEqualTo(staffDTO2);
        staffDTO2.setId(2L);
        assertThat(staffDTO1).isNotEqualTo(staffDTO2);
        staffDTO1.setId(null);
        assertThat(staffDTO1).isNotEqualTo(staffDTO2);
    }
}
