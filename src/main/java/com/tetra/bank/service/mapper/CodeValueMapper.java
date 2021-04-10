package com.tetra.bank.service.mapper;


import com.tetra.bank.domain.*;
import com.tetra.bank.service.dto.CodeValueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CodeValue} and its DTO {@link CodeValueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CodeValueMapper extends EntityMapper<CodeValueDTO, CodeValue> {



    default CodeValue fromId(Long id) {
        if (id == null) {
            return null;
        }
        CodeValue codeValue = new CodeValue();
        codeValue.setId(id);
        return codeValue;
    }
}
