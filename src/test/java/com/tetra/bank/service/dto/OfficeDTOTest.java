package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class OfficeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfficeDTO.class);
        OfficeDTO officeDTO1 = new OfficeDTO();
        officeDTO1.setId(1L);
        OfficeDTO officeDTO2 = new OfficeDTO();
        assertThat(officeDTO1).isNotEqualTo(officeDTO2);
        officeDTO2.setId(officeDTO1.getId());
        assertThat(officeDTO1).isEqualTo(officeDTO2);
        officeDTO2.setId(2L);
        assertThat(officeDTO1).isNotEqualTo(officeDTO2);
        officeDTO1.setId(null);
        assertThat(officeDTO1).isNotEqualTo(officeDTO2);
    }
}
