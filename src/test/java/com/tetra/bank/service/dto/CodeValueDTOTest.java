package com.tetra.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tetra.bank.web.rest.TestUtil;

public class CodeValueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodeValueDTO.class);
        CodeValueDTO codeValueDTO1 = new CodeValueDTO();
        codeValueDTO1.setId(1L);
        CodeValueDTO codeValueDTO2 = new CodeValueDTO();
        assertThat(codeValueDTO1).isNotEqualTo(codeValueDTO2);
        codeValueDTO2.setId(codeValueDTO1.getId());
        assertThat(codeValueDTO1).isEqualTo(codeValueDTO2);
        codeValueDTO2.setId(2L);
        assertThat(codeValueDTO1).isNotEqualTo(codeValueDTO2);
        codeValueDTO1.setId(null);
        assertThat(codeValueDTO1).isNotEqualTo(codeValueDTO2);
    }
}
