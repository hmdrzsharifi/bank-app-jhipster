package com.tetra.bank.service.mapper;


import com.tetra.bank.domain.*;
import com.tetra.bank.service.dto.SavingsProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SavingsProduct} and its DTO {@link SavingsProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SavingsProductMapper extends EntityMapper<SavingsProductDTO, SavingsProduct> {



    default SavingsProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        SavingsProduct savingsProduct = new SavingsProduct();
        savingsProduct.setId(id);
        return savingsProduct;
    }
}
