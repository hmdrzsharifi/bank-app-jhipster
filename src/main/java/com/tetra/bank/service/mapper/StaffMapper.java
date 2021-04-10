package com.tetra.bank.service.mapper;


import com.tetra.bank.domain.*;
import com.tetra.bank.service.dto.StaffDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Staff} and its DTO {@link StaffDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StaffMapper extends EntityMapper<StaffDTO, Staff> {



    default Staff fromId(Long id) {
        if (id == null) {
            return null;
        }
        Staff staff = new Staff();
        staff.setId(id);
        return staff;
    }
}
