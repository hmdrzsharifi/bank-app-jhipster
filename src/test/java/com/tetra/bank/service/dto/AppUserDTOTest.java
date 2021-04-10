package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class AppUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppUserDTO.class);
        AppUserDTO appUserDTO1 = new AppUserDTO();
        appUserDTO1.setId(1L);
        AppUserDTO appUserDTO2 = new AppUserDTO();
        assertThat(appUserDTO1).isNotEqualTo(appUserDTO2);
        appUserDTO2.setId(appUserDTO1.getId());
        assertThat(appUserDTO1).isEqualTo(appUserDTO2);
        appUserDTO2.setId(2L);
        assertThat(appUserDTO1).isNotEqualTo(appUserDTO2);
        appUserDTO1.setId(null);
        assertThat(appUserDTO1).isNotEqualTo(appUserDTO2);
    }
}
