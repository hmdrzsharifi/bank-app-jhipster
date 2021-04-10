package com.tetra.bank.service.mapper;


import com.tetra.bank.domain.*;
import com.tetra.bank.service.dto.ClientNonPersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClientNonPerson} and its DTO {@link ClientNonPersonDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClientNonPersonMapper extends EntityMapper<ClientNonPersonDTO, ClientNonPerson> {



    default ClientNonPerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClientNonPerson clientNonPerson = new ClientNonPerson();
        clientNonPerson.setId(id);
        return clientNonPerson;
    }
}
