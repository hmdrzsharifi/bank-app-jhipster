package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class ClientNonPersonDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientNonPersonDTO.class);
        ClientNonPersonDTO clientNonPersonDTO1 = new ClientNonPersonDTO();
        clientNonPersonDTO1.setId(1L);
        ClientNonPersonDTO clientNonPersonDTO2 = new ClientNonPersonDTO();
        assertThat(clientNonPersonDTO1).isNotEqualTo(clientNonPersonDTO2);
        clientNonPersonDTO2.setId(clientNonPersonDTO1.getId());
        assertThat(clientNonPersonDTO1).isEqualTo(clientNonPersonDTO2);
        clientNonPersonDTO2.setId(2L);
        assertThat(clientNonPersonDTO1).isNotEqualTo(clientNonPersonDTO2);
        clientNonPersonDTO1.setId(null);
        assertThat(clientNonPersonDTO1).isNotEqualTo(clientNonPersonDTO2);
    }
}
