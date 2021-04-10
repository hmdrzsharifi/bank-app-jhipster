package com.tetra.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class CodeValueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodeValue.class);
        CodeValue codeValue1 = new CodeValue();
        codeValue1.setId(1L);
        CodeValue codeValue2 = new CodeValue();
        codeValue2.setId(codeValue1.getId());
        assertThat(codeValue1).isEqualTo(codeValue2);
        codeValue2.setId(2L);
        assertThat(codeValue1).isNotEqualTo(codeValue2);
        codeValue1.setId(null);
        assertThat(codeValue1).isNotEqualTo(codeValue2);
    }
}
