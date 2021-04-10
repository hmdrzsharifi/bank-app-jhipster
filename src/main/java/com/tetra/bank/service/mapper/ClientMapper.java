package com.tetra.bank.service.mapper;


import com.tetra.bank.domain.*;
import com.tetra.bank.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {ImageMapper.class, StaffMapper.class, OfficeMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "image.id", target = "imageId")
    @Mapping(source = "staff.id", target = "staffId")
    @Mapping(source = "office.id", target = "officeId")
    ClientDTO toDto(Client client);

    @Mapping(source = "imageId", target = "image")
    @Mapping(source = "staffId", target = "staff")
    @Mapping(source = "officeId", target = "office")
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
