package com.tetra.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class ClientNonPersonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientNonPerson.class);
        ClientNonPerson clientNonPerson1 = new ClientNonPerson();
        clientNonPerson1.setId(1L);
        ClientNonPerson clientNonPerson2 = new ClientNonPerson();
        clientNonPerson2.setId(clientNonPerson1.getId());
        assertThat(clientNonPerson1).isEqualTo(clientNonPerson2);
        clientNonPerson2.setId(2L);
        assertThat(clientNonPerson1).isNotEqualTo(clientNonPerson2);
        clientNonPerson1.setId(null);
        assertThat(clientNonPerson1).isNotEqualTo(clientNonPerson2);
    }
}
